package com.didiglobal.logi.elasticsearch.client.response.indices.catindices;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;

public class ESIndicesCatIndicesResponse extends ESActionResponse {

    private List<CatIndexResult> catIndexResults;

    public List<CatIndexResult> getCatIndexResults() {
        return catIndexResults;
    }

    public void setCatIndexResults(List<CatIndexResult> catIndexResults) {
        this.catIndexResults = catIndexResults;
    }

    @Override
    public String toString() {
        return toJson().toJSONString();
    }

    public JSONObject toJson() {
        return (JSONObject) JSONObject.toJSON(this);
    }

    public static ESIndicesCatIndicesResponse getResponse(String str) throws Exception {
        ESIndicesCatIndicesResponse response = new ESIndicesCatIndicesResponse();
        response.setCatIndexResults(JSON.parseArray(str, CatIndexResult.class));
        return response;
    }
}
