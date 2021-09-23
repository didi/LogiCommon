package com.didiglobal.logi.security.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.didiglobal.logi.security.common.dto.role.RoleQueryDTO;
import com.didiglobal.logi.security.common.entity.role.Role;
import com.didiglobal.logi.security.common.entity.role.RoleBrief;
import com.didiglobal.logi.security.common.po.RolePO;
import com.didiglobal.logi.security.dao.RoleDao;
import com.didiglobal.logi.security.dao.mapper.RoleMapper;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjm
 */
@Component
public class RoleDaoImpl extends BaseDaoImpl<RolePO> implements RoleDao {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role selectByRoleId(Integer roleId) {
        QueryWrapper<RolePO> queryWrapper = getQueryWrapper();
        queryWrapper.eq(FieldConstant.ID, roleId);
        return CopyBeanUtil.copy(roleMapper.selectOne(queryWrapper), Role.class);
    }

    @Override
    public IPage<Role> selectPage(RoleQueryDTO queryDTO) {
        IPage<RolePO> pageInfo = new Page<>(queryDTO.getPage(), queryDTO.getSize());
        QueryWrapper<RolePO> roleWrapper = getQueryWrapper();
        String roleName = queryDTO.getRoleName();
        String description = queryDTO.getDescription();
        if(!StringUtils.isEmpty(queryDTO.getRoleCode())) {
            roleWrapper.eq(FieldConstant.ROLE_CODE, queryDTO.getRoleCode());
        } else {
            roleWrapper
                    .like(!StringUtils.isEmpty(roleName), FieldConstant.ROLE_NAME, roleName)
                    .like(!StringUtils.isEmpty(description), FieldConstant.DESCRIPTION, description);
        }
        roleMapper.selectPage(pageInfo, roleWrapper);
        return CopyBeanUtil.copyPage(pageInfo, Role.class);
    }

    @Override
    public void insert(Role role) {
        RolePO rolePO = CopyBeanUtil.copy(role, RolePO.class);
        roleMapper.insert(rolePO);
        role.setId(rolePO.getId());
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        roleMapper.deleteById(roleId);
    }

    @Override
    public void update(Role role) {
        roleMapper.updateById(CopyBeanUtil.copy(role, RolePO.class));
    }

    @Override
    public List<RoleBrief> selectBriefListByRoleNameAndDescOrderByCreateTime(String roleName) {
        QueryWrapper<RolePO> queryWrapper = wrapBriefQuery();
        queryWrapper
                .like(!StringUtils.isEmpty(roleName), FieldConstant.ROLE_NAME, roleName)
                // 据角色添加时间排序（倒序）
                .orderByDesc(FieldConstant.CREATE_TIME);
        return CopyBeanUtil.copyList(roleMapper.selectList(queryWrapper), RoleBrief.class);
    }

    @Override
    public List<RoleBrief> selectAllBrief() {
        return CopyBeanUtil.copyList(roleMapper.selectList(wrapBriefQuery()), RoleBrief.class);
    }

    @Override
    public List<RoleBrief> selectBriefListByRoleIdList(List<Integer> roleIdList) {
        if(CollectionUtils.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }
        QueryWrapper<RolePO> queryWrapper = wrapBriefQuery();
        queryWrapper.in(FieldConstant.ID, roleIdList);
        return CopyBeanUtil.copyList(roleMapper.selectList(queryWrapper), RoleBrief.class);
    }

    @Override
    public int selectCountByRoleNameAndNotRoleId(String roleName, Integer roleId) {
        QueryWrapper<RolePO> queryWrapper = getQueryWrapper();
        queryWrapper
                .eq(FieldConstant.ROLE_NAME, roleName)
                .ne(roleId != null, FieldConstant.ID, roleId);
        return roleMapper.selectCount(queryWrapper);
    }

    private QueryWrapper<RolePO> wrapBriefQuery() {
        QueryWrapper<RolePO> queryWrapper = getQueryWrapper();
        queryWrapper.select(FieldConstant.ID, FieldConstant.ROLE_NAME);
        return queryWrapper;
    }
}
