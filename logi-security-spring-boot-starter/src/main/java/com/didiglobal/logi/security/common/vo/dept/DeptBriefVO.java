package com.didiglobal.logi.security.common.vo.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 */
@Data
@ApiModel(description = "部门简要信息")
public class DeptBriefVO {

    @ApiModelProperty(value = "部门id", dataType = "Integer", required = false)
    private Integer id;

    /**
     * 部门名
     */
    @ApiModelProperty(value = "部门名", dataType = "String", required = false)
    private String deptName;

    /**
     * 父部门id（根部门parentId为0）
     */
    @ApiModelProperty(value = "父部门id（根部门parentId为0）", dataType = "Integer", required = false)
    private Integer parentId;
}
