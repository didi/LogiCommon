package com.didiglobal.logi.security.common.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author cjm
 *
 * 角色删除前检查
 */
@Data
@ApiModel(description = "角色删除前的检查信息")
public class RoleDeleteCheckVO {

    @ApiModelProperty(value = "角色id", dataType = "Integer", required = false)
    private Integer roleId;

    @ApiModelProperty(value = "用户名list，存放引用该角色的用户名", dataType = "List<String>", required = false)
    private List<String> usernameList;

}
