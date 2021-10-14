package com.didiglobal.logi.metrics.helper.convert;

/**
 * 
 * 
 * @author liujianhui
 * @version:2015年12月4日 上午11:40:14
 */
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverter(Class<?> sourceType, Class<?> targetType, Converter<?, ?> converter);
}
