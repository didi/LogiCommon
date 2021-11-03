package com.didiglobal.logi.elasticsearch.client.request.security;

import com.didiglobal.logi.elasticsearch.client.response.security.ESDeleteSecurityUserResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeleteSecurityUserAction extends Action<ESDeleteSecurityUserRequest, ESDeleteSecurityUserResponse, ESDeleteSecurityUserRequestBuilder> {
    public static final ESDeleteSecurityUserAction INSTANCE = new ESDeleteSecurityUserAction();
    public static final String NAME = "cluster:admin/security/user/delete";

    private ESDeleteSecurityUserAction() {
        super(NAME);
    }

    @Override
    public ESDeleteSecurityUserResponse newResponse() {
        return new ESDeleteSecurityUserResponse();
    }

    @Override
    public ESDeleteSecurityUserRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESDeleteSecurityUserRequestBuilder(client, this);
    }
}
