package com.didiglobal.logi.security.common.dto.resource;

import lombok.Data;

/**
 * @author cjm
 *
 * 集群资源信息
 */
@Data
public class ResourceDTO {

    /**
     * 资源id（资源id指的是该资源所在服务对该资源的标识）
     */
    private Integer resourceId;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 所属项目id
     */
    private Integer projectId;

    /**
     * 所属资源类别id
     */
    private Integer resourceTypeId;

    public ResourceDTO() {

    }

    public ResourceDTO(Integer projectId, Integer resourceTypeId, Integer resourceId) {
        this.projectId = projectId;
        this.resourceTypeId = resourceTypeId;
        this.resourceId = resourceId;
    }

}
