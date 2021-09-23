package com.didiglobal.logi.job.common.po;


import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务信息.
 * </p>
 *
 * @author ds
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LogITaskPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * task taskCode
     */
    private String taskCode;

    /**
     * 名称
     */
    private String taskName;

    /**
     * 任务描述
     */
    private String taskDesc;

    /**
     * cron 表达式
     */
    private String cron;

    /**
     * 类的全限定名
     */
    private String className;

    /**
     * 执行参数 map 形式{key1:value1,key2:value2}
     */
    private String params;

    /**
     * 重试次数
     */
    private Integer retryTimes;

    /**
     * 上次执行时间
     */
    private Timestamp lastFireTime;

    /**
     * 超时 毫秒
     */
    private Long timeout;

    /**
     * 1等待 2运行中 3暂停
     */
    private Integer status;

    /**
     * 子任务code列表,逗号分隔
     */
    private String subTaskCodes;

    /**
     * 抢占算法
     */
    private String consensual;

    /**
     * 执行信息
     */
    private String taskWorkerStr;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 责任人
     */
    private String owner;

}
