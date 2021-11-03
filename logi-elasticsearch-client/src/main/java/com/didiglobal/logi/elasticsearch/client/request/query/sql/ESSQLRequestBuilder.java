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

package com.didiglobal.logi.elasticsearch.client.request.query.sql;

import com.didiglobal.logi.elasticsearch.client.request.query.query.ESQueryRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.response.query.query.ESQueryResponse;
import org.elasticsearch.ExceptionsHelper;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentHelper;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.innerhits.InnerHitsBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.rescore.RescoreBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SuggestBuilder;

import java.util.Map;

/**
 * A search request request builder.
 */
public class ESSQLRequestBuilder extends ActionRequestBuilder<ESSQLRequest, ESQueryResponse, ESSQLRequestBuilder> {

    private String sql;

    public ESSQLRequestBuilder(ElasticsearchClient client, ESSQLAction action) {
        super(client, action, new ESSQLRequest());
    }

    public ESSQLRequestBuilder setClazz(Class clazz) {
        request.clazz(clazz);
        return this;
    }

    @Override
    public String toString() {
        return sql;
    }

    public ESSQLRequestBuilder SQL(String sql) {
        request.setSql(sql);
        return this;
    }

    public ESSQLRequestBuilder setSQL(String sql) {
        request.setSql(sql);
        return this;
    }
}
