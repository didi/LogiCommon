package com.didiglobal.logi.job.examples.task;

import com.didiglobal.logi.job.annotation.Task;
import com.didiglobal.logi.job.common.TaskResult;
import com.didiglobal.logi.job.core.consensual.ConsensualEnum;
import com.didiglobal.logi.job.core.job.Job;
import com.didiglobal.logi.job.core.job.JobContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Task(name = "dd broad", description = "dd broad", cron = "0 0/1 * * * ? *", autoRegister = true, timeout = 100)
public class JobBroadcastInterruptTest implements Job {
    private static final Logger logger = LoggerFactory.getLogger(JobBroadcastInterruptTest.class);

    @Override
    public TaskResult execute(JobContext jobContext) throws Exception {
        for (int i = 0; i < 500; i++) {
//      Thread.sleep(1000);
            logger.info("hihi broad broad");
            System.out.println("hello world broad broad");
        }
        return TaskResult.SUCCESS;
    }
}