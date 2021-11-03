package com.didiglobal.logi.elasticsearch.client.response.cluster.nodes;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;

import java.util.Map;

public class ESClusterNodesResponse extends ESActionResponse {
    @JSONField(name = "cluster_name")
    private String clusterName;

    @JSONField(name = "nodes")
    private Map<String, ClusterNodeInfo> nodes;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Map<String, ClusterNodeInfo> getNodes() {
        return nodes;
    }

    public void setNodes(Map<String, ClusterNodeInfo> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return toJson().toJSONString();
    }

    public JSONObject toJson() {
        return (JSONObject) JSONObject.toJSON(this);
    }
}
