package com.didiglobal.logi.security.common.dto.resource;

import lombok.Data;

/**
 * @author cjm
 */
@Data
public class UserResourceQueryDTO {

    private int controlLevel;

    private Integer projectId;

    private Integer resourceTypeId;

    private Integer resourceId;

    public UserResourceQueryDTO(int controlLevel, Integer projectId, Integer resourceTypeId, Integer resourceId) {
        this.controlLevel = controlLevel;
        this.projectId = projectId;
        this.resourceTypeId = resourceTypeId;
        this.resourceId = resourceId;
    }

    public UserResourceQueryDTO(int controlLevel, Integer projectId, Integer resourceTypeId) {
        this.controlLevel = controlLevel;
        this.projectId = projectId;
        this.resourceTypeId = resourceTypeId;
        this.resourceId = null;
    }

    public UserResourceQueryDTO(int controlLevel, Integer projectId) {
        this.controlLevel = controlLevel;
        this.projectId = projectId;
        this.resourceTypeId = null;
        this.resourceId = null;
    }

    public static UserResourceQueryDTO getOpenViewPermissionControlQueryEntity() {
        // 构造查看控制权限的条件实体（全为0）
        return new UserResourceQueryDTO(0, 0, 0, 0);
    }
}
