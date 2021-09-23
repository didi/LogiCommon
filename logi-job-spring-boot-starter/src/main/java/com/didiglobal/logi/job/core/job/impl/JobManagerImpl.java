package com.didiglobal.logi.job.core.job.impl;

import com.didiglobal.logi.job.LogIJobProperties;
import com.didiglobal.logi.job.common.TaskResult;
import com.didiglobal.logi.job.common.domain.LogIJob;
import com.didiglobal.logi.job.common.domain.LogITask;
import com.didiglobal.logi.job.common.enums.JobStatusEnum;
import com.didiglobal.logi.job.common.enums.TaskWorkerStatusEnum;
import com.didiglobal.logi.job.common.po.LogIJobLogPO;
import com.didiglobal.logi.job.common.po.LogIJobPO;
import com.didiglobal.logi.job.common.po.LogITaskLockPO;
import com.didiglobal.logi.job.common.po.LogITaskPO;
import com.didiglobal.logi.job.core.WorkerSingleton;
import com.didiglobal.logi.job.core.job.JobExecutor;
import com.didiglobal.logi.job.core.job.JobFactory;
import com.didiglobal.logi.job.core.job.JobManager;
import com.didiglobal.logi.job.core.task.TaskLockService;
import com.didiglobal.logi.job.mapper.LogIJobLogMapper;
import com.didiglobal.logi.job.mapper.LogIJobMapper;
import com.didiglobal.logi.job.mapper.LogITaskLockMapper;
import com.didiglobal.logi.job.mapper.LogITaskMapper;
import com.didiglobal.logi.job.utils.BeanUtil;
import com.didiglobal.logi.job.utils.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.didiglobal.logi.job.common.TaskResult.FAIL_CODE;
import static com.didiglobal.logi.job.common.TaskResult.RUNNING_CODE;

/**
 * job manager impl.
 *
 * @author ds
 */
@Service
public class JobManagerImpl implements JobManager {
    private static final Logger logger = LoggerFactory.getLogger(JobManagerImpl.class);
    // 停止任务尝试次数
    private static final int TRY_MAX_TIMES = 3;
    // 停止任务每次尝试后sleep 时间 秒
    private static final int STOP_SLEEP_SECONDS = 3;

    // 续约锁提前检查时间 秒
    private static final Long CHECK_BEFORE_INTERVAL = 60L;
    // 每次给锁续约的时间 秒
    private static final Long RENEW_INTERVAL = 60L;

    private static final Long ONE_HOUR = 3600L;

    private JobFactory jobFactory;
    private LogIJobMapper logIJobMapper;
    private LogIJobLogMapper logIJobLogMapper;
    private LogITaskMapper logITaskMapper;
    private JobExecutor jobExecutor;
    private TaskLockService taskLockService;
    private LogITaskLockMapper logITaskLockMapper;
    private LogIJobProperties logIJobProperties;

    private ConcurrentHashMap<LogIJob, Future> jobFutureMap = new ConcurrentHashMap<>();

    /**
     * constructor.
     */
    @Autowired
    public JobManagerImpl(JobFactory jobFactory, LogIJobMapper logIJobMapper,
                          LogIJobLogMapper logIJobLogMapper, LogITaskMapper logITaskMapper,
                          JobExecutor jobExecutor, TaskLockService taskLockService,
                          LogITaskLockMapper logITaskLockMapper, LogIJobProperties logIJobProperties) {
        this.jobFactory = jobFactory;
        this.logIJobMapper = logIJobMapper;
        this.logIJobLogMapper = logIJobLogMapper;
        this.logITaskMapper = logITaskMapper;
        this.jobExecutor = jobExecutor;
        this.taskLockService = taskLockService;
        this.logITaskLockMapper = logITaskLockMapper;
        this.logIJobProperties = logIJobProperties;
        initialize();
    }

    private void initialize() {
        new Thread(new JobFutureHandler(), "JobFutureHandler Thread").start();
        new Thread(new LockRenewHandler(), "LockRenewHandler Thread").start();
        new Thread(new LogCleanHandler(this.logIJobProperties.getLogExpire()),
                "LogCleanHandler Thread").start();
    }

    @Override
    public Future<Object> start(LogITask logITask) {
        // 添加job信息
        LogIJob logIJob = jobFactory.newJob(logITask);
        LogIJobPO job = logIJob.getAuvJob();
        logIJobMapper.insert(job);

        Future jobFuture = jobExecutor.submit(new JobHandler(logIJob));
        jobFutureMap.put(logIJob, jobFuture);

        // 增加auvJobLog
        LogIJobLogPO logIJobLogPO = logIJob.getAuvJobLog();
        logIJobLogMapper.insert(logIJobLogPO);
        return jobFuture;
    }

