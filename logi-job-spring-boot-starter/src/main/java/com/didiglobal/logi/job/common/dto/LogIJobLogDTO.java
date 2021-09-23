package com.didiglobal.logi.job.common.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LogIJobLogDTO {

    private String jobCode;
    private String taskCode;
    private String className;
    private Integer tryTimes;
    private String workerCode;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer status;
    private String error;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String result;
    private String operator;
}