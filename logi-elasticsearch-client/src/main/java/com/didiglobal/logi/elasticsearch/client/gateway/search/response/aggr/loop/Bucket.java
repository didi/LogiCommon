package com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr.loop;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr.Aggr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bucket {
    private String key;
    private long docCount;
    private String keyAsString = null;

    private Map<String, Aggr> aggrMap = new HashMap<>();

    private static final String KEY_STR = "key";
    private static final String DOC_COUNT_STR = "doc_count";
    private static final String KEY_AS_STRING_STR = "key_as_string";

    public Bucket(JSONObject root) {

        for (String key : root.keySet()) {
            if (key.equalsIgnoreCase(KEY_STR)) {
                this.key = root.getString(KEY_STR);
                continue;
            }

            if (key.equalsIgnoreCase(DOC_COUNT_STR)) {
                this.docCount = root.getLong(DOC_COUNT_STR);
                continue;
            }

            if (key.equalsIgnoreCase(KEY_AS_STRING_STR)) {
                this.keyAsString = root.getString(KEY_AS_STRING_STR);
                continue;
            }

            aggrMap.put(key, Aggr.getAggr(root.getJSONObject(key)));
        }
    }

    public JSONObject toJson() {
        JSONObject root = new JSONObject();
        root.put(KEY_STR, key);
        root.put(DOC_COUNT_STR, docCount);

        if (keyAsString != null) {
            root.put(KEY_AS_STRING_STR, keyAsString);
        }

        for (String key : aggrMap.keySet()) {
            root.put(key, aggrMap.get(key).toJson());
        }

        return root;
    }


    public LoopResult loop(List<String> keys, AggrLooper looper) throws Exception {
        keys.add(key);
        try {
            if (looper.process(keys, docCount) == LoopResult.RETURN) {
                return LoopResult.RETURN;
            }

            for (String aggrKey : aggrMap.keySet()) {
                if (aggrMap.get(aggrKey).loop(keys, looper) == LoopResult.RETURN) {
                    return LoopResult.RETURN;
                }
            }

            return LoopResult.NORMAL;
        } finally {
            keys.remove(keys.size() - 1);
        }
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getDocCount() {
        return docCount;
    }

    public void setDocCount(long docCount) {
        this.docCount = docCount;
    }

    public Map<String, Aggr> getAggrMap() {
        return aggrMap;
    }

    public void setAggrMap(Map<String, Aggr> aggrMap) {
        this.aggrMap = aggrMap;
    }
}
