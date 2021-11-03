package com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr.loop.AggrLooper;
import com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr.loop.LoopResult;

import java.util.List;

public interface Aggr {

    JSONObject toJson();

    LoopResult loop(List<String> keys, AggrLooper looper) throws Exception;

    static Aggr getAggr(JSONObject root) {
        if (root == null) {
            return null;
        }
        // 这里为什么
        if (root.containsKey(DocCountAggr.BUCKETS_STR)) {
            return new DocCountAggr(root);
        } else {
            return new JsonAggr(root);
        }
    }
}

