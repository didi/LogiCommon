package com.didiglobal.logi.security.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.didiglobal.logi.security.common.entity.OplogExtra;
import com.didiglobal.logi.security.common.po.OplogExtraPO;
import com.didiglobal.logi.security.dao.OplogExtraDao;
import com.didiglobal.logi.security.dao.mapper.OplogExtraMapper;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author cjm
 */
@Component
public class OplogExtraDaoImpl extends BaseDaoImpl<OplogExtraPO> implements OplogExtraDao {

    @Autowired
    private OplogExtraMapper oplogExtraMapper;

    @Override
    public List<OplogExtra> selectListByType(Integer type) {
        QueryWrapper<OplogExtraPO> queryWrapper = getQueryWrapper();
        queryWrapper.eq(FieldConstant.TYPE, type);
        return CopyBeanUtil.copyList(oplogExtraMapper.selectList(queryWrapper), OplogExtra.class);
    }

    @Override
    public void insertBatch(List<OplogExtra> oplogExtraList) {
        if(CollectionUtils.isEmpty(oplogExtraList)) {
            return;
        }
        List<OplogExtraPO> oplogExtraPOList = CopyBeanUtil.copyList(oplogExtraList, OplogExtraPO.class);
        for(OplogExtraPO oplogExtraPO : oplogExtraPOList) {
            oplogExtraMapper.insert(oplogExtraPO);
        }

    }
}
