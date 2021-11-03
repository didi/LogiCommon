package com.didiglobal.logi.elasticsearch.client.request.security;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.security.ESDeleteSecurityRoleResponse;
import com.didiglobal.logi.elasticsearch.client.response.security.ESDeleteSecurityUserResponse;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeleteSecurityUserRequest extends ESActionRequest<ESDeleteSecurityUserRequest> {

    private String userName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        if (StringUtils.isEmpty(userName)) {
            throw new Exception("userName is null");
        }

        String endPoint = "/_security/user/" + userName;
        RestRequest rr = new RestRequest("DELETE", endPoint, null);
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESDeleteSecurityUserResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
