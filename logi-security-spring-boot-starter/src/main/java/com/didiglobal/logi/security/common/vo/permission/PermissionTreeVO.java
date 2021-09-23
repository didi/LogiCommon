package com.didiglobal.logi.security.common.vo.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

/**
 * @author cjm
 */
@Data
@Builder
@ApiModel(description = "权限信息")
public class PermissionTreeVO {

    @ApiModelProperty(value = "权限id", dataType = "Integer", required = false)
    private Integer id;

    /**
     * 是否拥有该权限点的权限
     * true：有
     */
    @ApiModelProperty(value = "是否拥有该权限点的权限", dataType = "Boolean", required = false)
    private Boolean has;

    /**
     * 权限名
     */
    @ApiModelProperty(value = "权限名", dataType = "String", required = false)
    private String permissionName;

    /**
     * 父权限id（根权限parentId为0）
     */
    @ApiModelProperty(value = "父权限id（根权限parentId为0）", dataType = "Integer", required = false)
    private Integer parentId;

    /**
     * 是否是叶子权限
     */
    @ApiModelProperty(value = "是否是叶子权限", dataType = "Boolean", required = false)
    private Boolean leaf;

    /**
     * 孩子权限
     */
    @ApiModelProperty(value = "孩子权限", dataType = "List<PermissionVo>", required = false)
    private List<PermissionTreeVO> childList;

    @Tolerate
    public PermissionTreeVO() {
        // Do nothing
    }
}
