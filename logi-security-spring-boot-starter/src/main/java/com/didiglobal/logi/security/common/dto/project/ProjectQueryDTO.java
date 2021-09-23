package com.didiglobal.logi.security.common.dto.project;

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
@ApiModel(description = "项目查找条件信息")
public class ProjectQueryDTO extends PageParamDTO {

    /**
     * 项目名
     */
    @ApiModelProperty(value = "项目名（模糊）", dataType = "String", required = false)
    private String projectName;

    /**
     * 项目编号
     */
    @ApiModelProperty(value = "项目编号（精确）", dataType = "String", required = false)
    private String projectCode;

    /**
     * 负责人的用户账号名
     */
    @ApiModelProperty(value = "负责人的账号名（模糊）", dataType = "String", required = false)
    private String chargeUsername;

    /**
     * 所属部门id
     */
    @ApiModelProperty(value = "所属部门id", dataType = "Integer", required = false)
    private Integer deptId;

    /**
     * 如果running为null，则表示所有状态
     */
    @ApiModelProperty(value = "项目运行状态（为null，表示所有状态）", dataType = "Boolean", required = false)
    private Boolean running;
}
