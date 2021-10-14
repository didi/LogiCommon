package com.didiglobal.logi.metrics.helper.convert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.didiglobal.logi.metrics.Metric;
import com.didiglobal.logi.metrics.MetricsRecord;
import com.didiglobal.logi.metrics.MetricsTag;
import com.didiglobal.logi.metrics.adapt.bean.MetricBean;
import com.didiglobal.logi.metrics.adapt.bean.MetricRecordBean;
import com.didiglobal.logi.metrics.adapt.bean.MetricTagBean;
import com.didiglobal.logi.metrics.helper.convert.support.MetricBeanConverter;
import com.didiglobal.logi.metrics.helper.convert.support.MetricRecordBeanConverter;
import com.didiglobal.logi.metrics.helper.convert.support.MetricTagBeanConverter;
import com.didiglobal.logi.metrics.util.ClassUtils;

/**
 * 类型转换服务的简单实现
 * 
 * @author liujianhui
 * @version:2015年12月5日 下午4:37:41
 */
public class SimpleConversionService implements ConversionService, ConverterRegistry {

    private static final SimpleConversionService  INSTANCE       = new SimpleConversionService();

    //暂时只考虑在系统启动的时候注册，不考虑运行时的动态注册，线程不安全
    private Map<ConvertiblePair, Converter<?, ?>> converters     = new HashMap<ConvertiblePair, Converter<?, ?>>();

    private Map<ConvertiblePair, Converter<?, ?>> cacheConverter = new ConcurrentHashMap<ConvertiblePair, Converter<?, ?>>();

    private SimpleConversionService() {
        addConverter(Metric.class, MetricBean.class, new MetricBeanConverter());
        addConverter(MetricsTag.class, MetricTagBean.class, new MetricTagBeanConverter());
        addConverter(MetricsRecord.class, MetricRecordBean.class, new MetricRecordBeanConverter(this));
    }

    @Override
    public void addConverter(Converter<?, ?> converter) {
    }

    @Override
    public void addConverter(Class<?> sourceType, Class<?> targetType, Converter<?, ?> converter) {
        converters.put(new ConvertiblePair(sourceType, targetType), converter);
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        //先从缓冲中获取
        ConvertiblePair key = new ConvertiblePair(source.getClass(), targetType);
        Converter<?, ?> converter = cacheConverter.get(key);
        if (null != converter) {
            return (T) ((Converter<Object, ?>) converter).convert(source);
        }

        //从注册的converters中查询匹配到的converter
        converter = find(source.getClass(), targetType);
        if (null == converter) {
            throw new ConverterNotFoundException(source.getClass(), targetType);
        }
        return (T) ((Converter<Object, ?>) converter).convert(source);
    }

    public static SimpleConversionService getInstance() {
        return INSTANCE;
    }

    public Converter find(Class<?> sourceType, Class<?> targetType) {
        // Search the full type hierarchy
        List<Class<?>> sourceCandidates = getClassHierarchy(sourceType);
        List<Class<?>> targetCandidates = getClassHierarchy(targetType);
        for (Class<?> sourceCandidate : sourceCandidates) {
            for (Class<?> targetCandidate : targetCandidates) {
                ConvertiblePair convertiblePair = new ConvertiblePair(sourceCandidate, targetCandidate);
                Converter converter = converters.get(convertiblePair);
                if (converter != null) {
                    return converter;
                }
            }
        }
        return null;
    }

    /**
     * Returns an ordered class hierarchy for the given type.
     * @param type the type
     * @return an ordered list of all classes that the given type extends or implements
     */
    private List<Class<?>> getClassHierarchy(Class<?> type) {
        List<Class<?>> hierarchy = new ArrayList<Class<?>>(20);
        Set<Class<?>> visited = new HashSet<Class<?>>(20);
        addToClassHierarchy(0, ClassUtils.resolvePrimitiveIfNecessary(type), false, hierarchy, visited);
        boolean array = type.isArray();
        int i = 0;
        while (i < hierarchy.size()) {
            Class<?> candidate = hierarchy.get(i);
            candidate = (array ? candidate.getComponentType() : ClassUtils.resolvePrimitiveIfNecessary(candidate));
            Class<?> superclass = candidate.getSuperclass();
            if (superclass != null && superclass != Object.class && superclass != Enum.class) {
                addToClassHierarchy(i + 1, candidate.getSuperclass(), array, hierarchy, visited);
            }
            addInterfacesToClassHierarchy(candidate, array, hierarchy, visited);
            i++;
        }

        if (Enum.class.isAssignableFrom(type)) {
            addToClassHierarchy(hierarchy.size(), Enum.class, array, hierarchy, visited);
            addToClassHierarchy(hierarchy.size(), Enum.class, false, hierarchy, visited);
            addInterfacesToClassHierarchy(Enum.class, array, hierarchy, visited);
        }

        addToClassHierarchy(hierarchy.size(), Object.class, array, hierarchy, visited);
        addToClassHierarchy(hierarchy.size(), Object.class, false, hierarchy, visited);
        return hierarchy;
    }

    private void addInterfacesToClassHierarchy(Class<?> type, boolean asArray, List<Class<?>> hierarchy,
                                               Set<Class<?>> visited) {

        for (Class<?> implementedInterface : type.getInterfaces()) {
            addToClassHierarchy(hierarchy.size(), implementedInterface, asArray, hierarchy, visited);
        }
    }

    private void addToClassHierarchy(int index, Class<?> type, boolean asArray, List<Class<?>> hierarchy,
                                     Set<Class<?>> visited) {

        if (asArray) {
            type = Array.newInstance(type, 0).getClass();
        }
        if (visited.add(type)) {
            hierarchy.add(index, type);
        }
    }

}
