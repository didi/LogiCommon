package com.didiglobal.logi.security.common.enums.resource;

import lombok.Getter;

/**
 * @author cjm
 *
 * 权限拥有级别，例如用户A拥有项目1下的部分资源的管理或者查看权限
 * 则拥有级别level为：1 半拥有
 *
 * （0 不拥有、1 半拥有、2 全拥有）
 */
@Getter
public enum HasLevelCode {

    /* 权限拥有级别 */
    NONE(0, "不拥有"),

    HALF(1, "半拥有"),

    ALL(2, "全拥有");

    private final Integer type;

    private final String info;

    HasLevelCode(Integer type, String info) {
        this.type = type;
        this.info = info;
    }

    public static HasLevelCode getByType(Integer type) {
        HasLevelCode[] hasLevelCodes = HasLevelCode.values();
        for(HasLevelCode hasLevelCode : hasLevelCodes) {
            if(hasLevelCode.type.equals(type)) {
                return hasLevelCode;
            }
        }
        return null;
    }
}
