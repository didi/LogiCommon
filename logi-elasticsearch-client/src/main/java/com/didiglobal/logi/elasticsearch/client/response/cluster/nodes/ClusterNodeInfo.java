package com.didiglobal.logi.elasticsearch.client.response.cluster.nodes;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.response.model.http.HttpInfo;

import java.util.List;
import java.util.Map;

public class ClusterNodeInfo extends ESActionResponse {
    @JSONField(name = "name")
    private String name;

    @JSONField(name = "transport_address")
    private String transportAddress;

    @JSONField(name = "host")
    private String host;

    @JSONField(name = "ip")
    private String ip;

    @JSONField(name = "version")
    private String version;

    @JSONField(name = "roles")
    private List<String> roles;

    @JSONField(name = "attributes")
    private Map<String, String> attributes;

    @JSONField(name = "http")
    private HttpInfo httpInfo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransportAddress() {
        return transportAddress;
    }

    public void setTransportAddress(String transportAddress) {
        this.transportAddress = transportAddress;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public HttpInfo getHttpInfo() {
        return httpInfo;
    }

    public void setHttpInfo(HttpInfo httpInfo) {
        this.httpInfo = httpInfo;
    }

    @Override
    public String toString() {
        return toJson().toJSONString();
    }

    public JSONObject toJson() {
        return (JSONObject) JSONObject.toJSON(this);
    }
}
