package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESGetDCDRIndexResponse;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetDCDRIndexRequest extends ESActionRequest<ESGetDCDRIndexRequest> {

    private String primaryIndex;

    public void setPrimaryIndex(String primaryIndex) {
        this.primaryIndex = primaryIndex;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        String endPoint = null;
        if (StringUtils.isEmpty(primaryIndex)) {
            endPoint = "/_dcdr/replication/";
        } else {
            endPoint = "/_dcdr/" + primaryIndex + "/replication/";
        }

        RestRequest rr = new RestRequest("GET", endPoint, null);
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESGetDCDRIndexResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
