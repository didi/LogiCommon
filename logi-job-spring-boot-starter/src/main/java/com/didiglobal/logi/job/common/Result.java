package com.didiglobal.logi.job.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by limeng on 2020-04-27.
 */
@Data
public class Result<T> extends BaseResult implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Result.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final long serialVersionUID = 3472961240718956029L;

    private T data;

    private String tips;

    public Result() {
    }

    public boolean success() {
        return getCode() != null && ResultType.SUCCESS.getCode() == getCode();
    }

    public boolean duplicate() {
        return getCode() != null && ResultType.DUPLICATION.getCode() == getCode();
    }

    public boolean failed() {
        return !success();
    }

    @Override
    public String toString() {
        String ret = "null";
        try {
            ret = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.error("", e);
        }
        return ret;
    }

    /**
     *
     * @param resultType 返回信息枚举
     * @return Result
     */
    public static Result build(ResultType resultType) {
        Result result = new Result();
        result.setCode(resultType.getCode());
        result.setMessage(resultType.getMessage());
        return result;
    }

    /**
     *
     * @param code 编号
     * @param msg 消息
     * @return Result
     */
    public static Result build(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    /**
     *
     * @param code 返回编号
     * @param msg 返回消息
     * @param data 返回数据
     * @param <T> 返回对象类型
     * @return Result
     */
    public static <T> Result<T> build(int code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    /**
     *
     * @param succ 是否成功
     * @param data 数据
     * @param <T> 对象类型
     * @return Result
     */
    public static <T> Result<T> build(boolean succ, T data) {
        Result<T> result = new Result<>();
        if (succ) {
            result.setCode(ResultType.SUCCESS.getCode());
            result.setMessage(ResultType.SUCCESS.getMessage());
            result.setData(data);
        } else {
            result.setCode(ResultType.FAIL.getCode());
            result.setMessage(ResultType.FAIL.getMessage());
        }
        return result;
    }

    /**

     * @param succ 是否成功
     * @return Result
     */
    public static Result build(boolean succ) {
        if (succ) {
            return buildSucc();
        }
        return buildFail();
    }

    /**
     *
     * @param msg 消息
     * @return Result
     */
    public static Result buildSucc(String msg) {
        Result result = new Result();
        result.setCode(ResultType.SUCCESS.getCode());
        result.setMessage(msg);
        return result;
    }

    /**
     *
     * @return Result
     */
    public static Result buildSucc() {
        Result result = new Result();
        result.setCode(ResultType.SUCCESS.getCode());
        result.setMessage(ResultType.SUCCESS.getMessage());
        return result;
    }

    /**
     *
     * @param data 数据
     * @param <T> 类型
     * @return Result
     */
    public static <T> Result<T> buildSucc(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultType.SUCCESS.getCode());
        result.setMessage(ResultType.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     *
     * @param data 数据
     * @param msg 消息
     * @param <T> 类型
     * @return Result
     */
    public static <T> Result<T> buildSucc(T data, String msg) {
        Result<T> result = new Result<>();
        result.setCode(ResultType.SUCCESS.getCode());
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    /**
     *
     * @param failMsg 错误信息
     * @return Result
     */
    public static Result buildFail(String failMsg) {
        Result result = new Result();
        result.setCode(ResultType.FAIL.getCode());
        result.setMessage(failMsg);
        return result;
    }

    /**
     * @return Result
     */
    public static Result buildFail() {
        Result result = new Result();
        result.setCode(ResultType.FAIL.getCode());
        result.setMessage(ResultType.FAIL.getMessage());
        return result;
    }

    /**
     *
     * @param msg 消息
     * @return Result
     */
    public static Result buildParamIllegal(String msg) {
        Result result = new Result();
        result.setCode(ResultType.ILLEGAL_PARAMS.getCode());
        result.setMessage(msg);
        return result;
    }

    /**
     *
     * @param result 源数据
     * @param <T> 目标类型
     * @return 目标
     */
    public static <T> Result<T> buildFrom(Result result) {
        Result<T> resultT = new Result<>();
        resultT.setCode(result.getCode());
        resultT.setMessage(result.getMessage());
        return resultT;
    }
}
