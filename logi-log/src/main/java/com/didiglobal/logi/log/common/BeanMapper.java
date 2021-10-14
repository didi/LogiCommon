package com.didiglobal.logi.log.common;

/**
 * @author jinbinbin
 * @version $Id: BeanMapper.java, v 0.1 2018年04月09日 16:37 jinbinbin Exp $
 */

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * bean映射属性拷贝工具. 
 * <p>
 * dozer类似于apache的BeanUtils, 但功能更强大. 支持不同类型之间同名属性的copy以及深度递归copy.
 * </p>
 * @author didi
 */
public final class BeanMapper {
    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    private BeanMapper() {
    }

    /**
     * 基于Dozer转换对象的类型.
     * @param source source
     * @param destinationClass destinationClass
     * @param <T> 类型
     * @return T
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     * @param sourceList sourceList
     * @param destinationClass destinationClass
     * @param <T> T
     * @return T
     */
    public static <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<T>();
        }
        List<T> destinationList = new ArrayList<T>();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     * @param source source
     * @param destinationObject destinationObject
     */
    public static void copy(Object source, Object destinationObject) {
        if (destinationObject == null || source == null) {
            return;
        }
        dozer.map(source, destinationObject);
    }

}