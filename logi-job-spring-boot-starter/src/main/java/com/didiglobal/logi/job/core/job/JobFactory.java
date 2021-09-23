package com.didiglobal.logi.job.core.job;

import com.didiglobal.logi.job.common.domain.LogIJob;
import com.didiglobal.logi.job.common.domain.LogITask;

/**
 * job factory.
 *
 * @author ds
 */
public interface JobFactory {
    void addJob(String className, Job job);

    LogIJob newJob(LogITask logITask);
}
