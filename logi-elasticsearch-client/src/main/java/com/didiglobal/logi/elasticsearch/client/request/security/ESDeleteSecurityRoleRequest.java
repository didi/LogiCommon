package com.didiglobal.logi.elasticsearch.client.request.security;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESDeleteDCDRIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.security.ESDeleteSecurityRoleResponse;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

import java.util.Map;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeleteSecurityRoleRequest extends ESActionRequest<ESDeleteSecurityRoleRequest> {

    private String roleName;

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        if (StringUtils.isEmpty(roleName)) {
            throw new Exception("roleName is null");
        }

        String endPoint = "/_security/role/" + roleName;
        RestRequest rr = new RestRequest("DELETE", endPoint, null);
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESDeleteSecurityRoleResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
