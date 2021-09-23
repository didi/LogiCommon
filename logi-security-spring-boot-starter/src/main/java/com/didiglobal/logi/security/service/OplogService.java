package com.didiglobal.logi.security.service;

import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.dto.oplog.OplogQueryDTO;
import com.didiglobal.logi.security.common.dto.oplog.OplogDTO;
import com.didiglobal.logi.security.common.vo.oplog.OplogVO;

/**
 * @author cjm
 */
public interface OplogService {

    /**
     * 保存操作日志，并获取操作日志id
     *
     * @param userId   操作用户id
     * @param oplogDTO 操作日志
     * @return 操作日志id
     */
    Integer saveOplogWithUserId(Integer userId, OplogDTO oplogDTO);

    /**
     * 分页查询操作日志
     *
     * @param queryDTO 查询条件
     * @return 分页信息
     */
    PagingData<OplogVO> getOplogPage(OplogQueryDTO queryDTO);

    /**
     * 根据id查找操作日志详情
     *
     * @param opLogId 操作日志id
     * @return OplogVo 详情
     */
    OplogVO getOplogDetailByOplogId(Integer opLogId);
}
