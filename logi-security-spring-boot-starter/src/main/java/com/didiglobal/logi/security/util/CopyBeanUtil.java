package com.didiglobal.logi.security.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cjm
 * entity<->vo
 */
public class CopyBeanUtil {

    private CopyBeanUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 复制对象
     */
    public static <T> T copy(Object source, Class<T> target) {
        if(source == null || target == null) {
            return null;
        }
        try {
            T newInstance = target.newInstance();
            BeanUtils.copyProperties(source, newInstance);
            return newInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 复制list
     */
    public static <T, K> List<K> copyList(List<T> source, Class<K> target) {
        if (null == source || source.isEmpty()) {
            return Collections.emptyList();
        }
        return source.stream().map(e -> copy(e, target)).collect(Collectors.toList());
    }

    /**
     * 复制page
     */
    public static <T, K> IPage<K> copyPage(IPage<T> source, Class<K> target) {
        if(source == null || target == null) {
            return null;
        }
        IPage<K> newInstance = copy(source, Page.class);
        if(newInstance != null) {
            newInstance.setRecords(copyList(source.getRecords(), target));
        }
        return newInstance;
    }

    /**
     * 复制page（不copyList）
     */
    public static <T, K> IPage<K> copyPageExcludeList(IPage<T> source) {
        if(source == null ) {
            return null;
        }
        return copy(source, Page.class);
    }
}