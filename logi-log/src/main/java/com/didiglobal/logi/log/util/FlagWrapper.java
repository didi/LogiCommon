package com.didiglobal.logi.log.util;

import com.didiglobal.logi.log.LogFactory;
import com.didiglobal.logi.log.common.Constants;
import com.didiglobal.logi.log.common.TraceContext;

/**
 * @author jinbinbin
 * @version $Id: FlagWrapper.java, v 0.1 2017年12月19日 22:52 jinbinbin Exp $
 */
public class FlagWrapper {

    public static String wrapMessage(String message) {
        if (null == message) {
            return "";
        }
        String flag = LogFactory.getFlag();

        StringBuilder stringBuilder = new StringBuilder();
        if (flag != null && !flag.isEmpty() && !"null".equals(flag)) {
            stringBuilder.append("||").append(Constants.FLAG).append("=").append(flag);
        }
        TraceContext traceContext = LogFactory.getTrace();
        if (traceContext != null) {
            if (traceContext.getTraceId() != null) {
                stringBuilder.append("||").append(Constants.TRACE_ID).append("=").append(traceContext.getTraceId());
            }
            if (traceContext.getSpanId() != null) {
                stringBuilder.append("||").append(Constants.SPAN_ID).append("=").append(traceContext.getSpanId());
            }

            if (traceContext.getCspanId() != null) {
                stringBuilder.append("||").append(Constants.CSPAN_ID).append("=").append(traceContext.getCspanId());
            }
        }

        if (stringBuilder.length() == 0) {
            return message;
        }
        return message + stringBuilder.toString();
    }

    public static String wrapExceptionMessage(String message) {
        return wrapMessage(message) + "||hostName=" + HostUtil.getHostName();
    }

}
