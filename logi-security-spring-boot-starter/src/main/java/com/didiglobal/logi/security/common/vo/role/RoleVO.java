package com.didiglobal.logi.security.common.vo.role;

import com.didiglobal.logi.security.common.vo.permission.PermissionTreeVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 */
@Data
@ApiModel(description = "角色信息")
public class RoleVO {

    @ApiModelProperty(value = "角色id", dataType = "Integer", required = false)
    private Integer id;

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色名", dataType = "String", required = false)
    private String roleName;

    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号", dataType = "String", required = false)
    private String roleCode;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述", dataType = "String", required = false)
    private String description;

    /**
     * 授权用户数（拥有该角色的用户数）
     */
    @ApiModelProperty(value = "授权用户数（拥有该角色的用户数）", dataType = "String", required = false)
    private Integer authedUserCnt;

    /**
     * 最后修改者（用户账号）
     */
    @ApiModelProperty(value = "最后修改者（用户账号）", dataType = "String", required = false)
    private String lastReviser;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间（时间戳ms）", dataType = "Long", required = false)
    private Long createTime;

    /**
     * 角色拥有的权限（树）
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "角色拥有的权限（树）", dataType = "PermissionTreeVO", required = false)
    private PermissionTreeVO permissionTreeVO;

}
