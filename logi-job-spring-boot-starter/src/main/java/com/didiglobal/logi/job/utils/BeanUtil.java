package com.didiglobal.logi.job.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class BeanUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转换为目标类对象.
     *
     * @param source      源
     * @param targetClass 目标类
     * @param <T>         目标对象
     * @return 转换到的对象
     */
    public static <T> T convertTo(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        T tgt = null;
        try {
            tgt = targetClass.newInstance();
            BeanUtils.copyProperties(source, tgt);
        } catch (Exception e) {
            logger.warn("convert obj2Obj error||msg={}", e.getMessage(), e);
        }

        return tgt;
    }

    /**
     * 对象转换为目标类对象.
     *
     * @param source      源
     * @param targetClass 目标类
     * @param <T>         目标对象
     * @return 转换到的对象
     */
    public static <T> List<T> convertToList(String source, Class<T> targetClass) {
        try {
            JavaType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, targetClass);
            return objectMapper.readValue(source, javaType);
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    /**
     * 对象转json 字符串.
     *
     * @param source source
     * @return str
     */
    public static String convertToJson(Object source) {
        try {
            return source == null ? null : objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            logger.error("source to json error, e->", e);
            return null;
        }
    }
}