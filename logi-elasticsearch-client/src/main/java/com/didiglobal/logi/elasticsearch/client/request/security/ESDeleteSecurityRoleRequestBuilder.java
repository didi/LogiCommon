package com.didiglobal.logi.elasticsearch.client.request.security;

import com.didiglobal.logi.elasticsearch.client.response.security.ESDeleteSecurityRoleResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeleteSecurityRoleRequestBuilder extends ActionRequestBuilder<ESDeleteSecurityRoleRequest, ESDeleteSecurityRoleResponse, ESDeleteSecurityRoleRequestBuilder> {
    public ESDeleteSecurityRoleRequestBuilder(ElasticsearchClient client, ESDeleteSecurityRoleAction action) {
        super(client, action, new ESDeleteSecurityRoleRequest());
    }

    public ESDeleteSecurityRoleRequestBuilder setRoleName(String roleName) {
        request.setRoleName(roleName);
        return this;
    }
}