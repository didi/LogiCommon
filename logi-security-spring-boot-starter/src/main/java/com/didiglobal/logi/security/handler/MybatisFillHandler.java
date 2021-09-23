package com.didiglobal.logi.security.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.didiglobal.logi.security.properties.LogiSecurityProper;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cjm
 *
 * 这个是mybatis-plus，在插入或更新数据的时候会自动填充默认字段值
 */
@Component("logiSecurityMybatisFillHandler")
public class MybatisFillHandler implements MetaObjectHandler {

    @Autowired
    private LogiSecurityProper logiSecurityProper;

    @Override
    public void insertFill(MetaObject metaObject) {
        // 在插入数据的时候自动填充默认字段值 appName
        metaObject.setValue("appName", logiSecurityProper.getAppName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // do nothing
    }
}
