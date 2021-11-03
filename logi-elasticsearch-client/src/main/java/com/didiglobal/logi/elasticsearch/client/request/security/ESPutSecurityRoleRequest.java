package com.didiglobal.logi.elasticsearch.client.request.security;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.security.ESPutSecurityRoleResponse;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutSecurityRoleRequest extends ESActionRequest<ESPutSecurityRoleRequest> {

    private String name;

    private SecurityRole securityRole;


    public SecurityRole getSecurityRole() {
        return securityRole;
    }

    public void setSecurityRole(SecurityRole securityRole) {
        this.securityRole = securityRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        if (securityRole == null) {
            throw new Exception("securityRole is null");
        }

        if (StringUtils.isBlank(name)) {
            throw new Exception("securityRole name is blank");
        }

        if (securityRole.getCluster() == null && securityRole.getIndices() == null) {
            throw new Exception("securityRole is null");
        }

        String endPoint = "/_security/role/" + name;
        RestRequest rr = new RestRequest("PUT", endPoint, null);
        rr.setBody(JSON.toJSONString(securityRole));
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESPutSecurityRoleResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
