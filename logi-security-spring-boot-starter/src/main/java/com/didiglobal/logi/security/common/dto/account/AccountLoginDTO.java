package com.didiglobal.logi.security.common.dto.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author cjm
 */
@Data
@ApiModel(description = "用户登陆信息")
public class AccountLoginDTO {

    @ApiModelProperty(name = "username", value = "用户登录名（可以是用户名登录或者邮箱登录）", dataType = "String")
    private String username;

    @ApiModelProperty(name = "password", value = "用户登录密码（密码需RSA加密再Base64编码加密）", dataType = "String")
    private String password;
}
