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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.deletebyquery.ESIndicesDeleteByQueryResponse;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.common.bytes.BytesArray;

public class ESIndicesDeleteByQueryRequest extends ESActionRequest<ESIndicesDeleteByQueryRequest> {
    private String index;

    private String type;

    private String query;

    public boolean isHighES = false;

    public ESIndicesDeleteByQueryRequest setIndex(String index) {
        this.index = index;
        return this;
    }

    public ESIndicesDeleteByQueryRequest setType(String type) {
        this.type = type;
        return this;
    }

    public ESIndicesDeleteByQueryRequest setQuery(String query) {
        this.query = query;
        return this;
    }

    public ESIndicesDeleteByQueryRequest setHighES(boolean isHighES) {
        this.isHighES = isHighES;
        return this;
    }


    @Override
    public RestRequest toRequest() throws Exception {
        if (index == null || index.length() == 0) {
            throw new Exception("index is null");
        }

        if (query == null || query.length() == 0) {
            throw new Exception("query is null");
        }

        String method;
        String endPoint;
        if (isHighES) {
            method = "POST";
            if (StringUtils.isBlank(type)) {
                endPoint = String.format("/%s/_delete_by_query", index);
            } else {
                endPoint = String.format("/%s/%s/_delete_by_query", index, type);
            }

        } else {
            if (type == null || type.length() == 0) {
                throw new Exception("type is null");
            }

            method = "DELETE";
            endPoint = String.format("/%s/%s/_query", index, type);
        }


        RestRequest rr = new RestRequest(method, endPoint, new BytesArray(query));
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        JSONObject obj = JSON.parseObject(respStr);

        ESIndicesDeleteByQueryResponse resp = new ESIndicesDeleteByQueryResponse();
        resp.setRoot(obj);
        return resp;
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
