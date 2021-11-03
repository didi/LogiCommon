package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESDeleteDCDRTemplateResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeleteDCDRTemplateRequestBuilder extends ActionRequestBuilder<ESDeleteDCDRTemplateRequest, ESDeleteDCDRTemplateResponse, ESDeleteDCDRTemplateRequestBuilder> {
    public ESDeleteDCDRTemplateRequestBuilder(ElasticsearchClient client, ESDeleteDCDRTemplateAction action) {
        super(client, action, new ESDeleteDCDRTemplateRequest());
    }


    public ESDeleteDCDRTemplateRequestBuilder setName(String name) {
        request.setName(name);
        return this;
    }
}