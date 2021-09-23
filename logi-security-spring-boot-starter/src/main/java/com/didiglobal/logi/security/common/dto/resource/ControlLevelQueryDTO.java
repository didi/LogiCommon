package com.didiglobal.logi.security.common.dto.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 */
@Data
@ApiModel(description = "获取用户拥有资源管理权限类别的查询条件")
public class ControlLevelQueryDTO {

    @ApiModelProperty(value = "用户id", dataType = "Integer", required = true)
    private Integer userId;

    @ApiModelProperty(value = "项目id", dataType = "Integer", required = true)
    private Integer projectId;

    @ApiModelProperty(value = "资源类别id", dataType = "Integer", required = true)
    private Integer resourceTypeId;

    @ApiModelProperty(value = "具体资源id", dataType = "Integer", required = true)
    private Integer  resourceId;
}
