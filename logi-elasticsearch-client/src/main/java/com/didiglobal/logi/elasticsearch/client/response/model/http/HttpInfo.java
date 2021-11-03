package com.didiglobal.logi.elasticsearch.client.response.model.http;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class HttpInfo {
    @JSONField(name = "bound_address")
    private List<String> boundAddress;

    @JSONField(name = "publish_address")
    private String publishAddress;

    @JSONField(name = "max_content_length_in_bytes")
    private long maxContentLengthInBytes;

    public List<String> getBoundAddress() {
        return boundAddress;
    }

    public void setBoundAddress(List<String> boundAddress) {
        this.boundAddress = boundAddress;
    }

    public String getPublishAddress() {
        return publishAddress;
    }

    public void setPublishAddress(String publishAddress) {
        this.publishAddress = publishAddress;
    }

    public long getMaxContentLengthInBytes() {
        return maxContentLengthInBytes;
    }

    public void setMaxContentLengthInBytes(long maxContentLengthInBytes) {
        this.maxContentLengthInBytes = maxContentLengthInBytes;
    }
}


