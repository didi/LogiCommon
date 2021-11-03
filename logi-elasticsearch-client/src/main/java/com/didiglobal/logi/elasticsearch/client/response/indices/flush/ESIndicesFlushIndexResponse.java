package com.didiglobal.logi.elasticsearch.client.response.indices.flush;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;

public class ESIndicesFlushIndexResponse extends ESActionResponse {
    @JSONField(name = "_shards")
    private JSONObject shards;

    public JSONObject getShards() {
        return shards;
    }

    public void setShards(JSONObject shards) {
        this.shards = shards;
    }

    public int getFaild() {
        return shards.getInteger("failed");
    }

    public int getTotal() {
        return shards.getInteger("total");
    }

    public int getsuccessful() {
        return shards.getInteger("successful");
    }
}

