package com.didiglobal.logi.security.config;

import com.didiglobal.logi.security.properties.LogiSecurityProper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author cjm
 */
@Configuration("logiSecurityAutoConfiguration")
@EnableConfigurationProperties(LogiSecurityProper.class)
public class AutoConfiguration {

    private final LogiSecurityProper proper;

    public AutoConfiguration(LogiSecurityProper proper) {
        this.proper = proper;
    }

    public LogiSecurityProper getProper() {
        return proper;
    }
}

