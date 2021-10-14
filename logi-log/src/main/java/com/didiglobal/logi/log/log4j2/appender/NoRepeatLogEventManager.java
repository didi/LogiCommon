/*
 * Copyright 2017 didichuxing.com All right reserved. This software is the
 * confidential and proprietary information of didichuxing.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with didichuxing.com.
 */
package com.didiglobal.logi.log.log4j2.appender;

import java.util.concurrent.*;

/**
 * NoRepeatLogEventManager.java的实现描述：用于生成当前线程的Flag
 *
 * @author lichong 2017年6月25日 上午12:33:47  
 */
class NoRepeatLogEventManager {

    private static final ScheduledExecutorService LOG_SCHEDULE = new ScheduledThreadPoolExecutor(1,
        new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "NoRepeatLogEventManager-schedule-pool");
            }
        });

    static {
        LOG_SCHEDULE.scheduleWithFixedDelay(new LogCountSwitcher(), 60, 60, TimeUnit.SECONDS);
    }

    private static volatile ConcurrentMap<String, NoRepeatLogEvent> LOG_COUNT = new ConcurrentHashMap<>();

    static NoRepeatLogEvent getNoRepeatLogEvent(String key) {
        return LOG_COUNT.get(key);
    }

    static NoRepeatLogEvent putNoRepeatLogEventIfAbsent(String key, NoRepeatLogEvent logEvent) {
        return LOG_COUNT.putIfAbsent(key, logEvent);
    }

    private static class LogCountSwitcher implements Runnable {

        /**
         * @see Runnable#run()  
         */
        @Override
        public void run() {
            ConcurrentMap<String, NoRepeatLogEvent> logAggregateResult = new ConcurrentHashMap<>();
            logAggregateResult.putAll(LOG_COUNT);

            LOG_COUNT = new ConcurrentHashMap<>();
            for (NoRepeatLogEvent noRepeatLogEvent : logAggregateResult.values()) {
                if (noRepeatLogEvent.isRestrain()) {
                    noRepeatLogEvent.log();
                }
            }
        }
    }

}
