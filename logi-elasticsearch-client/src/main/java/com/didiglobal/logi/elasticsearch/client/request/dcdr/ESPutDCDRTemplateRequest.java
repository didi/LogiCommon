package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESPutDCDRTemplateResponse;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

import java.util.Map;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutDCDRTemplateRequest extends ESActionRequest<ESPutDCDRTemplateRequest> {

    public static final String TEMPLATE = "template";
    public static final String REPLICA_CLUSTER = "replica_cluster";


    private String name;
    private Map<String, String> param = Maps.newHashMap();


    public void setName(String name) {
        this.name = name;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public void putParam(String key, String value) {
        param.put(key, value);
    }

    @Override
    public RestRequest toRequest() throws Exception {
        if (StringUtils.isEmpty(name)) {
            throw new Exception("name is null");
        }

        String endPoint = "/_dcdr/auto_replication/" + name;
        RestRequest rr = new RestRequest("PUT", endPoint, null);
        rr.setBody(JSON.toJSONString(param));
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESPutDCDRTemplateResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
