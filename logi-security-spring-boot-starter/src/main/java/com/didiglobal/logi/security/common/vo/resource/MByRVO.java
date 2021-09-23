package com.didiglobal.logi.security.common.vo.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author cjm
 *
 * 资源权限管理>按资源管理的列表信息
 *
 * MByR（ManageByResource）
 */
@Data
@ApiModel(description = "资源权限管理/按资源管理/列表信息")
public class MByRVO {

    /**
     * 管理权限用户数
     */
    @ApiModelProperty(value = "管理权限用户数", dataType = "Integer", required = false)
    private Integer adminUserCnt;

    /**
     * 查看权限用户数
     */
    @ApiModelProperty(value = "查看权限用户数", dataType = "Integer", required = false)
    private Integer viewUserCnt;

    @ApiModelProperty(value = "项目id", dataType = "Integer", required = false)
    private Integer projectId;

    @ApiModelProperty(value = "项目code", dataType = "String", required = false)
    private String projectCode;

    @ApiModelProperty(value = "项目名", dataType = "String", required = false)
    private String projectName;

    @ApiModelProperty(value = "资源类别id", dataType = "Integer", required = false)
    private Integer resourceTypeId;

    @ApiModelProperty(value = "资源类别名", dataType = "String", required = false)
    private String resourceTypeName;

    @ApiModelProperty(value = "具体资源id", dataType = "Integer", required = false)
    private Integer resourceId;

    @ApiModelProperty(value = "具体资源名", dataType = "String", required = false)
    private String resourceName;
}
