/**
 * Kuaidadi.com Inc.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package com.didiglobal.logi.metrics.sink;

import com.didiglobal.logi.metrics.MetricsPlugin;
import com.didiglobal.logi.metrics.MetricsSystem;
import com.didiglobal.logi.metrics.MetricsTag;
import com.didiglobal.logi.metrics.lib.MetricsRegistry;
import org.apache.commons.configuration.SubsetConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.didiglobal.logi.metrics.Metric;
import com.didiglobal.logi.metrics.MetricsRecord;
import com.didiglobal.logi.metrics.MetricsSink;
import com.didiglobal.logi.metrics.util.TimeUtils;

/**
 * 
 * @author liujianhui
 * @version:2015年11月11日 上午10:34:42
 */
public class RemoteFileSink implements MetricsSink {
    private static final Log    LOG              = LogFactory.getLog(RemoteFileSink.class);

    private static final String SEPERATOR        = "|";

    private static final String METRIC_SEPARATOR = "=";

    private Sender              sender;

    private MetricsSystem metricsSystem;

    /** 
     * @see MetricsPlugin#init(org.apache.commons.configuration.SubsetConfiguration)
     */
    @Override
    public void init(SubsetConfiguration conf) {
    }

    /** 
     * @see MetricsSink#putMetrics(MetricsRecord)
     */
    //    @Override
    //    public void putMetrics(MetricsRecord record) {
    //        Iterable<Metric> metrics = record.metrics();
    //        if (null == metrics || !metrics.iterator().hasNext()) {
    //            return;
    //        }
    //
    //        String timeStr = TimeUtils.formatTimestamp(record.timestamp(), TimeUtils.TIME_YYYY_MM_DD_HH_MM_SS);
    //        String modeleName = "";
    //        String nodeId = "";
    //
    //        //retrieve the model name and node id from the tags
    //        for (MetricsTag loopTag : record.tags()) {
    //            if (MetricsRegistry.MODEL_NAME.equals(loopTag.name())) {
    //                modeleName = StringUtils.trimToEmpty(loopTag.value());
    //            } else if (MetricsRegistry.NODE_ID.equals(loopTag.name())) {
    //                nodeId = StringUtils.trimToEmpty(loopTag.value());
    //            }
    //        }
    //
    //        //build the record content
    //        // format of content like timestamp|modelname|nodeid|recordname|metricname1=metricvalue1|...
    //        StringBuilder recordContent = new StringBuilder();
    //        recordContent.append(timeStr).append(SEPERATOR).append(modeleName).append(SEPERATOR).append(nodeId)
    //            .append(SEPERATOR).append(record.name()).append(SEPERATOR);
    //
    //        //append all the metrics
    //        for (Metric loopMetric : record.metrics()) {
    //            recordContent.append(loopMetric.name()).append(METRIC_SEPARATOR).append(loopMetric.value())
    //                .append(SEPERATOR);
    //        }
    //
    //        //remove the last separator
    //        recordContent.setLength(recordContent.length() - SEPERATOR.length());
    //        sender.send(recordContent.toString());
    //        if (LOG.isDebugEnabled()) {
    //            LOG.debug("success to send record " + recordContent);
    //        }
    //    }

    /** 
     * @see MetricsSink#putMetrics(MetricsRecord)
     */
    @Override
    public void putMetrics(MetricsRecord record) {
        Iterable<Metric> metrics = record.metrics();
        if (null == metrics || !metrics.iterator().hasNext()) {
            return;
        }

        String timeStr = TimeUtils.formatTimestamp(record.timestamp(), TimeUtils.TIME_YYYY_MM_DD_HH_MM_SS);
        String modeleName = "";
        String nodeId = "";

        //retrieve the model name and node id from the tags
        StringBuilder tagBuilder = new StringBuilder();
        for (MetricsTag loopTag : record.tags()) {
            if (MetricsRegistry.MODEL_NAME.equals(loopTag.name())) {
                modeleName = StringUtils.trimToEmpty(loopTag.value());
            } else if (MetricsRegistry.NODE_ID.equals(loopTag.name())) {
                nodeId = StringUtils.trimToEmpty(loopTag.value());
            } else {
                //tagBuilder.append(SEPERATOR).append(loopTag.name()).append(METRIC_SEPARATOR).append(loopTag.value());
            }
        }

        //build the record content
        // format of content like timestamp|modelname|nodeid|recordname|metrictag1=tagvalue1|...|metricname1=metricvalue1|...
        StringBuilder recordContent = new StringBuilder();
        recordContent.append(timeStr).append(SEPERATOR).append(modeleName).append(SEPERATOR).append(nodeId)
            .append(SEPERATOR).append(record.name()).append(tagBuilder).append(SEPERATOR);

        //append all the metrics
        for (Metric loopMetric : record.metrics()) {
            StringBuilder tmpSb = new StringBuilder(recordContent.toString());
            tmpSb.append(loopMetric.name()).append(METRIC_SEPARATOR).append(loopMetric.value()).append(SEPERATOR);

            //remove the last separator
            tmpSb.setLength(tmpSb.length() - SEPERATOR.length());
            sender.send(tmpSb.toString());
            if (LOG.isDebugEnabled()) {
                LOG.debug("success to send record " + tmpSb);
            }
        }
    }

    /** 
     * @see MetricsSink#flush()
     */
    @Override
    public void flush() {
    }

    public void init() {
        metricsSystem.register("remoteFileSink", "", this);
        LOG.info("success register remoteFileSink ");
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public MetricsSystem getMetricsSystem() {
        return metricsSystem;
    }

    public void setMetricsSystem(MetricsSystem metricsSystem) {
        this.metricsSystem = metricsSystem;
    }

    public interface Sender {
        void send(String content);
    }
}
