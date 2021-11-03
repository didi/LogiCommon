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

package com.didiglobal.logi.elasticsearch.client.request.index.puttemplate;

import org.elasticsearch.action.ActionRequestValidationException;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.model.type.ESVersion;
import com.didiglobal.logi.elasticsearch.client.response.indices.puttemplate.ESIndicesPutTemplateResponse;
import com.didiglobal.logi.elasticsearch.client.response.setting.template.TemplateConfig;

public class ESIndicesPutTemplateRequest extends ESActionRequest<ESIndicesPutTemplateRequest> {
    private String template;
    private String templateConfig;
    private boolean include_type_name = false;

    public ESIndicesPutTemplateRequest setTemplate(String template) {
        this.template = template;
        return this;
    }


    public ESIndicesPutTemplateRequest setTemplateConfig(String templateConfig) {
        this.templateConfig = templateConfig;
        return this;
    }


    public ESIndicesPutTemplateRequest setTemplateConfig(TemplateConfig templateConfig) {
        this.templateConfig = templateConfig.toJson().toJSONString();
        return this;
    }

    public ESIndicesPutTemplateRequest setTemplateConfig(TemplateConfig templateConfig, ESVersion toVersion) {
        this.templateConfig = templateConfig.toJson(toVersion).toJSONString();
        return this;
    }

    public ESIndicesPutTemplateRequest setIncludeTypeName(boolean include_type_name) {
        this.include_type_name = include_type_name;
        return this;
    }


    @Override
    public RestRequest toRequest() throws Exception {
        if (template == null || template.length() == 0) {
            throw new Exception("template is null");
        }

        if (templateConfig == null || templateConfig.length() == 0) {
            throw new Exception("template config is null");
        }

        String endPoint = "/_template/" + template;

        RestRequest rr = new RestRequest("PUT", endPoint, null);
        rr.setBody(templateConfig);

        if (include_type_name) {
            rr.addParam("include_type_name", "true");
        }

        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESIndicesPutTemplateResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
