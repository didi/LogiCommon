package com.didiglobal.logi.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.constant.OplogConstant;
import com.didiglobal.logi.security.common.entity.role.Role;
import com.didiglobal.logi.security.common.entity.role.RoleBrief;
import com.didiglobal.logi.security.common.vo.role.AssignInfoVO;
import com.didiglobal.logi.security.common.dto.role.RoleAssignDTO;
import com.didiglobal.logi.security.common.dto.role.RoleQueryDTO;
import com.didiglobal.logi.security.common.dto.role.RoleSaveDTO;
import com.didiglobal.logi.security.common.vo.role.RoleBriefVO;
import com.didiglobal.logi.security.common.vo.role.RoleDeleteCheckVO;
import com.didiglobal.logi.security.common.vo.role.RoleVO;
import com.didiglobal.logi.security.common.dto.message.MessageDTO;
import com.didiglobal.logi.security.common.dto.oplog.OplogDTO;
import com.didiglobal.logi.security.common.enums.message.MessageCode;
import com.didiglobal.logi.security.common.vo.permission.PermissionTreeVO;
import com.didiglobal.logi.security.common.enums.ResultCode;
import com.didiglobal.logi.security.common.vo.user.UserBriefVO;
import com.didiglobal.logi.security.dao.RoleDao;
import com.didiglobal.logi.security.exception.LogiSecurityException;
import com.didiglobal.logi.security.service.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.didiglobal.logi.security.util.CopyBeanUtil;
import com.didiglobal.logi.security.util.HttpRequestUtil;
import com.didiglobal.logi.security.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cjm
 */
