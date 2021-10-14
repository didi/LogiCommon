package com.didiglobal.logi.metrics.instrument;

import java.io.Serializable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.didiglobal.logi.metrics.MetricsBuilder;
import com.didiglobal.logi.metrics.MetricsSource;
import com.didiglobal.logi.metrics.impl.MetricsRecordBuilderImpl;
import com.didiglobal.logi.metrics.lib.MetricMutable;
import com.didiglobal.logi.metrics.lib.MetricMutableFactory;
import com.didiglobal.logi.metrics.lib.MetricMutablePeriodGaugeInt;
import com.didiglobal.logi.metrics.lib.MetricMutablePeriodGaugeLong;
import com.didiglobal.logi.metrics.lib.MetricMutableReference;
import com.didiglobal.logi.metrics.lib.MetricsRegistry;

public class DataInstrumentation implements MetricsSource, Serializable {
    protected MetricsRegistry metricsRegistry;

    private MetricMutableReference<String> timestamp;

    //用于保存时间戳变化时之前的指标的缓存
    private MetricsRecordBuilderImpl       metricsRecordBuilder;

    public DataInstrumentation(String metricSetName) {
        this(new MetricsRegistry(metricSetName, new PeriodMetricMutableFactory()));
    }

    public DataInstrumentation(MetricsRegistry metricsRegistry) {
        this.metricsRegistry = metricsRegistry;
        this.metricsRecordBuilder = new MetricsRecordBuilderImpl(metricsRegistry.name(), null, null, true);
    }

    public DataInstrumentation(String name, MetricMutableFactory metricMutableFactory) {
        this.metricsRegistry = new MetricsRegistry(name, metricMutableFactory);
        this.timestamp = this.metricsRegistry.newReference("TimestampTag", "timestamp tag", "");
        this.metricsRecordBuilder = new MetricsRecordBuilderImpl(metricsRegistry.name(), null, null, true);
    }

    /**
     * 更新记录的时间戳，当时间戳更新的时候会
     * 
     * @param updateTimestamp updateTimestamp
     */
    public synchronized void updateTimestamp(String updateTimestamp) {
        if (null == timestamp) {
            this.timestamp = this.metricsRegistry.newReference("TimestampTag", "timestamp tag", "");
        }

        //时间戳未改变
        if (StringUtils.equals(updateTimestamp, timestamp.get())) {
            return;
        }

        //将指标记录先保存到缓存的metrics record中，等待下一次指标的收集
        if (StringUtils.isNotEmpty(updateTimestamp)) {
            metricsRecordBuilder = new MetricsRecordBuilderImpl(metricsRegistry.name(), null, null, true);
            metricsRegistry.snapshot(metricsRecordBuilder, true);
        }
        timestamp.set(updateTimestamp);
    }

    public void incr(String name) {
        metricsRegistry.incr(name);
    }

    public <T> void set(String name, T value) {
        metricsRegistry.set(name, value);
    }

    public void context(String context) {
        metricsRegistry.setContext(context);
    }

    public void incr(String metricName, int size) {
        MetricMutable metricMutable = getMetricsRegistry().get(metricName);
        if (null == metricMutable) {
            incr(metricName);
            size--;
        }

        if (size <= 0) {
            return;
        }
        if (metricMutable instanceof MetricMutablePeriodGaugeLong) {
            ((MetricMutablePeriodGaugeLong) metricMutable).incr(size);
        } else if (metricMutable instanceof MetricMutablePeriodGaugeInt) {
            ((MetricMutablePeriodGaugeInt) metricMutable).incr(size);
        }
    }

    @Override
    public synchronized void getMetrics(MetricsBuilder builder, boolean all) {
        //收集之前缓存的数据指标
        if (!CollectionUtils.isEmpty(metricsRecordBuilder.metrics())) {
            builder.addRecord(metricsRecordBuilder);
            metricsRecordBuilder = new MetricsRecordBuilderImpl(metricsRegistry.name(), null, null, true);
        }
        metricsRegistry.snapshot(builder.addRecord(metricsRegistry.name()), true);
    }

    public void setModelName(String modelName) {
        metricsRegistry.tag(MetricsRegistry.MODEL_NAME, MetricsRegistry.MODEL_NAME_DESC, modelName);
    }

    public void setNodeId(String nodeId) {
        metricsRegistry.tag(MetricsRegistry.NODE_ID, MetricsRegistry.NODE_ID_DESC, nodeId);
    }

    public MetricsRegistry getMetricsRegistry() {
        return metricsRegistry;
    }

    private static class PeriodMetricMutableFactory extends MetricMutableFactory {
        @Override
        public MetricMutable newMetric(String name) {
            return new MetricMutablePeriodGaugeLong(name, "", 0);
        }
    }
}
