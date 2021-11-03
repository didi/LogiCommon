package com.didiglobal.logi.job.core.task;

import com.didiglobal.logi.job.LogIJobProperties;
import com.didiglobal.logi.job.common.Result;
import com.didiglobal.logi.job.common.domain.LogITask;
import com.didiglobal.logi.job.common.dto.TaskPageQueryDTO;
import com.didiglobal.logi.job.common.enums.TaskStatusEnum;
import com.didiglobal.logi.job.common.po.LogITaskPO;
import com.didiglobal.logi.job.common.dto.LogITaskDTO;
import com.didiglobal.logi.job.common.enums.TaskWorkerStatusEnum;
import com.didiglobal.logi.job.core.WorkerSingleton;
import com.didiglobal.logi.job.core.consensual.Consensual;
import com.didiglobal.logi.job.core.consensual.ConsensualFactory;
import com.didiglobal.logi.job.core.job.JobManager;
import com.didiglobal.logi.job.mapper.LogITaskMapper;
import com.didiglobal.logi.job.utils.BeanUtil;
import com.didiglobal.logi.job.utils.CronExpression;
import com.didiglobal.logi.job.utils.ThreadUtil;
import com.google.common.collect.Lists;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * task manager impl.
 *
 * @author ds
 */
@Service
public class TaskManagerImpl implements TaskManager {
    private static final Logger logger = LoggerFactory.getLogger(TaskManagerImpl.class);

    private static final long WAIT_INTERVAL_SECONDS = 10L;

    private JobManager jobManager;
    private ConsensualFactory consensualFactory;
    private TaskLockService taskLockService;
    private LogITaskMapper logITaskMapper;
    private LogIJobProperties logIJobProperties;


    /**
     * constructor.
     *
     * @param jobManager      jobManager
     * @param taskLockService taskLockService
     * @param logITaskMapper  logITaskMapper
     * @param logIJobProperties 配置信息
     * @param consensualFactory 一致协议工厂
     */
    public TaskManagerImpl(JobManager jobManager, ConsensualFactory consensualFactory,
                           TaskLockService taskLockService, LogITaskMapper logITaskMapper,
                           LogIJobProperties logIJobProperties) {
        this.jobManager = jobManager;
        this.consensualFactory = consensualFactory;
        this.taskLockService = taskLockService;
        this.logITaskMapper = logITaskMapper;
        this.logIJobProperties = logIJobProperties;
    }

    @Override
    public Result delete(String taskCode) {
        LogITaskPO logITaskPO = logITaskMapper.selectByCode(taskCode, logIJobProperties.getAppName());
        if (logITaskPO == null) {
            return Result.buildFail("任务不存在！");
        }
        return Result.buildSucc(logITaskMapper.deleteByCode(taskCode, logIJobProperties.getAppName()) > 0);
    }

    @Override
    public boolean update(LogITaskDTO logITaskDTO) {
        LogITaskPO logITaskPO = BeanUtil.convertTo(logITaskDTO, LogITaskPO.class);
        return logITaskMapper.updateByCode(logITaskPO) > 0 ? true : false;
    }

    @Override
    public List<LogITask> nextTriggers(Long interval) {
        return nextTriggers(System.currentTimeMillis(), interval);
    }

    @Override
    public List<LogITask> nextTriggers(Long fromTime, Long interval) {
        List<LogITask> logITaskList = getAllRuning();

        logITaskList = logITaskList.stream().filter(taskInfo -> {
            try {
                Timestamp lastFireTime = new Timestamp(0L);
                List<LogITask.TaskWorker> taskWorkers = taskInfo.getTaskWorkers();
                for (LogITask.TaskWorker taskWorker : taskWorkers) {
                    // 取到当前worker做进一步判断，如果没有找到证明没有执行过
                    if (Objects.equals(WorkerSingleton.getInstance().getLogIWorker().getWorkerCode(),
                            taskWorker.getWorkerCode())) {
                        // 判断是否在当前worker可执行状态
                        if (!Objects.equals(taskWorker.getStatus(), TaskWorkerStatusEnum.WAITING.getValue())) {
                            logger.info("class=TaskManagerImpl||method=nextTriggers||url=||msg=has task running! "
                                            + "taskCode={}, workerCode={}", taskInfo.getTaskCode(),
                                    taskWorker.getWorkerCode());
                            return false;
                        }
                        lastFireTime = taskWorker.getLastFireTime();
                        break;
                    }
                }
                CronExpression cronExpression = new CronExpression(taskInfo.getCron());
                long nextTime = cronExpression.getNextValidTimeAfter(lastFireTime).getTime();
                taskInfo.setNextFireTime(new Timestamp(nextTime));
            } catch (Exception e) {
                logger.error("class=TaskManagerImpl||method=nextTriggers||url=||msg=", e);
                return false;
            }
            return (new Timestamp(fromTime + interval * 1000)).after(taskInfo.getNextFireTime());
        }).collect(Collectors.toList());

        // sort
        logITaskList.sort(Comparator.comparing(LogITask::getNextFireTime));
        return logITaskList;
    }

