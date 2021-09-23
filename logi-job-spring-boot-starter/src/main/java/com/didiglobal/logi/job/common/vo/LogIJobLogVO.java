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
@ApiModel(description = "LogIJobLogVO 调度执行的任务详情")
public class LogIJobLogVO {

    @ApiModelProperty(value = "调度执行的任务id")
    private Long id;

    @ApiModelProperty(value = "调度执行的任务")
    private String jobCode;

    @ApiModelProperty(value = "配置的任务")
    private String taskCode;

    @ApiModelProperty(value = "配置的任务Id")
    private Long taskId;

    @ApiModelProperty(value = "配置的任务名称")
    private String taskName;

    @ApiModelProperty(value = "配置的任务描述")
    private String taskDesc;

    @ApiModelProperty(value = "定时任务调度执行代码")
    private String className;

    @ApiModelProperty(value = "调度执行的机器")
    private String workerCode;

    @ApiModelProperty(value = "任务开始执行时间")
    private Timestamp startTime;

    @ApiModelProperty(value = "任务结束执行时间")
    private Timestamp endTime;

    @ApiModelProperty(value = "任务被调度时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "任务被调度时间")
    private Timestamp updateTime;

    @ApiModelProperty(value = "任务调度结果，0:调度启动中、1:运行中、 2：成功、3：失败、4：取消")
    private Integer status;

    @ApiModelProperty(value = "任务执行错误")
    private String error;

    @ApiModelProperty(value = "任务执行结果")
    private String result;

    @ApiModelProperty(value = "所有可被调度的机器列表")
    private List<String> allWorkerIps;

    @ApiModelProperty(value = "调度到的机器列表")
    private String workerIp;
}
