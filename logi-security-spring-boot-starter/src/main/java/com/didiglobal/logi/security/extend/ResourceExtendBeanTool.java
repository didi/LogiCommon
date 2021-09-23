package com.didiglobal.logi.security.extend;

import com.didiglobal.logi.security.properties.LogiSecurityProper;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author cjm
 */
@Component("logiSecurityResourceExtendBeanTool")
public class ResourceExtendBeanTool {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private LogiSecurityProper logiSecurityProper;

    private static final String DEFAULT_BEAN_NAME = "logiSecurityDefaultResourceExtendImpl";

    private ResourceExtend getCustomResourceExtendImplBean() {
        String customBeanName = logiSecurityProper.getResourceExtendBeanName();
        try {
            return (ResourceExtend) applicationContext.getBean(customBeanName);
        } catch (NoSuchBeanDefinitionException e) {
            throw new UnsupportedOperationException("未能找到自定义的ResourceExtend实现类的bean，使用默认bean");
        }
    }

    private ResourceExtend getDefaultResourceExtendImplBean() {
        return (ResourceExtend) applicationContext.getBean(DEFAULT_BEAN_NAME);
    }

    public ResourceExtend getResourceExtendImpl() {
        ResourceExtend resourceExtend;
        try {
            resourceExtend = getCustomResourceExtendImplBean();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
            // 如果用户没有自己实现ResourceExtend接口，则用默认的
            resourceExtend = getDefaultResourceExtendImplBean();
        }
        return resourceExtend;
    }
}
