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

package com.didiglobal.logi.elasticsearch.client.request.index.flush;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.flush.ESIndicesFlushIndexResponse;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

public class ESIndicesFlushIndexRequest extends ESActionRequest<ESIndicesFlushIndexRequest> {

    private String[] indices;
    private boolean updateCheckpoint = false;

    public ESIndicesFlushIndexRequest setIndices(String... indices) {
        this.indices = indices;
        return this;
    }

    public ESIndicesFlushIndexRequest setUpdateCheckpoint(boolean updateCheckpoint) {
        this.updateCheckpoint = updateCheckpoint;
        return this;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        String indicesStr = null;
        if (indices != null) {
            indicesStr = StringUtils.join(indices, ",");
        }

        if (StringUtils.isBlank(indicesStr)) {
            throw new Exception("not set indices");
        }

        String endPoint = "/" + indicesStr + "/_flush";
        if (updateCheckpoint) {
            endPoint = endPoint + "?update_checkpoint=true";
        }
        return new RestRequest("POST", endPoint, null);
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESIndicesFlushIndexResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
