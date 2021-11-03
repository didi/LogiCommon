package com.didiglobal.logi.elasticsearch.client.request.ingest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.ingest.ESPutPipelineResponse;
import org.elasticsearch.action.ActionRequestValidationException;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutPipelineRequest extends ESActionRequest<ESPutPipelineRequest> {
    private String pipelineId;
    private Pipeline pipeline;

    public ESPutPipelineRequest setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
        return this;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        if (pipelineId == null || pipelineId.length() == 0) {
            throw new Exception("pipelineId is null");
        }

        String endPoint = "/_ingest/pipeline/" + pipelineId;
        RestRequest rr = new RestRequest("PUT", endPoint, null);
        JSONObject obj = pipeline.toJson();
        rr.setBody(obj.toJSONString());
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESPutPipelineResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }
}
