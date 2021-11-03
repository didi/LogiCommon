package com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr.loop.AggrLooper;
import com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr.loop.Bucket;
import com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr.loop.LoopResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocCountAggr implements Aggr {

    private Map<String, Object> notUseMap = new HashMap<>();
//    private long docCountErrorUpperBound;
//    private long sumOtherDocCount;

    private List<Bucket> buckets = new ArrayList<>();


    //    public static final String DOC_COUNT_ERROR_UPPER_BOUND_STR = "doc_count_error_upper_bound";
//    private static final String SUM_OTHER_DOC_COUNT_STR = "sum_other_doc_count";
    public static final String BUCKETS_STR = "buckets";

    public DocCountAggr(JSONObject root) {

        for (String key : root.keySet()) {
            if (BUCKETS_STR.equalsIgnoreCase(key)) {
                JSONArray array = root.getJSONArray(key);
                for (Object obj : array) {
                    buckets.add(new Bucket((JSONObject) obj));
                }
            } else {
                notUseMap.put(key, root.get(key));
            }
        }

//        this.docCountErrorUpperBound = root.getLong(DOC_COUNT_ERROR_UPPER_BOUND_STR);
//        this.sumOtherDocCount = root.getLong(SUM_OTHER_DOC_COUNT_STR);

    }

    @Override
    public JSONObject toJson() {
        JSONObject root = new JSONObject();

        for (String key : notUseMap.keySet()) {
            root.put(key, notUseMap.get(key));
        }
//        root.put(DOC_COUNT_ERROR_UPPER_BOUND_STR, docCountErrorUpperBound);
//        root.put(SUM_OTHER_DOC_COUNT_STR, sumOtherDocCount);

        JSONArray array = new JSONArray();
        for (Bucket bucket : buckets) {
            array.add(bucket.toJson());
        }
        root.put(BUCKETS_STR, array);
        return root;
    }

    @Override
    public LoopResult loop(List<String> keys, AggrLooper looper) throws Exception {
        for (Bucket bucket : buckets) {
            if (bucket.loop(keys, looper) == LoopResult.RETURN) {
                return LoopResult.RETURN;
            }
        }

        return LoopResult.NORMAL;
    }
}

