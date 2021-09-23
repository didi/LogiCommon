package com.didiglobal.logi.job.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum JobStatusEnum {
    STARTED(0),
    RUNNING(1),
    SUCCEED(2),
    FAILED(3),
    CANCELED(4);

    private static Map<Integer, JobStatusEnum> map = new HashMap<>(8);

    static {
        map.put(STARTED.getValue(), STARTED);
        map.put(SUCCEED.getValue(), SUCCEED);
        map.put(FAILED.getValue(), FAILED);
        map.put(CANCELED.getValue(), CANCELED);
    }

    private Integer value;

    public Integer getValue() {
        return value;
    }

    JobStatusEnum(Integer value) {
        this.value = value;
    }

    public static JobStatusEnum get(Integer value) {
        return map.get(value);
    }
}