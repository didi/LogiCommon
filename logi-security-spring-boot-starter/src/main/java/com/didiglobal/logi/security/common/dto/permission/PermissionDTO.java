package com.didiglobal.logi.security.common.dto.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjm
 */
@Data
@ApiModel(description = "权限导入信息")
public class PermissionDTO {

    @ApiModelProperty(value = "权限名", dataType = "String", required = true)
    private String permissionName;

    @ApiModelProperty(value = "权限描述", dataType = "String", required = false)
    private String description;

    @ApiModelProperty(value = "子权限", dataType = "List<PermissionDTO>", required = false)
    private List<PermissionDTO> childPermissionDTOList;

    public List<PermissionDTO> getChildPermissionDTOList() {
        if(childPermissionDTOList == null) {
            childPermissionDTOList = new ArrayList<>();
        }
        return childPermissionDTOList;
    }
}
