package com.didiglobal.logi.elasticsearch.client.request.security;

import com.didiglobal.logi.elasticsearch.client.response.security.ESPutSecurityRoleResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutSecurityRoleRequestBuilder extends ActionRequestBuilder<ESPutSecurityRoleRequest, ESPutSecurityRoleResponse, ESPutSecurityRoleRequestBuilder> {
    public ESPutSecurityRoleRequestBuilder(ElasticsearchClient client, ESPutSecurityRoleAction action) {
        super(client, action, new ESPutSecurityRoleRequest());
    }


    public ESPutSecurityRoleRequestBuilder setName(String name) {
        request.setName(name);
        return this;
    }

    public ESPutSecurityRoleRequestBuilder setRole(SecurityRole securityRole) {
        request.setSecurityRole(securityRole);
        return this;
    }
}