package com.didiglobal.logi.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.constant.OplogConstant;
import com.didiglobal.logi.security.common.dto.oplog.OplogDTO;
import com.didiglobal.logi.security.common.dto.project.ProjectBriefQueryDTO;
import com.didiglobal.logi.security.common.dto.resource.ResourceDTO;
import com.didiglobal.logi.security.common.entity.dept.Dept;
import com.didiglobal.logi.security.common.entity.project.Project;
import com.didiglobal.logi.security.common.entity.project.ProjectBrief;
import com.didiglobal.logi.security.common.enums.ResultCode;
import com.didiglobal.logi.security.common.dto.project.ProjectQueryDTO;
import com.didiglobal.logi.security.common.dto.project.ProjectSaveDTO;
import com.didiglobal.logi.security.common.vo.project.ProjectBriefVO;
import com.didiglobal.logi.security.common.vo.project.ProjectDeleteCheckVO;
import com.didiglobal.logi.security.common.vo.project.ProjectVO;
import com.didiglobal.logi.security.dao.ProjectDao;
import com.didiglobal.logi.security.exception.LogiSecurityException;
import com.didiglobal.logi.security.extend.ResourceExtend;
import com.didiglobal.logi.security.extend.ResourceExtendBeanTool;
import com.didiglobal.logi.security.service.*;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import com.didiglobal.logi.security.util.HttpRequestUtil;
import com.didiglobal.logi.security.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author cjm
 */
