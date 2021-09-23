package com.didiglobal.logi.security.common.enums.resource;

import lombok.Getter;

/**
 * @author cjm
 *
 * 资源管理级别：
 * 0（不具备任何权限）
 * 1（默认，查看权限）
 * 2（管理权限）
 */
@Getter
public enum ControlLevelCode {

    /* 资源管理级别 */
    NONE(0, "无权限"),

    VIEW(1, "查看权限"),

    ADMIN(2, "管理权限");

    private final Integer type;

    private final String info;

    ControlLevelCode(Integer type, String info) {
        this.type = type;
        this.info = info;
    }

    public static ControlLevelCode getByType(Integer type) {
        ControlLevelCode[] controlLevelCodes = ControlLevelCode.values();
        for(ControlLevelCode controlLevelCode : controlLevelCodes) {
            if(controlLevelCode.type.equals(type)) {
                return controlLevelCode;
            }
        }
        return null;
    }
}
