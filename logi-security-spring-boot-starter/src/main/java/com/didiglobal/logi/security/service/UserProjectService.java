package com.didiglobal.logi.security.service;

import java.util.List;

/**
 * @author cjm
 */
public interface UserProjectService {

    /**
     * 根据项目id，获取用户idList
     * @param projectId 项目id
     * @return 用户idList
     */
    List<Integer> getUserIdListByProjectId(Integer projectId);

    /**
     * 根据用户idList，获取项目idList
     * @param userIdList 用户idList
     * @return 项目idList
     */
    List<Integer> getProjectIdListByUserIdList(List<Integer> userIdList);

    /**
     * 保存用户与项目的关联信息
     * @param projectId 项目id
     * @param userIdList 用户idList
     */
    void saveUserProject(Integer projectId, List<Integer> userIdList);

    /**
     * 更新用户与项目的关联信息，保存新关系之前会删除old的关联信息
     * @param projectId 项目id
     * @param userIdList 用户idList
     */
    void updateUserProject(Integer projectId, List<Integer> userIdList);


    /**
     * 删除用户与项目的关联信息
     * @param projectId 项目id
     */
    void deleteUserProjectByProjectId(Integer projectId);
}
