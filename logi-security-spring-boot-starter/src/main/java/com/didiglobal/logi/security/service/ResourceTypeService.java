package com.didiglobal.logi.security.service;

import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.dto.resource.type.ResourceTypeQueryDTO;
import com.didiglobal.logi.security.common.vo.resource.ResourceTypeVO;

import java.util.List;

/**
 * @author cjm
 */
public interface ResourceTypeService {

    /**
     * 获取所有资源类型list
     * @return 资源类型信息list
     */
    List<ResourceTypeVO> getAllResourceTypeList();

    /**
     * 获取所有资源类型IdList
     * @return 资源类型IdList
     */
    List<Integer> getAllResourceTypeIdList();

    /**
     * 分页获取资源类型
     * @param queryDTO 查询条件
     * @return 资源类型List
     */
    PagingData<ResourceTypeVO> getResourceTypePage(ResourceTypeQueryDTO queryDTO);

    /**
     * 根据资源类别id，获取资源类别信息
     * @param resourceTypeId 资源类别id
     * @return 资源类别信息
     */
    ResourceTypeVO getResourceTypeByResourceTypeId(Integer resourceTypeId);

    /**
     * 保存资源类型
     * @param resourceTypeNameList 资源类型名List
     */
    void saveResourceType(List<String> resourceTypeNameList);
}
