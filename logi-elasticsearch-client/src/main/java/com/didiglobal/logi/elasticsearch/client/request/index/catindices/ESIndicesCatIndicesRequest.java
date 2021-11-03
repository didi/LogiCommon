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

package com.didiglobal.logi.elasticsearch.client.request.index.catindices;

import com.didiglobal.logi.elasticsearch.client.response.indices.catindices.ESIndicesCatIndicesResponse;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;

public class ESIndicesCatIndicesRequest extends ESActionRequest<ESIndicesCatIndicesRequest> {

    private String[] indices;

    public ESIndicesCatIndicesRequest setIndices(String... indices) {
        this.indices = indices;
        return this;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        String indicesStr = null;
        if (indices != null) {
            indicesStr = StringUtils.join(indices, ",");
        }

        String endPoint = "_cat/indices";
        if (StringUtils.isNotBlank(indicesStr)) {
            endPoint = endPoint + "/" + indicesStr;
        }

        return new RestRequest("GET", endPoint, null);
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        return ESIndicesCatIndicesResponse.getResponse(response.getResponseContent());
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
