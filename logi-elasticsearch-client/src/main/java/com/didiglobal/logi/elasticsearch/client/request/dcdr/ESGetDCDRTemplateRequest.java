package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESGetDCDRTemplateResponse;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetDCDRTemplateRequest extends ESActionRequest<ESGetDCDRTemplateRequest> {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        String endPoint = null;
        if (StringUtils.isEmpty(name)) {
            endPoint = "/_dcdr/auto_replication/";
        } else {
            endPoint = "/_dcdr/auto_replication/" + name;
        }

        RestRequest rr = new RestRequest("GET", endPoint, null);
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESGetDCDRTemplateResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
