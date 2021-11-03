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

package com.didiglobal.logi.elasticsearch.client.model.admin;

import com.didiglobal.logi.elasticsearch.client.request.dcdr.*;
import com.didiglobal.logi.elasticsearch.client.request.index.catindices.ESIndicesCatIndicesRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.clearcache.ESIndicesClearCacheAction;
import com.didiglobal.logi.elasticsearch.client.request.index.clearcache.ESIndicesClearCacheRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.clearcache.ESIndicesClearCacheRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.closeindex.ESIndicesCloseIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.closeindex.ESIndicesCloseIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.deleteIndex.ESIndicesDeleteIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.deleteIndex.ESIndicesDeleteIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.deletebyquery.ESIndicesDeleteByQueryRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.deletebyquery.ESIndicesDeleteByQueryRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.deletetemplate.ESIndicesDeleteTemplateRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.deletetemplate.ESIndicesDeleteTemplateRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.exists.ESIndicesExistsRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.exists.ESIndicesExistsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.flush.ESIndicesFlushIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.flush.ESIndicesFlushIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.frozen.ESIndicesFreezeIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.frozen.ESIndicesFreezeIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.getalias.ESIndicesGetAliasRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.getalias.ESIndicesGetAliasRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.getindex.ESIndicesGetIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.getindex.ESIndicesGetIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.gettemplate.ESIndicesGetTemplateRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.gettemplate.ESIndicesGetTemplateRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.openindex.ESIndicesOpenIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.openindex.ESIndicesOpenIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.putalias.ESIndicesPutAliasRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.putalias.ESIndicesPutAliasRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.putindex.ESIndicesPutIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.putindex.ESIndicesPutIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.puttemplate.ESIndicesPutTemplateRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.puttemplate.ESIndicesPutTemplateRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.refreshindex.ESIndicesRefreshIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.refreshindex.ESIndicesRefreshIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.searchshards.ESIndicesSearchShardsRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.searchshards.ESIndicesSearchShardsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.stats.ESIndicesStatsRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.stats.ESIndicesStatsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.updatemapping.ESIndicesUpdateMappingRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.updatemapping.ESIndicesUpdateMappingRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.updatesettings.ESIndicesUpdateSettingsRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.updatesettings.ESIndicesUpdateSettingsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.ingest.*;
import com.didiglobal.logi.elasticsearch.client.request.security.*;
import com.didiglobal.logi.elasticsearch.client.response.dcdr.*;
import com.didiglobal.logi.elasticsearch.client.response.indices.clearcache.ESIndicesClearCacheResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.closeindex.ESIndicesCloseIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.deletebyquery.ESIndicesDeleteByQueryResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.deleteindex.ESIndicesDeleteIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.deletetemplate.ESIndicesDeleteTemplateResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.exists.ESIndicesExistsResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.flush.ESIndicesFlushIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.frozen.ESIndicesFreezeIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.getalias.ESIndicesGetAliasResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.getindex.ESIndicesGetIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.gettemplate.ESIndicesGetTemplateResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.openindex.ESIndicesOpenIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.putalias.ESIndicesPutAliasResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.putindex.ESIndicesPutIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.puttemplate.ESIndicesPutTemplateResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.refreshindex.ESIndicesRefreshIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.searchshards.ESIndicesSearchShardsResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.stats.ESIndicesStatsResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.updatemapping.ESIndicesUpdateMappingResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.updatesettings.ESIndicesUpdateSettingsResponse;
import com.didiglobal.logi.elasticsearch.client.response.ingest.ESDeletePipelineResponse;
import com.didiglobal.logi.elasticsearch.client.response.ingest.ESGetPipelineResponse;
import com.didiglobal.logi.elasticsearch.client.response.ingest.ESPutPipelineResponse;
import com.didiglobal.logi.elasticsearch.client.response.security.*;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * Administrative actions/operations against indices.
 *
 * @see AdminClient#indices()
 */
public interface ESIndicesAdminClient extends ElasticsearchClient {
    ActionFuture<ESIndicesStatsResponse> stats(ESIndicesStatsRequest request);

    void stats(ESIndicesStatsRequest request, ActionListener<ESIndicesStatsResponse> listener);

    ESIndicesStatsRequestBuilder prepareStats(String... indices);

    ESIndicesStatsRequestBuilder prepareStats();


    ActionFuture<ESIndicesSearchShardsResponse> searchShards(ESIndicesSearchShardsRequest request);

    void searchShards(ESIndicesSearchShardsRequest request, ActionListener<ESIndicesSearchShardsResponse> listener);

    ESIndicesSearchShardsRequestBuilder prepareSearchShards(String... indices);

    ESIndicesSearchShardsRequestBuilder prepareSearchShards();


    ActionFuture<ESIndicesGetAliasResponse> alias(ESIndicesGetAliasRequest request);

