package com.didiglobal.logi.log.common.web.spring;

/**
 * @author jinbinbin
 * @version $Id: RequestBodyCache.java, v 0.1 2017年07月25日 12:24 jinbinbin Exp $
 */
public class RequestBodyCache {

    private static ThreadLocal<String> requestBodyThreadLocal = new ThreadLocal<String>();

    public static void setRequestBody(String requestBody) {
        requestBodyThreadLocal.set(requestBody);
    }

    public static String getRequestBody() {
        return requestBodyThreadLocal.get();
    }

    public static void clearRequestBody() {
        requestBodyThreadLocal.remove();
    }
}
