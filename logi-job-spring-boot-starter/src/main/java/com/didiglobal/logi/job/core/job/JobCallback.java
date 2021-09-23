package com.didiglobal.logi.job.core.job;

import com.didiglobal.logi.job.common.domain.LogIJob;

public interface JobCallback {
    void callback(LogIJob logIJob);
}
