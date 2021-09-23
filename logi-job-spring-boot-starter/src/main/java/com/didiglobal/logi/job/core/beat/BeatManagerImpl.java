package com.didiglobal.logi.job.core.beat;

import com.didiglobal.logi.job.LogIJobProperties;
import com.didiglobal.logi.job.common.domain.LogIWorker;
import com.didiglobal.logi.job.common.po.LogIWorkerPO;
import com.didiglobal.logi.job.core.WorkerSingleton;
import com.didiglobal.logi.job.core.job.JobManager;
import com.didiglobal.logi.job.mapper.LogIWorkerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeatManagerImpl implements BeatManager {
    private static final Logger logger = LoggerFactory.getLogger(BeatManagerImpl.class);

    private JobManager jobManager;
    private LogIWorkerMapper logIWorkerMapper;
    private LogIJobProperties logIJobProperties;

    private static final Long ONE_HOUR = 3600L;

    /**
     * constructor.
     *
     * @param jobManager       job manager
     * @param logIWorkerMapper worker mapper
     */
    @Autowired
    public BeatManagerImpl(JobManager jobManager, LogIWorkerMapper logIWorkerMapper,
                           LogIJobProperties logIJobProperties) {
        this.jobManager = jobManager;
        this.logIWorkerMapper = logIWorkerMapper;
        this.logIJobProperties = logIJobProperties;
    }

    @Override
    public boolean beat() {
        logger.info("class=BeatManagerImpl||method=||url=||msg=beat beat!!!");
        cleanWorker();

        WorkerSingleton workerSingleton = WorkerSingleton.getInstance();
        workerSingleton.updateInstanceMetrics();
        LogIWorker logIWorker = workerSingleton.getLogIWorker();
        logIWorker.setJobNum(jobManager.runningJobSize());
        logIWorker.setAppName(logIJobProperties.getAppName());
        return logIWorkerMapper.saveOrUpdateById(logIWorker.getWorker()) > 0 ? true : false;
    }

    @Override
    public boolean stop() {
        // clean worker
        WorkerSingleton workerSingleton = WorkerSingleton.getInstance();
        LogIWorker logIWorker = workerSingleton.getLogIWorker();
        logIWorkerMapper.deleteByCode(logIWorker.getWorkerCode());
        return true;
    }

    private void cleanWorker() {
        long currentTime = System.currentTimeMillis();
        List<LogIWorkerPO> logIWorkerPOS = logIWorkerMapper.selectByAppName(logIJobProperties.getAppName());
        for (LogIWorkerPO logIWorkerPO : logIWorkerPOS) {
            if (logIWorkerPO.getUpdateTime().getTime() + 2 * 24 * ONE_HOUR * 1000 < currentTime) {
                logIWorkerMapper.deleteByCode(logIWorkerPO.getWorkerCode());
            }
        }
    }
}