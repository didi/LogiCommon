package com.didiglobal.logi.job;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * job属性.
 *
 * @author ds
 */
@ConfigurationProperties("spring.logi-job")
@Data
public class LogIJobProperties {
    private String username;
    private String password;
    private String jdbcUrl;
    private String driverClassName;
    private Long maxLifetime;
    private Boolean initSql;
    private Integer initThreadNum;
    private Integer maxThreadNum;
    private Integer logExpire;
    private String appName;
}
