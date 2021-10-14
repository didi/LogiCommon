package com.didiglobal.logi.metrics.helper.convert;

public interface ConversionService {
    <T> T convert(Object source, Class<T> targetType);
}
