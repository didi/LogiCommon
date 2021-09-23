package com.didiglobal.logi.security.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.didiglobal.logi.security.common.entity.UserProject;
import com.didiglobal.logi.security.common.po.UserProjectPO;
import com.didiglobal.logi.security.dao.UserProjectDao;
import com.didiglobal.logi.security.dao.mapper.UserProjectMapper;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cjm
 */
@Component
public class UserProjectDaoImpl extends BaseDaoImpl<UserProjectPO> implements UserProjectDao {

    @Autowired
    private UserProjectMapper userProjectMapper;

    @Override
    public List<Integer> selectUserIdListByProjectId(Integer projectId) {
        if(projectId == null) {
            return new ArrayList<>();
        }
        QueryWrapper<UserProjectPO> queryWrapper = getQueryWrapper();
        queryWrapper.select(FieldConstant.USER_ID).eq(FieldConstant.PROJECT_ID, projectId);
        List<Object> userIdList = userProjectMapper.selectObjs(queryWrapper);
        return userIdList.stream().map(Integer.class::cast).collect(Collectors.toList());
    }

    @Override
    public List<Integer> selectProjectIdListByUserIdList(List<Integer> userIdList) {
        if(CollectionUtils.isEmpty(userIdList)) {
            return new ArrayList<>();
        }
        QueryWrapper<UserProjectPO> queryWrapper = getQueryWrapper();
        queryWrapper.select(FieldConstant.PROJECT_ID).in(FieldConstant.USER_ID, userIdList);
        List<Object> projectIdList = userProjectMapper.selectObjs(queryWrapper);
        return projectIdList.stream().map(Integer.class::cast).collect(Collectors.toList());
    }

    @Override
    public void insertBatch(List<UserProject> userProjectList) {
        if(!CollectionUtils.isEmpty(userProjectList)) {
            List<UserProjectPO> userProjectPOList = CopyBeanUtil.copyList(userProjectList, UserProjectPO.class);
            for(UserProjectPO userProjectPO : userProjectPOList) {
                userProjectMapper.insert(userProjectPO);
            }

        }
    }

    @Override
    public void deleteByProjectId(Integer projectId) {
        QueryWrapper<UserProjectPO> queryWrapper = getQueryWrapper();
        queryWrapper.eq(FieldConstant.PROJECT_ID, projectId);
        userProjectMapper.delete(queryWrapper);
    }
}
