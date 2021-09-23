package com.didiglobal.logi.security.controller.v1;

import com.didiglobal.logi.security.common.constant.Constants;
import com.didiglobal.logi.security.common.Result;
import com.didiglobal.logi.security.common.dto.account.AccountLoginDTO;
import com.didiglobal.logi.security.common.vo.user.UserBriefVO;
import com.didiglobal.logi.security.exception.LogiSecurityException;
import com.didiglobal.logi.security.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cjm
 */
@RestController
@Api(value = "account相关API接口", tags = "账户相关API接口")
@RequestMapping(Constants.V1 + "/logi-security/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录检查", notes = "检查SSO返回的Code")
    public Result<UserBriefVO> login(@RequestBody AccountLoginDTO loginDTO, HttpServletRequest request) {
        try {
            UserBriefVO userBriefVO = userService.verifyLogin(loginDTO, request);
            return Result.success(userBriefVO);
        } catch (LogiSecurityException e) {
            return Result.fail(e);
        }
    }
}
