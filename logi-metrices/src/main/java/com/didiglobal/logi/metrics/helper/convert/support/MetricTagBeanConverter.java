package com.didiglobal.logi.metrics.helper.convert.support;

import com.didiglobal.logi.metrics.MetricsTag;
import com.didiglobal.logi.metrics.adapt.bean.MetricTagBean;
import com.didiglobal.logi.metrics.helper.convert.Converter;

public class MetricTagBeanConverter implements Converter<MetricsTag, MetricTagBean> {

    @Override
    public MetricTagBean convert(MetricsTag source) {
        MetricTagBean metricTagBean = new MetricTagBean();
        metricTagBean.setDescription(source.description());
        metricTagBean.setName(source.name());
        metricTagBean.setValue(source.value());
        return metricTagBean;
    }

}
