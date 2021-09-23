package com.didiglobal.logi.security.common.vo.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 *
 * 资源类型
 */
@Data
@ApiModel(description = "资源类型信息")
public class ResourceTypeVO {

    @ApiModelProperty(value = "资源类型标识", dataType = "Integer", required = false)
    private Integer id;

    @ApiModelProperty(value = "资源类型名", dataType = "String", required = false)
    private String typeName;
}
