package com.didiglobal.logi.security.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.didiglobal.logi.security.common.dto.user.UserBriefQueryDTO;
import com.didiglobal.logi.security.common.dto.user.UserQueryDTO;
import com.didiglobal.logi.security.common.entity.user.User;
import com.didiglobal.logi.security.common.entity.user.UserBrief;
import com.didiglobal.logi.security.common.po.UserPO;
import com.didiglobal.logi.security.dao.UserDao;
import com.didiglobal.logi.security.dao.mapper.UserMapper;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cjm
 */
@Component
public class UserDaoImpl extends BaseDaoImpl<UserPO> implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> selectPageByDeptIdListAndUserIdList(UserQueryDTO queryDTO, List<Integer> deptIdList,
                                                           List<Integer> userIdList) {
        IPage<UserPO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());
        if(deptIdList != null && deptIdList.isEmpty()) {
            return CopyBeanUtil.copyPage(page, User.class);
        }
        if(userIdList != null && userIdList.isEmpty()) {
            return CopyBeanUtil.copyPage(page, User.class);
        }
        QueryWrapper<UserPO> queryWrapper = getQueryWrapper();
        queryWrapper
                .like(queryDTO.getUsername() != null, FieldConstant.USERNAME, queryDTO.getUsername())
                .like(queryDTO.getRealName() != null, FieldConstant.REAL_NAME, queryDTO.getRealName())
                .in(deptIdList != null, FieldConstant.DEPT_ID, deptIdList)
                .in(userIdList != null, FieldConstant.ID, userIdList);
        userMapper.selectPage(page, queryWrapper);
        return CopyBeanUtil.copyPage(page, User.class);
    }

    @Override
    public IPage<UserBrief> selectBriefPageByDeptIdList(UserBriefQueryDTO queryDTO, List<Integer> deptIdList) {
        IPage<UserPO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());
        if(deptIdList != null && deptIdList.isEmpty()) {
            return CopyBeanUtil.copyPage(page, UserBrief.class);
        }
        QueryWrapper<UserPO> queryWrapper = wrapBriefQuery();
        queryWrapper
                .like(!StringUtils.isEmpty(queryDTO.getUsername()), FieldConstant.USERNAME, queryDTO.getUsername())
                .like(!StringUtils.isEmpty(queryDTO.getRealName()), FieldConstant.REAL_NAME, queryDTO.getRealName())
                .in(deptIdList != null, FieldConstant.DEPT_ID, deptIdList);
        userMapper.selectPage(page, queryWrapper);
        return CopyBeanUtil.copyPage(page, UserBrief.class);
    }

    @Override
    public User selectByUserId(Integer userId) {
        if(userId == null) {
            return null;
        }
        QueryWrapper<UserPO> queryWrapper = getQueryWrapper();
        queryWrapper.eq("id", userId);
        return CopyBeanUtil.copy(userMapper.selectOne(queryWrapper), User.class);
    }

    @Override
    public List<UserBrief> selectBriefListByUserIdList(List<Integer> userIdList) {
        if(CollectionUtils.isEmpty(userIdList)) {
            return new ArrayList<>();
        }
        QueryWrapper<UserPO> queryWrapper = wrapBriefQuery();
        queryWrapper.in("id", userIdList);
        return CopyBeanUtil.copyList(userMapper.selectList(queryWrapper), UserBrief.class);
    }

    @Override
    public List<UserBrief> selectBriefListByNameAndDescOrderByCreateTime(String name) {
        QueryWrapper<UserPO> queryWrapper = wrapBriefQuery();
        queryWrapper
                .like(!StringUtils.isEmpty(name), FieldConstant.USERNAME, name)
                .or()
                .like(!StringUtils.isEmpty(name), FieldConstant.REAL_NAME, name)
                .orderByDesc(FieldConstant.CREATE_TIME);
        return CopyBeanUtil.copyList(userMapper.selectList(queryWrapper), UserBrief.class);
    }

    @Override
    public List<UserBrief> selectBriefListByDeptIdList(List<Integer> deptIdList) {
        if(deptIdList != null && deptIdList.isEmpty()) {
            return new ArrayList<>();
        }
        QueryWrapper<UserPO> queryWrapper = wrapBriefQuery();
        queryWrapper.in(deptIdList != null, FieldConstant.DEPT_ID, deptIdList);
        return CopyBeanUtil.copyList(userMapper.selectList(queryWrapper), UserBrief.class);
    }

    @Override
    public List<UserBrief> selectBriefListOrderByCreateTime(boolean isAsc) {
        QueryWrapper<UserPO> queryWrapper = wrapBriefQuery();
        if(isAsc) {
            queryWrapper.orderByAsc(FieldConstant.CREATE_TIME);
        } else {
            queryWrapper.orderByDesc(FieldConstant.CREATE_TIME);
        }
        userMapper.selectList(queryWrapper);
        return CopyBeanUtil.copyList(userMapper.selectList(queryWrapper), UserBrief.class);
    }

    @Override
    public List<UserBrief> selectAllBriefList() {
        QueryWrapper<UserPO> queryWrapper = wrapBriefQuery();
        return CopyBeanUtil.copyList(userMapper.selectList(queryWrapper), UserBrief.class);
    }

    @Override
    public List<Integer> selectUserIdListByUsernameOrRealName(String name) {
        QueryWrapper<UserPO> queryWrapper = getQueryWrapper();
        queryWrapper.select(FieldConstant.ID)
                .like(!StringUtils.isEmpty(name), FieldConstant.USERNAME, name)
                .or()
                .like(!StringUtils.isEmpty(name), FieldConstant.REAL_NAME, name);
        List<Object> userIdList = userMapper.selectObjs(queryWrapper);
        return userIdList.stream().map(Integer.class::cast).collect(Collectors.toList());
    }

    @Override
    public User selectByUsername(String username) {
        if(StringUtils.isEmpty(username)) {
            return null;
        }
        QueryWrapper<UserPO> queryWrapper = getQueryWrapper();
        queryWrapper.eq(FieldConstant.USERNAME, username);
        UserPO userPO = userMapper.selectOne(queryWrapper);
        return CopyBeanUtil.copy(userPO, User.class);
    }

    private QueryWrapper<UserPO> wrapBriefQuery() {
        QueryWrapper<UserPO> queryWrapper = getQueryWrapper();
        queryWrapper.select(FieldConstant.ID, FieldConstant.USERNAME, FieldConstant.REAL_NAME, FieldConstant.DEPT_ID);
        return queryWrapper;
    }
}
