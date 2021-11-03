package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author weizijun
 * date：2020-01-16
 */
public class DCDRIndexStats {
    private Map<String, List<DCDRStats>> dcdrStats = new HashMap<>();

    public DCDRIndexStats(JSONObject stats) {
        for (String key : stats.keySet()) {
            JSONArray array = stats.getJSONArray(key);
            List<DCDRStats> dcdrStatsList = new ArrayList<>(array.size());
            array.forEach(v -> {
                DCDRStats dcdrStat = JSON.toJavaObject((JSON) v, DCDRStats.class);
                dcdrStatsList.add(dcdrStat);
            });
            dcdrStats.put(key, dcdrStatsList);
        }
    }

    public Map<String, List<DCDRStats>> getDcdrStats() {
        return dcdrStats;
    }

    public void setDcdrStats(Map<String, List<DCDRStats>> dcdrStats) {
        this.dcdrStats = dcdrStats;
    }
}
