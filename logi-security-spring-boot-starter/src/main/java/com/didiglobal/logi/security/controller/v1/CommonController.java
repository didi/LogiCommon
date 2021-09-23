package com.didiglobal.logi.security.controller.v1;

import com.didiglobal.logi.security.common.Result;
import com.didiglobal.logi.security.common.constant.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author cjm
 */
@RestController
@Api(value = "common相关API接口", tags = "common相关API接口")
@RequestMapping(Constants.V1 + "/logi-security/common")
public class CommonController {

    @GetMapping("/pubKey")
    @ApiOperation(value = "获取公共密钥", notes = "用于加密登陆信息的RSA公钥")
    public Result<String> pubKey() {
        // 获取公共密钥
        return Result.success();
    }

    @GetMapping("/heart")
    @ApiOperation(value = "http请求测试", notes = "http请求测试")
    public Result<String> health() {
        // 心跳工具
        return Result.success("一个普通的请求响应了普通的结果");
    }
}
