package com.didiglobal.logi.elasticsearch.client.response.ingest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.request.ingest.Pipeline;

import java.util.Map;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetPipelineResponse extends ESActionResponse {
    private Map<String, Pipeline> pipelineMap;

    public static ESGetPipelineResponse getResponse(String str, String esVersion) throws Exception {
        ESGetPipelineResponse response = new ESGetPipelineResponse();
        Map<String, Pipeline> pipelineMap = JSONObject.parseObject(str, new TypeReference<Map<String, Pipeline>>() {
        });
        response.setPipelineMap(pipelineMap);
        return response;
    }

    public Map<String, Pipeline> getPipelineMap() {
        return pipelineMap;
    }

    public void setPipelineMap(Map<String, Pipeline> pipelineMap) {
        this.pipelineMap = pipelineMap;
    }
}
