package com.didiglobal.logi.security.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.didiglobal.logi.security.common.entity.Permission;
import com.didiglobal.logi.security.common.po.PermissionPO;
import com.didiglobal.logi.security.dao.PermissionDao;
import com.didiglobal.logi.security.dao.mapper.PermissionMapper;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author cjm
 */
@Component
public class PermissionDaoImpl extends BaseDaoImpl<PermissionPO> implements PermissionDao {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectAllAndAscOrderByLevel() {
        QueryWrapper<PermissionPO> queryWrapper = getQueryWrapper();
        queryWrapper.orderByAsc(FieldConstant.LEVEL);
        return CopyBeanUtil.copyList(permissionMapper.selectList(queryWrapper), Permission.class);
    }

    @Override
    public void insertBatch(List<Permission> permissionList) {
        if(CollectionUtils.isEmpty(permissionList)) {
            return;
        }
        List<PermissionPO> permissionPOList = CopyBeanUtil.copyList(permissionList, PermissionPO.class);
        for(PermissionPO permissionPO : permissionPOList) {
            permissionMapper.insert(permissionPO);
        }

    }
}
