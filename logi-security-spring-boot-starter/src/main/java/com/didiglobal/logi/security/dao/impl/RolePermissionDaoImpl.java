package com.didiglobal.logi.security.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.didiglobal.logi.security.common.entity.RolePermission;
import com.didiglobal.logi.security.common.po.RolePermissionPO;
import com.didiglobal.logi.security.dao.RolePermissionDao;
import com.didiglobal.logi.security.dao.mapper.RolePermissionMapper;
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
public class RolePermissionDaoImpl extends BaseDaoImpl<RolePermissionPO> implements RolePermissionDao {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void insertBatch(List<RolePermission> rolePermissionList) {
        if(CollectionUtils.isEmpty(rolePermissionList)) {
            return;
        }
        List<RolePermissionPO> rolePermissionPOList = CopyBeanUtil.copyList(rolePermissionList, RolePermissionPO.class);
        for(RolePermissionPO rolePermissionPO : rolePermissionPOList) {
            rolePermissionMapper.insert(rolePermissionPO);
        }

    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        if (roleId == null) {
            return;
        }
        QueryWrapper<RolePermissionPO> queryWrapper = getQueryWrapper();
        queryWrapper.eq(FieldConstant.ROLE_ID, roleId);
        rolePermissionMapper.delete(queryWrapper);
    }

    @Override
    public List<Integer> selectPermissionIdListByRoleId(Integer roleId) {
        if(roleId == null) {
            return new ArrayList<>();
        }
        List<Integer> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        return selectPermissionIdListByRoleIdList(roleIdList);
    }

    @Override
    public List<Integer> selectPermissionIdListByRoleIdList(List<Integer> roleIdList) {
        if(CollectionUtils.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }
        QueryWrapper<RolePermissionPO> queryWrapper = getQueryWrapper();
        queryWrapper.select(FieldConstant.PERMISSION_ID).in(FieldConstant.ROLE_ID, roleIdList);
        List<Object> permissionIdList = rolePermissionMapper.selectObjs(queryWrapper);
        return permissionIdList.stream().map(Integer.class::cast).collect(Collectors.toList());
    }
}
