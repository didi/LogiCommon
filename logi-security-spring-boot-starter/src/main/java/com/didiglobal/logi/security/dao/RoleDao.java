package com.didiglobal.logi.security.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.didiglobal.logi.security.common.dto.role.RoleQueryDTO;
import com.didiglobal.logi.security.common.entity.role.Role;
import com.didiglobal.logi.security.common.entity.role.RoleBrief;

import java.util.List;

/**
 * @author cjm
 */
public interface RoleDao {

    /**
     * 根据角色id，获取角色信息
     * @param roleId 角色id
     * @return 角色信息
     */
    Role selectByRoleId(Integer roleId);

    /**
     * 分页查询
     * @param queryDTO 查询条件
     * @return 角色信息page
     */
    IPage<Role> selectPage(RoleQueryDTO queryDTO);

    /**
     * 新增角色
     * @param role 角色信息
     */
    void insert(Role role);

    /**
     * 删除角色
     * @param roleId 角色id
     */
    void deleteByRoleId(Integer roleId);

    /**
     * 更新角色
     * @param role 角色信息
     */
    void update(Role role);

    /**
     * 获取角色简要信息
     * @param roleName 角色名
     * @return 角色简要信息List
     */
    List<RoleBrief> selectBriefListByRoleNameAndDescOrderByCreateTime(String roleName);

    /**
     * 获取全部角色简要信息
     * @return 角色简要信息list
     */
    List<RoleBrief> selectAllBrief();

    /**
     * 根据角色idList获取角色简要信息
     * @param roleIdList 角色idList
     * @return 角色简要信息List
     */
    List<RoleBrief> selectBriefListByRoleIdList(List<Integer> roleIdList);

    /**
     * 根据角色名查询符合数据数
     * 如果是更新操作，则判断角色名重复的时候要排除old角色id信息
     * @param roleName 角色名
     * @param roleId 角色id
     * @return 角色数
     */
    int selectCountByRoleNameAndNotRoleId(String roleName, Integer roleId);
}
