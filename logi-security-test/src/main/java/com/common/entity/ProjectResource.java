package com.common.entity;

import lombok.Data;

/**
 * @author cjm
 */
@Data
public class ProjectResource {

    private Integer projectId;

    private Integer resourceTypeId;

    private Integer resourceId;

    private String resourceName;
}