    @Override
    public Integer runningJobSize() {
        return jobFutureMap.size();
    }

    @Override
    public boolean stopByTaskCode(String taskCode) {
        for (Map.Entry<LogIJob, Future> jobFuture : jobFutureMap.entrySet()) {
            LogIJob logIJob = jobFuture.getKey();
            if (Objects.equals(taskCode, logIJob.getTaskCode())) {
                return stopJob(logIJob, jobFuture.getValue());
            }
        }
        return true;
    }

    @Override
    public boolean stopByJobCode(String jobCode) {
        for (Map.Entry<LogIJob, Future> jobFuture : jobFutureMap.entrySet()) {
            LogIJob logIJob = jobFuture.getKey();
            if (Objects.equals(jobCode, logIJob.getJobCode())) {
                return stopJob(logIJob, jobFuture.getValue());
            }
        }
        return true;
    }

    @Override
    public int stopAll() {
        AtomicInteger succeedNum = new AtomicInteger();

        for (Map.Entry<LogIJob, Future> jobFuture : jobFutureMap.entrySet()) {
            LogIJob logIJob = jobFuture.getKey();
            if (stopJob(logIJob, jobFuture.getValue())) {
                succeedNum.addAndGet(1);
            }
        }

        return succeedNum.get();
    }

    @Override
    public List<LogIJob> getJobs() {
        List<LogIJobPO> logIJobPOS = logIJobMapper.selectByAppName(logIJobProperties.getAppName());
        if (CollectionUtils.isEmpty(logIJobPOS)) {
            return null;
        }
        List<LogIJob> logIJobDTOS = logIJobPOS.stream().map(logIJobPO -> BeanUtil.convertTo(logIJobPO, LogIJob.class))
                .collect(Collectors.toList());
        return logIJobDTOS;
    }

    /**
     * job 执行线程.
     */
    class JobHandler implements Callable {

        private LogIJob logIJob;

        public JobHandler(LogIJob logIJob) {
            this.logIJob = logIJob;
        }

        @Override
        public Object call() {
            TaskResult object = null;

            logger.info("class=JobHandler||method=call||url=||msg=start job {} with classname {}",
                    logIJob.getJobCode(), logIJob.getClassName());

            try {
                logIJob.setStartTime(new Timestamp(System.currentTimeMillis()));
                logIJob.setStatus(JobStatusEnum.SUCCEED.getValue());
                logIJob.setResult(new TaskResult(RUNNING_CODE, "task job is running!"));
                logIJob.setError("");

                //开始执行，记录日志
                LogIJobLogPO logIJobLogPO = logIJob.getAuvJobLog();
                logIJobLogMapper.updateByCode(logIJobLogPO);

                object = logIJob.getJob().execute(null);

                logIJob.setResult(object);
                logIJob.setEndTime(new Timestamp(System.currentTimeMillis()));
            } catch (InterruptedException e) {
                // 记录任务被打断 进程关闭/线程关闭
                logIJob.setStatus(JobStatusEnum.CANCELED.getValue());
                logIJob.setResult(new TaskResult(FAIL_CODE, "task job be canceld!"));
                logIJob.setError(printStackTraceAsString(e));
                logger.error("class=JobHandler||method=call||url=||msg={}", e);
            } catch (Exception e) {
                // 记录任务异常信息
                logIJob.setStatus(JobStatusEnum.FAILED.getValue());
                logIJob.setResult(new TaskResult(FAIL_CODE, "task job has exception when running!" + e));
                String error = printStackTraceAsString(e);
                logIJob.setError(printStackTraceAsString(e));
                logger.error("class=JobHandler||method=call||url=||msg={}", error);
            } finally {

                //执行完成，记录日志
                LogIJobLogPO logIJobLogPO = logIJob.getAuvJobLog();
                logIJobLogMapper.updateByCode(logIJobLogPO);

                // job callback, 释放任务锁
                if (logIJob.getTaskCallback() != null) {
                    logIJob.getTaskCallback().callback(logIJob.getTaskCode());
                }
            }

            return object;
        }
    }

    /**
     * Job 执行清理线程，对超时的要主动杀死，执行完的收集信息并记录日志.
     */
    class JobFutureHandler implements Runnable {
        private static final long JOB_FUTURE_CLEAN_INTERVAL = 10L;

