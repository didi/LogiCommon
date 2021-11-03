package com.didiglobal.logi.elasticsearch.client.request.security;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.security.ESGetSecurityRoleResponse;
import com.google.common.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

import java.util.Map;

/**
 * author weizijun
 * date：2019-07-11
 *
 * @author didi
 */
public class ESGetSecurityRoleRequest extends ESActionRequest<ESGetSecurityRoleRequest> {

    private String roleName;

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        String endPoint = null;
        if (StringUtils.isEmpty(roleName)) {
            endPoint = "/_security/role/";
        } else {
            endPoint = "/_security/role/" + roleName;
        }

        RestRequest rr = new RestRequest("GET", endPoint, null);
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        Map<String, SecurityRole> roleMap = JSON.parseObject(respStr, new TypeToken<Map<String, SecurityRole>>() {
        }.getType());
        ESGetSecurityRoleResponse resp = new ESGetSecurityRoleResponse();
        resp.setRoleMap(roleMap);
        return resp;
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
