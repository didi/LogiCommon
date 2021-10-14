/*
 * Copyright 2017 didichuxing.com All right reserved. This software is the
 * confidential and proprietary information of didichuxing.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with didichuxing.com.
 */
package com.didiglobal.logi.log.log4j2.appender;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.impl.MutableLogEvent;
import org.apache.logging.log4j.util.StringMap;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NoRepeatLogEvent.java的实现描述：DLog相关属性，主要是错误次数以及开始计时的时间  
 *
 * @author lichong 2017年6月25日 下午10:13:57  
 */
class NoRepeatLogEvent {

    private AtomicInteger                     count = new AtomicInteger(0);
    private final LogEvent                    event;
    private final NoRepeatRollingFileAppender rollingFileAppender;
    private String                            value;

    NoRepeatLogEvent(LogEvent event, NoRepeatRollingFileAppender rollingFileAppender) {
        //因为event是reusable的，此处必须使用toImmutable，不然会被后续的event冲掉
        this.event = event.toImmutable();
        this.rollingFileAppender = rollingFileAppender;
        this.value = event.getLoggerFqcn() + "_" + event.getMessage().getFormattedMessage();
    }

    int incrementAndGet() {
        return count.incrementAndGet();
    }

    /**
     * @return the count  
     */
    public AtomicInteger getCount() {
        return count;
    }

    /**
     * @return the restrain  
     */
    boolean isRestrain() {
        return count.get() > 1;
    }

    private LogEvent generateEvent() {
        StringBuilder sb = new StringBuilder();
        sb.append(" count=").append(count).append(", ").append(event.getMessage().getFormattedMessage());

        MutableLogEvent logEvent = new MutableLogEvent(sb, null);
        logEvent.setTimeMillis(System.currentTimeMillis());
        logEvent.setThrown(event.getThrown());
        logEvent.setContextData((StringMap) event.getContextData());
        logEvent.setContextStack(event.getContextStack());
        logEvent.setLoggerFqcn(event.getLoggerFqcn());
        logEvent.setMarker(event.getMarker());
        logEvent.setLevel(event.getLevel());
        logEvent.setLoggerName(event.getLoggerName());
        logEvent.setThrown(event.getThrown());
        logEvent.setThreadId(Thread.currentThread().getId());
        logEvent.setThreadName(Thread.currentThread().getName());
        logEvent.setThreadPriority(Thread.currentThread().getPriority());
        logEvent.setEndOfBatch(event.isEndOfBatch());
        logEvent.setIncludeLocation(event.isIncludeLocation());
        logEvent.setNanoTime(event.getNanoTime());

        return logEvent;
    }

    /**
         * 输出之前被抑制的日志
         */
    void log() {
        rollingFileAppender.appendRestrain(generateEvent());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoRepeatLogEvent)) {
            return false;
        }
        NoRepeatLogEvent that = (NoRepeatLogEvent) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "{\"NoRepeatLogEvent\":{" + "\"count\":\"" + count + "\"" + "," + "\"event\":" + event + ","
               + "\"value\":\"" + value + "\"" + "}}";
    }
}
