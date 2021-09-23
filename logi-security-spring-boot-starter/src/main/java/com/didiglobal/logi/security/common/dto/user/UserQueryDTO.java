package com.didiglobal.logi.security.common.dto.user;

import com.didiglobal.logi.security.common.dto.PageParamDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 * 用户列表查询条件
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "用户查找条件信息")
public class UserQueryDTO extends PageParamDTO {

    /**
     * 角色id
     */
    @ApiModelProperty(value = "根据角色id查询", dataType = "Integer", required = false)
    private Integer roleId;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号（模糊）", dataType = "String", required = false)
    private String username;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名（模糊）", dataType = "String", required = false)
    private String realName;

    /**
     * 根据部门id查询
     */
    @ApiModelProperty(value = "根据部门id查询", dataType = "Integer", required = false)
    private Integer deptId;
}
