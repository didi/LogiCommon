package com.didiglobal.logi.security.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.didiglobal.logi.security.common.dto.resource.ControlLevelQueryDTO;
import com.didiglobal.logi.security.common.dto.resource.UserResourceQueryDTO;
import com.didiglobal.logi.security.common.entity.UserResource;
import com.didiglobal.logi.security.common.enums.resource.ControlLevelCode;
import com.didiglobal.logi.security.common.po.UserResourcePO;
import com.didiglobal.logi.security.dao.UserResourceDao;
import com.didiglobal.logi.security.dao.mapper.UserResourceMapper;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cjm
 */
@Component
public class UserResourceDaoImpl extends BaseDaoImpl<UserResourcePO> implements UserResourceDao {

    @Autowired
    private UserResourceMapper userResourceMapper;

    /**
     * 封装UserResource的查询条件
     * @param userId 用户id
     * @param queryDTO 查询条件
     * @return QueryWrapper<UserResourcePO>
     */
    private QueryWrapper<UserResourcePO> wrapQueryCriteria(Integer userId, UserResourceQueryDTO queryDTO) {
        QueryWrapper<UserResourcePO> queryWrapper = getQueryWrapper();
        Integer resourceTypeId = queryDTO.getResourceTypeId();
        queryWrapper
                .eq(FieldConstant.CONTROL_LEVEL, queryDTO.getControlLevel())
                .eq(userId != null, FieldConstant.USER_ID, userId)
                .eq(queryDTO.getProjectId() != null, FieldConstant.PROJECT_ID, queryDTO.getProjectId())
                .eq(resourceTypeId != null, FieldConstant.RESOURCE_TYPE_ID, resourceTypeId)
                .eq(queryDTO.getResourceId() != null, FieldConstant.RESOURCE_ID, queryDTO.getResourceId());
        return queryWrapper;
    }

    @Override
    public int selectCountByUserId(Integer userId, UserResourceQueryDTO queryDTO) {
        return userResourceMapper.selectCount(wrapQueryCriteria(userId, queryDTO));
    }

    @Override
    public void deleteByUserId(Integer userId, UserResourceQueryDTO queryDTO) {
        userResourceMapper.delete(wrapQueryCriteria(userId, queryDTO));
    }

    @Override
    public void deleteByControlLevel(ControlLevelCode controlLevel) {
        QueryWrapper<UserResourcePO> queryWrapper = getQueryWrapper();
        queryWrapper.eq(FieldConstant.CONTROL_LEVEL, controlLevel.getType());
        userResourceMapper.delete(queryWrapper);
    }

    @Override
    public void insert(UserResource userResource) {
        userResourceMapper.insert(CopyBeanUtil.copy(userResource, UserResourcePO.class));
    }

    @Override
    public void insertBatch(List<UserResource> userResourceList) {
        if(CollectionUtils.isEmpty(userResourceList)) {
            return;
        }
        List<UserResourcePO> userResourcePOList = CopyBeanUtil.copyList(userResourceList, UserResourcePO.class);
        for(UserResourcePO userResourcePO : userResourcePOList) {
            userResourceMapper.insert(userResourcePO);
        }

    }

    @Override
    public void deleteByUserIdList(List<Integer> userIdList, UserResourceQueryDTO queryDTO) {
        QueryWrapper<UserResourcePO> queryWrapper = wrapQueryCriteria(null, queryDTO);
        queryWrapper.in(FieldConstant.USER_ID, userIdList);
        userResourceMapper.delete(queryWrapper);
    }

    @Override
    public void deleteByProjectIdList(List<Integer> projectIdList, UserResourceQueryDTO queryDTO) {
        QueryWrapper<UserResourcePO> queryWrapper = wrapQueryCriteria(null, queryDTO);
        queryWrapper.in(FieldConstant.PROJECT_ID, projectIdList);
        userResourceMapper.delete(queryWrapper);
    }

    @Override
    public void deleteByResourceTypeIdList(List<Integer> resourceTypeIdList, UserResourceQueryDTO queryDTO) {
        QueryWrapper<UserResourcePO> queryWrapper = wrapQueryCriteria(null, queryDTO);
        queryWrapper.in(FieldConstant.RESOURCE_TYPE_ID, resourceTypeIdList);
        userResourceMapper.delete(queryWrapper);
    }

