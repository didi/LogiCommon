package com.didiglobal.logi.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jinbinbin
 * @version $Id: GatherLogSinkRegistry.java, v 0.1 2018年06月13日 14:02 jinbinbin Exp $
 */
public class GatherLogSinkRegistry {

    private static final Map<String, ILogSink> METRICS_REGISTRY_MAP = new HashMap<>();
    private static final Map<String, ILogSink> INFO_REGISTRY_MAP    = new HashMap<>();
    private static final Map<String, ILogSink> WARN_REGISTRY_MAP    = new HashMap<>();
    private static final Map<String, ILogSink> ERROR_REGISTRY_MAP   = new HashMap<>();

    public static void registryMetricsSink(String name, ILogSink logSink) {
        synchronized (METRICS_REGISTRY_MAP) {
            METRICS_REGISTRY_MAP.put(name, logSink);
        }
    }

    public static void unRegistryMetricsSink(String name) {
        synchronized (METRICS_REGISTRY_MAP) {
            METRICS_REGISTRY_MAP.remove(name);
        }
    }

    static void metricsTrigger(String log) {
        List<ILogSink> sinkList = new ArrayList<>();
        synchronized (METRICS_REGISTRY_MAP) {
            sinkList.addAll(METRICS_REGISTRY_MAP.values());
        }

        for (ILogSink logSink : sinkList) {
            logSink.log(log);
        }
        sinkList.clear();
    }

    public static void registryInfoSink(String name, ILogSink logSink) {
        synchronized (INFO_REGISTRY_MAP) {
            INFO_REGISTRY_MAP.put(name, logSink);
        }
    }

    public static void unRegistryInfoSink(String name) {
        synchronized (INFO_REGISTRY_MAP) {
            INFO_REGISTRY_MAP.remove(name);
        }
    }

    static void infoTrigger(String log) {
        List<ILogSink> sinkList = new ArrayList<>();
        synchronized (INFO_REGISTRY_MAP) {
            sinkList.addAll(INFO_REGISTRY_MAP.values());
        }

        for (ILogSink logSink : sinkList) {
            logSink.log(log);
        }
        sinkList.clear();
    }

    public static void registryWarnSink(String name, ILogSink logSink) {
        synchronized (WARN_REGISTRY_MAP) {
            WARN_REGISTRY_MAP.put(name, logSink);
        }
    }

    public static void unRegistryWarnSink(String name) {
        synchronized (WARN_REGISTRY_MAP) {
            WARN_REGISTRY_MAP.remove(name);
        }
    }

    static void warnTrigger(String log) {
        List<ILogSink> sinkList = new ArrayList<>();
        synchronized (WARN_REGISTRY_MAP) {
            sinkList.addAll(WARN_REGISTRY_MAP.values());
        }

        for (ILogSink logSink : sinkList) {
            logSink.log(log);
        }
        sinkList.clear();
    }

    public static void registryErrorSink(String name, ILogSink logSink) {
        synchronized (ERROR_REGISTRY_MAP) {
            ERROR_REGISTRY_MAP.put(name, logSink);
        }
    }

    public static void unRegistryErrorSink(String name) {
        synchronized (ERROR_REGISTRY_MAP) {
            ERROR_REGISTRY_MAP.remove(name);
        }
    }

    static void errorTrigger(String log) {
        List<ILogSink> sinkList = new ArrayList<>();
        synchronized (ERROR_REGISTRY_MAP) {
            sinkList.addAll(ERROR_REGISTRY_MAP.values());
        }

        for (ILogSink logSink : sinkList) {
            logSink.log(log);
        }
        sinkList.clear();
    }

}
