package com.didiglobal.logi.metrics.adapt.bean;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MetricRecordBean {

    private String              name;

    private long                timestamp;

    private List<MetricTagBean> metricTags;

    private List<MetricBean>    metrics;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<MetricTagBean> getMetricTags() {
        return metricTags;
    }

    public void setMetricTags(List<MetricTagBean> metricTags) {
        this.metricTags = metricTags;
    }

    public List<MetricBean> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricBean> metrics) {
        this.metrics = metrics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
