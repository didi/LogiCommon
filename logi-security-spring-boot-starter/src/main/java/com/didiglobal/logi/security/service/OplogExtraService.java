package com.didiglobal.logi.security.service;

import com.didiglobal.logi.security.common.enums.oplog.OplogCode;

import java.util.List;

/**
 * @author cjm
 */
public interface OplogExtraService {

    /**
     * 操作日志信息（操作页面、操作类型、对象分类）
     * @param type 操作页面 or 操作类型 or 对象分类
     * @return 操作日志信息（操作页面、操作类型、对象分类）List
     */
    List<String> getOplogExtraNameListByType(Integer type);

    /**
     * 保存操作日志extra的信息
     * @param nameList 操作页面、操作类型、对象分类 名list信息
     * @param oplogCode 指明是操作页面还是操作类型还是对象分类的信息
     */
    void saveOplogExtraList(List<String> nameList, OplogCode oplogCode);
}
