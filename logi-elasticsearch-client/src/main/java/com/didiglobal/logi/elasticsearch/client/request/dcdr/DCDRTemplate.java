package com.didiglobal.logi.elasticsearch.client.request.dcdr;

public class DCDRTemplate {

    private String name;

    private String template;

    private String replicaCluster;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getReplicaCluster() {
        return replicaCluster;
    }

    public void setReplicaCluster(String replicaCluster) {
        this.replicaCluster = replicaCluster;
    }
}
