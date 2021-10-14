package com.didiglobal.logi.security.service;

import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.vo.role.AssignInfoVO;
import com.didiglobal.logi.security.common.dto.role.RoleAssignDTO;
import com.didiglobal.logi.security.common.dto.role.RoleQueryDTO;
import com.didiglobal.logi.security.common.dto.role.RoleSaveDTO;
import com.didiglobal.logi.security.common.vo.role.RoleBriefVO;
import com.didiglobal.logi.security.common.vo.role.RoleDeleteCheckVO;
import com.didiglobal.logi.security.common.vo.role.RoleVO;
import com.didiglobal.logi.security.exception.LogiSecurityException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cjm
 */
public interface RoleService {

    /**
     * 获取角色详情（主要是获取角色所拥有的权限信息）
     * @param roleId 角色id
     * @return RoleVo 角色信息
     */
    RoleVO getRoleDetailByRoleId(Integer roleId);

    /**
     * 分页获取角色列表
     *
     * @param queryDTO 查询角色列表条件
     * @return 角色列表
     */
    PagingData<RoleVO> getRolePage(RoleQueryDTO queryDTO);

    /**
     * 保存角色
     * @param saveDTO 角色信息
     * @param request 请求信息
     * @throws LogiSecurityException 参数检查错误信息
     */
    void createRole(RoleSaveDTO saveDTO, HttpServletRequest request) throws LogiSecurityException;

    /**
     * 删除角色
     * @param id 角色id
     * @param request 请求信息
     * @throws LogiSecurityException 该角色已分配给用户，不能删除
     */
    void deleteRoleByRoleId(Integer id, HttpServletRequest request) throws LogiSecurityException;

    /**
     * 更新角色信息
     * @param saveDTO 角色信息
     * @param request 请求信息
     * @throws LogiSecurityException 参数检查错误信息
     */
    void updateRole(RoleSaveDTO saveDTO, HttpServletRequest request) throws LogiSecurityException;

    /**
     * 分配角色给用户
     * @param assignDTO 分配信息
     * @param request 请求信息
     * @throws LogiSecurityException 角色分配flag不可为空
     */
    void assignRoles(RoleAssignDTO assignDTO, HttpServletRequest request) throws LogiSecurityException;

    /**
     * 根据角色id，获取分配信息
     * @param roleId 角色id
     * @return 分配信息List
     */
    List<AssignInfoVO> getAssignInfoByRoleId(Integer roleId);

    /**
     * 根据角色名模糊查询
     * @param roleName 角色名
     * @return 角色简要信息list
     */
    List<RoleBriefVO> getRoleBriefListByRoleName(String roleName);

    /**
     * 判断该角色是否已经分配给用户，如有分配给用户，则返回用户名list
     * @param roleId 角色id
     * @return 检查结果
     */
    RoleDeleteCheckVO checkBeforeDelete(Integer roleId);

    /**
     * 获取所有角色的简要信息
     * @return 角色简要信息List
     */
    List<RoleBriefVO> getAllRoleBriefList();

    /**
     * 根据用户id获取用户拥有的角色信息
     * @param userId 用户id
     * @return 角色简要信息List
     */
    List<RoleBriefVO> getRoleBriefListByUserId(Integer userId);
}
