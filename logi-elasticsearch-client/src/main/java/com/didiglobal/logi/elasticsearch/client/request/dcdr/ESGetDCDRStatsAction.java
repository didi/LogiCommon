package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESGetDCDRStatsResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2020-01-16
 */
public class ESGetDCDRStatsAction extends Action<ESGetDCDRStatsRequest, ESGetDCDRStatsResponse, ESGetDCDRStatsRequestBuilder> {
    public static final ESGetDCDRStatsAction INSTANCE = new ESGetDCDRStatsAction();
    public static final String NAME = "indices:admin/dcdr/replication_stats";

    private ESGetDCDRStatsAction() {
        super(NAME);
    }

    @Override
    public ESGetDCDRStatsResponse newResponse() {
        return new ESGetDCDRStatsResponse();
    }

    @Override
    public ESGetDCDRStatsRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESGetDCDRStatsRequestBuilder(client, this);
    }
}
