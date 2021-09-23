package com.didiglobal.logi.security.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.didiglobal.logi.security.common.dto.project.ProjectBriefQueryDTO;
import com.didiglobal.logi.security.common.dto.project.ProjectQueryDTO;
import com.didiglobal.logi.security.common.entity.project.Project;
import com.didiglobal.logi.security.common.entity.project.ProjectBrief;

import java.util.List;

/**
 * @author cjm
 */
public interface ProjectDao {

    /**
     * 根据项目id获取项目信息
     * @param projectId 项目id
     * @return 项目信息
     */
    Project selectByProjectId(Integer projectId);

    /**
     * 新增
     * @param project 项目信息
     */
    void insert(Project project);

    /**
     * 根据指定条件分页查询
     * @param queryDTO 查询条件
     * @param deptIdList 部门idList
     * @param projectIdList 项目idList
     * @return 分页信息
     */
    IPage<Project> selectPageByDeptIdListAndProjectIdList(
            ProjectQueryDTO queryDTO, List<Integer> deptIdList, List<Integer> projectIdList
    );

    /**
     * 根据项目id删除
     * @param projectId 项目id
     */
    void deleteByProjectId(Integer projectId);

    /**
     * 根据项目名查询符合数据数
     * 如果是更新操作，则判断项目名重复的时候要排除old项目id信息
     * @param projectName 项目名
     * @param projectId 项目id
     * @return 项目数
     */
    int selectCountByProjectNameAndNotProjectId(String projectName, Integer projectId);

    /**
     * 分页获取项目简要信息
     * @param queryDTO 查询条件
     * @return 项目简要信息page
     */
    IPage<ProjectBrief> selectBriefPage(ProjectBriefQueryDTO queryDTO);

    /**
     * 获取所有项目简要信息
     * @return 项目简要信息List
     */
    List<ProjectBrief> selectAllBriefList();

    /**
     * 更新项目
     * @param project 项目信息
     */
    void update(Project project);
}
