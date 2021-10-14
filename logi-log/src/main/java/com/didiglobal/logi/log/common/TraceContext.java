package com.didiglobal.logi.log.common;

/**
 * @author jinbinbin
 * @version $Id: TraceContext.java, v 0.1 2019年01月20日 19:32 jinbinbin Exp $
 */
public class TraceContext {

    private String traceId;
    private String spanId;
    private String cspanId;

    public String getTraceId() {
        return traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public String getCspanId() {
        return cspanId;
    }

    public void setCspanId(String cspanId) {
        this.cspanId = cspanId;
    }

    public TraceContext(String traceId, String spanId){
        this.traceId = traceId;
        this.spanId = spanId;
    }
}
