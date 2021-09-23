package com.didiglobal.logi.security.common.vo.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author cjm
 *
 * 项目删除前检查
 */
@Data
@ApiModel(description = "项目删除前的检查信息")
public class ProjectDeleteCheckVO {

    @ApiModelProperty(value = "项目id", dataType = "Integer", required = false)
    private Integer projectId;

    @ApiModelProperty(value = "服务名list，存放引用该项目的服务名", dataType = "List<String>", required = false)
    private List<String> serviceNameList;

    @ApiModelProperty(value = "服务名list，存放引用该项目的具体资源名", dataType = "List<String>", required = false)
    private List<String> resourceNameList;

    public ProjectDeleteCheckVO(Integer projectId) {
        this.projectId = projectId;
    }
}
