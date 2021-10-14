package com.didiglobal.logi.job.core.task;

import com.didiglobal.logi.job.LogIJobProperties;
import com.didiglobal.logi.job.common.po.LogITaskLockPO;
import com.didiglobal.logi.job.common.dto.LogITaskLockDTO;
import com.didiglobal.logi.job.core.WorkerSingleton;
import com.didiglobal.logi.job.mapper.LogITaskLockMapper;
import com.didiglobal.logi.job.utils.BeanUtil;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * task lock service.
 *
 * @author ds
 */
@Service
public class TaskLockServiceImpl implements TaskLockService {
    private static final Logger logger = LoggerFactory.getLogger(TaskLockServiceImpl.class);
    // 每次申请锁的默认过期时间
    private static final Long EXPIRE_TIME_SECONDS = 120L;

    private LogITaskLockMapper logITaskLockMapper;
    private LogIJobProperties logIJobProperties;

    /**
     * constructor
     * @param logITaskLockMapper mapper
     * @param logIJobProperties 配置信息
     */
    @Autowired
    public TaskLockServiceImpl(LogITaskLockMapper logITaskLockMapper, LogIJobProperties logIJobProperties) {
        this.logITaskLockMapper = logITaskLockMapper;
        this.logIJobProperties = logIJobProperties;
    }

    @Override
    public Boolean tryAcquire(String taskCode) {
        return tryAcquire(taskCode, WorkerSingleton.getInstance().getLogIWorker().getWorkerCode(),
                EXPIRE_TIME_SECONDS);
    }

    @Override
    public Boolean tryAcquire(String taskCode, String workerCode, Long expireTime) {
        List<LogITaskLockPO> logITaskLockPOList =
                logITaskLockMapper.selectByTaskCode(taskCode, logIJobProperties.getAppName());

        boolean hasLock;
        if (CollectionUtils.isEmpty(logITaskLockPOList)) {
            hasLock = false;
        } else {
            long current = System.currentTimeMillis() / 1000;
            Long inLockSize = logITaskLockPOList.stream().filter(logITaskLockPO -> logITaskLockPO.getCreateTime()
                    .getTime() / 1000 + expireTime > current).collect(Collectors.counting());
            hasLock = inLockSize > 0 ? true : false;
        }

        if (!hasLock) {
            LogITaskLockPO taskLock = new LogITaskLockPO();
            taskLock.setTaskCode(taskCode);
            taskLock.setWorkerCode(workerCode);
            taskLock.setExpireTime(expireTime);
            taskLock.setCreateTime(new Timestamp(System.currentTimeMillis()));
            taskLock.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            taskLock.setAppName(logIJobProperties.getAppName());
            try {
                return logITaskLockMapper.insert(taskLock) > 0 ? true : false;
            } catch (Exception e) {
                if (e instanceof SQLException && e.getMessage().contains("Duplicate entry")) {
                    logger.info("class=TaskLockServiceImpl||method=tryAcquire||taskCode=||msg=duplicate key", taskCode);
                } else {
                    logger.error(
                            "class=TaskLockServiceImpl||method=tryAcquire||taskCode=||msg=", taskCode, e.getMessage()
                    );
                }
            }
        }
        return false;
    }

    @Override
    public Boolean tryRelease(String taskCode) {
        return tryRelease(taskCode, WorkerSingleton.getInstance().getLogIWorker().getWorkerCode());
    }

    @Override
    public Boolean tryRelease(String taskCode, String workerCode) {
        List<LogITaskLockPO> logITaskLockPOList = logITaskLockMapper.selectByTaskCodeAndWorkerCode(taskCode,
                workerCode, logIJobProperties.getAppName());
        if (CollectionUtils.isEmpty(logITaskLockPOList)) {
            logger.error("class=TaskLockServiceImpl||method=tryRelease||url=||msg=taskCode={}, "
                    + "workerCode={}", taskCode, workerCode);
            return false;
        }
        long current = System.currentTimeMillis() / 1000;
        List<Long> taskLockIdList = logITaskLockPOList.stream().filter(logITaskLockPO ->
                        logITaskLockPO.getCreateTime().getTime() / 1000 + logITaskLockPO.getExpireTime() < current)
                .map(LogITaskLockPO::getId)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(taskLockIdList)) {
            return true;
        }

        int result = logITaskLockMapper.deleteByIds(taskLockIdList);
        return result > 0 ? true : false;
    }

    @Override
    public List<LogITaskLockDTO> getAll() {
        List<LogITaskLockPO> logITaskLockPOS = logITaskLockMapper.selectByAppName(logIJobProperties.getAppName());
        if (CollectionUtils.isEmpty(logITaskLockPOS)) {
            return null;
        }
        return logITaskLockPOS.stream().map(logITaskLockPO -> BeanUtil.convertTo(logITaskLockPO,
                LogITaskLockDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void renewAll() {

    }
}
