package com.didiglobal.logi.log.common.api;

/**
 * @author jinbinbin
 * @version $Id: ResultCodeEntry.java, v 0.1 2018年01月10日 16:01 jinbinbin Exp $
 */
public class ResultCodeEntry {

    private int    code;
    private String message;

    public ResultCodeEntry(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "{\"ResultCodeEntry\":{" + "\"code\":\"" + code + "\"" + "," + "\"message\":\"" + message + "\"" + "}}";
    }
}
