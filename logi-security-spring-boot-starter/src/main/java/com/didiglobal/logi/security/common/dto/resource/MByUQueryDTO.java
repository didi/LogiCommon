package com.didiglobal.logi.security.common.dto.resource;

import com.didiglobal.logi.security.common.dto.PageParamDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 资源权限管理/按用户管理的列表查询条件
 *
 * MByU（ManagerByUser）
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "资源权限管理（按用户管理的列表查询条件）")
public class MByUQueryDTO extends PageParamDTO {

    @ApiModelProperty(value = "部门id", dataType = "Integer", required = false)
    private Integer deptId;

    @ApiModelProperty(value = "部门名（模糊）", dataType = "String", required = false)
    private String deptName;

    @ApiModelProperty(value = "用户账号", dataType = "String", required = false)
    private String username;

    @ApiModelProperty(value = "用户实名", dataType = "String", required = false)
    private String realName;
}
