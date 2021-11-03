package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.alibaba.fastjson.annotation.JSONField;

public class DCDRIndex {

    @JSONField(name = "primary_index")
    private String primaryIndex;


    @JSONField(name = "replica_index")
    private String replicaIndex;


    @JSONField(name = "replica_cluster")
    private String replicaCluster;

    @JSONField(name = "replication_state")
    private Boolean replicationState;


    public String getPrimaryIndex() {
        return primaryIndex;
    }

    public void setPrimaryIndex(String primaryIndex) {
        this.primaryIndex = primaryIndex;
    }

    public String getReplicaIndex() {
        return replicaIndex;
    }

    public void setReplicaIndex(String replicaIndex) {
        this.replicaIndex = replicaIndex;
    }

    public String getReplicaCluster() {
        return replicaCluster;
    }

    public void setReplicaCluster(String replicaCluster) {
        this.replicaCluster = replicaCluster;
    }

    public Boolean getReplicationState() {
        return replicationState;
    }

    public void setReplicationState(Boolean replicationState) {
        this.replicationState = replicationState;
    }
}
