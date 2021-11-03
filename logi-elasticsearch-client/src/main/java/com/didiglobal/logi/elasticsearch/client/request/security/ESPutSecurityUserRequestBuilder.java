package com.didiglobal.logi.elasticsearch.client.request.security;

import com.didiglobal.logi.elasticsearch.client.response.security.ESPutSecurityUserResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutSecurityUserRequestBuilder extends ActionRequestBuilder<ESPutSecurityUserRequest, ESPutSecurityUserResponse, ESPutSecurityUserRequestBuilder> {
    public ESPutSecurityUserRequestBuilder(ElasticsearchClient client, ESPutSecurityUserAction action) {
        super(client, action, new ESPutSecurityUserRequest());
    }


    public ESPutSecurityUserRequestBuilder setName(String name) {
        request.setName(name);
        return this;
    }

    public ESPutSecurityUserRequestBuilder setUser(SecurityUser securityUser) {
        request.setSecurityUser(securityUser);
        return this;
    }
}