        public JobFutureHandler() {
        }

        @Override
        public void run() {
            while (true) {
                try {
                    logger.info("class=JobFutureHandler||method=run||url=||msg=check running jobs at regular "
                            + "time {}", JOB_FUTURE_CLEAN_INTERVAL);

                    // 定时轮询任务，检查状态并处理
                    jobFutureMap.forEach(((jobInfo, future) -> {
                        // job完成，信息整理
                        if (future.isDone()) {
                            reorganizeFinishedJob(jobInfo);
                        }

                        // 超时处理
                        Long timeout = jobInfo.getTimeout();
                        if (timeout <= 0) {
                            return;
                        }
                        Long startTime = jobInfo.getStartTime().getTime();
                        Long now = System.currentTimeMillis();
                        Long between = (now - startTime) / 1000;

                        if (between > timeout && !future.isDone()) {
                            future.cancel(true);
                        }
                    }));

                    // 间隔一段时间执行一次
                    ThreadUtil.sleep(JOB_FUTURE_CLEAN_INTERVAL, TimeUnit.SECONDS);
                } catch (Exception e) {
                    logger.error("class=JobFutureHandler||method=run||url=||msg=", e);
                }
            }
        }
    }

    /**
     * 整理已完成的任务.
     *
     * @param logIJob logIJob
     */
    @Transactional(rollbackFor = Exception.class)
    public void reorganizeFinishedJob(LogIJob logIJob) {
        // 移除记录
        jobFutureMap.remove(logIJob);

        if (JobStatusEnum.CANCELED.getValue().equals(logIJob.getStatus())) {
            logIJob.setResult(new TaskResult(FAIL_CODE, "task job be canceld!"));
            logIJob.setError("task job be canceld!");
            LogIJobLogPO logIJobLogPO = logIJob.getAuvJobLog();
            logIJobLogMapper.updateByCode(logIJobLogPO);
        }

        // 删除auvJob
        logIJobMapper.deleteByCode(logIJob.getJobCode());

        // 更新任务状态
        LogITaskPO logITaskPO = logITaskMapper.selectByCode(logIJob.getTaskCode(), logIJobProperties.getAppName());
        List<LogITask.TaskWorker> taskWorkers = BeanUtil.convertToList(logITaskPO.getTaskWorkerStr(),
                LogITask.TaskWorker.class);

        long currentTime = System.currentTimeMillis();

        if (!CollectionUtils.isEmpty(taskWorkers)) {
            taskWorkers.sort((o1, o2) -> o1.getLastFireTime().after(o2.getLastFireTime()) ? 1 : -1);

            Iterator<LogITask.TaskWorker> iter = taskWorkers.iterator();
            while (iter.hasNext()) {
                LogITask.TaskWorker taskWorker = iter.next();
                if (TaskWorkerStatusEnum.WAITING.getValue().equals(taskWorker.getStatus())
                        && taskWorker.getLastFireTime().getTime() + 12 * ONE_HOUR * 1000 < currentTime) {
                    iter.remove();
                }

                if (Objects.equals(taskWorker.getWorkerCode(), WorkerSingleton.getInstance()
                        .getLogIWorker().getWorkerCode())) {
                    taskWorker.setStatus(TaskWorkerStatusEnum.WAITING.getValue());
                }
            }
        }
        logITaskPO.setTaskWorkerStr(BeanUtil.convertToJson(taskWorkers));
        logITaskMapper.updateByCode(logITaskPO);
    }

    /**
     * 锁续约线程.
     */
    class LockRenewHandler implements Runnable {
        private static final long JOB_INTERVAL = 10L;

        public LockRenewHandler() {
        }

