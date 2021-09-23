package com.didiglobal.logi.security.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 */
@Data
public class BaseResult {

    @ApiModelProperty(value = "返回信息")
    protected String message;

    @ApiModelProperty(value = "返回编号（200成功，其他见message）")
    protected Integer code;
}
