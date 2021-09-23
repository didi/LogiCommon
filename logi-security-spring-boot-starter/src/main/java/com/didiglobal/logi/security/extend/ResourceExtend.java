package com.didiglobal.logi.security.extend;

import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.dto.resource.ResourceDTO;

import java.util.List;

/**
 * @author cjm
 *
 * 资源扩展接口
 */
public interface ResourceExtend {

    /**
     * 获取资源信息List，资源id指的是该资源所在服务对该资源的标识
     * @param projectId 项目id（可为null）
     * @param resourceTypeId 资源类型id（可为null，不为null则projectId必不为null）
     * @param resourceName 资源名称（可为null，模糊查询条件）
     * @param page 当前页（分页条件）
     * @param size 页大小（分页条件）
     * @return 资源信息List
     */
    PagingData<ResourceDTO> getResourcePage(
            Integer projectId, Integer resourceTypeId, String resourceName, int page, int size
    );

    /**
     * 获取资源信息List，资源id指的是该资源所在服务对该资源的标识
     * @param projectId 项目id（可为null）
     * @param resourceTypeId 资源类型id（可为null，不为null则projectId必不为null）
     * @return 资源信息List
     */
    List<ResourceDTO> getResourceList(Integer projectId, Integer resourceTypeId);

    /**
     * 获取具体资源个数，资源id指的是该资源所在服务对该资源的标识
     * @param projectId 项目id（可为null）
     * @param resourceTypeId 资源类型id（可为null，不为null则projectId必不为null）
     * @return 资源信息List
     */
    int getResourceCnt(Integer projectId, Integer resourceTypeId);
}
