package com.didiglobal.logi.elasticsearch.client.request.ingest;

import com.didiglobal.logi.elasticsearch.client.response.ingest.ESPutPipelineResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutPipelineRequestBuilder extends ActionRequestBuilder<ESPutPipelineRequest, ESPutPipelineResponse, ESPutPipelineRequestBuilder> {
    public ESPutPipelineRequestBuilder(ElasticsearchClient client, ESPutPipelineAction action) {
        super(client, action, new ESPutPipelineRequest());
    }

    public ESPutPipelineRequestBuilder setPipelineId(String pipelineId) {
        request.setPipelineId(pipelineId);
        return this;
    }

    public ESPutPipelineRequestBuilder setPipeline(Pipeline pipeline) {
        request.setPipeline(pipeline);
        return this;
    }
}