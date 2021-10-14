package com.didiglobal.logi.security.common.enums.resource;

import lombok.Getter;

/**
 * @author cjm
 *
 * 这个主要是为了服务：
 * 资源权限管理 / 按资源管理 / 列表展示 / 管理权限用户数、查看权限用户数
 */
@Getter
public enum ShowLevelCode {

    /**
     * 项目级别：具体某个项目的管理权限用户数、查看权限用户数
     */
    PROJECT(1, "项目级别"),

    /**
     * 资源类别级别：具体某个项目下的某个资源类别的管理权限用户数、查看权限用户数
     */
    RESOURCE_TYPE(2, "资源类别级别"),

    /**
     * 资源级别：具体某个项目下的某个资源类别的某个资源的管理权限用户数、查看权限用户数
     */
    RESOURCE(3, "资源级别");

    private final Integer type;

    private final String info;

    ShowLevelCode(Integer type, String info) {
        this.type = type;
        this.info = info;
    }

    public static ShowLevelCode getByType(Integer type) {
        ShowLevelCode[] showLevelCodes = ShowLevelCode.values();
        for(ShowLevelCode showLevelCode : showLevelCodes) {
            if(showLevelCode.type.equals(type)) {
                return showLevelCode;
            }
        }
        return null;
    }
}
