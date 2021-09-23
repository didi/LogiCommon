package com.didiglobal.logi.job;

import com.didiglobal.logi.job.annotation.Task;
import com.didiglobal.logi.job.common.enums.TaskStatusEnum;
import com.didiglobal.logi.job.common.po.LogITaskPO;
import com.didiglobal.logi.job.common.enums.TaskWorkerStatusEnum;
import com.didiglobal.logi.job.core.job.Job;
import com.didiglobal.logi.job.core.job.JobFactory;
import com.didiglobal.logi.job.mapper.LogITaskMapper;
import com.didiglobal.logi.job.utils.CronExpression;
import com.didiglobal.logi.job.utils.IdWorker;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class TaskBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(TaskBeanPostProcessor.class);

    private static Map<String, LogITaskPO> taskMap = new HashMap<>();

    @Autowired
    private LogITaskMapper logITaskMapper;

    @Autowired
    private JobFactory jobFactory;

    @Autowired
    private LogIJobProperties logIJobProperties;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        // add job to jobFactory
        if (bean instanceof Job) {
            jobFactory.addJob(beanClass.getCanonicalName(), (Job) bean);
        } else {
            return bean;
        }

        // check and register to db

        Task taskAnnotation = beanClass.getAnnotation(Task.class);
        if (taskAnnotation == null || !taskAnnotation.autoRegister()) {
            return bean;
        }
        // check
        if (!check(taskAnnotation)) {
            logger.error("class=TaskBeanPostProcessor||method=blacklist||url=||msg=invalid schedule {}",
                    taskAnnotation.toString());
        }
        // not exists register
        LogITaskPO task = getAuvTask(beanClass, taskAnnotation);
        task.setTaskCode(IdWorker.getIdStr());
        if (!contains(task)) {
            task.setStatus(TaskStatusEnum.RUNNING.getValue());
            logITaskMapper.insert(task);
        }
        return bean;
    }

    /*********************************************** private method ***********************************************/
    private boolean check(Task schedule) {
        return CronExpression.isValidExpression(schedule.cron());
    }

    private LogITaskPO getAuvTask(Class<?> beanClass, Task schedule) {
        LogITaskPO logITaskPO = new LogITaskPO();
        logITaskPO.setTaskName(schedule.name());
        logITaskPO.setTaskDesc(schedule.description());
        logITaskPO.setCron(schedule.cron());
        logITaskPO.setClassName(beanClass.getCanonicalName());
        logITaskPO.setParams("");
        logITaskPO.setRetryTimes(schedule.retryTimes());
        logITaskPO.setLastFireTime(new Timestamp(System.currentTimeMillis()));
        logITaskPO.setTimeout(schedule.timeout());
        logITaskPO.setSubTaskCodes("");
        logITaskPO.setConsensual(schedule.consensual().name());
        logITaskPO.setTaskWorkerStr("");
        logITaskPO.setAppName(logIJobProperties.getAppName());
        logITaskPO.setOwner(schedule.owner());
        return logITaskPO;
    }

    private boolean contains(LogITaskPO task) {
        if (taskMap.isEmpty()) {
            List<LogITaskPO> logITaskPOS = logITaskMapper.selectByAppName(logIJobProperties.getAppName());
            taskMap = logITaskPOS.stream().collect(Collectors.toMap(LogITaskPO::getClassName,
                    Function.identity()));
        }
        return taskMap.containsKey(task.getClassName());
    }
}