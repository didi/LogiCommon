package com.didiglobal.logi.security.service.impl;

import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.constant.OplogConstant;
import com.didiglobal.logi.security.common.dto.oplog.OplogDTO;
import com.didiglobal.logi.security.common.dto.project.ProjectBriefQueryDTO;
import com.didiglobal.logi.security.common.dto.resource.*;
import com.didiglobal.logi.security.common.dto.resource.type.ResourceTypeQueryDTO;
import com.didiglobal.logi.security.common.dto.user.UserBriefQueryDTO;
import com.didiglobal.logi.security.common.dto.resource.ResourceDTO;
import com.didiglobal.logi.security.common.entity.UserResource;
import com.didiglobal.logi.security.common.entity.dept.Dept;
import com.didiglobal.logi.security.common.enums.ResultCode;
import com.didiglobal.logi.security.common.enums.resource.ControlLevelCode;
import com.didiglobal.logi.security.common.enums.resource.HasLevelCode;
import com.didiglobal.logi.security.common.enums.resource.ShowLevelCode;
import com.didiglobal.logi.security.common.vo.project.ProjectBriefVO;
import com.didiglobal.logi.security.common.vo.resource.*;
import com.didiglobal.logi.security.common.vo.user.UserBriefVO;
import com.didiglobal.logi.security.dao.UserResourceDao;
import com.didiglobal.logi.security.exception.LogiSecurityException;
import com.didiglobal.logi.security.extend.ResourceExtend;
import com.didiglobal.logi.security.extend.ResourceExtendBeanTool;
import com.didiglobal.logi.security.service.*;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import com.didiglobal.logi.security.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author cjm
 */
@Service("logiSecurityUserResourceServiceImpl")
public class UserResourceServiceImpl implements UserResourceService {

    @Autowired
    private UserResourceDao userResourceDao;

    @Autowired
    private DeptService deptService;

    @Autowired
    private OplogService oplogService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ResourceTypeService resourceTypeService;

    @Autowired
    private ResourceExtendBeanTool resourceExtendBeanTool;

    @Override
    public int getResourceCntByUserId(Integer userId, UserResourceQueryDTO queryDTO) {
        if (userId == null) {
            return 0;
        }
        return userResourceDao.selectCountByUserId(userId, queryDTO);
    }

    /**
     * VPC（ViewPermissionControl）
     * 判断资源查看权限控制状态是否开启
     * 只要数据库中有这样的记录（user_id、project_id、resource_type_id、resource_id、control_level）都为0
     * 则开启了
     */
    @Override
    public boolean getViewPermissionControlStatus() {
        UserResourceQueryDTO queryDTO = UserResourceQueryDTO.getOpenViewPermissionControlQueryEntity();
        return userResourceDao.selectCountByUserId(0, queryDTO) > 0;
    }

    @Override
    public List<MByUDataVO> getManagerByUserDataList(MByUDataQueryDTO queryDTO) throws LogiSecurityException {
        // 检查参数
        checkParam(queryDTO);

        Integer projectId = queryDTO.getProjectId();
        Integer resourceTypeId = queryDTO.getResourceTypeId();
        int showLevel = queryDTO.getShowLevel();
        int controlLevel = queryDTO.getControlLevel();
        int userId = queryDTO.getUserId();
        boolean isBatch = queryDTO.getBatch();

        List<MByUDataVO> resultList = new ArrayList<>();
        if (ShowLevelCode.PROJECT.getType().equals(showLevel)) {
            // 如果是项目展示级别
            List<ProjectBriefVO> projectBriefVOList = projectService.getProjectBriefList();
            for (ProjectBriefVO projectBriefVO : projectBriefVOList) {
                MByUDataVO dataVo = new MByUDataVO(projectBriefVO.getId(), projectBriefVO.getProjectName());
                HasLevelCode hasLevel = getHasLevel(
                        isBatch, controlLevel, userId, projectBriefVO.getId(), null, null
                );
                dataVo.setHasLevel(hasLevel.getType());
                resultList.add(dataVo);
            }
        } else if (ShowLevelCode.RESOURCE_TYPE.getType().equals(showLevel)) {
            // 如果是资源类别展示级别
            List<ResourceTypeVO> resourceTypeVOList = resourceTypeService.getAllResourceTypeList();
            for (ResourceTypeVO resourceTypeVO : resourceTypeVOList) {
                MByUDataVO dataVo = new MByUDataVO(resourceTypeVO.getId(), resourceTypeVO.getTypeName());
                HasLevelCode hasLevel = getHasLevel(
                        isBatch, controlLevel, userId, projectId, resourceTypeVO.getId(), null
                );
                dataVo.setHasLevel(hasLevel.getType());
                resultList.add(dataVo);
            }
        } else {
            // 如果是具体资源展示级别
            ResourceExtend resourceExtend = resourceExtendBeanTool.getResourceExtendImpl();
            List<ResourceDTO> resourceDTOList = resourceExtend.getResourceList(projectId, resourceTypeId);
            for (ResourceDTO resourceDto : resourceDTOList) {
                MByUDataVO dataVo = new MByUDataVO(resourceDto.getResourceId(), resourceDto.getResourceName());
                HasLevelCode hasLevel = getHasLevel(
                        isBatch, controlLevel, userId, projectId, resourceTypeId, resourceDto.getResourceId()
                );
                dataVo.setHasLevel(hasLevel.getType());
                resultList.add(dataVo);
            }
        }

        return resultList;
    }

