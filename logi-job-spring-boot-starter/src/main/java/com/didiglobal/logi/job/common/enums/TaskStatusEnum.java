package com.didiglobal.logi.job.common.enums;

public enum TaskStatusEnum {
    STOP(0),
    RUNNING(1);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    TaskStatusEnum(Integer value) {
        this.value = value;
    }

    public static boolean isValid(Integer status) {
        for (TaskStatusEnum taskStatusEnum : TaskStatusEnum.values()) {
            if (status.intValue() == taskStatusEnum.value.intValue()) {
                return true;
            }
        }

        return false;
    }
}
