package com.didiglobal.logi.job.examples.task;

import com.didiglobal.logi.job.annotation.Task;
import com.didiglobal.logi.job.common.TaskResult;
import com.didiglobal.logi.job.core.job.Job;
import com.didiglobal.logi.job.core.job.JobContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Task(name = "cc", description = "hello cc", cron = "0 0/1 * * * ? *", autoRegister = true)
public class JobTest implements Job {
    private static final Logger logger = LoggerFactory.getLogger(JobTest.class);

    @Override
    public TaskResult execute(JobContext jobContext) throws Exception {
        for (int i = 0; i < 40; i++) {
//      ThreadUtil.sleep(1, TimeUnit.SECONDS);
            logger.info("hihi：" + i);
            System.out.println("hello world");
        }

        throw new NullPointerException();

//    return new TaskResult(TaskResult.FAIL_CODE, "sdfsdfsdfdsfsdfdsfsdfsfsfsfs");
    }

}