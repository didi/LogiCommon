package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESDeleteDCDRTemplateResponse;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeleteDCDRTemplateRequest extends ESActionRequest<ESDeleteDCDRTemplateRequest> {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        if (StringUtils.isEmpty(name)) {
            throw new Exception("name is null");
        }

        String endPoint = "/_dcdr/auto_replication/" + name;
        RestRequest rr = new RestRequest("DELETE", endPoint, null);
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESDeleteDCDRTemplateResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
