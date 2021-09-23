package com.didiglobal.logi.security.service.impl;

import com.didiglobal.logi.security.common.entity.RolePermission;
import com.didiglobal.logi.security.dao.RolePermissionDao;
import com.didiglobal.logi.security.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjm
 */
@Service("logiSecurityRolePermissionServiceImpl")
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public void saveRolePermission(Integer roleId, List<Integer> permissionIdList) {
        if(roleId == null || CollectionUtils.isEmpty(permissionIdList)) {
            return;
        }
        // 插入新的关联信息
        rolePermissionDao.insertBatch(getRolePermissionList(roleId, permissionIdList));
    }

    @Override
    public void updateRolePermission(Integer roleId, List<Integer> permissionIdList) {
        // 先删除old的关联信息
        deleteRolePermissionByRoleId(roleId);
        // 插入新的关联信息
        saveRolePermission(roleId, permissionIdList);
    }

    @Override
    public void deleteRolePermissionByRoleId(Integer roleId) {
        if(roleId == null) {
            return;
        }
        rolePermissionDao.deleteByRoleId(roleId);
    }

    @Override
    public List<Integer> getPermissionIdListByRoleId(Integer roleId) {
        if(roleId == null) {
            return new ArrayList<>();
        }
        return rolePermissionDao.selectPermissionIdListByRoleId(roleId);
    }

    @Override
    public List<Integer> getPermissionIdListByRoleIdList(List<Integer> roleIdList) {
        if(CollectionUtils.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }
        return rolePermissionDao.selectPermissionIdListByRoleIdList(roleIdList);
    }

    /**
     * 用于构建可以直接插入角色与权限中间表的数据
     * @param roleId 角色Id
     * @param permissionIdList 权限idList
     * @return List<RolePermission>
     */
    private List<RolePermission> getRolePermissionList(Integer roleId, List<Integer> permissionIdList) {
        List<RolePermission> rolePermissionList = new ArrayList<>();
        for(Integer permissionId : permissionIdList) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }
        return rolePermissionList;
    }
}
