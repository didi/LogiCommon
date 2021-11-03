package com.didiglobal.logi.elasticsearch.client.request.ingest;

import com.didiglobal.logi.elasticsearch.client.response.ingest.ESPutPipelineResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutPipelineAction extends Action<ESPutPipelineRequest, ESPutPipelineResponse, ESPutPipelineRequestBuilder> {
    public static final ESPutPipelineAction INSTANCE = new ESPutPipelineAction();
    public static final String NAME = "cluster:admin/ingest/pipeline/put";

    private ESPutPipelineAction() {
        super(NAME);
    }

    @Override
    public ESPutPipelineResponse newResponse() {
        return new ESPutPipelineResponse();
    }

    @Override
    public ESPutPipelineRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESPutPipelineRequestBuilder(client, this);
    }
}