    @Override
    public void submit(List<LogITask> logITaskList) {
        if (CollectionUtils.isEmpty(logITaskList)) {
            return;
        }
        for (LogITask logITask : logITaskList) {
            // 不能在本工作器执行，跳过
            Consensual consensual = consensualFactory.getConsensual(logITask.getConsensual());
            if (!consensual.canClaim(logITask)) {
                continue;
            }
            execute(logITask, false);
        }
    }

    /**
     * execute.
     */
    @Override
    public Result execute(String taskCode, Boolean executeSubs) {
        LogITaskPO logITaskPO = logITaskMapper.selectByCode(taskCode, logIJobProperties.getAppName());
        if (logITaskPO == null) {
            return Result.buildFail("任务不存在！");
        }
        if (!taskLockService.tryAcquire(taskCode)) {
            return Result.buildFail("未能获取到执行锁！");
        }

        LogITask logITask = logITaskPO2LogITask(logITaskPO);
        logITask.setTaskCallback(code -> taskLockService.tryRelease(code));
        execute(logITask, false);

        return Result.buildSucc();
    }

    @Override
    public void execute(LogITask logITask, Boolean executeSubs) {
        Timestamp lastFireTime = new Timestamp(System.currentTimeMillis());

        LogITaskPO logITaskPO = BeanUtil.convertTo(logITask, LogITaskPO.class);
        List<LogITask.TaskWorker> taskWorkers = logITask.getTaskWorkers();

        boolean worked = false;
        for (LogITask.TaskWorker taskWorker : taskWorkers) {
            if (Objects.equals(taskWorker.getWorkerCode(),
                    WorkerSingleton.getInstance().getLogIWorker().getWorkerCode())) {
                taskWorker.setLastFireTime(lastFireTime);
                taskWorker.setStatus(TaskWorkerStatusEnum.RUNNING.getValue());
                worked = true;
                break;
            }
        }

        if (!worked) {
            taskWorkers.add(new LogITask.TaskWorker(TaskWorkerStatusEnum.RUNNING.getValue(),
                    new Timestamp(System.currentTimeMillis()),
                    WorkerSingleton.getInstance().getLogIWorker().getWorkerCode(),
                    WorkerSingleton.getInstance().getLogIWorker().getIp()));
        }

        logITaskPO.setTaskWorkerStr(BeanUtil.convertToJson(taskWorkers));
        logITaskPO.setLastFireTime(lastFireTime);
        // 更新任务状态，最近更新时间
        logITaskMapper.updateByCode(logITaskPO);

        // 执行
        executeInternal(logITask, executeSubs);
    }

    @Override
    public int stopAll() {
        return jobManager.stopAll();
    }

    @Override
    public Result<Boolean> updateTaskStatus(String taskCode, int status) {
        if (!TaskStatusEnum.isValid(status)) {
            return Result.buildFail("status error");
        }

        LogITaskPO logITaskPO = logITaskMapper.selectByCode(taskCode, logIJobProperties.getAppName());
        if (null == logITaskPO) {
            return Result.buildFail("task 不存在");
        }

        if (TaskStatusEnum.STOP.getValue().intValue() == status) {
            if (!jobManager.stopByTaskCode(taskCode)) {
                return Result.buildFail("stop task error");
            }
        }

        if(TaskStatusEnum.RUNNING.getValue() == status){
            execute(logITaskPO.getTaskCode(), false);
        }

        logITaskPO.setStatus(status);

        return Result.buildSucc(logITaskMapper.updateByCode(logITaskPO) > 0);
    }

    @Override
    public List<LogITask> getAllRuning() {
        List<LogITaskPO> logITaskPOList = logITaskMapper.selectRuningByAppName(logIJobProperties.getAppName());
        if (CollectionUtils.isEmpty(logITaskPOList)) {
            return new ArrayList<>();
        }

        return logITaskPOList.stream().map(p -> logITaskPO2LogITask(p)).collect(Collectors.toList());
    }

