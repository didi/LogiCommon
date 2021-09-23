package com.didiglobal.logi.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.dto.account.AccountLoginDTO;
import com.didiglobal.logi.security.common.dto.user.UserBriefQueryDTO;
import com.didiglobal.logi.security.common.entity.dept.Dept;
import com.didiglobal.logi.security.common.entity.user.User;
import com.didiglobal.logi.security.common.entity.user.UserBrief;
import com.didiglobal.logi.security.common.vo.role.AssignInfoVO;
import com.didiglobal.logi.security.common.vo.role.RoleBriefVO;
import com.didiglobal.logi.security.common.dto.user.UserQueryDTO;
import com.didiglobal.logi.security.common.vo.user.UserBriefVO;
import com.didiglobal.logi.security.common.vo.user.UserVO;
import com.didiglobal.logi.security.common.enums.ResultCode;
import com.didiglobal.logi.security.dao.UserDao;
import com.didiglobal.logi.security.exception.LogiSecurityException;
import com.didiglobal.logi.security.service.*;
import com.didiglobal.logi.security.util.CopyBeanUtil;

import java.util.*;
import java.util.stream.Collectors;

import com.didiglobal.logi.security.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author cjm
 */
@Service("logiSecurityUserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public PagingData<UserVO> getUserPage(UserQueryDTO queryDTO) {
        List<Integer> userIdList = null;
        // 是否有角色id条件
        if(queryDTO.getRoleId() != null) {
            // 根据角色获取用户IdList
            userIdList = userRoleService.getUserIdListByRoleId(queryDTO.getRoleId());
        }
        // 获取该部门下的所有子部门idList
        List<Integer> deptIdList = deptService.getDeptIdListByParentId(queryDTO.getDeptId());

        IPage<User> pageInfo = userDao.selectPageByDeptIdListAndUserIdList(queryDTO, deptIdList, userIdList);
        List<UserVO> userVOList = new ArrayList<>();
        List<User> userList = pageInfo.getRecords();

        // 提前获取所有部门
        Map<Integer, Dept> deptMap = deptService.getAllDeptMap();
        for (User user : userList) {
            UserVO userVo = CopyBeanUtil.copy(user, UserVO.class);
            // 设置角色信息
            userVo.setRoleList(roleService.getRoleBriefListByUserId(userVo.getId()));
            // 设置部门信息
            userVo.setDeptList(deptService.getDeptBriefListFromDeptMapByChildId(deptMap, user.getDeptId()));
            userVo.setUpdateTime(user.getUpdateTime().getTime());
            // 隐私信息处理
            privacyProcessing(userVo);
            userVOList.add(userVo);
        }
        return new PagingData<>(userVOList, pageInfo);
    }

    @Override
    public PagingData<UserBriefVO> getUserBriefPage(UserBriefQueryDTO queryDTO) {
        // 查找合适的部门idList
        List<Integer> deptIdList = deptService.
                getDeptIdListByParentIdAndDeptName(queryDTO.getDeptId(), queryDTO.getDeptName());
        // 分页获取
        IPage<UserBrief> pageInfo = userDao.selectBriefPageByDeptIdList(queryDTO, deptIdList);
        List<UserBriefVO> userBriefVOList = CopyBeanUtil.copyList(pageInfo.getRecords(), UserBriefVO.class);
        return new PagingData<>(userBriefVOList, pageInfo);
    }

    @Override
    public UserVO getUserDetailByUserId(Integer userId) throws LogiSecurityException {
        User user = userDao.selectByUserId(userId);
        if (user == null) {
            throw new LogiSecurityException(ResultCode.USER_NOT_EXISTS);
        }
        UserVO userVo = CopyBeanUtil.copy(user, UserVO.class);

        // 根据用户id获取角色List
        List<RoleBriefVO> roleBriefVOList = roleService.getRoleBriefListByUserId(userId);
        // 设置角色信息
        userVo.setRoleList(roleBriefVOList);

        List<Integer> roleIdList = roleBriefVOList.stream().map(RoleBriefVO::getId).collect(Collectors.toList());
        // 根据角色idList获取权限idList
        List<Integer> hasPermissionIdList = rolePermissionService.getPermissionIdListByRoleIdList(roleIdList);
        // 构建权限树
        userVo.setPermissionTreeVO(permissionService.buildPermissionTreeWithHas(hasPermissionIdList));

        // 查找用户所在部门信息
        userVo.setDeptList(deptService.getDeptBriefListByChildId(user.getDeptId()));

        userVo.setUpdateTime(user.getUpdateTime().getTime());
        return userVo;
    }

    @Override
    public UserBriefVO getUserBriefByUserId(Integer userId) {
        if(userId == null) {
            return null;
        }
        User user = userDao.selectByUserId(userId);
        return CopyBeanUtil.copy(user, UserBriefVO.class);
    }

    @Override
    public List<UserBriefVO> getUserBriefListByUserIdList(List<Integer> userIdList) {
        if(CollectionUtils.isEmpty(userIdList)) {
            return new ArrayList<>();
        }
        List<UserBrief> userBriefList = userDao.selectBriefListByUserIdList(userIdList);
        return CopyBeanUtil.copyList(userBriefList, UserBriefVO.class);
    }

    @Override
    public List<UserBriefVO> getUserBriefListByUsernameOrRealName(String name) {
        List<UserBrief> userBriefList = userDao.selectBriefListByNameAndDescOrderByCreateTime(name);
        return CopyBeanUtil.copyList(userBriefList, UserBriefVO.class);
    }

    @Override
    public List<UserBriefVO> getAllUserBriefListOrderByCreateTime(boolean isAsc) {
        List<UserBrief> userBriefList = userDao.selectBriefListOrderByCreateTime(isAsc);
        return CopyBeanUtil.copyList(userBriefList, UserBriefVO.class);
    }

    @Override
    public List<Integer> getUserIdListByUsernameOrRealName(String name) {
        return userDao.selectUserIdListByUsernameOrRealName(name);
    }

    @Override
    public List<UserBriefVO> getAllUserBriefList() {
        List<UserBrief> userBriefList = userDao.selectAllBriefList();
        return CopyBeanUtil.copyList(userBriefList, UserBriefVO.class);
    }

    @Override
    public List<UserBriefVO> getUserBriefListByDeptId(Integer deptId) {
        // 根据部门id查找用户，该部门的子部门的用户都属于该部门
        List<Integer> deptIdList = deptService.getDeptIdListByParentId(deptId);
        List<UserBrief> userBriefList = userDao.selectBriefListByDeptIdList(deptIdList);
        return CopyBeanUtil.copyList(userBriefList, UserBriefVO.class);
    }

    @Override
    public List<AssignInfoVO> getAssignDataByUserId(Integer userId) throws LogiSecurityException {
        if(userId == null) {
            throw new LogiSecurityException(ResultCode.USER_ID_CANNOT_BE_NULL);
        }
        // 获取所有角色
        List<RoleBriefVO> roleBriefVOList = roleService.getAllRoleBriefList();
        // 获取该用户拥有的角色
        Set<Integer> hasRoleIdSet = new HashSet<>(userRoleService.getRoleIdListByUserId(userId));

        List<AssignInfoVO> list = new ArrayList<>();
        for(RoleBriefVO roleBriefVO : roleBriefVOList) {
            AssignInfoVO data = new AssignInfoVO();
            data.setName(roleBriefVO.getRoleName());
            data.setId(roleBriefVO.getId());
            data.setHas(hasRoleIdSet.contains(roleBriefVO.getId()));
            list.add(data);
        }
        return list;
    }

    @Override
    public List<UserBriefVO> getUserBriefListByRoleId(Integer roleId) {
        // 先获取拥有该角色的用户id
        List<Integer> userIdList = userRoleService.getUserIdListByRoleId(roleId);
        List<UserBrief> userBriefList = userDao.selectBriefListByUserIdList(userIdList);
        return CopyBeanUtil.copyList(userBriefList, UserBriefVO.class);
    }

    /**
     * 隐私处理
     *
     * @param userVo 返回给页面的用户信息
     */
    private void privacyProcessing(UserVO userVo) {
        String phone = userVo.getPhone();
        userVo.setPhone(phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
    }

    @Override
    public UserBriefVO verifyLogin(AccountLoginDTO loginDTO,
                                   HttpServletRequest request) throws LogiSecurityException {
        User user = userDao.selectByUsername(loginDTO.getUsername());
        if(user == null) {
            throw new LogiSecurityException(ResultCode.USER_NOT_EXISTS);
        }

        // 解密密码
        String saltPassword = user.getSalt() + loginDTO.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(saltPassword.getBytes());
        if(!user.getPassword().equals(md5Password)) {
            // 密码错误
            throw new LogiSecurityException(ResultCode.USER_CREDENTIALS_ERROR);
        }

        // 登录成功后
        HttpSession session = request.getSession();
        // 设置过期时间（秒）
        session.setMaxInactiveInterval(60 * 60);
        session.setAttribute(HttpRequestUtil.USER, loginDTO.getUsername());

        return CopyBeanUtil.copy(user, UserBriefVO.class);
    }
}