package com.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author qmoj
 * @version 1.0
 * @date 2021/1/20 13:10
 */
@Configuration
@MapperScan({"com.mapper"})
public class DataSourceConfig {

}
