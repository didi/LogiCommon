package com.didiglobal.logi.job.configuration;

import com.didiglobal.logi.job.LogIJobProperties;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@MapperScan(value = "com.didiglobal.logi.job.mapper",
        sqlSessionFactoryRef = "auvSqlSessionFactory")
public class AuvDataSourceConfig {

    /**
     * 配置数据源.
     *
     * @return 数据源
     */
    @Bean("auvDataSource")
    public DataSource dataSource(LogIJobProperties logIJobProperties) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(logIJobProperties.getUsername());
        dataSource.setPassword(logIJobProperties.getPassword());
        dataSource.setJdbcUrl(logIJobProperties.getJdbcUrl());
        dataSource.setDriverClassName(logIJobProperties.getDriverClassName());
        dataSource.setMaxLifetime(logIJobProperties.getMaxLifetime());
        return dataSource;
    }

    /**
     * 配置SqlSessionFactory.
     *
     * @param dataSource dataSource
     * @return SqlSessionFactory
     * @throws Exception Exception
     */
    @Bean("auvSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("auvDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/logi-job/*.xml"));
        org.apache.ibatis.session.Configuration configuration =
                new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        return bean.getObject(); // 设置mybatis的xml所在位置
    }

    @Bean("auvSqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("auvSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }

}