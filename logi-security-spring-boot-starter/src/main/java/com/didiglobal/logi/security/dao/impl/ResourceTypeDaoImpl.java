package com.didiglobal.logi.security.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.didiglobal.logi.security.common.dto.resource.type.ResourceTypeQueryDTO;
import com.didiglobal.logi.security.common.entity.ResourceType;
import com.didiglobal.logi.security.common.po.ResourceTypePO;
import com.didiglobal.logi.security.dao.ResourceTypeDao;
import com.didiglobal.logi.security.dao.mapper.ResourceTypeMapper;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author cjm
 */
@Component
public class ResourceTypeDaoImpl extends BaseDaoImpl<ResourceTypePO> implements ResourceTypeDao {

    @Autowired
    private ResourceTypeMapper resourceTypeMapper;

    @Override
    public List<ResourceType> selectAll() {
        return CopyBeanUtil.copyList(resourceTypeMapper.selectList(null), ResourceType.class);
    }

    @Override
    public IPage<ResourceType> selectPage(ResourceTypeQueryDTO queryDTO) {
        IPage<ResourceTypePO> pageInfo = new Page<>(queryDTO.getPage(), queryDTO.getSize());
        QueryWrapper<ResourceTypePO> queryWrapper = getQueryWrapper();
        String typeName = queryDTO.getTypeName();
        queryWrapper.like(!StringUtils.isEmpty(typeName), FieldConstant.TYPE_NAME, typeName);
        resourceTypeMapper.selectPage(pageInfo, queryWrapper);
        return CopyBeanUtil.copyPage(pageInfo, ResourceType.class);
    }

    @Override
    public ResourceType selectByResourceTypeId(Integer resourceTypeId) {
        QueryWrapper<ResourceTypePO> queryWrapper = getQueryWrapper();
        queryWrapper.eq(FieldConstant.ID, resourceTypeId);
        return CopyBeanUtil.copy(resourceTypeMapper.selectOne(queryWrapper), ResourceType.class);
    }

    @Override
    public void insertBatch(List<ResourceType> resourceTypeList) {
        if(CollectionUtils.isEmpty(resourceTypeList)) {
            return;
        }
        List<ResourceTypePO> resourceTypePOList = CopyBeanUtil.copyList(resourceTypeList, ResourceTypePO.class);
        for(ResourceTypePO resourceTypePO : resourceTypePOList) {
            resourceTypeMapper.insert(resourceTypePO);
        }

    }
}
