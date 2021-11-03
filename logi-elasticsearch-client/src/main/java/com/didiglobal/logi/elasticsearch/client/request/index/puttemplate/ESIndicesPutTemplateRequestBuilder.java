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

import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

import com.didiglobal.logi.elasticsearch.client.model.type.ESVersion;
import com.didiglobal.logi.elasticsearch.client.response.indices.puttemplate.ESIndicesPutTemplateResponse;
import com.didiglobal.logi.elasticsearch.client.response.setting.template.TemplateConfig;

public class ESIndicesPutTemplateRequestBuilder extends ActionRequestBuilder<ESIndicesPutTemplateRequest, ESIndicesPutTemplateResponse, ESIndicesPutTemplateRequestBuilder> {

    public ESIndicesPutTemplateRequestBuilder(ElasticsearchClient client, ESIndicesPutTemplateAction action) {
        super(client, action, new ESIndicesPutTemplateRequest());
    }

    public ESIndicesPutTemplateRequestBuilder setTemplate(String template) {
        request.setTemplate(template);
        return this;
    }

    public ESIndicesPutTemplateRequestBuilder setTemplateConfig(String templateConfig) {
        request.setTemplateConfig(templateConfig);
        return this;
    }

    public ESIndicesPutTemplateRequestBuilder setTemplateConfig(TemplateConfig templateConfig) {
        request.setTemplateConfig(templateConfig);
        return this;
    }

    public ESIndicesPutTemplateRequestBuilder setTemplateConfig(TemplateConfig templateConfig, ESVersion version) {
        request.setTemplateConfig(templateConfig, version);
        return this;
    }

    public ESIndicesPutTemplateRequestBuilder setIncludeTypeName(boolean include_type_name) {
        request.setIncludeTypeName(include_type_name);
        return this;
    }

}
