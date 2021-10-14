package com.didiglobal.logi.log.common.api;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author jinbinbin
 * @version $Id: Result.java, v 0.1 2018年01月08日 20:20 jinbinbin Exp $
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -2772975319944108658L;

    private T                 data;
    private String            message;
    private Integer           code;
    private String            errmsg;

    public Result(T data){
        this.data = data;
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
    }

    public Result(){
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
    }

    public Result(Integer code, String message){
        this.message = message;
        this.code = code;
    }

    public Result(Integer code, T data, String message){
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public Result(ResultCodeEntry resultCodeEntry, T data){
        this.data = data;
        this.message = resultCodeEntry.getMessage();
        this.code = resultCodeEntry.getCode();
    }

    public Result(ResultCodeEntry resultCodeEntry){
        this.message = resultCodeEntry.getMessage();
        this.code = resultCodeEntry.getCode();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
