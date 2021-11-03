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

package com.didiglobal.logi.elasticsearch.client.request.index.updatesettings;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.updatesettings.ESIndicesUpdateSettingsResponse;
import com.didiglobal.logi.elasticsearch.client.utils.JsonUtils;
import org.elasticsearch.action.ActionRequestValidationException;

import java.util.HashMap;
import java.util.Map;

public class ESIndicesUpdateSettingsRequest extends ESActionRequest<ESIndicesUpdateSettingsRequest> {
    private String index;
    private Map<String, String> settings = new HashMap<>();
    private String masterTimeout = null;


    public ESIndicesUpdateSettingsRequest setIndex(String index) {
        this.index = index;
        return this;
    }


    public ESIndicesUpdateSettingsRequest addSettings(String key, String value) {
        settings.put(key, value);
        return this;
    }

    public void setMasterTimeout(String masterTimeout) {
        this.masterTimeout = masterTimeout;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        if (index == null || index.length() == 0) {
            throw new Exception("index is null");
        }

        String endPoint = "/" + index.trim() + "/_settings";
        if (masterTimeout != null) {
            endPoint = endPoint + "?master_timeout=" + masterTimeout;
        }

        RestRequest rr = new RestRequest("PUT", endPoint, null);

        JSONObject obj = JsonUtils.reFlat(settings);
        rr.setBody(obj.toJSONString());
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESIndicesUpdateSettingsResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
