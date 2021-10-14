package com.didiglobal.logi.log;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.log.util.HostUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jinbinbin
 * @version $Id: LogGather.java, v 0.1 2017年07月13日 20:13 jinbinbin Exp $
 */
public class LogGather {

    private static final ILog LOGGER = LogFactory.getLog(LogGather.class);
    private static final ILog INFO_LOGGER = LogFactory.getLog("gatherInfoLogger");
    private static final ILog WARN_LOGGER = LogFactory.getLog("gatherWarnLogger");
    private static final ILog ERROR_LOGGER = LogFactory.getLog("gatherErrorLogger");
    private static final ILog METRICS_LOGGER = LogFactory.getLog("gatherMetricsLogger");
    private static final ConcurrentMap<String, LogDetail> WARN_LOGS = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, LogDetail> ERROR_LOGS = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, LogDetail> INFO_LOGS = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, MetricsLog> METRICS_LOGS = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, LogMetricsObj> LOG_OBJECT_METRICS = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService LOG_SCHEDULE = new ScheduledThreadPoolExecutor(1,
            new ThreadFactory() {

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r,
                            "LogGather-schedule-pool");
                }
            });

    static {
        LOG_SCHEDULE.scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                try {
                    ConcurrentMap<String, LogDetail> tempErrorLogs = new ConcurrentHashMap<String, LogDetail>();
                    tempErrorLogs.putAll(ERROR_LOGS);
                    ERROR_LOGS.clear();
                    for (LogDetail log : tempErrorLogs.values()) {
                        try {
                            if (log.restrain()) {
                                continue;
                            }
                            String slog = log.toString();
                            if (ERROR_LOGGER.isErrorEnabled()) {
                                ERROR_LOGGER.error(slog);
                            }
                            GatherLogSinkRegistry.errorTrigger(slog);
                        } catch (Exception e) {
                            LOGGER.error("WriteLogTask runs failed", e);
                        }
                    }

                    ConcurrentMap<String, LogDetail> tempWarnLogs = new ConcurrentHashMap<String, LogDetail>();
                    tempWarnLogs.putAll(WARN_LOGS);
                    WARN_LOGS.clear();
                    for (LogDetail log : tempWarnLogs.values()) {
                        try {
                            if (log.restrain()) {
                                continue;
                            }
                            String slog = log.toString();
                            if (WARN_LOGGER.isWarnEnabled()) {
                                WARN_LOGGER.warn(slog);
                            }
                            GatherLogSinkRegistry.warnTrigger(slog);
                        } catch (Exception e) {
                            LOGGER.error("WriteLogTask runs failed", e);
                        }
                    }

                    ConcurrentMap<String, LogDetail> tempInfoLogs = new ConcurrentHashMap<String, LogDetail>();
                    tempInfoLogs.putAll(INFO_LOGS);
                    INFO_LOGS.clear();
                    for (LogDetail log : tempInfoLogs.values()) {
                        try {
                            if (log.restrain()) {
                                continue;
                            }
                            String slog = log.toString();
                            if (INFO_LOGGER.isInfoEnabled()) {
                                INFO_LOGGER.info(slog);
                            }
                            GatherLogSinkRegistry.infoTrigger(slog);
                        } catch (Exception e) {
                            LOGGER.error("WriteLogTask runs failed", e);
                        }
                    }

                    ConcurrentMap<String, MetricsLog> tempMetricsLogs = new ConcurrentHashMap<String, MetricsLog>();
                    tempMetricsLogs.putAll(METRICS_LOGS);
                    METRICS_LOGS.clear();
                    for (MetricsLog log : tempMetricsLogs.values()) {
                        try {
                            if (log.restrain()) {
                                continue;
                            }
                            String slog = log.toString();
                            if (METRICS_LOGGER.isInfoEnabled()) {
                                METRICS_LOGGER.info(slog);
                            }
                            GatherLogSinkRegistry.metricsTrigger(slog);
                        } catch (Exception e) {
                            LOGGER.error("WriteLogTask runs failed", e);
                        }
                    }

                    ConcurrentMap<String, LogMetricsObj> tempLogObjectMetrics = new ConcurrentHashMap<String, LogMetricsObj>();
                    tempLogObjectMetrics.putAll(LOG_OBJECT_METRICS);
                    LOG_OBJECT_METRICS.clear();
                    for (LogMetricsObj logMetricsObj : tempLogObjectMetrics.values()) {
                        try {
                            if (logMetricsObj.restrain()) {
                                continue;
                            }
                            String slog = logMetricsObj.toString();
                            if (METRICS_LOGGER.isInfoEnabled()) {
                                METRICS_LOGGER.info(slog);
                            }
                            GatherLogSinkRegistry.metricsTrigger(slog);
                        } catch (Exception e) {
                            LOGGER.error("WriteLogTask runs failed", e);
                        }
                    }

                } catch (Exception e) {
                    LOGGER.error("WriteLogTask runs failed", e);
                }
            }
        }, 1, 1, TimeUnit.MINUTES);
    }

    public static void recordWarnLog(String logCode, String logMsg, Throwable e) {
        LogDetail detail = record(WARN_LOGS, logCode, logMsg, e);
        if (detail != null) {
            if (WARN_LOGGER.isWarnEnabled()) {
                String slog = detail.toString();
                GatherLogSinkRegistry.warnTrigger(slog);
                if (e != null) {
                    WARN_LOGGER.warn(slog, e);
                } else {
                    WARN_LOGGER.warn(slog);
                }
            }
        }
    }

    public static void recordWarnLog(String logCode, String logMsg) {
        recordWarnLog(logCode, logMsg, null);
    }

    public static void recordMetrics(String logCode, Map<String, Object> msgMap) {
        boolean first = false;
        MetricsLog metricsLog = METRICS_LOGS.get(logCode);
        if (metricsLog == null) {
            metricsLog = new MetricsLog(logCode, msgMap);
            MetricsLog oLog = METRICS_LOGS.putIfAbsent(logCode, metricsLog);
            if (oLog != null) {
                oLog.incCount();
            } else {
                first = true;
            }
        } else {
            metricsLog.incCount();
        }
        if (first) {
            String slog = metricsLog.toString();
            GatherLogSinkRegistry.metricsTrigger(slog);
            METRICS_LOGGER.info(slog);
        }
    }

    public static void recordObjectMetrics(String logCode, LogMetrics logMetrics) {
        boolean first = false;
        LogMetricsObj logMetricsObj = LOG_OBJECT_METRICS.get(logCode);
        if (logMetricsObj == null) {
            logMetricsObj = new LogMetricsObj(logCode, logMetrics);
            LogMetricsObj oLogMetricsObj = LOG_OBJECT_METRICS.putIfAbsent(logCode, logMetricsObj);
            if (oLogMetricsObj != null) {
                oLogMetricsObj.incCount();
            } else {
                first = true;
            }
        } else {
            logMetricsObj.incCount();
        }
        if (first) {
            String slog = logMetricsObj.toString();
            GatherLogSinkRegistry.metricsTrigger(slog);
            METRICS_LOGGER.info(slog);
        }
    }

    public static void recordErrorLog(String logCode, String logMsg, Exception e) {
        recordErrorLog(logCode, logMsg, (Throwable) e);
    }

    public static void recordErrorLog(String logCode, String logMsg, Throwable e) {
        LogDetail detail = record(ERROR_LOGS, logCode, logMsg, e);
        if (detail != null) {
            if (ERROR_LOGGER.isErrorEnabled()) {
                String slog = detail.toString();
                GatherLogSinkRegistry.errorTrigger(slog);
                if (e != null) {
                    ERROR_LOGGER.error(slog, e);
                } else {
                    ERROR_LOGGER.error(slog);
                }
            }
        }
    }

    public static void recordErrorLog(String logCode, String logMsg) {
        recordErrorLog(logCode, logMsg, null);
    }

    public static void recordInfoLog(String logCode, String logMsg) {
        LogDetail detail = record(INFO_LOGS, logCode, logMsg, null);
        if (detail != null) {
            if (INFO_LOGGER.isInfoEnabled()) {
                String slog = detail.toString();
                GatherLogSinkRegistry.infoTrigger(slog);
                INFO_LOGGER.info(slog);
            }
        }
    }

    private static LogDetail record(ConcurrentMap<String, LogDetail> detailMap, String logCode, String logMsg,
                                    Throwable e) {
        boolean first = false;
        LogDetail detail = detailMap.get(logCode);
        if (detail == null) {
            detail = new LogDetail(logCode, logMsg, e);
            LogDetail oDetail = detailMap.putIfAbsent(logCode, detail);
            if (oDetail != null) {
                oDetail.incCount();
            } else {
                first = true;
            }
        } else {
            detail.incCount();
        }
        if (first) {
            return detail;
        }
        return null;
    }

    private static class LogDetail {

        private final String logCode;
        private final AtomicInteger count;
        private final String logMsg;
        private final Throwable throwable;

        boolean restrain() {
            return count.get() == 1;
        }

        public int getCount() {
            return count.get();
        }

        public String getLogCode() {
            return logCode;
        }

        public String getLogMsg() {
            return logMsg;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public LogDetail(String logCode, String logMsg, Throwable throwable) {
            this.logCode = logCode;
            this.logMsg = logMsg;
            this.throwable = throwable;
            this.count = new AtomicInteger(1);
        }

        public void incCount() {
            count.getAndIncrement();
        }

        public void incCount(int delta) {
            count.getAndAdd(delta);
        }

        @Override
        public String toString() {
            Map<String, Object> tempMsgMap = new HashMap<>();
            tempMsgMap.put("logCode", logCode);
            tempMsgMap.put("count", count);
            tempMsgMap.put("logMsg", logMsg);
            // 直接传入exception时，反序列化时会报autoType is not supported错误。log gather都是日志输出，可以只支持字符串
            tempMsgMap.put("throwable", JSON.toJSONString(throwable));

            return JSON.toJSONString(tempMsgMap);
        }
    }

    private static class LogMetricsObj {

        private final String logCode;
        private final AtomicInteger logCount;
        private final LogMetrics logMetrics;
        private final String flag;

        public LogMetricsObj(String logCode, LogMetrics logMetrics) {
            this.logCode = logCode;
            this.logMetrics = logMetrics;
            this.logCount = new AtomicInteger(1);
            this.flag = LogFactory.getFlag() != null ? LogFactory.getFlag() : LogFactory.getUniqueFlag();
        }

        boolean restrain() {
            return logCount.get() == 1;
        }

        public int getLogCount() {
            return logCount.get();
        }

        public String getLogCode() {
            return logCode;
        }

        public Map<String, Object> getMsgMap() {
            return logMetrics.asMap();
        }

        public void incCount() {
            logCount.getAndIncrement();
        }

        public void incCount(int delta) {
            logCount.getAndAdd(delta);
        }

        @Override
        public String toString() {
            Map<String, Object> tempMsgMap = new HashMap<>();
            for (Map.Entry<String, Object> entry : getMsgMap().entrySet()) {
                if (entry.getValue() instanceof Throwable) {
                    tempMsgMap.put(entry.getKey(), JSON.toJSONString(entry.getValue()));
                } else {
                    tempMsgMap.put(entry.getKey(), entry.getValue());
                }
            }

            tempMsgMap.put("_logCode", logCode);
            tempMsgMap.put("_logCount", logCount);
            tempMsgMap.put("_logTime", System.currentTimeMillis());
            tempMsgMap.put("_logHost", HostUtil.getHostName());
            tempMsgMap.put("_flag", flag);

            return JSON.toJSONString(tempMsgMap);
        }
    }

    private static class MetricsLog {

        private final String logCode;
        private final AtomicInteger logCount;
        private final Map<String, Object> msgMap;
        private final String flag;

        public MetricsLog(String logCode, Map<String, Object> msgMap) {
            this.logCode = logCode;
            this.msgMap = msgMap;
            this.logCount = new AtomicInteger(1);
            this.flag = LogFactory.getFlag() != null ? LogFactory.getFlag() : LogFactory.getUniqueFlag();
        }

        boolean restrain() {
            return logCount.get() == 1;
        }

        public int getLogCount() {
            return logCount.get();
        }

        public String getLogCode() {
            return logCode;
        }

        public Map<String, Object> getMsgMap() {
            return msgMap;
        }

        public void incCount() {
            logCount.getAndIncrement();
        }

        public void incCount(int delta) {
            logCount.getAndAdd(delta);
        }

        @Override
        public String toString() {
            Map<String, Object> tempMsgMap = new HashMap<>();
            for (Map.Entry<String, Object> entry : msgMap.entrySet()) {
                if (entry.getValue() instanceof Throwable) {
                    tempMsgMap.put(entry.getKey(), JSON.toJSONString(entry.getValue()));
                } else {
                    tempMsgMap.put(entry.getKey(), entry.getValue());
                }
            }

            tempMsgMap.put("_logCode", logCode);
            tempMsgMap.put("_logCount", logCount);
            tempMsgMap.put("_logTime", System.currentTimeMillis());
            tempMsgMap.put("_logHost", HostUtil.getHostName());
            tempMsgMap.put("_flag", flag);

            return JSON.toJSONString(tempMsgMap);
        }
    }
}
