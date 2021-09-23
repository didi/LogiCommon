package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.dto.resource.ResourceDTO;
import com.didiglobal.logi.security.extend.ResourceExtend;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import com.common.po.ProjectResourcePO;
import com.mapper.ProjectResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author cjm
 */
@Component("myResourceExtendImpl")
public class ResourceExtendImpl implements ResourceExtend {

    @Autowired
    private ProjectResourceMapper projectResourceMapper;

    private static final String PROJECT_ID = "project_id";
    private static final String RESOURCE_TYPE_ID = "resource_type_id";
    private static final String RESOURCE_NAME = "resource_name";

    @Override
    public PagingData<ResourceDTO> getResourcePage(Integer projectId, Integer resourceTypeId,
                                                   String resourceName, int page, int size) {

        IPage<ProjectResourcePO> pageInfo = new Page<>(page, size);
        QueryWrapper<ProjectResourcePO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(projectId != null, PROJECT_ID, projectId)
                .eq(resourceTypeId != null, RESOURCE_TYPE_ID, resourceTypeId)
                .like(!StringUtils.isEmpty(resourceName), RESOURCE_NAME, resourceName);
        projectResourceMapper.selectPage(pageInfo, queryWrapper);
        List<ResourceDTO> resourceDTOList = CopyBeanUtil.copyList(pageInfo.getRecords(), ResourceDTO.class);
        return new PagingData<>(resourceDTOList, pageInfo);
    }

    @Override
    public List<ResourceDTO> getResourceList(Integer projectId, Integer resourceTypeId) {
        QueryWrapper<ProjectResourcePO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(projectId != null, PROJECT_ID, projectId)
                .eq(resourceTypeId != null, RESOURCE_TYPE_ID, resourceTypeId);
        List<ProjectResourcePO> projectResourcePOList = projectResourceMapper.selectList(queryWrapper);
        return CopyBeanUtil.copyList(projectResourcePOList, ResourceDTO.class);
    }

    @Override
    public int getResourceCnt(Integer projectId, Integer resourceTypeId) {
        QueryWrapper<ProjectResourcePO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(projectId != null, PROJECT_ID, projectId)
                .eq(resourceTypeId != null, RESOURCE_TYPE_ID, resourceTypeId);
        return projectResourceMapper.selectCount(queryWrapper);
    }
}
