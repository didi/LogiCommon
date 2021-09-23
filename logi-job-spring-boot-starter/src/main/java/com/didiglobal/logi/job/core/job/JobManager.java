package com.didiglobal.logi.job.core.job;

import com.didiglobal.logi.job.common.domain.LogIJob;
import com.didiglobal.logi.job.common.domain.LogITask;

import java.util.List;
import java.util.concurrent.Future;

/**
 * job管理器，负责CRUD、启动、暂停job.
 *
 * @author ds
 */
public interface JobManager {

    /**
     * 启动任务.
     *
     * @param logITask 任务
     * @return future
     */
    Future<Object> start(LogITask logITask);

    /**
     * @return
     */
    Integer runningJobSize();

    /**
     * 停止任务.
     *
     * @param jobCode job taskCode
     * @return true/false
     */
    boolean stopByJobCode(String jobCode);

    /**
     * @param taskCode
     * @return
     */
    boolean stopByTaskCode(String taskCode);

    /**
     * @return
     */
    int stopAll();

    /**
     * @return
     */
    List<LogIJob> getJobs();
}
