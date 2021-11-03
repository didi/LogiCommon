package com.didiglobal.logi.elasticsearch.client.request.security;

import com.didiglobal.logi.elasticsearch.client.response.security.ESGetSecurityUserResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetSecurityUserRequestBuilder extends ActionRequestBuilder<ESGetSecurityUserRequest, ESGetSecurityUserResponse, ESGetSecurityUserRequestBuilder> {

    public ESGetSecurityUserRequestBuilder(ElasticsearchClient client, ESGetSecurityUserAction action) {
        super(client, action, new ESGetSecurityUserRequest());
    }

    public ESGetSecurityUserRequestBuilder setUserName(String userName) {
        request.setUserName(userName);
        return this;
    }
}
