package com.didiglobal.logi.elasticsearch.client.request.security;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class SecurityRole {

    @JSONField(name = "cluster")
    private List<String> cluster;

    @JSONField(name = "indices")
    private List<SecurityRoleIndex> indices;

    public List<String> getCluster() {
        return cluster;
    }

    public void setCluster(List<String> cluster) {
        this.cluster = cluster;
    }

    public List<SecurityRoleIndex> getIndices() {
        return indices;
    }

    public void setIndices(List<SecurityRoleIndex> indices) {
        this.indices = indices;
    }
}
