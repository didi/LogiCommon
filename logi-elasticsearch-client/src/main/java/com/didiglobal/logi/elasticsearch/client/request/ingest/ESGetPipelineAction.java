package com.didiglobal.logi.elasticsearch.client.request.ingest;

import com.didiglobal.logi.elasticsearch.client.response.ingest.ESGetPipelineResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetPipelineAction extends Action<ESGetPipelineRequest, ESGetPipelineResponse, ESGetPipelineRequestBuilder> {
    public static final ESGetPipelineAction INSTANCE = new ESGetPipelineAction();
    public static final String NAME = "cluster:admin/ingest/pipeline/get";

    private ESGetPipelineAction() {
        super(NAME);
    }

    @Override
    public ESGetPipelineResponse newResponse() {
        return new ESGetPipelineResponse();
    }

    @Override
    public ESGetPipelineRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESGetPipelineRequestBuilder(client, this);
    }
}
