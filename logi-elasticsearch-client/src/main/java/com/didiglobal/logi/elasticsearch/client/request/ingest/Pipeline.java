package com.didiglobal.logi.elasticsearch.client.request.ingest;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * author weizijun
 * date：2019-07-11
 */
public class Pipeline {
    private static final String DESC_STR = "description";
    private static final String THROTTLE_STR = "throttle";
    private static final String PROCESSORS_STR = "processors";

    /**
     * pipeline 描述
     */
    private String description;

    /**
     * pipeline限流值
     */
    private Integer throttle;

    /**
     * pipeline处理器
     */
    private List<JSONObject> processors;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getThrottle() {
        return throttle;
    }

    public void setThrottle(Integer throttle) {
        this.throttle = throttle;
    }

    public List<JSONObject> getProcessors() {
        return processors;
    }

    public void setProcessors(List<JSONObject> processors) {
        this.processors = processors;
    }

    public JSONObject toJson() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put(DESC_STR, description);
        if (throttle != null) {
            obj.put(THROTTLE_STR, throttle);
        }

        obj.put(PROCESSORS_STR, processors);
        return obj;
    }
}
