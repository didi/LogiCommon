package com.didiglobal.logi.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.dto.resource.type.ResourceTypeQueryDTO;
import com.didiglobal.logi.security.common.entity.ResourceType;
import com.didiglobal.logi.security.common.vo.resource.ResourceTypeVO;
import com.didiglobal.logi.security.dao.ResourceTypeDao;
import com.didiglobal.logi.security.service.ResourceTypeService;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjm
 */
@Service("logiSecurityResourceTypeServiceImpl")
public class ResourceTypeServiceImpl implements ResourceTypeService {

    @Autowired
    private ResourceTypeDao resourceTypeDao;

    @Override
    public List<ResourceTypeVO> getAllResourceTypeList() {
        List<ResourceType> resourceTypeList = resourceTypeDao.selectAll();
        return CopyBeanUtil.copyList(resourceTypeList, ResourceTypeVO.class);
    }

    @Override
    public List<Integer> getAllResourceTypeIdList() {
        List<ResourceType> resourceTypeList = resourceTypeDao.selectAll();
        List<Integer> result = new ArrayList<>();
        for(ResourceType resourceType : resourceTypeList) {
            result.add(resourceType.getId());
        }
        return result;
    }

    @Override
    public PagingData<ResourceTypeVO> getResourceTypePage(ResourceTypeQueryDTO queryDTO) {
        IPage<ResourceType> pageInfo = resourceTypeDao.selectPage(queryDTO);
        List<ResourceTypeVO> list = CopyBeanUtil.copyList(pageInfo.getRecords(), ResourceTypeVO.class);
        return new PagingData<>(list, pageInfo);
    }

    @Override
    public ResourceTypeVO getResourceTypeByResourceTypeId(Integer resourceTypeId) {
        if(resourceTypeId == null) {
            return null;
        }
        ResourceType resourceType = resourceTypeDao.selectByResourceTypeId(resourceTypeId);
        return CopyBeanUtil.copy(resourceType, ResourceTypeVO.class);
    }

    @Override
    public void saveResourceType(List<String> resourceTypeNameList) {
        List<ResourceType> resourceTypeList = new ArrayList<>();
        for(String resourceName : resourceTypeNameList) {
            ResourceType resourceType = new ResourceType();
            resourceType.setTypeName(resourceName);
            resourceTypeList.add(resourceType);
        }
        resourceTypeDao.insertBatch(resourceTypeList);
    }
}
