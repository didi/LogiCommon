package com.didiglobal.logi.elasticsearch.client.request.security;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.security.ESPutSecurityRoleResponse;
import com.didiglobal.logi.elasticsearch.client.response.security.ESPutSecurityUserResponse;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutSecurityUserRequest extends ESActionRequest<ESPutSecurityUserRequest> {

    private String name;

    private SecurityUser securityUser;


    public SecurityUser getSecurityUser() {
        return securityUser;
    }

    public void setSecurityUser(SecurityUser securityUser) {
        this.securityUser = securityUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        if (securityUser == null) {
            throw new Exception("securityUser is null");
        }

        if (StringUtils.isBlank(name)) {
            throw new Exception("securityUser name is blank");
        }

        if (securityUser.getPassword() == null || securityUser.getRoles() == null) {
            throw new Exception("securityUser param is null");
        }

        String endPoint = "/_security/user/" + name;
        RestRequest rr = new RestRequest("PUT", endPoint, null);
        rr.setBody(JSON.toJSONString(securityUser));
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESPutSecurityUserResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
