package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESPutDCDRIndexResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

public class ESPutDCDRIndexAction extends Action<ESPutDCDRIndexRequest, ESPutDCDRIndexResponse, ESPutDCDRIndexRequestBuilder> {
    public static final ESPutDCDRIndexAction INSTANCE = new ESPutDCDRIndexAction();
    public static final String NAME = "cluster:admin/dcdr/replication/create";

    private ESPutDCDRIndexAction() {
        super(NAME);
    }

    @Override
    public ESPutDCDRIndexResponse newResponse() {
        return new ESPutDCDRIndexResponse();
    }

    @Override
    public ESPutDCDRIndexRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESPutDCDRIndexRequestBuilder(client, this);
    }
}
