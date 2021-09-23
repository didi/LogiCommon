package com.didiglobal.logi.job.common.po;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务锁.
 * </p>
 *
 * @author ds
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LogITaskLockPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * task taskCode
     */
    private String taskCode;

    /**
     * worker taskCode
     */
    private String workerCode;

    /**
     * expire time
     */
    private Long expireTime;

    /**
     * 应用名称
     */
    private String appName;

}
