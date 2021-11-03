package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESPutDCDRIndexResponse;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

import java.util.Map;

public class ESPutDCDRIndexRequest extends ESActionRequest<ESPutDCDRIndexRequest> {

    public static final String REPLICA_CLUSTER = "replica_cluster";
    public static final String REPLICA_INDEX = "replica_index";

    private String indexName;
    private Map<String, String> param = Maps.newHashMap();

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public void putParam(String key, String value) {
        param.put(key, value);
    }

    @Override
    public RestRequest toRequest() throws Exception {
        if (StringUtils.isEmpty(indexName)) {
            throw new Exception("index name is null");
        }

        String endPoint = "/_dcdr/" + indexName + "/replication/create";
        RestRequest rr = new RestRequest("PUT", endPoint, null);
        rr.setBody(JSON.toJSONString(param));
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESPutDCDRIndexResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
