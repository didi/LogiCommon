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
     * @return 当前运行任务大小
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
     * 通过任务编号停止任务
     * @param taskCode 任务编号
     * @return 是否成功
     */
    boolean stopByTaskCode(String taskCode);

    /**
     * 停止全部
     * @return 停止成功数量
     */
    int stopAll();

    /**
     * 获取job信息
     * @return job信息List
     */
    List<LogIJob> getJobs();
}
