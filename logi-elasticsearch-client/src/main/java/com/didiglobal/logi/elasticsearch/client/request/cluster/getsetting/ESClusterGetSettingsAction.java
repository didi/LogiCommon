/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.didiglobal.logi.elasticsearch.client.request.cluster.getsetting;

import com.didiglobal.logi.elasticsearch.client.response.cluster.getsetting.ESClusterGetSettingsResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

public class ESClusterGetSettingsAction extends Action<ESClusterGetSettingsRequest, ESClusterGetSettingsResponse, ESClusterGetSettingsRequestBuilder> {

    public static final ESClusterGetSettingsAction INSTANCE = new ESClusterGetSettingsAction();
    public static final String NAME = "cluster:settings/get";

    private ESClusterGetSettingsAction() {
        super(NAME);
    }

    @Override
    public ESClusterGetSettingsResponse newResponse() {
        return new ESClusterGetSettingsResponse();
    }

    @Override
    public ESClusterGetSettingsRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new ESClusterGetSettingsRequestBuilder(client, this);
    }
}
