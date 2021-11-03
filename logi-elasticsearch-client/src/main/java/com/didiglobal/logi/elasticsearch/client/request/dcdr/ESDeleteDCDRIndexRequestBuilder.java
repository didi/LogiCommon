package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESDeleteDCDRIndexResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeleteDCDRIndexRequestBuilder extends ActionRequestBuilder<ESDeleteDCDRIndexRequest, ESDeleteDCDRIndexResponse, ESDeleteDCDRIndexRequestBuilder> {
    public ESDeleteDCDRIndexRequestBuilder(ElasticsearchClient client, ESDeleteDCDRIndexAction action) {
        super(client, action, new ESDeleteDCDRIndexRequest());
    }

    public ESDeleteDCDRIndexRequestBuilder setPrimaryIndex(String primaryIndex) {
        request.setPrimaryIndex(primaryIndex);
        return this;
    }

    public ESDeleteDCDRIndexRequestBuilder setReplicaIndex(String replicaIndex) {
        request.setReplicaIndex(replicaIndex);
        return this;
    }

    public ESDeleteDCDRIndexRequestBuilder setReplicaCluster(String replicaCluster) {
        request.setReplicaCluster(replicaCluster);
        return this;
    }
}