package com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr.loop.AggrLooper;
import com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr.loop.LoopResult;

import java.util.List;

public class JsonAggr implements Aggr {
    private JSONObject obj;


    public JsonAggr(JSONObject root) {
        this.obj = root;
    }

    public JSONObject getObj() {
        return obj;
    }

    public boolean containKey(String key) {
        if (obj != null && obj.containsKey(key)) {
            return true;
        }

        return false;
    }

    public JSONObject toJson() {
        return obj;
    }

    public LoopResult loop(List<String> keys, AggrLooper looper) throws Exception {
        return looper.process(this);
    }
}

