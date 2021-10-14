package com.didiglobal.logi.log.common.web.http;

import com.didiglobal.logi.log.LogFactory;
import com.didiglobal.logi.log.common.Constants;
import com.didiglobal.logi.log.common.TraceContext;
import org.apache.http.message.AbstractHttpMessage;

/**
 * @author jinbinbin
 * @version $Id: FlagHeaderUtils.java, v 0.1 2018年03月05日 22:18 jinbinbin Exp $
 */
class FlagHeaderUtils {

    static void addFlagHeader(AbstractHttpMessage httpMessage) {
        TraceContext traceContext = LogFactory.getTrace();
        if (traceContext != null) {
            httpMessage.addHeader(Constants.X_REQUEST_ID, traceContext.getTraceId());
            httpMessage.addHeader(Constants.FLAG, traceContext.getTraceId());
            httpMessage.addHeader(Constants.SPAN_ID, traceContext.getSpanId());
            httpMessage.addHeader(Constants.TRACE_ID, traceContext.getTraceId());
            httpMessage.addHeader(Constants.CSPAN_ID, LogFactory.getUniqueFlag());
        }
    }
}
