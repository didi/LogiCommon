package com.didiglobal.logi.security.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.didiglobal.logi.security.common.dto.oplog.OplogQueryDTO;
import com.didiglobal.logi.security.common.entity.Oplog;
import com.didiglobal.logi.security.common.po.OplogPO;
import com.didiglobal.logi.security.dao.OplogDao;
import com.didiglobal.logi.security.dao.mapper.OplogMapper;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;

/**
 * @author cjm
 */
@Component
public class OplogDaoImpl extends BaseDaoImpl<OplogPO> implements OplogDao {

    @Autowired
    private OplogMapper oplogMapper;

    @Override
    public IPage<Oplog> selectPageWithoutDetail(OplogQueryDTO queryDTO) {
        QueryWrapper<OplogPO> queryWrapper = getQueryWrapper();
        // 分页查询
        IPage<OplogPO> pageInfo = new Page<>(queryDTO.getPage(), queryDTO.getSize());
        // 不查找detail字段
        queryWrapper.select(OplogPO.class, oplog -> !FieldConstant.DETAIL.equals(oplog.getColumn()));
        String operatorIp = queryDTO.getOperatorIp();
        String operatorUsername = queryDTO.getOperatorUsername();
        queryWrapper
                .eq(queryDTO.getOperateType() != null, FieldConstant.OPERATE_TYPE, queryDTO.getOperateType())
                .eq(queryDTO.getTargetType() != null, FieldConstant.TARGET_TYPE, queryDTO.getTargetType())
                .like(!StringUtils.isEmpty(queryDTO.getTarget()), FieldConstant.TARGET, queryDTO.getTarget())
                .like(!StringUtils.isEmpty(operatorIp), FieldConstant.OPERATOR_IP, operatorIp)
                .like(!StringUtils.isEmpty(operatorUsername), FieldConstant.OPERATOR_USERNAME, operatorUsername);
        if(queryDTO.getStartTime() != null) {
            queryWrapper.ge(FieldConstant.CREATE_TIME, new Timestamp(queryDTO.getStartTime()));
        }
        if(queryDTO.getEndTime() != null) {
            queryWrapper.le(FieldConstant.CREATE_TIME, new Timestamp(queryDTO.getEndTime()));
        }
        return CopyBeanUtil.copyPage(oplogMapper.selectPage(pageInfo, queryWrapper), Oplog.class);
    }

    @Override
    public Oplog selectByOplogId(Integer oplogId) {
        if(oplogId == null) {
            return null;
        }
        QueryWrapper<OplogPO> queryWrapper = getQueryWrapper();
        queryWrapper.eq(FieldConstant.ID, oplogId);
        return CopyBeanUtil.copy(oplogMapper.selectOne(queryWrapper), Oplog.class);
    }

    @Override
    public void insert(Oplog oplog) {
        OplogPO oplogPO = CopyBeanUtil.copy(oplog, OplogPO.class);
        oplogMapper.insert(oplogPO);
        oplog.setId(oplogPO.getId());
    }
}
