package com.didiglobal.logi.job.common.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LogIJobDTO {

    private String code;
    private String taskCode;
    private String className;
    private Integer tryTimes;
    private String workerCode;
    private Timestamp startTime;
    private Timestamp createTime;
    private Timestamp updateTime;

}