    @Override
    public List<LogITask> getPagineList(TaskPageQueryDTO queryDTO) {
        List<LogITaskPO> logITaskPOList = logITaskMapper.pagineListByCondition(logIJobProperties.getAppName(),
                queryDTO.getTaskId(), queryDTO.getTaskDesc(), queryDTO.getClassName(), queryDTO.getTaskStatus(),
                (queryDTO.getPage() - 1) * queryDTO.getSize(), queryDTO.getSize());
        if (CollectionUtils.isEmpty(logITaskPOList)) {
            return new ArrayList<>();
        }

        return logITaskPOList.stream().map(p -> logITaskPO2LogITask(p)).collect(Collectors.toList());
    }

    @Override
    public int pagineTaskConut(TaskPageQueryDTO queryDTO) {
        return logITaskMapper.pagineCountByCondition(logIJobProperties.getAppName(),
                queryDTO.getTaskId(), queryDTO.getTaskDesc(), queryDTO.getClassName(), queryDTO.getTaskStatus());
    }

    @Override
    public Result<Boolean> release(String taskCode, String workerCode) {
        // 释放锁
        Boolean lockRet = taskLockService.tryRelease(taskCode, workerCode);
        if (!lockRet) {
            return Result.buildFail("释放锁失败！");
        }

        // 更新任务状态
        boolean updateResult = updateTaskWorker(taskCode, workerCode);
        if (!updateResult) {
            return Result.buildFail("更新锁失败！");
        }
        return Result.buildSucc();
    }

    @Override
    public LogITask getByCode(String taskCode) {
        LogITaskPO logITaskPO = logITaskMapper.selectByCode(taskCode, logIJobProperties.getAppName());

        return logITaskPO2LogITask(logITaskPO);
    }

    /**************************************** private method ****************************************************/
    private void executeInternal(LogITask logITask, Boolean executeSubs) {
        // jobManager 将job管理起来，超时退出抛异常
        final Future<Object> jobFuture = jobManager.start(logITask);
        if (jobFuture == null || !executeSubs) {
            return;
        }
        // 等待父任务运行完
        while (!jobFuture.isDone()) {
            ThreadUtil.sleep(WAIT_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }
        // 递归拉起子任务
        if (!StringUtils.isEmpty(logITask.getSubTaskCodes())) {
            String[] subTaskCodeArray = logITask.getSubTaskCodes().split(",");
            List<LogITaskPO> subTasks = logITaskMapper
                    .selectByCodes(Arrays.asList(subTaskCodeArray), logIJobProperties.getAppName());
            List<LogITask> subLogITaskList = subTasks.stream().map(logITaskPO -> BeanUtil.convertTo(logITaskPO,
                    LogITask.class)).collect(Collectors.toList());
            for (LogITask subLogITask : subLogITaskList) {
                execute(subLogITask, executeSubs);
            }
        }
    }

    private boolean updateTaskWorker(String taskCode, String workerCode) {
        LogITaskPO logITaskPO = logITaskMapper.selectByCode(taskCode, logIJobProperties.getAppName());
        if (logITaskPO == null) {
            return false;
        }
        List<LogITask.TaskWorker> taskWorkers = BeanUtil.convertToList(logITaskPO.getTaskWorkerStr(),
                LogITask.TaskWorker.class);
        boolean needUpdate = false;
        if (!CollectionUtils.isEmpty(taskWorkers)) {
            for (LogITask.TaskWorker taskWorker : taskWorkers) {
                if (Objects.equals(taskWorker.getWorkerCode(), workerCode)
                        && Objects.equals(taskWorker.getStatus(), TaskWorkerStatusEnum.RUNNING.getValue())) {
                    needUpdate = true;
                    taskWorker.setStatus(TaskWorkerStatusEnum.WAITING.getValue());
                }
            }
        }
        if (needUpdate) {
            logITaskPO.setTaskWorkerStr(BeanUtil.convertToJson(taskWorkers));
            int updateResult = logITaskMapper.updateByCode(logITaskPO);
            if (updateResult <= 0) {
                return false;
            }
        }
        return true;
    }

    private LogITask logITaskPO2LogITask(LogITaskPO logITaskPO) {
        LogITask logITask = BeanUtil.convertTo(logITaskPO, LogITask.class);
        List<LogITask.TaskWorker> taskWorkers = Lists.newArrayList();
        if (!StringUtils.isEmpty(logITaskPO.getTaskWorkerStr())) {
            List<LogITask.TaskWorker> tmpTaskWorkers = BeanUtil.convertToList(
                    logITaskPO.getTaskWorkerStr(), LogITask.TaskWorker.class);
            if (!CollectionUtils.isEmpty(tmpTaskWorkers)) {
                taskWorkers = tmpTaskWorkers;
            }
        }
        logITask.setTaskWorkers(taskWorkers);

        return logITask;
    }
}