    void alias(ESIndicesGetAliasRequest request, ActionListener<ESIndicesGetAliasResponse> listener);

    ESIndicesGetAliasRequestBuilder prepareAlias(String... indices);

    ESIndicesGetAliasRequestBuilder prepareAlias();


    ActionFuture<ESIndicesPutAliasResponse> putAlias(ESIndicesPutAliasRequest request);

    void putAlias(ESIndicesPutAliasRequest request, ActionListener<ESIndicesPutAliasResponse> listener);

    ESIndicesPutAliasRequestBuilder preparePutAlias();


    ActionFuture<ESIndicesGetIndexResponse> getIndex(final ESIndicesGetIndexRequest request);

    void getIndex(ESIndicesGetIndexRequest request, ActionListener<ESIndicesGetIndexResponse> listener);

    ESIndicesGetIndexRequestBuilder prepareGetIndex(String... indices);

    ESIndicesGetIndexRequestBuilder prepareGetIndex();

    ESIndicesCatIndicesRequestBuilder prepareCatIndices();

    ESIndicesCatIndicesRequestBuilder prepareCatIndices(String... indices);


    ActionFuture<ESIndicesPutIndexResponse> putIndex(final ESIndicesPutIndexRequest request);

    void putIndex(ESIndicesPutIndexRequest request, ActionListener<ESIndicesPutIndexResponse> listener);

    ESIndicesPutIndexRequestBuilder preparePutIndex(String index);


    ActionFuture<ESIndicesDeleteIndexResponse> deleteIndex(final ESIndicesDeleteIndexRequest request);

    void deleteIndex(ESIndicesDeleteIndexRequest request, ActionListener<ESIndicesDeleteIndexResponse> listener);

    ESIndicesDeleteIndexRequestBuilder prepareDeleteIndex(String index);


    ActionFuture<ESIndicesRefreshIndexResponse> refreshIndex(final ESIndicesRefreshIndexRequest request);

    void refreshIndex(ESIndicesRefreshIndexRequest request, ActionListener<ESIndicesRefreshIndexResponse> listener);

    ESIndicesRefreshIndexRequestBuilder prepareRefreshIndex(String index);


    ActionFuture<ESIndicesGetTemplateResponse> getTemplate(final ESIndicesGetTemplateRequest request);

    void getTemplate(ESIndicesGetTemplateRequest request, ActionListener<ESIndicesGetTemplateResponse> listener);

    ESIndicesGetTemplateRequestBuilder prepareGetTemplate(String... templates);

    ESIndicesGetTemplateRequestBuilder prepareGetTemplate();


    public ActionFuture<ESIndicesPutTemplateResponse> putTemplate(final ESIndicesPutTemplateRequest request);

    void putTemplate(ESIndicesPutTemplateRequest request, ActionListener<ESIndicesPutTemplateResponse> listener);

    ESIndicesPutTemplateRequestBuilder preparePutTemplate(String template);


    ActionFuture<ESIndicesDeleteTemplateResponse> deleteTemplate(final ESIndicesDeleteTemplateRequest request);

    void deleteTemplate(ESIndicesDeleteTemplateRequest request, ActionListener<ESIndicesDeleteTemplateResponse> listener);

    ESIndicesDeleteTemplateRequestBuilder prepareDeleteTemplate(String template);


    ActionFuture<ESIndicesUpdateSettingsResponse> updateSettings(final ESIndicesUpdateSettingsRequest request);

    void updateSettings(ESIndicesUpdateSettingsRequest request, ActionListener<ESIndicesUpdateSettingsResponse> listener);

    ESIndicesUpdateSettingsRequestBuilder prepareUpdateSettings(String index);


    ActionFuture<ESIndicesUpdateMappingResponse> updateMapping(final ESIndicesUpdateMappingRequest request);

    void updateMapping(ESIndicesUpdateMappingRequest request, ActionListener<ESIndicesUpdateMappingResponse> listener);

    ESIndicesUpdateMappingRequestBuilder prepareUpdateMapping();


    ActionFuture<ESIndicesExistsResponse> exists(final ESIndicesExistsRequest request);

    void exists(ESIndicesExistsRequest request, ActionListener<ESIndicesExistsResponse> listener);

    ESIndicesExistsRequestBuilder prepareExists(String index);


    ActionFuture<ESIndicesOpenIndexResponse> openIndex(final ESIndicesOpenIndexRequest request);

    void openIndex(ESIndicesOpenIndexRequest request, ActionListener<ESIndicesOpenIndexResponse> listener);

    ESIndicesOpenIndexRequestBuilder prepareOpenIndex(String index);


    ActionFuture<ESIndicesCloseIndexResponse> closeIndex(final ESIndicesCloseIndexRequest request);

    void closeIndex(ESIndicesCloseIndexRequest request, ActionListener<ESIndicesCloseIndexResponse> listener);

