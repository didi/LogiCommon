package com.didiglobal.logi.job;

import com.didiglobal.logi.job.core.Scheduler;
import com.didiglobal.logi.job.core.SimpleScheduler;
import com.didiglobal.logi.job.core.monitor.BeatMonitor;
import com.didiglobal.logi.job.core.monitor.MisfireMonitor;
import com.didiglobal.logi.job.core.monitor.TaskMonitor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 配置类.
 *
 * @author ds
 */
@Configuration
@ConditionalOnClass({Scheduler.class, PlatformTransactionManager.class})
@EnableConfigurationProperties({LogIJobProperties.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.didiglobal.logi.job")
public class LogIJobAutoConfiguration {

    /**
     * start scheduler.
     */
    @Bean
    @ConditionalOnMissingBean
    public Scheduler quartzScheduler(ApplicationContext applicationContext) {
        BeatMonitor beatMonitor = applicationContext.getBean(BeatMonitor.class);
        TaskMonitor taskMonitor = applicationContext.getBean(TaskMonitor.class);
        MisfireMonitor misfireMonitor = applicationContext.getBean(MisfireMonitor.class);
        SimpleScheduler simpleScheduler = new SimpleScheduler(beatMonitor, taskMonitor, misfireMonitor);
        simpleScheduler.startup();
        return simpleScheduler;
    }
}