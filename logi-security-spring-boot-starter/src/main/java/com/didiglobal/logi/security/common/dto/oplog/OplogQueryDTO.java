package com.didiglobal.logi.security.common.dto.oplog;

import com.didiglobal.logi.security.common.dto.PageParamDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "操作日志查找条件信息")
public class OplogQueryDTO extends PageParamDTO {

    /**
     * 操作类型
     */
    @ApiModelProperty(value = "操作类型（精确）", dataType = "String", required = false)
    private String operateType;

    /**
     * 对象类型
     */
    @ApiModelProperty(value = "对象类型（精确）", dataType = "String", required = false)
    private String targetType;

    /**
     * 操作者ip
     */
    @ApiModelProperty(value = "操作者ip（模糊）", dataType = "String", required = false)
    private String operatorIp;

    /**
     * 操作者用户账号
     */
    @ApiModelProperty(value = "操作者用户账号（模糊）", dataType = "String", required = false)
    private String operatorUsername;

    /**
     * 操作对象
     */
    @ApiModelProperty(value = "操作对象（模糊）", dataType = "String", required = false)
    private String target;

    /**
     * 操作起始时间
     */
    @ApiModelProperty(value = "操作起始时间（时间戳ms）", dataType = "Long", required = false)
    private Long startTime;

    /**
     * 操作结束时间
     */
    @ApiModelProperty(value = "操作结束时间（时间戳ms）", dataType = "Long", required = false)
    private Long endTime;
}
