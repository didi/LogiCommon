package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESGetDCDRIndexResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetDCDRIndexRequestBuilder extends ActionRequestBuilder<ESGetDCDRIndexRequest, ESGetDCDRIndexResponse, ESGetDCDRIndexRequestBuilder> {
    public ESGetDCDRIndexRequestBuilder(ElasticsearchClient client, ESGetDCDRIndexAction action) {
        super(client, action, new ESGetDCDRIndexRequest());
    }

    public ESGetDCDRIndexRequestBuilder setPrimaryIndex(String primaryIndex) {
        request.setPrimaryIndex(primaryIndex);
        return this;
    }
}