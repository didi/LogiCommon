package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESGetDCDRTemplateResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetDCDRTemplateAction extends Action<ESGetDCDRTemplateRequest, ESGetDCDRTemplateResponse, ESGetDCDRTemplateRequestBuilder> {
    public static final ESGetDCDRTemplateAction INSTANCE = new ESGetDCDRTemplateAction();
    public static final String NAME = "cluster:admin/dcdr/auto_replication/get";

    private ESGetDCDRTemplateAction() {
        super(NAME);
    }

    @Override
    public ESGetDCDRTemplateResponse newResponse() {
        return new ESGetDCDRTemplateResponse();
    }

    @Override
    public ESGetDCDRTemplateRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESGetDCDRTemplateRequestBuilder(client, this);
    }
}
