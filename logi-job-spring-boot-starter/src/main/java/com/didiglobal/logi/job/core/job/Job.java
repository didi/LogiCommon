package com.didiglobal.logi.job.core.job;

import com.didiglobal.logi.job.common.TaskResult;

/**
 * job.
 */
public interface Job {

    TaskResult execute(JobContext jobContext) throws Exception;
}
