package com.didiglobal.logi.metrics.sink;

import org.apache.log4j.Logger;

import com.didiglobal.logi.metrics.sink.mq.AbstractMetricSink;

/**
 * 指标输出到文件系统中
 * 
 * @author liujianhui
 * @version:2016年4月22日 上午9:49:09
 */
public class LoggerMetricSink extends AbstractMetricSink {

    private static final Logger METRIC_LOGGER = Logger.getLogger("metric");

    @Override
    public void sendMetrics(String content) {
        METRIC_LOGGER.info(content);
    }

}