    ESIndicesCloseIndexRequestBuilder prepareCloseIndex(String index);


    ActionFuture<ESIndicesDeleteByQueryResponse> deleteByQuery(final ESIndicesDeleteByQueryRequest request);

    void deleteByQuery(ESIndicesDeleteByQueryRequest request, ActionListener<ESIndicesDeleteByQueryResponse> listener);

    ESIndicesDeleteByQueryRequestBuilder prepareDeleteByQuery();


    ActionFuture<ESDeletePipelineResponse> deletePipeline(final ESDeletePipelineRequest request);

    void deletePipeline(ESDeletePipelineRequest request, ActionListener<ESDeletePipelineResponse> listener);

    ESDeletePipelineRequestBuilder prepareDeletePipeline();

    ActionFuture<ESPutPipelineResponse> putPipeline(final ESPutPipelineRequest request);

    void putPipeline(ESPutPipelineRequest request, ActionListener<ESPutPipelineResponse> listener);

    ESPutPipelineRequestBuilder preparePutPipeline();

    ActionFuture<ESGetPipelineResponse> getPipeline(final ESGetPipelineRequest request);

    void getPipeline(ESGetPipelineRequest request, ActionListener<ESGetPipelineResponse> listener);

    ESGetPipelineRequestBuilder prepareGetPipeline();

    ESPutDCDRTemplateRequestBuilder preparePutDCDRTemplate();

    ActionFuture<ESDeleteDCDRTemplateResponse> deleteDCDRTemplate(final ESDeleteDCDRTemplateRequest request);

    ActionFuture<ESGetDCDRIndexResponse> getDCDRIndex(final ESGetDCDRIndexRequest request);

    ActionFuture<ESGetDCDRTemplateResponse> getDCDRTemplate(final ESGetDCDRTemplateRequest request);

    ESDeleteDCDRIndexRequestBuilder prepareDeleteDCDRIndex();

    ESGetDCDRStatsRequestBuilder prepareGetDCDRStats();

    ActionFuture<ESGetDCDRStatsResponse> getDCDRStats(final ESGetDCDRStatsRequest request);

    void getDCDRStats(final ESGetDCDRStatsRequest request, ActionListener<ESGetDCDRStatsResponse> listener);

    ActionFuture<ESIndicesFreezeIndexResponse> freezeIndex(final ESIndicesFreezeIndexRequest request);

    void freezeIndex(ESIndicesFreezeIndexRequest request, ActionListener<ESIndicesFreezeIndexResponse> listener);

    ESIndicesFreezeIndexRequestBuilder prepareFreezeIndex(boolean freeze, String... indices);

    ESGetSecurityRoleRequestBuilder prepareGetSecurityRole();

    ESGetSecurityUserRequestBuilder prepareGetSecurityUser();

    ActionFuture<ESGetSecurityRoleResponse> getSecurityRole(final ESGetSecurityRoleRequest request);

    ActionFuture<ESGetSecurityUserResponse> getSecurityRole(final ESGetSecurityUserRequest request);

    ESPutSecurityRoleRequestBuilder preparePutSecurityRole();

    ESPutSecurityUserRequestBuilder preparePutSecurityUser();

    ActionFuture<ESPutSecurityRoleResponse> putSecurityRole(final ESPutSecurityRoleRequest request);

    ActionFuture<ESPutSecurityUserResponse> putSecurityUser(final ESPutSecurityUserRequest request);

    ESDeleteSecurityRoleRequestBuilder prepareDeleteSecurityRole();

    ESDeleteSecurityUserRequestBuilder prepareDeleteSecurityUser();

    ActionFuture<ESDeleteSecurityRoleResponse> deleteSecurityRole(final ESDeleteSecurityRoleRequest request);

    ActionFuture<ESDeleteSecurityUserResponse> deleteSecurityUser(final ESDeleteSecurityUserRequest request);

    public ActionFuture<ESIndicesClearCacheResponse> clearCache(final ESIndicesClearCacheRequest request);

    public void clearCache(ESIndicesClearCacheRequest request, ActionListener<ESIndicesClearCacheResponse> listener);

    public ESIndicesClearCacheRequestBuilder prepareClearCache(String index);


    public ActionFuture<ESIndicesFlushIndexResponse> flush(final ESIndicesFlushIndexRequest request);

    public void flush(ESIndicesFlushIndexRequest request, ActionListener<ESIndicesFlushIndexResponse> listener);

    public ESIndicesFlushIndexRequestBuilder prepareFlush(String index);


    public ActionFuture<ESPutDCDRIndexResponse> putDCDRIndex(final ESPutDCDRIndexRequest request);

    public void putDCDRIndex(ESPutDCDRIndexRequest request, ActionListener<ESPutDCDRIndexResponse> listener);

    public ESPutDCDRIndexRequestBuilder preparePutDCDRIndex(String index);

}
