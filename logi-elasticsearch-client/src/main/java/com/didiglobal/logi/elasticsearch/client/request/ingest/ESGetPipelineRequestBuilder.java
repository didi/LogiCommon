package com.didiglobal.logi.elasticsearch.client.request.ingest;

import com.didiglobal.logi.elasticsearch.client.response.ingest.ESGetPipelineResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetPipelineRequestBuilder extends ActionRequestBuilder<ESGetPipelineRequest, ESGetPipelineResponse, ESGetPipelineRequestBuilder> {
    public ESGetPipelineRequestBuilder(ElasticsearchClient client, ESGetPipelineAction action) {
        super(client, action, new ESGetPipelineRequest());
    }

    public ESGetPipelineRequestBuilder setPipelineId(String pipelineId) {
        request.setPipelineId(pipelineId);
        return this;
    }
}
