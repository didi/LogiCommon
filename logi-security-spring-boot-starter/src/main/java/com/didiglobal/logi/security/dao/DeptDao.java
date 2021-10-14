package com.didiglobal.logi.security.dao;

import com.didiglobal.logi.security.common.entity.dept.Dept;
import com.didiglobal.logi.security.common.entity.dept.DeptBrief;

import java.util.List;

/**
 * @author cjm
 */
public interface DeptDao {

    /**
     * 获取全部
     * @return 部门信息List
     */
    List<Dept> selectAllAndAscOrderByLevel();

    /**
     * 根据部门名模糊匹配，获取部门idList
     * @param deptName 部门名
     * @return 部门idList
     */
    List<Integer> selectIdListByLikeDeptName(String deptName);

    /**
     * 根据部门id获取部门简要信息
     * @param deptId 部门id
     * @return 部门简要信息
     */
    DeptBrief selectBriefByDeptId(Integer deptId);

    /**
     * 获取全部deptId
     * @return deptIdList
     */
    List<Integer> selectAllDeptIdList();

    /**
     * 根据部门id，获取该部门下（包括该部门）所有子部门idList
     * @param deptId 部门id
     * @return child部门idList
     */
    List<Integer> selectIdListByParentId(Integer deptId);

    /**
     * 批量插入
     * @param deptList 部门信息List
     */
    void insertBatch(List<Dept> deptList);
}
