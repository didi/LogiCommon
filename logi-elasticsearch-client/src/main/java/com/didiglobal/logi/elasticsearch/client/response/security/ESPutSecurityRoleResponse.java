package com.didiglobal.logi.elasticsearch.client.response.security;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutSecurityRoleResponse extends ESActionResponse {

    /**
     * {
     * "role": {
     * "created": true    // 如果是更新是false
     * }
     * }
     */
    @JSONField(name = "role")
    private JSONObject role;

    public JSONObject getRole() {
        return role;
    }

    public void setRole(JSONObject role) {
        this.role = role;
    }
}
