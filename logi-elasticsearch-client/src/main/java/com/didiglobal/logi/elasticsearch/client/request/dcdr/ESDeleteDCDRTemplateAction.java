package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESDeleteDCDRTemplateResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeleteDCDRTemplateAction extends Action<ESDeleteDCDRTemplateRequest, ESDeleteDCDRTemplateResponse, ESDeleteDCDRTemplateRequestBuilder> {
    public static final ESDeleteDCDRTemplateAction INSTANCE = new ESDeleteDCDRTemplateAction();
    public static final String NAME = "cluster:admin/dcdr/auto_replication/delete";

    private ESDeleteDCDRTemplateAction() {
        super(NAME);
    }

    @Override
    public ESDeleteDCDRTemplateResponse newResponse() {
        return new ESDeleteDCDRTemplateResponse();
    }

    @Override
    public ESDeleteDCDRTemplateRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESDeleteDCDRTemplateRequestBuilder(client, this);
    }
}
