package com.didiglobal.logi.job;

import com.didiglobal.logi.job.core.SimpleScheduler;
import com.didiglobal.logi.job.core.monitor.BeatMonitor;
import com.didiglobal.logi.job.core.monitor.MisfireMonitor;
import com.didiglobal.logi.job.core.monitor.TaskMonitor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.didiglobal.logi.job")
public class LogIJobApplication {

    /**
     * 入口函数.
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(LogIJobApplication.class);
        BeatMonitor beatMonitor = applicationContext.getBean(BeatMonitor.class);
        TaskMonitor taskMonitor = applicationContext.getBean(TaskMonitor.class);
        MisfireMonitor misfireMonitor = applicationContext.getBean(MisfireMonitor.class);
        SimpleScheduler simpleScheduler = new SimpleScheduler(beatMonitor, taskMonitor, misfireMonitor);
        simpleScheduler.startup();
    }
}
