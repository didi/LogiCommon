package com.didiglobal.logi.log.common.api;

/**
 * @author jinbinbin
 * @version $Id: ResultFactory.java, v 0.1 2018年01月10日 16:07 jinbinbin Exp $
 */
public class ResultFactory {

    public static <T> Result<T> buildSuccessResult(T t) {
        return new Result<T>(ResultCode.SUCCESS, t);
    }

    public static <T> Result<T> buildSuccessResult() {
        return new Result<T>(ResultCode.SUCCESS, null);
    }

    public static <T> Result<T> buildIllegalParamResult(T t) {
        return new Result<T>(ResultCode.ILLEGAL_PARAM, t);
    }

    public static <T> Result<T> buildIllegalParamResult() {
        return new Result<T>(ResultCode.ILLEGAL_PARAM, null);
    }

    public static <T> Result<T> buildResourceNotReadyResult(T t) {
        return new Result<T>(ResultCode.RESOURCE_NOT_READY, t);
    }

    public static <T> Result<T> buildResourceNotReadyResult() {
        return new Result<T>(ResultCode.RESOURCE_NOT_READY, null);
    }

    public static <T> Result<T> buildResourceApprovingResult(T t) {
        return new Result<T>(ResultCode.RESOURCE_APPROVING, t);
    }

    public static <T> Result<T> buildResourceApprovingResult() {
        return new Result<T>(ResultCode.RESOURCE_APPROVING, null);
    }

    public static <T> Result<T> buildFailRequestResult(ResultCodeEntry resultCodeEntry) {
        return new Result<T>(resultCodeEntry);
    }

}
