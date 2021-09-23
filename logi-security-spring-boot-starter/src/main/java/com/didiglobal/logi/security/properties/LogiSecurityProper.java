package com.didiglobal.logi.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * @author cjm
 *
 */
@Data
@Validated
@ConfigurationProperties(prefix = "logi.security")
public class LogiSecurityProper {

    /**
     * 应用名称
     */
    @NotEmpty(message = "配置文件配置必须要配置[logi.security.app-name]属性")
    private String appName;

    /**
     * 用户需要实现resourceExtend接口，并在spring容器中的bean name
     */
    @NotEmpty(message = "配置文件配置必须要配置[logi.security.resource-extend-bean-name]属性")
    private String resourceExtendBeanName;

    /**
     * 数据库信息
     */
    @NotEmpty(message = "配置文件配置必须要配置[logi.security.username]属性")
    private String username;

    /**
     * 数据库信息
     */
    @NotEmpty(message = "配置文件配置必须要配置[logi.security.password]属性")
    private String password;

    /**
     * 数据库信息
     */
    @NotEmpty(message = "配置文件配置必须要配置[logi.security.url]属性")
    private String url;

    /**
     * 数据库信息
     */
    @NotEmpty(message = "配置文件配置必须要配置[logi.security.driver-class-name]属性")
    private String driverClassName;
}
