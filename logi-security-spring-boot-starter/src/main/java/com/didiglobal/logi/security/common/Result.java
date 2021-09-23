package com.didiglobal.logi.security.common;

import com.didiglobal.logi.security.common.enums.ResultCode;
import com.didiglobal.logi.security.exception.LogiSecurityException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 非分页统一的返回规范
 *
 * @author cjm
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "统一返回格式")
public class Result<T> extends BaseResult {

    @ApiModelProperty(value = "返回数据")
    protected T data;

    public Result() {}

    private Result(Integer code) {
        this.code = code;
    }

    private Result(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public static <T> Result<T> success(T data) {
        Result<T> ret = new Result<>(ResultCode.SUCCESS.getCode());
        ret.setMessage(ResultCode.SUCCESS.getMessage());
        ret.setData(data);
        return ret;
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        Result<T> ret = new Result<>(resultCode.getCode());
        ret.setMessage(resultCode.getMessage());
        return ret;
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        Result<T> ret = new Result<>(code);
        ret.setMessage(msg);
        return ret;
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> ret = new Result<>(ResultCode.COMMON_FAIL.getCode());
        ret.setMessage(msg);
        return ret;
    }

    public static <T> Result<T> fail(LogiSecurityException e) {
        String[] s = e.getMessage().split("-", 2);
        return Result.fail(Integer.parseInt(s[0]), s[1]);
    }
}
