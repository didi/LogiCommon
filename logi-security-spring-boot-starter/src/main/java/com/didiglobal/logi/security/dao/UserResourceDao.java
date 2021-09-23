package com.didiglobal.logi.security.dao;

import com.didiglobal.logi.security.common.dto.resource.ControlLevelQueryDTO;
import com.didiglobal.logi.security.common.dto.resource.UserResourceQueryDTO;
import com.didiglobal.logi.security.common.entity.UserResource;
import com.didiglobal.logi.security.common.enums.resource.ControlLevelCode;

import java.util.List;

/**
 * @author cjm
 */
public interface UserResourceDao {

    /**
     * 根据用户id，和指定条件，查询用户与具体资源的关联数
     * @param userId 用户id
     * @param queryDTO 条件
     * @return 用户与具体资源的关联数
     */
    int selectCountByUserId(Integer userId, UserResourceQueryDTO queryDTO);

    /**
     * 根据用户id，和指定条件，删除用户与具体资源关联
     * @param userId 用户id
     * @param queryDTO 查询条件
     */
    void deleteByUserId(Integer userId, UserResourceQueryDTO queryDTO);

    /**
     * 根据资源控制权限级别删除
     * @param controlLevel 资源控制权限级别
     */
    void deleteByControlLevel(ControlLevelCode controlLevel);

    /**
     * 插入
     * @param userResource 用户与资源关联信息
     */
    void insert(UserResource userResource);

    /**
     * 批量插入
     * @param userResourceList 用于与资源的关联信息
     */
    void insertBatch(List<UserResource> userResourceList);

    /**
     * 根据用户idList删除
     * @param userIdList 用户idList
     * @param queryDTO 其他条件
     */
    void deleteByUserIdList(List<Integer> userIdList, UserResourceQueryDTO queryDTO);

    /**
     * 根据项目idList删除
     * @param projectIdList 项目idList
     * @param queryDTO 其他条件
     */
    void deleteByProjectIdList(List<Integer> projectIdList, UserResourceQueryDTO queryDTO);

    /**
     * 根据资源类别idList删除
     * @param resourceTypeIdList 资源类别idList
     * @param queryDTO 其他条件
     */
    void deleteByResourceTypeIdList(List<Integer> resourceTypeIdList, UserResourceQueryDTO queryDTO);

    /**
     * 根据资源idList删除
     * @param resourceIdList 资源idList
     * @param queryDTO 其他条件
     */
    void deleteByResourceIdList(List<Integer> resourceIdList, UserResourceQueryDTO queryDTO);

    /**
     * 根据用户id和权限级别获取用户资源关联数
     * @param userId 用户id
     * @param controlLevel 权限级别
     * @return 用户资源关联数
     */
    int selectCountByUserIdAndControlLevel(Integer userId, ControlLevelCode controlLevel);

    /**
     * 根据指定条件查询
     * @param queryDTO 查询条件
     * @return 用户资源关联数
     */
    int selectCount(UserResourceQueryDTO queryDTO);

    /**
     * 根据用户id和其他条件获取具体资源idList
     * @param userId 用户id
     * @param queryDTO 查询条件
     * @return 具体资源idList
     */
    List<Integer> selectResourceIdListByUserId(Integer userId, UserResourceQueryDTO queryDTO);

    /**
     * 删除用户与资源的关联信息
     * @param queryDTO 删除条件
     * @param excludeUserIdList 不删除与这些userId关联的数据
     */
    void deleteWithoutUserIdList(UserResourceQueryDTO queryDTO, List<Integer> excludeUserIdList);

    /**
     * 删除用户与资源的关联信息
     * @param userId 用户id
     * @param queryDTO 删除条件
     * @param excludeIdList 不删除与这些projectId关联的数据
     */
    void deleteByUserIdWithoutProjectIdList(
            Integer userId, UserResourceQueryDTO queryDTO, List<Integer> excludeIdList);

    /**
     * 删除用户与资源的关联信息
     * @param userId 用户id
     * @param queryDTO 删除条件
     * @param excludeIdList 不删除与这些resourceTypeId关联的数据
     */
    void deleteByUserIdWithoutResourceTypeIdList(
            Integer userId, UserResourceQueryDTO queryDTO, List<Integer> excludeIdList);

    /**
     * 根据指定条件查询，并根据用户id分组
     * @param queryDTO 查询条件
     * @return 用户资源关联数
     */
    int selectCountGroupByUserId(UserResourceQueryDTO queryDTO);

    List<Integer> selectUserIdListGroupByUserId(UserResourceQueryDTO queryDTO);

    Integer selectControlLevel(ControlLevelQueryDTO queryDTO);
}
