package com.didiglobal.logi.metrics.helper.convert;

/**
 * 
 * 
 * @author liujianhui
 * @version:2015年12月4日 上午11:28:16
 */
public interface Converter<S, T> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    T convert(S source);

}