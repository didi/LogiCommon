package com.didiglobal.logi.elasticsearch.client.request.ingest;

import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.ingest.ESGetPipelineResponse;
import org.elasticsearch.action.ActionRequestValidationException;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetPipelineRequest extends ESActionRequest<ESGetPipelineRequest> {
    private String pipelineId;

    public ESGetPipelineRequest setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
        return this;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        if (pipelineId == null || pipelineId.length() == 0) {
            throw new Exception("pipelineId is null");
        }

        String endPoint = "/_ingest/pipeline/" + pipelineId;

        RestRequest rr = new RestRequest("GET", endPoint, null);
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return ESGetPipelineResponse.getResponse(respStr, response.getEsVersion());
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
