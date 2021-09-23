package com.didiglobal.logi.security.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.didiglobal.logi.security.common.dto.resource.type.ResourceTypeQueryDTO;
import com.didiglobal.logi.security.common.entity.ResourceType;

import java.util.List;

/**
 * @author cjm
 */
public interface ResourceTypeDao {

    /**
     * 获取全部资源类别
     * @return 资源类别List
     */
    List<ResourceType> selectAll();

    /**
     * 分页获取资源类别
     * @param queryDTO 查询条件
     * @return 资源类别page
     */
    IPage<ResourceType> selectPage(ResourceTypeQueryDTO queryDTO);

    /**
     * 根据资源类别id，查询资源类别信息
     * @param resourceTypeId 资源类别id
     * @return 资源类别信息
     */
    ResourceType selectByResourceTypeId(Integer resourceTypeId);

    /**
     * 批量插入
     * @param resourceTypeList 资源类别List
     */
    void insertBatch(List<ResourceType> resourceTypeList);
}
