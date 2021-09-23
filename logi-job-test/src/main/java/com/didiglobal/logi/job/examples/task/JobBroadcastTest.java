package com.didiglobal.logi.job.examples.task;

import com.didiglobal.logi.job.annotation.Task;
import com.didiglobal.logi.job.common.TaskResult;
import com.didiglobal.logi.job.core.consensual.ConsensualEnum;
import com.didiglobal.logi.job.core.job.Job;
import com.didiglobal.logi.job.core.job.JobContext;
import com.didiglobal.logi.job.utils.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Task(name = "cc broad", description = "hello broad", cron = "0 0/1 * * * ? *", autoRegister = true, timeout = 300)
public class JobBroadcastTest implements Job {
    private static final Logger logger = LoggerFactory.getLogger(JobBroadcastTest.class);

    @Override
    public TaskResult execute(JobContext jobContext) {
        logger.info("**************************************** hihi broad broad start" + System.currentTimeMillis());


        for (long i = 0; i < 30000000L; i++) {
//      logger.info("hihi broad broad");
//      System.out.println("hello world broad broad" + i);
        }

        logger.info("**************************************** hihi broad broad end" + System.currentTimeMillis());

        return TaskResult.SUCCESS;
    }
}