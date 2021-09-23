package com.didiglobal.logi.security.service.impl;

import com.didiglobal.logi.security.common.entity.OplogExtra;
import com.didiglobal.logi.security.common.enums.oplog.OplogCode;
import com.didiglobal.logi.security.dao.OplogExtraDao;
import com.didiglobal.logi.security.service.OplogExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjm
 */
@Service("logiSecurityOplogExtraServiceImpl")
public class OplogExtraServiceImpl implements OplogExtraService {

    @Autowired
    private OplogExtraDao oplogExtraDao;

    @Override
    public void saveOplogExtraList(List<String> nameList, OplogCode oplogCode) {
        if(oplogCode == null || CollectionUtils.isEmpty(nameList)) {
            return;
        }
        List<OplogExtra> oplogExtraList = new ArrayList<>();
        for(String name : nameList) {
            OplogExtra oplogExtra = new OplogExtra();
            oplogExtra.setInfo(name);
            oplogExtra.setType(oplogCode.getType());
        }
        oplogExtraDao.insertBatch(oplogExtraList);
    }

    @Override
    public List<String> getOplogExtraNameListByType(Integer type) {
        List<OplogExtra> oplogExtraList = oplogExtraDao.selectListByType(type);
        List<String> result = new ArrayList<>();
        for(OplogExtra oplogExtra : oplogExtraList) {
            result.add(oplogExtra.getInfo());
        }
        return result;
    }
}
