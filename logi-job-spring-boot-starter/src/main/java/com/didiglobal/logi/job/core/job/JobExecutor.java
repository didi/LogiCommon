package com.didiglobal.logi.job.core.job;

import com.didiglobal.logi.job.LogIJobProperties;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * job thread pool executor.
 *
 * @author ds
 */
@Component
public class JobExecutor {

    public ThreadPoolExecutor threadPoolExecutor;

    /**
     * constructor.
     */
    @Autowired
    public JobExecutor(LogIJobProperties properties) {
        this.threadPoolExecutor = new ThreadPoolExecutor(properties.getInitThreadNum(),
                properties.getMaxThreadNum(), 10L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100));
    }

    public <T> Future<T> submit(Callable<T> task) {
        return threadPoolExecutor.submit(task);
    }
}
