package com.didiglobal.logi.elasticsearch.client.request.security;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.security.ESGetSecurityRoleResponse;
import com.didiglobal.logi.elasticsearch.client.response.security.ESGetSecurityUserResponse;
import com.google.common.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

import java.util.Map;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetSecurityUserRequest extends ESActionRequest<ESGetSecurityUserRequest> {

    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        String endPoint = null;
        if (StringUtils.isEmpty(userName)) {
            endPoint = "/_security/user/";
        } else {
            endPoint = "/_security/user/" + userName;
        }

        RestRequest rr = new RestRequest("GET", endPoint, null);
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        Map<String, SecurityUser> userMap = JSON.parseObject(respStr, new TypeToken<Map<String, SecurityUser>>() {
        }.getType());
        ESGetSecurityUserResponse resp = new ESGetSecurityUserResponse();
        resp.setUserMap(userMap);
        return resp;
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
