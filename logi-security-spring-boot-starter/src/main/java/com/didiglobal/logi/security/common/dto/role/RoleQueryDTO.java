package com.didiglobal.logi.security.common.dto.role;

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
@ApiModel(description = "角色查找条件信息")
public class RoleQueryDTO extends PageParamDTO {

    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号（精确）", dataType = "String", required = false)
    private String roleCode;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名（模糊）", dataType = "String", required = false)
    private String roleName;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述（模糊）", dataType = "String", required = false)
    private String description;
}
