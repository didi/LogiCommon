package com.didiglobal.logi.elasticsearch.client.request.ingest;

import com.didiglobal.logi.elasticsearch.client.response.ingest.ESDeletePipelineResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeletePipelineAction extends Action<ESDeletePipelineRequest, ESDeletePipelineResponse, ESDeletePipelineRequestBuilder> {
    public static final ESDeletePipelineAction INSTANCE = new ESDeletePipelineAction();
    public static final String NAME = "cluster:admin/ingest/pipeline/delete";

    private ESDeletePipelineAction() {
        super(NAME);
    }

    @Override
    public ESDeletePipelineResponse newResponse() {
        return new ESDeletePipelineResponse();
    }

    @Override
    public ESDeletePipelineRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESDeletePipelineRequestBuilder(client, this);
    }
}
