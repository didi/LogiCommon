package com.didiglobal.logi.security.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author cjm
 *
 * http请求操作类
 */
public class HttpRequestUtil {

    public static final String USER = "X-SSO-USER";

    public static final String APPID = "X-LOGI-SECURITY-APP-ID";

    private HttpRequestUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getFromHeader(HttpServletRequest request, String key, String defaultValue) {
        String value = request.getHeader(key);
        return value == null ? defaultValue : value;
    }

    public static Integer getOperatorId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute(HttpRequestUtil.USER);
        if(id == null) {
            return 1;
        }
        return id;
    }

    public static String getOperatorFromHeader(HttpServletRequest request) {
        return request.getHeader(USER);
    }

    public static Integer getAppId(HttpServletRequest request, int defaultAppid) {
        String appidStr = request.getHeader(APPID);

        if (StringUtils.isEmpty(appidStr)) {
            return defaultAppid;
        }

        return Integer.valueOf(appidStr);
    }

    public static Integer getAppId(HttpServletRequest request) {
        String appidStr = request.getHeader(APPID);

        if (StringUtils.isEmpty(appidStr)) {
            return null;
        }

        return Integer.valueOf(appidStr);
    }
}
