package com.didiglobal.logi.elasticsearch.client.request.security;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESPutDCDRTemplateResponse;
import com.didiglobal.logi.elasticsearch.client.response.security.ESPutSecurityRoleResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESPutSecurityRoleAction extends Action<ESPutSecurityRoleRequest, ESPutSecurityRoleResponse, ESPutSecurityRoleRequestBuilder> {
    public static final ESPutSecurityRoleAction INSTANCE = new ESPutSecurityRoleAction();
    public static final String NAME = "cluster:admin/security/role/put";

    private ESPutSecurityRoleAction() {
        super(NAME);
    }

    @Override
    public ESPutSecurityRoleResponse newResponse() {
        return new ESPutSecurityRoleResponse();
    }

    @Override
    public ESPutSecurityRoleRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESPutSecurityRoleRequestBuilder(client, this);
    }
}
