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

package com.didiglobal.logi.elasticsearch.client.request.index.deletebyquery;

import com.didiglobal.logi.elasticsearch.client.response.indices.deletebyquery.ESIndicesDeleteByQueryResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

public class ESIndicesDeleteByQueryRequestBuilder extends ActionRequestBuilder<ESIndicesDeleteByQueryRequest, ESIndicesDeleteByQueryResponse, ESIndicesDeleteByQueryRequestBuilder> {

    public ESIndicesDeleteByQueryRequestBuilder(ElasticsearchClient client, ESIndicesDeleteByQueryAction action) {
        super(client, action, new ESIndicesDeleteByQueryRequest());
    }

    public ESIndicesDeleteByQueryRequestBuilder setIndex(String index) {
        request.setIndex(index);
        return this;
    }

    public ESIndicesDeleteByQueryRequestBuilder setType(String type) {
        request.setType(type);
        return this;
    }

    public ESIndicesDeleteByQueryRequestBuilder setQuery(String query) {
        request.setQuery(query);
        return this;
    }

    public ESIndicesDeleteByQueryRequestBuilder setHighES(boolean isHighES) {
        request.setHighES(isHighES);
        return this;
    }
}
