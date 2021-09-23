package com.didiglobal.logi.security.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.didiglobal.logi.security.common.dto.project.ProjectBriefQueryDTO;
import com.didiglobal.logi.security.common.dto.project.ProjectQueryDTO;
import com.didiglobal.logi.security.common.entity.project.Project;
import com.didiglobal.logi.security.common.entity.project.ProjectBrief;
import com.didiglobal.logi.security.common.po.ProjectPO;
import com.didiglobal.logi.security.dao.ProjectDao;
import com.didiglobal.logi.security.dao.mapper.ProjectMapper;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author cjm
 */
@Component
public class ProjectDaoImpl extends BaseDaoImpl<ProjectPO> implements ProjectDao {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Project selectByProjectId(Integer projectId) {
        QueryWrapper<ProjectPO> queryWrapper = getQueryWrapper();
        queryWrapper.eq(FieldConstant.ID, projectId);
        return CopyBeanUtil.copy(projectMapper.selectOne(queryWrapper), Project.class);
    }

    @Override
    public void insert(Project project) {
        ProjectPO projectPO = CopyBeanUtil.copy(project, ProjectPO.class);
        projectMapper.insert(projectPO);
        project.setId(projectPO.getId());
    }

    @Override
    public void deleteByProjectId(Integer projectId) {
        projectMapper.deleteById(projectId);
    }

    @Override
    public void update(Project project) {
        projectMapper.updateById(CopyBeanUtil.copy(project, ProjectPO.class));
    }

    @Override
    public int selectCountByProjectNameAndNotProjectId(String projectName, Integer projectId) {
        QueryWrapper<ProjectPO> queryWrapper = getQueryWrapper();
        queryWrapper
                .eq(FieldConstant.PROJECT_NAME, projectName)
                .ne(projectId != null, FieldConstant.ID, projectId);
        return projectMapper.selectCount(queryWrapper);
    }

    private QueryWrapper<ProjectPO> wrapBriefQuery() {
        QueryWrapper<ProjectPO> queryWrapper = getQueryWrapper();
        queryWrapper.select(FieldConstant.ID, FieldConstant.PROJECT_CODE, FieldConstant.PROJECT_NAME);
        return queryWrapper;
    }

    @Override
    public IPage<ProjectBrief> selectBriefPage(ProjectBriefQueryDTO queryDTO) {
        IPage<ProjectPO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());
        QueryWrapper<ProjectPO> projectWrapper = wrapBriefQuery();
        String projectName = queryDTO.getProjectName();
        if(!StringUtils.isEmpty(projectName)) {
            projectWrapper.like(FieldConstant.PROJECT_NAME, projectName);
        }
        projectMapper.selectPage(page, projectWrapper);
        return CopyBeanUtil.copyPage(page, ProjectBrief.class);
    }

    @Override
    public List<ProjectBrief> selectAllBriefList() {
        return CopyBeanUtil.copyList(projectMapper.selectList(wrapBriefQuery()), ProjectBrief.class);
    }

    @Override
    public IPage<Project> selectPageByDeptIdListAndProjectIdList(ProjectQueryDTO queryDTO, List<Integer> deptIdList,
                                                                 List<Integer> projectIdList) {
        IPage<ProjectPO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());
        if(deptIdList != null && deptIdList.isEmpty()) {
            return CopyBeanUtil.copyPage(page, Project.class);
        }
        if(projectIdList != null && projectIdList.isEmpty()) {
            return CopyBeanUtil.copyPage(page, Project.class);
        }
        QueryWrapper<ProjectPO> projectWrapper = getQueryWrapper();
        String projectCode = queryDTO.getProjectCode();
        String projectName = queryDTO.getProjectName();
        projectWrapper
                .eq(queryDTO.getRunning() != null, FieldConstant.RUNNING, queryDTO.getRunning())
                .eq(!StringUtils.isEmpty(projectCode), FieldConstant.PROJECT_CODE, projectCode)
                .like(!StringUtils.isEmpty(projectName), FieldConstant.PROJECT_NAME, projectName)
                .in(deptIdList != null, FieldConstant.DEPT_ID, deptIdList)
                .in(projectIdList != null, FieldConstant.ID, projectIdList);
        return CopyBeanUtil.copyPage(projectMapper.selectPage(page, projectWrapper), Project.class);
    }
}
