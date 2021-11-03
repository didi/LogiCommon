package com.didiglobal.logi.elasticsearch.client.request.security;

import com.didiglobal.logi.elasticsearch.client.response.security.ESPutSecurityUserResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutSecurityUserAction extends Action<ESPutSecurityUserRequest, ESPutSecurityUserResponse, ESPutSecurityUserRequestBuilder> {
    public static final ESPutSecurityUserAction INSTANCE = new ESPutSecurityUserAction();
    public static final String NAME = "cluster:admin/security/user/put";

    private ESPutSecurityUserAction() {
        super(NAME);
    }

    @Override
    public ESPutSecurityUserResponse newResponse() {
        return new ESPutSecurityUserResponse();
    }

    @Override
    public ESPutSecurityUserRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESPutSecurityUserRequestBuilder(client, this);
    }
}
