package com.didiglobal.logi.security.dao;

import com.didiglobal.logi.security.common.entity.UserProject;

import java.util.List;

/**
 * @author cjm
 */
public interface UserProjectDao {

    /**
     * 根据项目id查负责人用户idList
     * @param projectId 项目id
     * @return 用户idList
     */
    List<Integer> selectUserIdListByProjectId(Integer projectId);

    /**
     * 根据用户idList查找项目idList
     * @param userIdList 用户idList
     * @return 项目idList
     */
    List<Integer> selectProjectIdListByUserIdList(List<Integer> userIdList);

    /**
     * 批量插入用户与项目的关联信息
     * @param userProjectList 用户与项目关联信息
     */
    void insertBatch(List<UserProject> userProjectList);

    /**
     * 根据项目id删除用户与项目的关联信息
     * @param projectId 项目id
     */
    void deleteByProjectId(Integer projectId);
}
