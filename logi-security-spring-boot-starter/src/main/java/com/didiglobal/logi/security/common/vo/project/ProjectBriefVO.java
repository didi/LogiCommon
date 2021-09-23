package com.didiglobal.logi.security.common.vo.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 */
@Data
@ApiModel(description = "项目简要信息")
public class ProjectBriefVO {

    @ApiModelProperty(value = "项目id", dataType = "Integer", required = false)
    private Integer id;

    @ApiModelProperty(value = "项目编号", dataType = "String", required = false)
    private String projectCode;

    /**
     * 项目名
     */
    @ApiModelProperty(value = "项目名", dataType = "String", required = false)
    private String projectName;
}
