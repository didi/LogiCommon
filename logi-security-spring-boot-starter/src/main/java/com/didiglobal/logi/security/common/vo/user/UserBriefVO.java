package com.didiglobal.logi.security.common.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cjm
 */
@Data
@ApiModel(description = "用户简要信息")
public class UserBriefVO {

    @ApiModelProperty(value = "用户id", dataType = "Integer", required = false)
    private Integer id;

    @ApiModelProperty(value = "用户账号", dataType = "String", required = false)
    private String username;

    @ApiModelProperty(value = "真实姓名", dataType = "String", required = false)
    private String realName;

    @ApiModelProperty(value = "部门id", dataType = "Integer", required = false)
    private Integer deptId;
}
