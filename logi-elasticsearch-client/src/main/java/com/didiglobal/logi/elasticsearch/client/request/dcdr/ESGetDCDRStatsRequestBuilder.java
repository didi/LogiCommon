package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESGetDCDRStatsResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2020-01-16
 */
public class ESGetDCDRStatsRequestBuilder extends ActionRequestBuilder<ESGetDCDRStatsRequest, ESGetDCDRStatsResponse, ESGetDCDRStatsRequestBuilder> {
    public ESGetDCDRStatsRequestBuilder(ElasticsearchClient client, ESGetDCDRStatsAction action) {
        super(client, action, new ESGetDCDRStatsRequest());
    }

    public ESGetDCDRStatsRequestBuilder setPrimaryIndex(String primaryIndex) {
        request.setPrimaryIndex(primaryIndex);
        return this;
    }
}