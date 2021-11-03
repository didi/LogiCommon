package com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr.loop;

import com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr.JsonAggr;

import java.util.List;

public interface AggrLooper {
    default LoopResult process(List<String> keys, Long count) throws Exception {
        return LoopResult.NORMAL;
    }

    default LoopResult process(JsonAggr aggr) {
        return LoopResult.NORMAL;
    }
}
