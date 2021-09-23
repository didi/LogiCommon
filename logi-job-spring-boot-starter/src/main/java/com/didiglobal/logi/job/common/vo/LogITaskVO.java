package com.didiglobal.logi.job.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 任务详情
 *
 * @author cjm
 */
@Data
@ApiModel(description = "LogITaskVO 任务详情")
public class LogITaskVO {

    @ApiModelProperty(value = "taskcode")
    private Long id;

    @ApiModelProperty(value = "taskcode")
    private String taskCode;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务责任人")
    private String owner;

    @ApiModelProperty(value = "任务描述")
    private String taskDesc;

    @ApiModelProperty(value = "定时任务调度时间表达式")
    private String cron;

    @ApiModelProperty(value = "定时任务调度执行代码")
    private String className;

    @ApiModelProperty(value = "执行参数 map 形式{key1:value1,key2:value2}")
    private String params;

    @ApiModelProperty(value = "上次调度执行时间")
    private Timestamp lastFireTime;

    @ApiModelProperty(value = "下次调度执行时间")
    private Timestamp nextFireTime;

    @ApiModelProperty(value = "任务状态，1：正常，0：暂停")
    private Integer status;

    @ApiModelProperty(value = "调度方式：单播、广播")
    private String consensual;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "调度机器列表")
    private List<String> workerIps;

    @ApiModelProperty(value = "路由策略")
    private String routing;

    @ApiModelProperty(value = "运行模式")
    private String runningType = "BEAN模式";

    @ApiModelProperty(value = "阻塞策略")
    private String blockPolicy = "单机串行";

    @ApiModelProperty(value = "任务被调度时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "任务被调度时间")
    private Timestamp updateTime;
}
