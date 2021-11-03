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

package com.didiglobal.logi.elasticsearch.client.request.index.updatemapping;

import com.didiglobal.logi.elasticsearch.client.response.indices.updatemapping.ESIndicesUpdateMappingResponse;
import com.didiglobal.logi.elasticsearch.client.response.setting.common.TypeConfig;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

public class ESIndicesUpdateMappingRequestBuilder extends ActionRequestBuilder<ESIndicesUpdateMappingRequest, ESIndicesUpdateMappingResponse, ESIndicesUpdateMappingRequestBuilder> {

    public ESIndicesUpdateMappingRequestBuilder(ElasticsearchClient client, ESIndicesUpdateMappingAction action) {
        super(client, action, new ESIndicesUpdateMappingRequest());
    }

    public ESIndicesUpdateMappingRequestBuilder setIndex(String index) {
        request.setIndex(index);
        return this;
    }

    public ESIndicesUpdateMappingRequestBuilder setType(String type) {
        request.setType(type);
        return this;
    }

    public ESIndicesUpdateMappingRequestBuilder setTypeConfig(TypeConfig type) {
        request.setTypeConfig(type);
        return this;
    }

    public ESIndicesUpdateMappingRequestBuilder setIncludeTypeName(boolean include_type_name) {
        request.setIncludeTypeName(include_type_name);
        return this;
    }
}