    @Override
    public List<MByRDataVO> getManagerByResourceDataList(MByRDataQueryDTO queryDTO) throws LogiSecurityException {
        checkParam(queryDTO);
        Integer projectId = queryDTO.getProjectId();
        Integer resourceTypeId = queryDTO.getResourceTypeId();
        Integer resourceId = queryDTO.getResourceId();
        int controlLevel = queryDTO.getControlLevel();
        boolean isBatch = queryDTO.getBatch();

        List<UserBriefVO> userBriefVOList = userService.getAllUserBriefListOrderByCreateTime(false);

        List<MByRDataVO> result = Collections.synchronizedList(new ArrayList<>());
        userBriefVOList.parallelStream().forEach(userBriefVO -> {
            MByRDataVO dataVo = new MByRDataVO();
            dataVo.setUserId(userBriefVO.getId());
            dataVo.setUsername(userBriefVO.getUsername());
            dataVo.setRealName(userBriefVO.getRealName());
            HasLevelCode hasLevel = getHasLevel(
                    isBatch, controlLevel, userBriefVO.getId(), projectId, resourceTypeId, resourceId
            );
            dataVo.setHasLevel(hasLevel.getType());
            result.add(dataVo);
        });
        return result;
    }

    /**
     * 获取用户userId，对该数据（项目、资源类别、具体资源）的拥有级别
     * 拥有级别：全拥有、半拥有、不拥有
     *
     * @param controlLevel   资源控制级别
     * @param userId         用户id
     * @param projectId      项目id
     * @param resourceTypeId 资源类别id
     * @param resourceId     具体资源id
     * @return HasLevelCode 拥有级别枚举
     */
    private HasLevelCode getHasLevel(boolean isBatch, int controlLevel, int userId,
                                     Integer projectId, Integer resourceTypeId, Integer resourceId) {
        if (isBatch) {
            // 如果是批量操作，则默认假设都不拥有权限
            return HasLevelCode.NONE;
        }
        UserResourceQueryDTO queryDTO = new UserResourceQueryDTO(controlLevel, projectId, resourceTypeId, resourceId);
        int hasResourceCnt = getResourceCntByUserId(userId, queryDTO);
        if (hasResourceCnt == 0) {
            return HasLevelCode.NONE;
        } else {
            // 获取该项目下具体资源的个数
            int resourceCnt = 1;
            if (resourceId == null) {
                // 如果具体资源id为null，则获取某个项目下 || 某个项目下某个资源类别的具体资源个数
                ResourceExtend resourceExtend = resourceExtendBeanTool.getResourceExtendImpl();
                resourceCnt = resourceExtend.getResourceCnt(projectId, resourceTypeId);
                return hasResourceCnt == resourceCnt ? HasLevelCode.ALL : HasLevelCode.HALF;
            }
            // resourceId != null，则cnt最大为1
            return hasResourceCnt == resourceCnt ? HasLevelCode.ALL : HasLevelCode.NONE;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeResourceViewControlStatus() {
        // 先获取当前 资源查看权限控制状态
        boolean isOn = getViewPermissionControlStatus();
        if (isOn) {
            // 如果true，则之前已经打开了资源的查看权限控制，将要置为false，则所有人具有查看权限
            UserResourceQueryDTO queryDTO = UserResourceQueryDTO.getOpenViewPermissionControlQueryEntity();
            userResourceDao.deleteByUserId(0, queryDTO);
        } else {
            // 如果false，则之前关闭了资源的查看权限控制，将要置为true，则所有人不具有查看权限
            // 则等于查看权限level的记录都要被删除（因为查看权限控制将被置为false，所有人默认具有查看权限）
            userResourceDao.deleteByControlLevel(ControlLevelCode.VIEW);
            // 构造全0的数据，表示 资源查看权限控制 被开启了
            UserResource userResource = UserResource.getOpenViewPermissionControlEntity();
            userResourceDao.insert(userResource);
        }
    }

    @Override
    public ControlLevelCode getControlLevel(ControlLevelQueryDTO queryDTO) throws LogiSecurityException {
        if (queryDTO.getUserId() == null) {
            throw new LogiSecurityException(ResultCode.USER_ID_CANNOT_BE_NULL);
        }
        if (queryDTO.getProjectId() == null) {
            throw new LogiSecurityException(ResultCode.PROJECT_ID_CANNOT_BE_NULL);
        }
        if (queryDTO.getResourceTypeId() == null) {
            throw new LogiSecurityException(ResultCode.RESOURCE_TYPE_ID_CANNOT_BE_NULL);
        }
        if (queryDTO.getResourceId() == null) {
            throw new LogiSecurityException(ResultCode.RESOURCE_ID_CANNOT_BE_NULL);
        }
        Integer controlLevel = userResourceDao.selectControlLevel(queryDTO);
        if (controlLevel == null) {
            // 如果找不到，判断下全局查看权限控制是否开启
            if (!getViewPermissionControlStatus()) {
                // 如果没开启启，则默认拥有查看权限
                return ControlLevelCode.VIEW;
            }
            return ControlLevelCode.NONE;
        }
        return ControlLevelCode.getByType(controlLevel);
    }

    /**
     * 校验参数
     *
     * @param controlLevel   控制级别
     * @param projectId      项目id
     * @param resourceTypeId 资源类别id
     * @param resourceId     具体资源id
     */
    private void checkParam(Integer controlLevel, Integer projectId,
                            Integer resourceTypeId, Integer resourceId) throws LogiSecurityException {
        if (projectId == null) {
            // 项目id不可为nul
            throw new LogiSecurityException(ResultCode.PROJECT_ID_CANNOT_BE_NULL);
        }
        if (resourceTypeId == null && resourceId != null) {
            // 这种情况不允许出现（如果resourceId != null，则resourceTypeId必不为null）
            throw new LogiSecurityException(ResultCode.RESOURCE_ASSIGN_ERROR);
        }
        if (ControlLevelCode.getByType(controlLevel) == null) {
            throw new LogiSecurityException(ResultCode.RESOURCE_INVALID_CONTROL_LEVEL);
        }
    }

    private void checkParam(MByRDataQueryDTO queryDTO) throws LogiSecurityException {
        checkParam(
                queryDTO.getControlLevel(), queryDTO.getProjectId(),
                queryDTO.getResourceTypeId(), queryDTO.getResourceId()
        );
    }

    private void checkParam(MByUDataQueryDTO queryDTO) throws LogiSecurityException {
        if (queryDTO.getUserId() == null) {
            throw new LogiSecurityException(ResultCode.USER_ID_CANNOT_BE_NULL);
        }
        if (ControlLevelCode.getByType(queryDTO.getControlLevel()) == null) {
            throw new LogiSecurityException(ResultCode.RESOURCE_INVALID_CONTROL_LEVEL);
        }
        checkParam(queryDTO.getShowLevel(), queryDTO.getProjectId(), queryDTO.getResourceTypeId());
    }

    private void checkParam(AssignToOneUserDTO assignDTO) throws LogiSecurityException {
        if (assignDTO.getUserId() == null) {
            throw new LogiSecurityException(ResultCode.USER_ID_CANNOT_BE_NULL);
        }
        if (ControlLevelCode.getByType(assignDTO.getControlLevel()) == null) {
            throw new LogiSecurityException(ResultCode.RESOURCE_INVALID_CONTROL_LEVEL);
        }
        if (assignDTO.getProjectId() == null && assignDTO.getResourceTypeId() != null) {
            // 资源类别id不为null，则项目id不可为null
            throw new LogiSecurityException(ResultCode.RESOURCE_ASSIGN_ERROR_2);
        }
    }

    /**
     * 封装UserResourceList，便于批量插入数据库
     *
     * @param projectId      项目id
     * @param resourceTypeId 资源类型id
     * @param idList         projectId==null，idList为项目idList、resourceTypeId==null，idList为资源类别idList
     * @param controlLevel   资源控制权限level
     * @param userIdList     用户idList
     * @return List<UserResource>
     */
    private List<UserResource> getUserResourceList(Integer projectId, Integer resourceTypeId, int controlLevel,
                                                   List<Integer> idList, List<Integer> userIdList) {
        List<Integer> projectIdList;
        List<Integer> resourceTypeIdList;
        List<Integer> resourceIdList = null;

        if (projectId == null) {
            // 说明idList是项目的idList
            projectIdList = new ArrayList<>(idList);
            resourceTypeIdList = resourceTypeService.getAllResourceTypeIdList();
        } else if (resourceTypeId == null) {
            // 说明idList是资源类别idList
            projectIdList = new ArrayList<>();
            projectIdList.add(projectId);
            resourceTypeIdList = new ArrayList<>(idList);
        } else {
            // 说明idList是具体资源idList
            projectIdList = new ArrayList<>();
            projectIdList.add(projectId);
            resourceTypeIdList = new ArrayList<>();
            resourceTypeIdList.add(resourceTypeId);
            resourceIdList = new ArrayList<>(idList);
        }

        // 封装List<ResourceDto>
        List<ResourceDTO> resourceDTOList = getResourceDTOList(projectIdList, resourceTypeIdList, resourceIdList);

        return buildUserResourceList(controlLevel, userIdList, resourceDTOList);
    }

    private List<ResourceDTO> getResourceDTOList(List<Integer> projectIdList,
                                                 List<Integer> resourceTypeIdList,
                                                 List<Integer> resourceIdList) {
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        for (Integer projectId : projectIdList) {
            for (Integer resourceTypeId : resourceTypeIdList) {
                if (resourceIdList == null) {
                    ResourceExtend resourceExtend = resourceExtendBeanTool.getResourceExtendImpl();
                    List<ResourceDTO> list = resourceExtend.getResourceList(projectId, resourceTypeId);
                    if (list != null) {
                        resourceDTOList.addAll(list);
                    }
                    continue;
                }
                for (Integer resourceId : resourceIdList) {
                    resourceDTOList.add(new ResourceDTO(projectId, resourceTypeId, resourceId));
                }
            }
        }
        return resourceDTOList;
    }

    private List<UserResource> buildUserResourceList(int controlLevel, List<Integer> userIdList,
                                                     List<ResourceDTO> resourceDTOList) {
        List<UserResource> userResourceList = new ArrayList<>();
        for (Integer userId : userIdList) {
            for (ResourceDTO resourceDTO : resourceDTOList) {
                UserResource userResource = new UserResource(resourceDTO);
                userResource.setUserId(userId);
                userResource.setControlLevel(controlLevel);
                userResourceList.add(userResource);
            }
        }
        return userResourceList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignResourcePermission(AssignToOneUserDTO assignDTO) throws LogiSecurityException {
        // 检查参数
        checkParam(assignDTO);
        Integer userId = assignDTO.getUserId();
        Integer projectId = assignDTO.getProjectId();
        Integer resourceTypeId = assignDTO.getResourceTypeId();
        int controlLevel = assignDTO.getControlLevel();

        // 删除old的关联信息
        UserResourceQueryDTO queryDTO = new UserResourceQueryDTO(controlLevel, projectId, resourceTypeId);
        if (CollectionUtils.isEmpty(assignDTO.getExcludeIdList())) {
            // 如果不存在排除的idList，则
            userResourceDao.deleteByUserId(userId, queryDTO);
        } else if (projectId == null) {
            userResourceDao.deleteByUserIdWithoutProjectIdList(userId, queryDTO, assignDTO.getExcludeIdList());
        } else if (resourceTypeId == null) {
            userResourceDao.deleteByUserIdWithoutResourceTypeIdList(userId, queryDTO, assignDTO.getExcludeIdList());
        }

        List<Integer> idList = assignDTO.getIdList();
        List<Integer> userIdList = new ArrayList<>();
        userIdList.add(userId);
        List<UserResource> userResourceList = getUserResourceList(
                projectId, resourceTypeId, controlLevel, idList, userIdList
        );
        // 插入new关联信息
        userResourceDao.insertBatch(userResourceList);

        // 保存操作日志
        oplogService.saveOplogWithUserId(userId,
                new OplogDTO(OplogConstant.RPM, OplogConstant.RPM_AR, OplogConstant.RPM_U, "用户+资源名称"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignResourcePermission(AssignToManyUserDTO assignDTO,
                                         HttpServletRequest request) throws LogiSecurityException {
        // 检查参数
        checkParam(assignDTO);
        List<Integer> userIdList = assignDTO.getUserIdList();
        Integer projectId = assignDTO.getProjectId();
        Integer resourceTypeId = assignDTO.getResourceTypeId();
        Integer resourceId = assignDTO.getResourceId();
        int controlLevel = assignDTO.getControlLevel();

        // 删除old关联信息，并排除指定的id
        UserResourceQueryDTO queryDTO = new UserResourceQueryDTO(controlLevel, projectId, resourceTypeId, resourceId);
        userResourceDao.deleteWithoutUserIdList(queryDTO, assignDTO.getExcludeUserIdList());

        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        if (resourceId == null) {
            // resourceId为null，说明是某个项目或者某个资源类别下的全部具体资源的权限分配给用户，获取所有的具体资源信息
            ResourceExtend resourceExtend = resourceExtendBeanTool.getResourceExtendImpl();
            List<ResourceDTO> list = resourceExtend.getResourceList(projectId, resourceTypeId);
            if (list != null) {
                resourceDTOList.addAll(list);
            }
        } else {
            // 说明只有一个资源的权限分配给用户
            resourceDTOList.add(new ResourceDTO(projectId, resourceTypeId, resourceId));
        }
        // 插入new关联信息
        userResourceDao.insertBatch(buildUserResourceList(controlLevel, userIdList, resourceDTOList));

        // 保存操作日志
        oplogService.saveOplogWithUserId(HttpRequestUtil.getOperatorId(request),
                new OplogDTO(OplogConstant.RPM, OplogConstant.RPM_AU, OplogConstant.RPM_R, "资源名称+用户"));
    }

    /**
     * 批量分配前删除全部old的关联信息
     *
     * @param projectId      项目id
     * @param resourceTypeId 资源类别id
     * @param flag           批量分配用户 or 批量分配资源
     * @param controlLevel   资源权限控制级别
     * @param idList         用户idList、项目idList、资源类别idLIst、具体资源idList
     */
    private void deleteOldRelationBeforeBatchAssign(Integer projectId, Integer resourceTypeId,
                                                    boolean flag, int controlLevel, List<Integer> idList) {
        UserResourceQueryDTO queryDTO = new UserResourceQueryDTO(controlLevel, projectId, resourceTypeId);
        if (flag) {
            // 按资源管理/批量分配用户，先删除N资源先前已分配的用户
            if (projectId == null) {
                // 项目批量分配级别
                userResourceDao.deleteByProjectIdList(idList, queryDTO);
            } else if (resourceTypeId == null) {
                // 资源类别批量分配级别
                userResourceDao.deleteByResourceTypeIdList(idList, queryDTO);
            } else {
                // 具体资源批量分配级别
                userResourceDao.deleteByResourceIdList(idList, queryDTO);
            }
        } else {
            // 按用户管理/批量分配资源，先删除N用户先前分配的资源权限
            userResourceDao.deleteByUserIdList(idList, queryDTO);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAssignResourcePermission(BatchAssignDTO assignDTO,
                                              HttpServletRequest request) throws LogiSecurityException {
        // 检查参数
        checkParam(assignDTO);
        // 获取参数
        List<Integer> userIdList = assignDTO.getUserIdList();
        List<Integer> idList = assignDTO.getIdList();
        int controlLevel = assignDTO.getControlLevel();
        boolean assignFlag = assignDTO.getAssignFlag();
        Integer projectId = assignDTO.getProjectId();
        Integer resourceTypeId = assignDTO.getResourceTypeId();
        // 先删除全部old关联信息
        deleteOldRelationBeforeBatchAssign(projectId, resourceTypeId, assignFlag, controlLevel, idList);
        // 插入新关联信息
        userResourceDao.insertBatch(getUserResourceList(projectId, resourceTypeId, controlLevel, idList, userIdList));

        Integer userId = HttpRequestUtil.getOperatorId(request);
        if (assignFlag) {
            // 保存操作日志
            oplogService.saveOplogWithUserId(userId,
                    new OplogDTO(OplogConstant.RPM, OplogConstant.RPM_BAU, OplogConstant.RPM_R, "资源名称+用户"));
        } else {
            // 保存操作日志
            oplogService.saveOplogWithUserId(userId,
                    new OplogDTO(OplogConstant.RPM, OplogConstant.RPM_BAR, OplogConstant.RPM_U, "用户+资源名称"));
        }
    }

    private void checkParam(BatchAssignDTO assignDTO) throws LogiSecurityException {
        if (assignDTO.getUserIdList() == null) {
            throw new LogiSecurityException(ResultCode.USER_ID_CANNOT_BE_NULL);
        }
        if (assignDTO.getAssignFlag() == null) {
            throw new LogiSecurityException(ResultCode.RESOURCE_ASSIGN_BATCH_FLAG_CANNOT_BE_NULL);
        }
        if (assignDTO.getProjectId() == null && assignDTO.getResourceTypeId() != null) {
            // 资源类别id不为null，则项目id不可为null
            throw new LogiSecurityException(ResultCode.RESOURCE_ASSIGN_ERROR_2);
        }
        if (ControlLevelCode.getByType(assignDTO.getControlLevel()) == null) {
            throw new LogiSecurityException(ResultCode.RESOURCE_INVALID_CONTROL_LEVEL);
        }
    }

    private void checkParam(AssignToManyUserDTO assignDTO) throws LogiSecurityException {
        checkParam(
                assignDTO.getControlLevel(), assignDTO.getProjectId(),
                assignDTO.getResourceTypeId(), assignDTO.getResourceId()
        );
    }

    //--------------------------资源权限管理（按用户管理）begin--------------------------

    @Override
    public PagingData<MByUVO> getManageByUserPage(MByUQueryDTO queryDTO) {

        // 提前获取所有部门
        Map<Integer, Dept> deptMap = deptService.getAllDeptMap();
        PagingData<UserBriefVO> userPage = userService.getUserBriefPage(new UserBriefQueryDTO(queryDTO));
        List<MByUVO> result = Collections.synchronizedList(new ArrayList<>());

        // 判断 资源查看控制权限 是否开启
        final boolean isOn = getViewPermissionControlStatus();
        userPage.getBizData().parallelStream().forEach(userBriefVO -> {
            MByUVO dataVo = CopyBeanUtil.copy(userBriefVO, MByUVO.class);
            dataVo.setUserId(userBriefVO.getId());
            // 设置部门信息
            dataVo.setDeptList(deptService.getDeptBriefListFromDeptMapByChildId(deptMap, userBriefVO.getDeptId()));
            // 计算管理权限资源数
            dataVo.setAdminResourceCnt(
                    userResourceDao.selectCountByUserIdAndControlLevel(userBriefVO.getId(), ControlLevelCode.ADMIN)
            );
            // 如果 资源查看控制权限 没开启，就不计算了
            if (isOn) {
                // 计算查看权限资源数
                dataVo.setViewResourceCnt(
                        userResourceDao.selectCountByUserIdAndControlLevel(userBriefVO.getId(), ControlLevelCode.VIEW)
                );
            }
            result.add(dataVo);
        });

        return new PagingData<>(result, userPage.getPagination());
    }

    //--------------------------资源权限管理（按用户管理）end--------------------------

    //--------------------------资源权限管理（按资源管理）begin--------------------------

    @Override
    public PagingData<MByRVO> getManageByResourcePage(MByRQueryDTO queryDTO) throws LogiSecurityException {
        // 检查参数
        checkParam(queryDTO);

        // 判断 资源查看控制权限 是否开启
        boolean isOn = getViewPermissionControlStatus();
        PagingData<MByRVO> result;
        if (queryDTO.getShowLevel().equals(ShowLevelCode.PROJECT.getType())) {
            // 项目展示级别，表示查找所有项目
            result = dealProjectLevel(queryDTO, isOn);
        } else if (queryDTO.getShowLevel().equals(ShowLevelCode.RESOURCE_TYPE.getType())) {
            // 资源类别展示级别，表示查找某个项目下所有资源类别
            result = dealResourceTypeLevel(queryDTO, isOn);
        } else {
            // 具体资源展示级别，表示查找该项目下该资源类别对应的资源
            result = dealResourceLevel(queryDTO, isOn);
        }
        return result;
    }

    private void checkParam(Integer showLevel, Integer projectId,
                            Integer resourceTypeId) throws LogiSecurityException {
        if (ShowLevelCode.getByType(showLevel) == null) {
            // 请输入有效的展示级别（1 <= showLevel <= 3）
            throw new LogiSecurityException(ResultCode.RESOURCE_INVALID_SHOW_LEVEL);
        }
        if (showLevel >= ShowLevelCode.RESOURCE_TYPE.getType()) {
            // 资源类别展示级别，表示查找某个项目下所有资源类别
            if (projectId == null) {
                // 2级展示级别，项目id不可为null
                throw new LogiSecurityException(ResultCode.RESOURCE_SHOW_LEVEL_ERROR);
            }
            ProjectBriefVO projectBriefVO = projectService.getProjectBriefByProjectId(projectId);
            if (projectBriefVO == null) {
                throw new LogiSecurityException(ResultCode.PROJECT_NOT_EXISTS);
            }
        }
        if (showLevel >= ShowLevelCode.RESOURCE.getType()) {
            // 具体资源展示级别，表示查找该项目下该资源类别对应的资源
            if (resourceTypeId == null) {
                // 3级展示级别，项目id或资源类别id不可为null
                throw new LogiSecurityException(ResultCode.RESOURCE_SHOW_LEVEL_ERROR_2);
            }
            ResourceTypeVO resourceTypeVO = resourceTypeService.getResourceTypeByResourceTypeId(resourceTypeId);
            if (resourceTypeVO == null) {
                throw new LogiSecurityException(ResultCode.RESOURCE_TYPE_NOT_EXISTS);
            }
        }
    }

    private void checkParam(MByRQueryDTO queryDTO) throws LogiSecurityException {
        checkParam(queryDTO.getShowLevel(), queryDTO.getProjectId(), queryDTO.getResourceTypeId());
    }

    private int getAdminOrViewUserCnt(UserResourceQueryDTO queryDTO) {
        List<Integer> userIdList = userResourceDao.selectUserIdListGroupByUserId(queryDTO);
        ResourceExtend resourceExtend = resourceExtendBeanTool.getResourceExtendImpl();
        int total = resourceExtend.getResourceCnt(queryDTO.getProjectId(), queryDTO.getResourceTypeId());
        int cnt = userIdList.size();
        for (Integer userId : userIdList) {
            if (total != userResourceDao.selectCountByUserId(userId, queryDTO)) {
                // 该用户不拥有 该项目下 或 该项目下某资源类别下 的全部具体资源
                cnt--;
            }
        }
        return cnt;
    }

    /**
     * 资源权限管理>按资源管理的列表信息>项目级别
     *
     * @param queryDTO 查询条件
     * @param isOn         资源查看控制权限是否开启
     * @return PagingData<ManageByResourceVo>
     */
    private PagingData<MByRVO> dealProjectLevel(MByRQueryDTO queryDTO, boolean isOn) {
        ProjectBriefQueryDTO projectBriefQueryDTO = new ProjectBriefQueryDTO(queryDTO);
        PagingData<ProjectBriefVO> projectPage = projectService.getProjectBriefPage(projectBriefQueryDTO);

        List<MByRVO> result = Collections.synchronizedList(new ArrayList<>());
        projectPage.getBizData().parallelStream().forEach(projectBriefVO -> {
            MByRVO data = new MByRVO();
            data.setProjectId(projectBriefVO.getId());
            data.setProjectCode(projectBriefVO.getProjectCode());
            data.setProjectName(projectBriefVO.getProjectName());

            Integer projectId = projectBriefVO.getId();
            // 获取管理权限用户数
            UserResourceQueryDTO queryDTO2 = new UserResourceQueryDTO(ControlLevelCode.ADMIN.getType(), projectId);
            data.setAdminUserCnt(getAdminOrViewUserCnt(queryDTO2));

            if (isOn) {
                // 获取查看权限用户数
                queryDTO2 = new UserResourceQueryDTO(ControlLevelCode.VIEW.getType(), projectId);
                data.setViewUserCnt(getAdminOrViewUserCnt(queryDTO2));
            }
            result.add(data);
        });
        return new PagingData<>(result, projectPage.getPagination());
    }

    /**
     * 资源权限管理>按资源管理的列表信息>资源列别级别
     *
     * @param queryDTO 查询条件
     * @param isOn     资源查看控制权限是否开启
     * @return PagingData<ManageByResourceVo>
     */
    private PagingData<MByRVO> dealResourceTypeLevel(MByRQueryDTO queryDTO, boolean isOn) {
        List<MByRVO> result = Collections.synchronizedList(new ArrayList<>());
        ResourceTypeQueryDTO resourceTypeQueryDTO = new ResourceTypeQueryDTO(queryDTO);
        PagingData<ResourceTypeVO> resourceTypePage = resourceTypeService.getResourceTypePage(resourceTypeQueryDTO);

        // 获取项目信息
        ProjectBriefVO projectBriefVO = projectService.getProjectBriefByProjectId(queryDTO.getProjectId());
        resourceTypePage.getBizData().parallelStream().forEach(resourceTypeVO -> {
            MByRVO data = new MByRVO();
            data.setResourceTypeId(resourceTypeVO.getId());
            data.setResourceTypeName(resourceTypeVO.getTypeName());
            data.setProjectId(queryDTO.getProjectId());
            data.setProjectName(projectBriefVO.getProjectName());

            // 获取管理权限用户数
            UserResourceQueryDTO queryDTO2 = new UserResourceQueryDTO(
                    ControlLevelCode.ADMIN.getType(), queryDTO.getProjectId(), resourceTypeVO.getId());
            data.setAdminUserCnt(getAdminOrViewUserCnt(queryDTO2));

            if (isOn) {
                // 获取查看权限用户数
                queryDTO2 = new UserResourceQueryDTO(
                        ControlLevelCode.VIEW.getType(), queryDTO.getProjectId(), resourceTypeVO.getId());
                data.setViewUserCnt(getAdminOrViewUserCnt(queryDTO2));
            }
            result.add(data);
        });
        return new PagingData<>(result, resourceTypePage.getPagination());
    }

    /**
     * 资源权限管理>按资源管理的列表信息>资源级别
     *
     * @param queryDTO 查询条件
     * @param isOn     资源查看控制权限是否开启
     * @return PagingData<ManageByResourceVo>
     */
    private PagingData<MByRVO> dealResourceLevel(MByRQueryDTO queryDTO, boolean isOn) {
        // 调用扩展接口获取具体资源信息
        ResourceExtend resourceExtend = resourceExtendBeanTool.getResourceExtendImpl();
        PagingData<ResourceDTO> page = resourceExtend.getResourcePage(
                queryDTO.getProjectId(), queryDTO.getResourceTypeId(),
                queryDTO.getName(), queryDTO.getPage(), queryDTO.getSize()
        );
        if (page == null) {
            return new PagingData<>();
        }

        List<MByRVO> list = new ArrayList<>();
        // 获取资源类别信息
        Integer projectId = queryDTO.getProjectId();
        Integer resourceTypeId = queryDTO.getResourceTypeId();
        ResourceTypeVO resourceTypeVO = resourceTypeService.getResourceTypeByResourceTypeId(resourceTypeId);

        for (ResourceDTO resourceDTO : page.getBizData()) {
            MByRVO data = new MByRVO();
            data.setResourceTypeId(resourceTypeVO.getId());
            data.setResourceTypeName(resourceTypeVO.getTypeName());
            data.setProjectId(queryDTO.getProjectId());
            data.setResourceId(resourceDTO.getResourceId());
            data.setResourceName(resourceDTO.getResourceName());

            // 获取管理权限用户数
            UserResourceQueryDTO queryDTO2 = new UserResourceQueryDTO(
                    ControlLevelCode.ADMIN.getType(), projectId, resourceTypeId, resourceDTO.getResourceId());
            data.setAdminUserCnt(userResourceDao.selectCountGroupByUserId(queryDTO2));

            if (isOn) {
                // 获取查看权限用户数
                queryDTO2 = new UserResourceQueryDTO(
                        ControlLevelCode.VIEW.getType(), projectId, resourceTypeId, resourceDTO.getResourceId());
                data.setViewUserCnt(userResourceDao.selectCountGroupByUserId(queryDTO2));
            }
            list.add(data);
        }
        return new PagingData<>(list, page.getPagination());
    }

    //--------------------------资源权限管理（按资源管理）end--------------------------

}
