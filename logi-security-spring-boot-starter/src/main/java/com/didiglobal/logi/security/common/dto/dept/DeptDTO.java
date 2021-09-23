package com.didiglobal.logi.security.common.dto.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjm
 */
@Data
@ApiModel(description = "部门导入信息")
public class DeptDTO {

    @ApiModelProperty(value = "部门名", dataType = "String", required = true)
    private String deptName;

    @ApiModelProperty(value = "部门描述", dataType = "String", required = false)
    private String description;

    @ApiModelProperty(value = "子部门", dataType = "List<DeptDTO>", required = false)
    private List<DeptDTO> childDeptDTOList;

    public List<DeptDTO> getChildDeptDTOList() {
        if(childDeptDTOList == null) {
            childDeptDTOList = new ArrayList<>();
        }
        return childDeptDTOList;
    }
}
