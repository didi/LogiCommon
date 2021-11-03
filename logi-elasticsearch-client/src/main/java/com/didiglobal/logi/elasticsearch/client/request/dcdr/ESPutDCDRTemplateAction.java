package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESPutDCDRTemplateResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutDCDRTemplateAction extends Action<ESPutDCDRTemplateRequest, ESPutDCDRTemplateResponse, ESPutDCDRTemplateRequestBuilder> {
    public static final ESPutDCDRTemplateAction INSTANCE = new ESPutDCDRTemplateAction();
    public static final String NAME = "cluster:admin/dcdr/auto_replication/put";

    private ESPutDCDRTemplateAction() {
        super(NAME);
    }

    @Override
    public ESPutDCDRTemplateResponse newResponse() {
        return new ESPutDCDRTemplateResponse();
    }

    @Override
    public ESPutDCDRTemplateRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESPutDCDRTemplateRequestBuilder(client, this);
    }
}
