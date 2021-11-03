package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESDeleteDCDRIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESGetDCDRIndexResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetDCDRIndexAction extends Action<ESGetDCDRIndexRequest, ESGetDCDRIndexResponse, ESGetDCDRIndexRequestBuilder> {
    public static final ESGetDCDRIndexAction INSTANCE = new ESGetDCDRIndexAction();
    public static final String NAME = "cluster:admin/dcdr/replication/get";

    private ESGetDCDRIndexAction() {
        super(NAME);
    }

    @Override
    public ESGetDCDRIndexResponse newResponse() {
        return new ESGetDCDRIndexResponse();
    }

    @Override
    public ESGetDCDRIndexRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESGetDCDRIndexRequestBuilder(client, this);
    }
}
