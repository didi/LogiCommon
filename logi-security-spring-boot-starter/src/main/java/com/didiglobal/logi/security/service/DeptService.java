package com.didiglobal.logi.security.service;

import com.didiglobal.logi.security.common.dto.dept.DeptDTO;
import com.didiglobal.logi.security.common.entity.dept.Dept;
import com.didiglobal.logi.security.common.vo.dept.DeptBriefVO;
import com.didiglobal.logi.security.common.vo.dept.DeptTreeVO;

import java.util.List;
import java.util.Map;


/**
 * @author cjm
 */
public interface DeptService {

    /**
     * 构建部门树
     * @return DeptDTO
     */
    DeptTreeVO buildDeptTree();

    /**
     * 获取该子部门所有祖先部门的信息
     * @param deptId 子部门id
     * @return [一级部门, 二级部门, ..., 子部门]
     */
    List<DeptBriefVO> getDeptBriefListByChildId(Integer deptId);

    /**
     * 获取当前部门的子部门部门idList
     * 根据部门id，获取所有子部门id（包括子部门、子子部门、子子子部门...）
     * @param deptId 部门id，如果id为null，则获取全部部门id
     * @return 子部门idList
     */
    List<Integer> getDeptIdListByParentId(Integer deptId);

    /**
     * 根据部门id，获取所有子部门id（包括子部门、子子部门、子子子部门...）
     * 并根据deptName模糊匹配
     * @param deptId 部门id，如果为null，则获取全部
     * @param deptName 部门名
     * @return 子部门idList
     */
    List<Integer> getDeptIdListByParentIdAndDeptName(Integer deptId, String deptName);

    /**
     * 获取所有部门，并转成Map
     * @return key部门id，value部门信息
     */
    Map<Integer, Dept> getAllDeptMap();

    /**
     * 从已有的deptMap（传入的参数），获取该子部门所有祖先部门的信息（数据源来自deptMap）
     * @param deptMap key: deptId、value: Dept
     * @param deptId 部门id
     * @return 子部门和祖先部门的信息
     */
    List<DeptBriefVO> getDeptBriefListFromDeptMapByChildId(Map<Integer, Dept> deptMap, Integer deptId);

    /**
     * 保存部门信息
     * @param deptDTOList 部门信息
     */
    void saveDept(List<DeptDTO> deptDTOList);
}
