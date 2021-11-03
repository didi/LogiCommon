package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESGetDCDRStatsResponse;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

/**
 * author weizijun
 * date：2020-01-16
 */
public class ESGetDCDRStatsRequest extends ESActionRequest<ESGetDCDRStatsRequest> {
    private String primaryIndex;

    public void setPrimaryIndex(String primaryIndex) {
        this.primaryIndex = primaryIndex;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        String endPoint = null;
        if (StringUtils.isEmpty(primaryIndex)) {
            endPoint = "/_dcdr/stats";
        } else {
            endPoint = "/_dcdr/" + primaryIndex + "/stats";
        }

        RestRequest rr = new RestRequest("GET", endPoint, null);
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return ESGetDCDRStatsResponse.parserResponse(respStr);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }

}