    @Override
    public void deleteByResourceIdList(List<Integer> resourceIdList, UserResourceQueryDTO queryDTO) {
        QueryWrapper<UserResourcePO> queryWrapper = wrapQueryCriteria(null, queryDTO);
        queryWrapper.in(FieldConstant.RESOURCE_ID, resourceIdList);
        userResourceMapper.delete(queryWrapper);
    }

    @Override
    public int selectCountByUserIdAndControlLevel(Integer userId, ControlLevelCode controlLevel) {
        QueryWrapper<UserResourcePO> queryWrapper = getQueryWrapper();
        queryWrapper
                .eq(userId != null, FieldConstant.USER_ID, userId)
                .eq(FieldConstant.CONTROL_LEVEL, controlLevel.getType());
        return userResourceMapper.selectCount(queryWrapper);
    }

    @Override
    public int selectCount(UserResourceQueryDTO queryDTO) {
        return userResourceMapper.selectCount(wrapQueryCriteria(null, queryDTO));
    }

    @Override
    public List<Integer> selectResourceIdListByUserId(Integer userId, UserResourceQueryDTO queryDTO) {
        QueryWrapper<UserResourcePO> queryWrapper = wrapQueryCriteria(userId, queryDTO);
        queryWrapper.select(FieldConstant.RESOURCE_ID);
        List<Object> resourceIdList = userResourceMapper.selectObjs(queryWrapper);
        return resourceIdList.stream().map(Integer.class::cast).collect(Collectors.toList());
    }

    @Override
    public void deleteWithoutUserIdList(UserResourceQueryDTO queryDTO, List<Integer> excludeUserIdList) {
        QueryWrapper<UserResourcePO> queryWrapper = wrapQueryCriteria(null, queryDTO);
        if(!CollectionUtils.isEmpty(excludeUserIdList)) {
            queryWrapper.notIn(FieldConstant.USER_ID, excludeUserIdList);
        }
        userResourceMapper.delete(queryWrapper);
    }

    @Override
    public void deleteByUserIdWithoutProjectIdList(Integer userId, UserResourceQueryDTO queryDTO,
                                                   List<Integer> excludeIdList) {
        QueryWrapper<UserResourcePO> queryWrapper = wrapQueryCriteria(userId, queryDTO);
        if(!CollectionUtils.isEmpty(excludeIdList)) {
            queryWrapper.notIn(FieldConstant.PROJECT_ID, excludeIdList);
        }
        userResourceMapper.delete(queryWrapper);
    }

    @Override
    public void deleteByUserIdWithoutResourceTypeIdList(Integer userId, UserResourceQueryDTO queryDTO,
                                                        List<Integer> excludeIdList) {
        QueryWrapper<UserResourcePO> queryWrapper = wrapQueryCriteria(userId, queryDTO);
        if(!CollectionUtils.isEmpty(excludeIdList)) {
            queryWrapper.notIn(FieldConstant.RESOURCE_TYPE_ID, excludeIdList);
        }
        userResourceMapper.delete(queryWrapper);
    }

    @Override
    public int selectCountGroupByUserId(UserResourceQueryDTO queryDTO) {
        QueryWrapper<UserResourcePO> queryWrapper = wrapQueryCriteria(null, queryDTO);
        queryWrapper.select("COUNT(*)").groupBy(FieldConstant.USER_ID);
        return userResourceMapper.selectObjs(queryWrapper).size();
    }

    @Override
    public List<Integer> selectUserIdListGroupByUserId(UserResourceQueryDTO queryDTO) {
        QueryWrapper<UserResourcePO> queryWrapper = wrapQueryCriteria(null, queryDTO);
        queryWrapper.select(FieldConstant.USER_ID).groupBy(FieldConstant.USER_ID);
        // 查询后，List<Object> -> List<Integer>
        return userResourceMapper.selectObjs(queryWrapper)
                .stream().map(Integer.class::cast).collect(Collectors.toList());
    }

    @Override
    public Integer selectControlLevel(ControlLevelQueryDTO queryDTO) {
        QueryWrapper<UserResourcePO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("MAX(" + FieldConstant.CONTROL_LEVEL + ")")
                .eq(FieldConstant.USER_ID, queryDTO.getUserId())
                .eq(FieldConstant.PROJECT_ID, queryDTO.getProjectId())
                .eq(FieldConstant.RESOURCE_TYPE_ID, queryDTO.getResourceTypeId())
                .eq(FieldConstant.RESOURCE_ID, queryDTO.getResourceId());
        List<Object> objects = userResourceMapper.selectObjs(queryWrapper);
        return (Integer) objects.get(0);
    }
}
