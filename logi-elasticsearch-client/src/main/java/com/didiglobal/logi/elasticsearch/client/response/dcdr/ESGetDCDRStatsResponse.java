package com.didiglobal.logi.elasticsearch.client.response.dcdr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.request.dcdr.DCDRIndexStats;

import java.util.HashMap;
import java.util.Map;

/**
 * author weizijun
 * date：2020-01-16
 */
public class ESGetDCDRStatsResponse extends ESActionResponse {
    private Map<String, DCDRIndexStats> indicesStats = new HashMap<>();

    public ESGetDCDRStatsResponse() {

    }

    public ESGetDCDRStatsResponse(JSONObject root) {
        if (root == null) {
            return;
        }

        for (String key : root.keySet()) {
            DCDRIndexStats dcdrIndexStats = new DCDRIndexStats(root.getJSONObject(key));
            indicesStats.put(key, dcdrIndexStats);
        }
    }

    public static ESGetDCDRStatsResponse parserResponse(String str) {
        JSONObject root = JSON.parseObject(str);
        return new ESGetDCDRStatsResponse(root);
    }

    public Map<String, DCDRIndexStats> getIndicesStats() {
        return indicesStats;
    }
}
