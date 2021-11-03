package com.didiglobal.logi.job.core.beat;

import com.didiglobal.logi.job.LogIJobProperties;
import com.didiglobal.logi.job.common.domain.LogIWorker;
import com.didiglobal.logi.job.common.po.LogIWorkerPO;
import com.didiglobal.logi.job.core.WorkerSingleton;
import com.didiglobal.logi.job.core.job.JobManager;
import com.didiglobal.logi.job.core.monitor.SimpleBeatMonitor;
import com.didiglobal.logi.job.mapper.LogIWorkerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class BeatManagerImpl implements BeatManager {
    private static final Logger logger = LoggerFactory.getLogger(BeatManagerImpl.class);

    private JobManager jobManager;
    private LogIWorkerMapper logIWorkerMapper;
    private LogIJobProperties logIJobProperties;

    /**
     * constructor.
     *
     * @param jobManager       job manager
     * @param logIWorkerMapper worker mapper
     * @param logIJobProperties job 配置信息
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

        if(CollectionUtils.isEmpty(logIWorkerPOS)){return;}

        for (LogIWorkerPO logIWorkerPO : logIWorkerPOS) {
            if (logIWorkerPO.getHeartbeat().getTime() + 3 * SimpleBeatMonitor.INTERVAL * 1000 < currentTime) {
                logIWorkerMapper.deleteByCode(logIWorkerPO.getWorkerCode());
            }
        }
    }
}