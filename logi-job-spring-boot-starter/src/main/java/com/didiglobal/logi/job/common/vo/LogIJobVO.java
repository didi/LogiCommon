package com.didiglobal.logi.job.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 任务详情
 *
 * @author cjm
 */
@Data
@ApiModel(description = "LogIJobVO 调度执行的任务详情")
public class LogIJobVO {

    @ApiModelProperty(value = "调度执行的任务")
    private String jobCode;

    @ApiModelProperty(value = "配置的任务")
    private String taskCode;

    @ApiModelProperty(value = "定时任务调度执行代码")
    private String className;

    @ApiModelProperty(value = "调度执行的机器")
    private String workerCode;

    @ApiModelProperty(value = "任务执行错误")
    private String error;

    @ApiModelProperty(value = "任务被调度时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "任务执行结果")
    private String result;
}