        @Override
        public void run() {
            while (true) {
                try {
                    logger.info("class=LockRenewHandler||method=run||url=||msg=check need renew lock at "
                            + "regular time {}", JOB_INTERVAL);

                    // 锁续约
                    List<LogITaskLockPO> logITaskLockPOS = logITaskLockMapper.selectByWorkerCode(WorkerSingleton
                            .getInstance().getLogIWorker().getWorkerCode(), logIJobProperties.getAppName());

                    if (!CollectionUtils.isEmpty(logITaskLockPOS)) {
                        for (LogITaskLockPO logITaskLockPO : logITaskLockPOS) {
                            boolean matched = jobFutureMap.keySet().stream().anyMatch(jobInfo ->
                                    Objects.equals(logITaskLockPO.getTaskCode(), jobInfo.getTaskCode()));
                            if (matched) {
                                long current = System.currentTimeMillis() / 1000;
                                long exTime = (logITaskLockPO.getCreateTime().getTime() / 1000)
                                        + logITaskLockPO.getExpireTime();

                                if (current < exTime) {
                                    // 续约
                                    if (current > exTime - CHECK_BEFORE_INTERVAL) {
                                        logger.info("class=TaskLockServiceImpl||method=run||url=||msg=update lock "
                                                        + "expireTime id={}, expireTime={}", logITaskLockPO.getId(),
                                                logITaskLockPO.getExpireTime());
                                        logITaskLockMapper.update(
                                                logITaskLockPO.getId(),
                                                logITaskLockPO.getExpireTime() + RENEW_INTERVAL);
                                    }
                                    continue;
                                }
                            }

                            // 否则，删除无效的锁、过期的锁
                            logger.info("class=TaskLockServiceImpl||method=run||url=||msg=lock clean "
                                    + "lockInfo={}", BeanUtil.convertToJson(logITaskLockPO));
                            logITaskLockMapper.deleteById(logITaskLockPO.getId());

                            // 更新当前worker任务状态
                            LogITaskPO logITaskPO = logITaskMapper
                                    .selectByCode(logITaskLockPO.getTaskCode(), logIJobProperties.getAppName());
                            if (logITaskPO != null) {
                                List<LogITask.TaskWorker> taskWorkers = BeanUtil.convertToList(
                                        logITaskPO.getTaskWorkerStr(), LogITask.TaskWorker.class);

                                if (!CollectionUtils.isEmpty(taskWorkers)) {
                                    for (LogITask.TaskWorker taskWorker : taskWorkers) {
                                        if (Objects.equals(taskWorker.getWorkerCode(), WorkerSingleton.getInstance()
                                                .getLogIWorker().getWorkerCode())) {
                                            taskWorker.setStatus(TaskWorkerStatusEnum.WAITING.getValue());
                                        }
                                    }
                                }
                                logITaskPO.setTaskWorkerStr(BeanUtil.convertToJson(taskWorkers));

                                logger.info("class=TaskLockServiceImpl||method=run||url=||msg=update task workers "
                                        + "status taskInfo={}", BeanUtil.convertToJson(logITaskPO));

                                logITaskMapper.updateByCode(logITaskPO);
                            }
                        }
                    }

                } catch (Exception e) {
                    logger.error("class=LockRenewHandler||method=run||url=||msg=", e);
                }

                ThreadUtil.sleep(JOB_INTERVAL, TimeUnit.SECONDS);
            }
        }
    }

    /**
     * 定时清理日志.
     */
    class LogCleanHandler implements Runnable {
        // 每小时执行一次
        private static final long JOB_INTERVAL = 3600L;
        // 日志保存时间[默认保存7天]
        private Integer logExpire = 7;

        public LogCleanHandler(Integer logExpire) {
            if (logExpire != null) {
                this.logExpire = logExpire;
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    logger.info("class=LogCleanHandler||method=run||url=||msg=clean auv_job_log regular"
                            + " time {}", JOB_INTERVAL);

                    // 删除日志
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DATE, -1 * logExpire);
                    int count = logIJobLogMapper.deleteByCreateTime(
                            new Timestamp(calendar.getTimeInMillis()), logIJobProperties.getAppName()
                    );

                    logger.info("class=LogCleanHandler||method=run||url=||msg=clean log count={}", count);
                    // 间隔一段时间执行一次
                    ThreadUtil.sleep(JOB_INTERVAL, TimeUnit.SECONDS);
                } catch (Exception e) {
                    logger.error("class=LogCleanHandler||method=run||url=||msg=", e);
                }
            }
        }
    }

    private boolean stopJob(LogIJob logIJob, Future future) {
        int tryTime = 0;
        while (tryTime < TRY_MAX_TIMES) {
            if (future.isDone()) {
                logIJob.setStatus(JobStatusEnum.CANCELED.getValue());
                if (logIJob.getTaskCallback() != null) {
                    logIJob.getTaskCallback().callback(logIJob.getTaskCode());
                }
                reorganizeFinishedJob(logIJob);
                return true;
            }
            future.cancel(true);
            tryTime++;
            ThreadUtil.sleep(STOP_SLEEP_SECONDS, TimeUnit.SECONDS);
        }

        return false;
    }

    private String printStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String error = stringWriter.toString();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return timestamp.toString() + "  " + error;
    }
}