@Service("logiSecurityProjectServiceImpl")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private DeptService deptService;

    @Autowired
    private OplogService oplogService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserProjectService userProjectService;

    @Autowired
    private ResourceExtendBeanTool resourceExtendBeanTool;

    @Override
    public ProjectVO getProjectDetailByProjectId(Integer projectId) throws LogiSecurityException {
        Project project = projectDao.selectByProjectId(projectId);
        if(project == null) {
            throw new LogiSecurityException(ResultCode.PROJECT_NOT_EXISTS);
        }
        ProjectVO projectVO = CopyBeanUtil.copy(project, ProjectVO.class);
        // 获取负责人信息
        List<Integer> userIdList = userProjectService.getUserIdListByProjectId(projectId);
        projectVO.setUserList(userService.getUserBriefListByUserIdList(userIdList));
        // 获取部门信息
        projectVO.setDeptList(deptService.getDeptBriefListByChildId(projectVO.getDeptId()));
        projectVO.setCreateTime(project.getCreateTime().getTime());
        return projectVO;
    }

    @Override
    public ProjectBriefVO getProjectBriefByProjectId(Integer projectId) {
        if(projectId == null) {
            return null;
        }
        Project project = projectDao.selectByProjectId(projectId);
        return CopyBeanUtil.copy(project, ProjectBriefVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectVO createProject(ProjectSaveDTO saveVo, HttpServletRequest request) throws LogiSecurityException {
        // 检查参数
        checkParam(saveVo, false);
        Project project = CopyBeanUtil.copy(saveVo, Project.class);
        project.setProjectCode("p" + MathUtil.getRandomNumber(7));
        projectDao.insert(project);
        // 插入用户项目关联信息（项目负责人）
        userProjectService.saveUserProject(project.getId(), saveVo.getUserIdList());
        // 保存操作日志
        oplogService.saveOplogWithUserId(HttpRequestUtil.getOperatorId(request),
                new OplogDTO(OplogConstant.PM, OplogConstant.PM_A, OplogConstant.PM_P, saveVo.getProjectName()));
        return CopyBeanUtil.copy(project, ProjectVO.class);
    }

    @Override
    public PagingData<ProjectVO> getProjectPage(ProjectQueryDTO queryDTO) {
        List<Integer> projectIdList = null;
        // 是否有负责人条件
        if(!StringUtils.isEmpty(queryDTO.getChargeUsername())) {
            List<Integer> userIdList = userService.getUserIdListByUsernameOrRealName(queryDTO.getChargeUsername());
            projectIdList = userProjectService.getProjectIdListByUserIdList(userIdList);
        }
        // 获取当前部门的子部门idList
        List<Integer> deptIdList = deptService.getDeptIdListByParentId(queryDTO.getDeptId());
        // 分页获取
        IPage<Project> page = projectDao.selectPageByDeptIdListAndProjectIdList(queryDTO, deptIdList, projectIdList);
        List<ProjectVO> projectVOList = new ArrayList<>();

        // 提前获取所有部门
        Map<Integer, Dept> deptMap = deptService.getAllDeptMap();
        for(Project project : page.getRecords()) {
            ProjectVO projectVO = CopyBeanUtil.copy(project, ProjectVO.class);
            // 获取负责人信息
            List<Integer> userIdList = userProjectService.getUserIdListByProjectId(project.getId());
            projectVO.setUserList(userService.getUserBriefListByUserIdList(userIdList));
            // 获取部门信息
            projectVO.setDeptList(deptService.getDeptBriefListFromDeptMapByChildId(deptMap, project.getDeptId()));
            projectVO.setCreateTime(project.getCreateTime().getTime());
            projectVOList.add(projectVO);
        }
        return new PagingData<>(projectVOList, page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProjectByProjectId(Integer projectId, HttpServletRequest request) {
        Project project = projectDao.selectByProjectId(projectId);
        if(project == null) {
            return;
        }
        // 删除前要判断一下有没有服务引用了这个项目，有没有具体资源引用了这个项目
        // 删除项目与负责人的联系
        userProjectService.deleteUserProjectByProjectId(projectId);
        // 逻辑删除项目（自动）
        projectDao.deleteByProjectId(projectId);
        // 保存操作日志
        oplogService.saveOplogWithUserId(HttpRequestUtil.getOperatorId(request),
                new OplogDTO(OplogConstant.PM, OplogConstant.PM_D, OplogConstant.PM_P, project.getProjectName()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProject(ProjectSaveDTO saveDTO, HttpServletRequest request) throws LogiSecurityException {
        if(projectDao.selectByProjectId(saveDTO.getId()) == null) {
            throw new LogiSecurityException(ResultCode.PROJECT_NOT_EXISTS);
        }
        // 检查参数
        checkParam(saveDTO, true);
        // 先更新项目基本信息
        Project project = CopyBeanUtil.copy(saveDTO, Project.class);
        projectDao.update(project);
        // 更新项目负责人与项目联系
        userProjectService.updateUserProject(saveDTO.getId(), saveDTO.getUserIdList());
        // 保存操作日志
        oplogService.saveOplogWithUserId(HttpRequestUtil.getOperatorId(request),
                new OplogDTO(OplogConstant.PM, OplogConstant.PM_E, OplogConstant.PM_P, saveDTO.getProjectName()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeProjectStatus(Integer projectId, HttpServletRequest request) {
        Project project = projectDao.selectByProjectId(projectId);
        if (project == null) {
            return;
        }
        // 状态取反
        project.setRunning(!project.getRunning());
        projectDao.update(project);
        // 保存操作日志
        String curRunningTag = Boolean.TRUE.equals(project.getRunning()) ? OplogConstant.PM_U : OplogConstant.PM_S;
        oplogService.saveOplogWithUserId(HttpRequestUtil.getOperatorId(request),
                new OplogDTO(OplogConstant.PM, curRunningTag, OplogConstant.PM_P, project.getProjectName()));
    }

    @Override
    public List<ProjectBriefVO> getProjectBriefList() {
        List<ProjectBrief> projectBriefList = projectDao.selectAllBriefList();
        return CopyBeanUtil.copyList(projectBriefList, ProjectBriefVO.class);
    }

    @Override
    public ProjectDeleteCheckVO checkBeforeDelete(Integer projectId) {
        ProjectDeleteCheckVO projectDeleteCheckVO = new ProjectDeleteCheckVO(projectId);
        if(projectDao.selectByProjectId(projectId) == null) {
            return projectDeleteCheckVO;
        }
        // 获取与该项目相关联的服务
        // 获取与该项目相关联的具体资源
        ResourceExtend resourceExtend = resourceExtendBeanTool.getResourceExtendImpl();
        List<ResourceDTO> resourceDTOList = resourceExtend.getResourceList(projectId, null);
        if(!CollectionUtils.isEmpty(resourceDTOList)) {
            List<String> list = resourceDTOList
                    .stream().map(ResourceDTO::getResourceName).collect(Collectors.toList());
            projectDeleteCheckVO.setResourceNameList(list);
        }
        return projectDeleteCheckVO;
    }

    @Override
    public PagingData<ProjectBriefVO> getProjectBriefPage(ProjectBriefQueryDTO queryDTO) {
        IPage<ProjectBrief> pageInfo = projectDao.selectBriefPage(queryDTO);
        List<ProjectBriefVO> list = CopyBeanUtil.copyList(pageInfo.getRecords(), ProjectBriefVO.class);
        return new PagingData<>(list, pageInfo);
    }

    /**
     * 校验参数
     * @param saveVo 项目参数
     * @param isUpdate 创建 or 更新
     */
    private void checkParam(ProjectSaveDTO saveVo, boolean isUpdate) throws LogiSecurityException {
        if(StringUtils.isEmpty(saveVo.getProjectName())) {
            throw new LogiSecurityException(ResultCode.PROJECT_NAME_CANNOT_BE_BLANK);
        }
        if(saveVo.getDeptId() == null) {
            throw new LogiSecurityException(ResultCode.PROJECT_DEPT_CANNOT_BE_NULL);
        }
        if(StringUtils.isEmpty(saveVo.getDescription())) {
            throw new LogiSecurityException(ResultCode.PROJECT_DES_CANNOT_BE_BLANK);
        }
        if(CollectionUtils.isEmpty(saveVo.getUserIdList())) {
            throw new LogiSecurityException(ResultCode.PROJECT_CHARGE_USER_CANNOT_BE_NULL);
        }
        // 如果是更新操作，则判断项目名重复的时候要排除old信息
        Integer projectId = isUpdate ? saveVo.getId() : null;
        int count = projectDao.selectCountByProjectNameAndNotProjectId(saveVo.getProjectName(), projectId);
        if(count > 0) {
            // 项目名不可重复
            throw new LogiSecurityException(ResultCode.PROJECT_NAME_ALREADY_EXISTS);
        }
    }
}
