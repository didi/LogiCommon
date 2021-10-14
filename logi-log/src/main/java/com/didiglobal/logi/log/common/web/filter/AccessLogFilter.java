package com.didiglobal.logi.log.common.web.filter;

import com.didiglobal.logi.log.ILog;
import com.didiglobal.logi.log.LogFactory;
import com.didiglobal.logi.log.common.URLHelper;
import com.didiglobal.logi.log.common.web.WebConstants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jinbinbin
 * @version $Id: AccessLogFilter.java, v 0.1 2018年03月05日 22:01 jinbinbin Exp $
 */
public class AccessLogFilter implements Filter {

    private static final ILog LOGGER = LogFactory.getLog("accessLogger");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException,
                                                                                          ServletException {
        long begin = System.currentTimeMillis();
        Map<String, String> params = null;
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            //设置flag
            String flag = request.getHeader(WebConstants.FLAG);
            if (flag == null) {
                flag = request.getHeader(WebConstants.X_REQUEST_ID);
            }
            if (flag == null) {
                flag = LogFactory.getUniqueFlag();
            }

            LogFactory.setFlag(flag);
            params = getRequestParams(request);
            LOGGER.info("beforeRequest||params={}", params);
            chain.doFilter(req, response);
        } finally {
            LOGGER.info("afterRequest||params={}||timeCost={}ms", params, (System.currentTimeMillis() - begin));
            LogFactory.removeFlag();
        }
    }

    protected Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        StringBuilder requestUrl = new StringBuilder(request.getRequestURI());
        if (StringUtils.isNotEmpty(request.getQueryString())) {
            requestUrl.append("?").append(request.getQueryString());
        }
        params.put("requestUrl", requestUrl.toString());
        params.put("method", request.getMethod());
        params.put("host", URLHelper.getIpAddr(request));
        params.put("refer", request.getHeader("referer"));
        params.put("agent", request.getHeader("User-Agent"));

        return params;
    }

    @Override
    public void destroy() {

    }
}
