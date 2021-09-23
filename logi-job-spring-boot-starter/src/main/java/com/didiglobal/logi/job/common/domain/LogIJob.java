package com.didiglobal.logi.job.common.domain;

import com.didiglobal.logi.job.common.TaskResult;
import com.didiglobal.logi.job.common.po.LogIJobPO;
import com.didiglobal.logi.job.common.po.LogIJobLogPO;
import com.didiglobal.logi.job.core.job.Job;
import com.didiglobal.logi.job.core.task.TaskCallback;

import java.sql.Timestamp;
import java.util.Objects;

import com.didiglobal.logi.job.utils.BeanUtil;
import lombok.Data;

@Data
public class LogIJob {
    private String jobCode;
    private String taskCode;
    private Long taskId;
    private String taskName;
    private String taskDesc;
    private String className;
    private Integer retryTimes;
    private Integer tryTimes;
    private String workerCode;
    private String workerIp;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer status;
    private String error;
    private Long timeout;
    private TaskResult result;
    private Job job;
    private TaskCallback taskCallback;
    private String appName;

    /**
     * auv job.
     *
     * @return auv job
     */
    public LogIJobPO getAuvJob() {
        LogIJobPO job = new LogIJobPO();
        job.setJobCode(getJobCode());
        job.setTaskCode(getTaskCode());
        job.setClassName(getClassName());
        job.setTryTimes(getTryTimes());
        job.setWorkerCode(getWorkerCode());
        job.setAppName(getAppName());
        job.setStartTime(new Timestamp(System.currentTimeMillis()));
        job.setCreateTime(new Timestamp(System.currentTimeMillis()));
        job.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return job;
    }

    /**
     * auv job log.
     *
     * @return job log
     */
    public LogIJobLogPO getAuvJobLog() {
        LogIJobLogPO logIJobLogPO = new LogIJobLogPO();
        logIJobLogPO.setJobCode(getJobCode());
        logIJobLogPO.setTaskCode(getTaskCode());
        logIJobLogPO.setTaskId(getTaskId());
        logIJobLogPO.setTaskName(getTaskName());
        logIJobLogPO.setTaskDesc(getTaskDesc());
        logIJobLogPO.setClassName(getClassName());
        logIJobLogPO.setWorkerCode(getWorkerCode());
        logIJobLogPO.setWorkerIp(getWorkerIp());
        logIJobLogPO.setTryTimes(getTryTimes());
        logIJobLogPO.setStartTime(getStartTime());
        logIJobLogPO.setEndTime(getEndTime());
        logIJobLogPO.setStatus(getStatus());
        logIJobLogPO.setError(getError() == null ? "" : getError());
        logIJobLogPO.setResult(getResult() == null ? "" : BeanUtil.convertToJson(getResult()));
        logIJobLogPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        logIJobLogPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        logIJobLogPO.setAppName(this.getAppName());
        return logIJobLogPO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogIJob logIJob = (LogIJob) o;
        return jobCode.equals(logIJob.jobCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobCode);
    }
}