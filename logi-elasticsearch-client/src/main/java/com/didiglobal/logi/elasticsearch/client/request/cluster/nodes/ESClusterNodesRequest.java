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

package com.didiglobal.logi.elasticsearch.client.request.cluster.nodes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.nodes.ESClusterNodesResponse;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.common.Strings;

import java.util.HashSet;
import java.util.Set;

public class ESClusterNodesRequest extends ESActionRequest<ESClusterNodesRequest> {
    private String nodeIds;
    private Set<String> flags = new HashSet<>();

    public ESClusterNodesRequest clear() {
        flags.clear();
        return this;
    }


    public ESClusterNodesRequest flag(String name) {
        flags.add(name);
        return this;
    }

    public ESClusterNodesRequest nodeIds(String nodeIds) {
        this.nodeIds = nodeIds;
        return this;
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        String endpoint = buildEndPoint();
        return new RestRequest("GET", endpoint, null);
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        JSONObject obj = JSON.parseObject(respStr);
        obj.remove("_nodes");

        return JSON.parseObject(obj.toJSONString(), ESClusterNodesResponse.class);
    }

    private String buildEndPoint() {
        String endpoint = "_nodes";
        String flagStr = null;
        if (Strings.hasText(nodeIds)) {
            endpoint = endpoint + "/" + nodeIds;
        }
        if (flags.size() < 10) {
            flagStr = StringUtils.join(flags, ",");
        }
        if (flagStr != null && flagStr.trim().length() == 0) {
            flagStr = null;
        }

        if (flagStr == null) {
            return endpoint;
        } else {
            return endpoint + "/" + flagStr.trim();
        }
    }
}
