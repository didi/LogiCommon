package com.didiglobal.logi.security.common.entity;

import com.didiglobal.logi.security.common.dto.resource.ResourceDTO;
import lombok.Data;

/**
 * @author cjm
 *
 * 用户资源关系
 * 1.如果不启用资源查看控制权限，则所有人默认都具有所有资源的查看控制权限；
 *      此时该实体对应的表，只记录管理控制权限
 * 2.如果启用资源查看控制权限，则所有人默认都不具有所有资源的查看控制权限；
 *      此时该实体对应的表，不仅要只记录管理控制权限，还要记录查看权限和不具备任何权限
 */
@Data
public class UserResource {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 资源类别Id
     */
    private Integer resourceTypeId;

    /**
     * 资源id
     */
    private Integer resourceId;

    /**
     * 资源管理级别：
     * 1（查看权限）
     * 2（管理权限）
     */
    private Integer controlLevel;

    public UserResource(ResourceDTO resourceDTO) {
        this.projectId = resourceDTO.getProjectId();
        this.resourceTypeId = resourceDTO.getResourceTypeId();
        this.resourceId = resourceDTO.getResourceId();
    }

    public UserResource() {}

    public UserResource(Integer userId, Integer projectId, Integer resourceTypeId,
                        Integer resourceId, Integer controlLevel) {
        this.userId = userId;
        this.projectId = projectId;
        this.resourceTypeId = resourceTypeId;
        this.resourceId = resourceId;
        this.controlLevel = controlLevel;
    }

    public static UserResource getOpenViewPermissionControlEntity() {
        // 构造查看控制权限，是否开启的标记实体（全为0）
        return new UserResource(0, 0, 0, 0, 0);
    }
}
