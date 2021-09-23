package com.didiglobal.logi.security.dao;

import com.didiglobal.logi.security.common.entity.Permission;

import java.util.List;

/**
 * @author cjm
 */
public interface PermissionDao {

    /**
     * 获取全部权限，并根据level升序排序
     * @return 权限List
     */
    List<Permission> selectAllAndAscOrderByLevel();

    /**
     * 批量插入
     * @param permissionList 权限信息List
     */
    void insertBatch(List<Permission> permissionList);
}
