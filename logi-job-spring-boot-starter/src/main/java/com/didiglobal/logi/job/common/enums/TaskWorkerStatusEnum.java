package com.didiglobal.logi.job.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum TaskWorkerStatusEnum {
    WAITING(1),
    RUNNING(2),
    STOPPED(3);

    private static Map<Integer, TaskWorkerStatusEnum> map = new HashMap<>(8);

    static {
        map.put(WAITING.getValue(), WAITING);
        map.put(RUNNING.getValue(), RUNNING);
        map.put(STOPPED.getValue(), STOPPED);
    }

    private Integer value;

    public Integer getValue() {
        return value;
    }

    TaskWorkerStatusEnum(Integer value) {
        this.value = value;
    }

    public static TaskWorkerStatusEnum get(Integer value) {
        return map.get(value);
    }
}