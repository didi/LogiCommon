package com.didiglobal.logi.elasticsearch.client.request.security;

import com.didiglobal.logi.elasticsearch.client.response.security.ESDeleteSecurityUserResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeleteSecurityUserRequestBuilder extends ActionRequestBuilder<ESDeleteSecurityUserRequest, ESDeleteSecurityUserResponse, ESDeleteSecurityUserRequestBuilder> {
    public ESDeleteSecurityUserRequestBuilder(ElasticsearchClient client, ESDeleteSecurityUserAction action) {
        super(client, action, new ESDeleteSecurityUserRequest());
    }

    public ESDeleteSecurityUserRequestBuilder setUserName(String roleName) {
        request.setUserName(roleName);
        return this;
    }
}