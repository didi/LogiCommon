package com.didiglobal.logi.job.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskResult implements Serializable {

    public static final long serialVersionUID = 42L;

    private int code;
    private String message;

    public static final int SUCCESS_CODE = 1;
    public static final int RUNNING_CODE = 0;
    public static final int FAIL_CODE = -1;

    public static final TaskResult SUCCESS = new TaskResult(SUCCESS_CODE, "scuucessed");
    public static final TaskResult FAIL = new TaskResult(FAIL_CODE, "failed");

    public TaskResult() {
    }

    public TaskResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
