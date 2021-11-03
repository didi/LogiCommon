package com.didiglobal.logi.elasticsearch.client.request.security;

import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESDeleteDCDRIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.security.ESDeleteSecurityRoleResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeleteSecurityRoleAction extends Action<ESDeleteSecurityRoleRequest, ESDeleteSecurityRoleResponse, ESDeleteSecurityRoleRequestBuilder> {
    public static final ESDeleteSecurityRoleAction INSTANCE = new ESDeleteSecurityRoleAction();
    public static final String NAME = "cluster:admin/security/role/delete";

    private ESDeleteSecurityRoleAction() {
        super(NAME);
    }

    @Override
    public ESDeleteSecurityRoleResponse newResponse() {
        return new ESDeleteSecurityRoleResponse();
    }

    @Override
    public ESDeleteSecurityRoleRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESDeleteSecurityRoleRequestBuilder(client, this);
    }
}
