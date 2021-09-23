package com.didiglobal.logi.job.common.po;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * worker信息.
 * </p>
 *
 * @author ds
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LogIWorkerBlacklistPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /*
     * workerCode
     */
    private String workerCode;

}
