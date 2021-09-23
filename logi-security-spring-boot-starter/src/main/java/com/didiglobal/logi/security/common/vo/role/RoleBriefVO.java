package com.didiglobal.logi.security.common.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 */
@Data
@ApiModel(description = "角色简要信息")
public class RoleBriefVO {

    @ApiModelProperty(value = "角色id", dataType = "Integer", required = false)
    private Integer id;

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色名", dataType = "String", required = false)
    private String roleName;
}
