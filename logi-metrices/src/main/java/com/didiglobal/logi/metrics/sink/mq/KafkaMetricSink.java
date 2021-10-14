package com.didiglobal.logi.metrics.sink.mq;

import java.util.Properties;

import org.apache.commons.configuration.SubsetConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.didiglobal.logi.metrics.MetricsSink;

public class KafkaMetricSink extends AbstractMetricSink implements MetricsSink {

    private static final Logger  LOGGER         = Logger.getLogger(KafkaMetricSink.class);

    private String               topic;

    private static String        DEAFAULT_TOPIC = "metric";

    private KafkaMessageProducer producer;

    @Override
    public void init(SubsetConfiguration conf) {
        this.topic = conf.getString("topic");
        if (StringUtils.isBlank(topic)) {
            topic = DEAFAULT_TOPIC;
        }

        String servers = conf.getString("servers");
        if (StringUtils.isNotBlank(servers)) {
            producer = new KafkaMessageProducer(servers, new Properties());
            try {
                producer.init();
            } catch (Exception e) {
                LOGGER.error("init KafkaMetricSink failed!config is:" + conf, e);
            }
        }
        LOGGER.info("success init KafkaMetricSink by config:" + conf.toString());

    }

    @Override
    public void flush() {
    }

    @Override
    public void sendMetrics(String content) {
        producer.sendMessage(content, topic);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("success to send record " + content + ",topic is:" + topic);
        }

    }
}
