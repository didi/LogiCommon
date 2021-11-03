package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESPutDCDRIndexResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

public class ESPutDCDRIndexRequestBuilder extends ActionRequestBuilder<ESPutDCDRIndexRequest, ESPutDCDRIndexResponse, ESPutDCDRIndexRequestBuilder> {
    public ESPutDCDRIndexRequestBuilder(ElasticsearchClient client, ESPutDCDRIndexAction action) {
        super(client, action, new ESPutDCDRIndexRequest());
    }


    public ESPutDCDRIndexRequestBuilder setIndexName(String indexName) {
        request.setIndexName(indexName);
        return this;
    }

    public ESPutDCDRIndexRequestBuilder setReplicaIndex(String indexName) {
        request.putParam(ESPutDCDRIndexRequest.REPLICA_INDEX, indexName);
        return this;
    }

    public ESPutDCDRIndexRequestBuilder setReplicaCluster(String replicaCluster) {
        request.putParam(ESPutDCDRIndexRequest.REPLICA_CLUSTER, replicaCluster);
        return this;
    }
}