@Service("logiSecurityRoleServiceImpl")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private OplogService oplogService;

    @Autowired
    private UserService userService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public RoleVO getRoleDetailByRoleId(Integer roleId) {
        Role role = roleDao.selectByRoleId(roleId);
        if(role == null) {
            return null;
        }
        // ????????????id???????????????
        PermissionTreeVO permissionTreeVO = permissionService.buildPermissionTreeByRoleId(role.getId());
        RoleVO roleVo = CopyBeanUtil.copy(role, RoleVO.class);
        roleVo.setPermissionTreeVO(permissionTreeVO);
        roleVo.setCreateTime(role.getCreateTime().getTime());
        // ?????????????????????
        List<Integer> userIdList = userRoleService.getUserIdListByRoleId(roleId);
        roleVo.setAuthedUserCnt(userIdList.size());
        return roleVo;
    }

    @Override
    public PagingData<RoleVO> getRolePage(RoleQueryDTO queryDTO) {
        IPage<Role> pageInfo = roleDao.selectPage(queryDTO);
        List<RoleVO> roleVOList = new ArrayList<>();
        for(Role role : pageInfo.getRecords()) {
            RoleVO roleVO = CopyBeanUtil.copy(role, RoleVO.class);
            // ???????????????????????????????????????
            roleVO.setAuthedUserCnt(userRoleService.getUserRoleCountByRoleId(role.getId()));
            roleVO.setCreateTime(role.getCreateTime().getTime());
            roleVOList.add(roleVO);
        }
        return new PagingData<>(roleVOList, pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRole(RoleSaveDTO roleSaveDTO, HttpServletRequest request) throws LogiSecurityException {
        Integer userId = HttpRequestUtil.getOperatorId(request);
        // ????????????
        checkParam(roleSaveDTO, false);
        // ??????????????????
        Role role = CopyBeanUtil.copy(roleSaveDTO, Role.class);
        // ?????????????????????
        UserBriefVO userBriefVO = userService.getUserBriefByUserId(userId);
        if(userBriefVO != null) {
            role.setLastReviser(userBriefVO.getUsername());
        }
        // ??????????????????
        role.setRoleCode("r" + MathUtil.getRandomNumber(7));
        roleDao.insert(role);
        // ????????????????????????????????????
        rolePermissionService.saveRolePermission(role.getId(), roleSaveDTO.getPermissionIdList());
        // ??????????????????
        oplogService.saveOplogWithUserId(userId,
                new OplogDTO(OplogConstant.RM, OplogConstant.RM_A, OplogConstant.RM_R, roleSaveDTO.getRoleName()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleByRoleId(Integer roleId, HttpServletRequest request) throws LogiSecurityException {
        Role role = roleDao.selectByRoleId(roleId);
        if(role == null) {
            return;
        }
        // ????????????????????????????????????
        List<Integer> userIdList = userRoleService.getUserIdListByRoleId(roleId);
        if(!userIdList.isEmpty()) {
            throw new LogiSecurityException(ResultCode.ROLE_USER_AUTHED);
        }
        // ??????????????????????????????
        rolePermissionService.deleteRolePermissionByRoleId(roleId);
        // ????????????????????????
        roleDao.deleteByRoleId(roleId);
        // ??????????????????
        OplogDTO oplogDTO = new OplogDTO(OplogConstant.RM, OplogConstant.RM_D, OplogConstant.RM_R, role.getRoleName());
        oplogService.saveOplogWithUserId(HttpRequestUtil.getOperatorId(request), oplogDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleSaveDTO saveDTO, HttpServletRequest request) throws LogiSecurityException {
        Integer userId = HttpRequestUtil.getOperatorId(request);
        if(roleDao.selectByRoleId(saveDTO.getId()) == null) {
            throw new LogiSecurityException(ResultCode.ROLE_NOT_EXISTS);
        }
        checkParam(saveDTO, true);
        // ????????????????????????
        Role role = CopyBeanUtil.copy(saveDTO, Role.class);
        // ?????????????????????
        UserBriefVO userBriefVO = userService.getUserBriefByUserId(userId);
        if(userBriefVO != null) {
            role.setLastReviser(userBriefVO.getUsername());
        }
        roleDao.update(role);
        // ?????????????????????????????????
        rolePermissionService.updateRolePermission(role.getId(), saveDTO.getPermissionIdList());
        // ??????????????????
        oplogService.saveOplogWithUserId(userId,
                new OplogDTO(OplogConstant.RM, OplogConstant.RM_E, OplogConstant.RM_R, saveDTO.getRoleName()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRoles(RoleAssignDTO assignDTO, HttpServletRequest request) throws LogiSecurityException {
        Integer operatorId = HttpRequestUtil.getOperatorId(request);
        if(assignDTO.getFlag() == null) {
            throw new LogiSecurityException(ResultCode.ROLE_ASSIGN_FLAG_IS_NULL);
        }
        if(Boolean.TRUE.equals(assignDTO.getFlag())) {
            // N??????????????????1?????????
            Integer userId = assignDTO.getId();
            // ??????old???????????????????????????
            List<Integer> oldRoleIdList = userRoleService.getRoleIdListByUserId(userId);
            // ??????????????????
            userRoleService.updateUserRoleByUserId(userId, assignDTO.getIdList());
            // ??????????????????
            UserBriefVO userBriefVO = userService.getUserBriefByUserId(assignDTO.getId());
            Integer oplogId = oplogService.saveOplogWithUserId(operatorId,
                    new OplogDTO(OplogConstant.UM, OplogConstant.UM_AR, OplogConstant.UM_U, userBriefVO.getUsername()));
            // ?????????????????????????????????
            packAndSaveMessage(oplogId, oldRoleIdList, assignDTO);
        } else {
            // 1??????????????????N?????????
            Integer roleId = assignDTO.getId();
            // ??????old???????????????????????????
            List<Integer> oldUserIdList = userRoleService.getUserIdListByRoleId(roleId);
            // ??????????????????
            userRoleService.updateUserRoleByRoleId(roleId, assignDTO.getIdList());
            // ??????????????????
            Role role = roleDao.selectByRoleId(assignDTO.getId());
            Integer oplogId = oplogService.saveOplogWithUserId(operatorId,
                    new OplogDTO(OplogConstant.RM, OplogConstant.RM_AU, OplogConstant.RM_R, role.getRoleName()));
            // ?????????????????????????????????
            packAndSaveMessage(oplogId, oldUserIdList, assignDTO);
        }
    }

    @Override
    public List<RoleBriefVO> getRoleBriefListByRoleName(String roleName) {
        List<RoleBrief> roleBriefList = roleDao.selectBriefListByRoleNameAndDescOrderByCreateTime(roleName);
        return CopyBeanUtil.copyList(roleBriefList, RoleBriefVO.class);
    }

    @Override
    public RoleDeleteCheckVO checkBeforeDelete(Integer roleId) {
        if(roleId == null) {
            return null;
        }
        RoleDeleteCheckVO roleDeleteCheckVO = new RoleDeleteCheckVO();
        roleDeleteCheckVO.setRoleId(roleId);
        // ????????????idList
        List<Integer> userIdList = userRoleService.getUserIdListByRoleId(roleId);
        if(!CollectionUtils.isEmpty(userIdList)) {
            // ????????????????????????List
            List<UserBriefVO> list = userService.getUserBriefListByUserIdList(userIdList);
            List<String> usernameList = list.stream().map(UserBriefVO::getUsername).collect(Collectors.toList());
            roleDeleteCheckVO.setUsernameList(usernameList);
        }
        return roleDeleteCheckVO;
    }

    @Override
    public List<RoleBriefVO> getAllRoleBriefList() {
        List<RoleBrief> roleBriefList = roleDao.selectAllBrief();
        return CopyBeanUtil.copyList(roleBriefList, RoleBriefVO.class);
    }

    @Override
    public List<RoleBriefVO> getRoleBriefListByUserId(Integer userId) {
        // ???????????????????????????
        List<Integer> roleIdList = userRoleService.getRoleIdListByUserId(userId);
        if(CollectionUtils.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }
        List<RoleBrief> roleBriefList =  roleDao.selectBriefListByRoleIdList(roleIdList);
        return CopyBeanUtil.copyList(roleBriefList, RoleBriefVO.class);
    }

    @Override
    public List<AssignInfoVO> getAssignInfoByRoleId(Integer roleId) {
        if(roleId == null) {
            return new ArrayList<>();
        }
        // ????????????List
        List<UserBriefVO> userBriefVOList = userService.getAllUserBriefList();

        // ????????????????????????????????????????????????set
        List<Integer> userIdList = userRoleService.getUserIdListByRoleId(roleId);
        Set<Integer> hasRoleUserIdSet = new HashSet<>(userIdList);

        // ??????List<AssignDataVo>
        List<AssignInfoVO> result = new ArrayList<>();
        for(UserBriefVO userBriefVO : userBriefVOList) {
            AssignInfoVO assignInfoVO = new AssignInfoVO();
            // ?????????????????????????????????
            assignInfoVO.setHas(hasRoleUserIdSet.contains(userBriefVO.getId()));
            assignInfoVO.setName(userBriefVO.getUsername() + "/" + userBriefVO.getRealName());
            assignInfoVO.setId(userBriefVO.getId());
            result.add(assignInfoVO);
        }
        return result;
    }

    private void packAndSaveMessage(Integer oplogId, List<Integer> oldIdList, RoleAssignDTO roleAssignDTO) {
        List<Integer> newIdList = roleAssignDTO.getIdList();

        List<Integer> removeIdList = new ArrayList<>();
        List<Integer> addIdList = new ArrayList<>();
        // ?????????
        Set<Integer> set = MathUtil.getIntersection(oldIdList, newIdList);
        for(Integer oldId : oldIdList) {
            if(!set.contains(oldId)) {
                removeIdList.add(oldId);
            }
        }
        for(Integer newId : newIdList) {
            if(!set.contains(newId)) {
                addIdList.add(newId);
            }
        }

        if (Boolean.TRUE.equals(roleAssignDTO.getFlag())) {
            // ?????????N??????????????????1????????????oldIdList???newIdList????????????idList
            List<Integer> userIdList = new ArrayList<>();
            userIdList.add(roleAssignDTO.getId());
            // ???????????????????????? ??? ??????????????????
            saveRoleAssignMessage(oplogId, userIdList, removeIdList, userIdList, addIdList);
        } else {
            // 1??????????????????N????????????oldIdList???newIdList????????????idList
            List<Integer> roleIdList = new ArrayList<>();
            roleIdList.add(roleAssignDTO.getId());
            // ???????????????????????? ??? ??????????????????
            saveRoleAssignMessage(oplogId, removeIdList, roleIdList, addIdList, roleIdList);
        }
    }

    /**
     * ??????????????????????????????
     * @param oplogId ????????????id
     * @param removeUserIdList ????????????????????????idList
     * @param removeRoleIdList ???????????????idList
     * @param addUserIdList ?????????????????????idList
     * @param addRoleIdList ???????????????idList
     */
    private void saveRoleAssignMessage(Integer oplogId,
                                       List<Integer> removeUserIdList, List<Integer> removeRoleIdList,
                                       List<Integer> addUserIdList, List<Integer> addRoleIdList) {
        // ??????????????????
        SimpleDateFormat formatter= new SimpleDateFormat("MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        String time = formatter.format(date);

        // ???????????????????????????
        String addRoleInfo = spliceRoleNameByRoleIdList(addRoleIdList);
        String removeRoleInfo = spliceRoleNameByRoleIdList(removeRoleIdList);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        if(!StringUtils.isEmpty(addRoleInfo)) {
            for(Integer userId : addUserIdList) {
                MessageDTO messageDTO = new MessageDTO(userId, oplogId);
                // ???????????????
                String content = String.format(MessageCode.ROLE_ADD_MESSAGE.getContent(), time, addRoleInfo);
                messageDTO.setContent(content);
                messageDTO.setTitle(MessageCode.ROLE_ADD_MESSAGE.getTitle());
                messageDTOList.add(messageDTO);
            }
        }
        if(!StringUtils.isEmpty(removeRoleInfo)) {
            for(Integer userId : removeUserIdList) {
                MessageDTO messageDTO = new MessageDTO(userId, oplogId);
                // ???????????????
                String content = String.format(MessageCode.ROLE_REMOVE_MESSAGE.getContent(), time, removeRoleInfo);
                messageDTO.setContent(content);
                messageDTO.setTitle(MessageCode.ROLE_REMOVE_MESSAGE.getTitle());
                messageDTOList.add(messageDTO);
            }
        }

        messageService.saveMessages(messageDTOList);
    }

    private String spliceRoleNameByRoleIdList(List<Integer> roleIdList) {
        List<RoleBrief> roleBriefList = roleDao.selectBriefListByRoleIdList(roleIdList);
        if(roleBriefList.isEmpty()) {
            return null;
        }
        // ??????????????????
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < roleBriefList.size() - 1; i++) {
            sb.append(roleBriefList.get(i).getRoleName()).append(",");
        }
        sb.append(roleBriefList.get(roleBriefList.size() - 1).getRoleName());
        return sb.toString();
    }

    /**
     * ????????????????????????????????????
     * @param saveDTO ????????????
     * @param isUpdate ?????????????????????
     * @throws LogiSecurityException ????????????
     */
    private void checkParam(RoleSaveDTO saveDTO, boolean isUpdate) throws LogiSecurityException {
        if(StringUtils.isEmpty(saveDTO.getRoleName())) {
            throw new LogiSecurityException(ResultCode.ROLE_NAME_CANNOT_BE_BLANK);
        }
        if(StringUtils.isEmpty(saveDTO.getDescription())) {
            throw new LogiSecurityException(ResultCode.ROLE_DEPT_CANNOT_BE_BLANK);
        }
        if(CollectionUtils.isEmpty(saveDTO.getPermissionIdList())) {
            throw new LogiSecurityException(ResultCode.ROLE_PERMISSION_CANNOT_BE_NULL);
        }
        // ??????????????????????????????????????????????????????????????????old??????
        Integer roleId = isUpdate ? saveDTO.getId() : null;
        int count = roleDao.selectCountByRoleNameAndNotRoleId(saveDTO.getRoleName(), roleId);
        if(count > 0) {
            // ?????????????????????
            throw new LogiSecurityException(ResultCode.ROLE_NAME_ALREADY_EXISTS);
        }
    }
}