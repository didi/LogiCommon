package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESPutDCDRTemplateResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutDCDRTemplateRequestBuilder extends ActionRequestBuilder<ESPutDCDRTemplateRequest, ESPutDCDRTemplateResponse, ESPutDCDRTemplateRequestBuilder> {
    public ESPutDCDRTemplateRequestBuilder(ElasticsearchClient client, ESPutDCDRTemplateAction action) {
        super(client, action, new ESPutDCDRTemplateRequest());
    }


    public ESPutDCDRTemplateRequestBuilder setName(String name) {
        request.setName(name);
        return this;
    }

    public ESPutDCDRTemplateRequestBuilder setTemplate(String template) {
        request.putParam(ESPutDCDRTemplateRequest.TEMPLATE, template);
        return this;
    }

    public ESPutDCDRTemplateRequestBuilder setReplicaCluster(String replicaCluster) {
        request.putParam(ESPutDCDRTemplateRequest.REPLICA_CLUSTER, replicaCluster);
        return this;
    }
}