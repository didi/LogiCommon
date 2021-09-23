package com.didiglobal.logi.security.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.didiglobal.logi.security.properties.LogiSecurityProper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cjm
 */
@Component
public class BaseDaoImpl<T> {

    @Autowired
    private LogiSecurityProper logiSecurityProper;

    protected QueryWrapper<T> getQueryWrapper() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_name", logiSecurityProper.getAppName());
        return queryWrapper;
    }
}
