package com.didiglobal.logi.log.common.web.spring;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.log.ILog;
import com.didiglobal.logi.log.LogFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author jinbinbin
 * @version $Id: ResponseResult.java, v 0.1 2017年04月18日 17:10 jinbinbin Exp $
 */
@RestControllerAdvice
public class TunnelResponseBodyAdvice implements ResponseBodyAdvice {

    private static final ILog LOGGER = LogFactory.getLog("responseLogger");

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            String clazzName = returnType.getDeclaringClass().getSimpleName();
            String javaMethodName = returnType.getMethod().getName();
            String url = request.getURI().getPath();
            String httpMethod = request.getMethod().name();
            String result = JSON.toJSONString(body);

            String params = RequestBodyCache.getRequestBody();
            if (StringUtils.isBlank(params)) {
                params = ((ServletServerHttpRequest) request).getServletRequest().getQueryString();
            }

            LOGGER.info("response||javaMethod={}||httpUrl={}||params={}||result={}", clazzName + "." + javaMethodName,
                httpMethod + " " + url, params, result);
            return body;
        } finally {
            RequestBodyCache.clearRequestBody();
        }
    }
}
