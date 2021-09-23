package com.didiglobal.logi.security.service;

import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.dto.resource.*;
import com.didiglobal.logi.security.common.enums.resource.ControlLevelCode;
import com.didiglobal.logi.security.common.vo.resource.MByRDataVO;
import com.didiglobal.logi.security.common.vo.resource.MByRVO;
import com.didiglobal.logi.security.common.vo.resource.MByUDataVO;
import com.didiglobal.logi.security.common.vo.resource.MByUVO;
import com.didiglobal.logi.security.exception.LogiSecurityException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cjm
 * 管理用户拥有的资源权限
 */
public interface UserResourceService {

    /**
     * 根据用户id和指定条件，获取用户拥有权限的具体资源个数
     * @param userId 用户id
     * @param queryDTO 查询条件
     * @return 用户idList
     */
    int getResourceCntByUserId(Integer userId, UserResourceQueryDTO queryDTO);

    /**
     * 资源权限管理>按资源管理的列表信息
     * 获取分页列表信息
     * @param queryDTO 查询条件
     * @return PagingData<ManageByResourceVo>
     * @throws LogiSecurityException 异常信息
     */
    PagingData<MByRVO> getManageByResourcePage(MByRQueryDTO queryDTO) throws LogiSecurityException;

    /**
     * 资源权限管理>按用户管理的列表信息
     * 获取分页列表信息
     * @param queryDTO 查询条件
     * @return PagingData<ManageByUserVo>
     */
    PagingData<MByUVO> getManageByUserPage(MByUQueryDTO queryDTO);

    /**
     * 分配资源的权限给用户（N资源权限分配给某用户）
     * @param assignDTO 分配信息
     * @throws LogiSecurityException 异常信息
     */
    void assignResourcePermission(AssignToOneUserDTO assignDTO) throws LogiSecurityException;

    /**
     * 分配资源的权限给用户（某资源权限分配N个用户）
     * @param assignDTO 分配信息
     * @throws LogiSecurityException 异常信息
     */
    void assignResourcePermission(AssignToManyUserDTO assignDTO,
                                  HttpServletRequest request) throws LogiSecurityException;

    /**
     * 批量分配资源的权限给用户
     * 按资源管理下的批量分配用户：分配之前先删除N资源先前已分配的用户
     * 按用户管理下的批量分配资源：分配之前先删除N用户已拥有的资源权限
     * @param assignDTO 分配信息
     * @throws LogiSecurityException 异常信息
     */
    void batchAssignResourcePermission(BatchAssignDTO assignDTO,
                                       HttpServletRequest request) throws LogiSecurityException;

    /**
     * 资源权限管理/按用户管理/分配资源/数据列表的信息
     * @param queryDTO 查询条件
     * @return 数据列表信息
     * @throws LogiSecurityException 异常信息
     */
    List<MByUDataVO> getManagerByUserDataList(MByUDataQueryDTO queryDTO) throws LogiSecurityException;

    /**
     * 资源权限管理/按资源管理/分配用户/数据列表的信息
     * @param queryDTO 查询条件
     * @return 数据列表信息
     * @throws LogiSecurityException 异常信息
     */
    List<MByRDataVO> getManagerByResourceDataList(MByRDataQueryDTO queryDTO) throws LogiSecurityException;

    /**
     * 获取资源查看权限控制状态
     * @return true开启，false关闭
     */
    boolean getViewPermissionControlStatus();

    /**
     * 调用该接口则资源查看权限控制状态被反转
     */
    void changeResourceViewControlStatus();

    /**
     * 获取用户拥有资源管理权限类别（级别）
     * @param queryDTO 查询条件
     * @return 资源管理权限类别枚举
     * @throws LogiSecurityException 用户、项目、资源类别、具体资源id 不可为空
     */
    ControlLevelCode getControlLevel(ControlLevelQueryDTO queryDTO) throws LogiSecurityException;
}
