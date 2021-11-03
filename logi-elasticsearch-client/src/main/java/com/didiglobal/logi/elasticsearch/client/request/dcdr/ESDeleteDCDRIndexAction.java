package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESDeleteDCDRIndexResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeleteDCDRIndexAction extends Action<ESDeleteDCDRIndexRequest, ESDeleteDCDRIndexResponse, ESDeleteDCDRIndexRequestBuilder> {
    public static final ESDeleteDCDRIndexAction INSTANCE = new ESDeleteDCDRIndexAction();
    public static final String NAME = "cluster:admin/dcdr/replication/delete";

    private ESDeleteDCDRIndexAction() {
        super(NAME);
    }

    @Override
    public ESDeleteDCDRIndexResponse newResponse() {
        return new ESDeleteDCDRIndexResponse();
    }

    @Override
    public ESDeleteDCDRIndexRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESDeleteDCDRIndexRequestBuilder(client, this);
    }
}
