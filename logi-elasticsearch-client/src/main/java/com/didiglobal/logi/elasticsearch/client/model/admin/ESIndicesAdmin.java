package com.didiglobal.logi.elasticsearch.client.model.admin;

import com.didiglobal.logi.elasticsearch.client.request.dcdr.*;
import com.didiglobal.logi.elasticsearch.client.request.index.catindices.ESIndicesCatIndicesAction;
import com.didiglobal.logi.elasticsearch.client.request.index.catindices.ESIndicesCatIndicesRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.clearcache.ESIndicesClearCacheAction;
import com.didiglobal.logi.elasticsearch.client.request.index.clearcache.ESIndicesClearCacheRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.clearcache.ESIndicesClearCacheRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.closeindex.ESIndicesCloseIndexAction;
import com.didiglobal.logi.elasticsearch.client.request.index.closeindex.ESIndicesCloseIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.closeindex.ESIndicesCloseIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.deleteIndex.ESIndicesDeleteIndexAction;
import com.didiglobal.logi.elasticsearch.client.request.index.deleteIndex.ESIndicesDeleteIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.deleteIndex.ESIndicesDeleteIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.deletebyquery.ESIndicesDeleteByQueryAction;
import com.didiglobal.logi.elasticsearch.client.request.index.deletebyquery.ESIndicesDeleteByQueryRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.deletebyquery.ESIndicesDeleteByQueryRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.deletetemplate.ESIndicesDeleteTemplateAction;
import com.didiglobal.logi.elasticsearch.client.request.index.deletetemplate.ESIndicesDeleteTemplateRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.deletetemplate.ESIndicesDeleteTemplateRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.exists.ESIndicesExistsAction;
import com.didiglobal.logi.elasticsearch.client.request.index.exists.ESIndicesExistsRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.exists.ESIndicesExistsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.flush.ESIndicesFlushIndexAction;
import com.didiglobal.logi.elasticsearch.client.request.index.flush.ESIndicesFlushIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.flush.ESIndicesFlushIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.frozen.ESIndicesFreezeIndexAction;
import com.didiglobal.logi.elasticsearch.client.request.index.frozen.ESIndicesFreezeIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.frozen.ESIndicesFreezeIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.getalias.ESIndicesGetAliasAction;
import com.didiglobal.logi.elasticsearch.client.request.index.getalias.ESIndicesGetAliasRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.getalias.ESIndicesGetAliasRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.getindex.ESIndicesGetIndexAction;
import com.didiglobal.logi.elasticsearch.client.request.index.getindex.ESIndicesGetIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.getindex.ESIndicesGetIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.gettemplate.ESIndicesGetTemplateAction;
import com.didiglobal.logi.elasticsearch.client.request.index.gettemplate.ESIndicesGetTemplateRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.gettemplate.ESIndicesGetTemplateRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.openindex.ESIndicesOpenIndexAction;
import com.didiglobal.logi.elasticsearch.client.request.index.openindex.ESIndicesOpenIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.openindex.ESIndicesOpenIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.putalias.ESIndicesPutAliasAction;
import com.didiglobal.logi.elasticsearch.client.request.index.putalias.ESIndicesPutAliasRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.putalias.ESIndicesPutAliasRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.putindex.ESIndicesPutIndexAction;
import com.didiglobal.logi.elasticsearch.client.request.index.putindex.ESIndicesPutIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.putindex.ESIndicesPutIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.puttemplate.ESIndicesPutTemplateAction;
import com.didiglobal.logi.elasticsearch.client.request.index.puttemplate.ESIndicesPutTemplateRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.puttemplate.ESIndicesPutTemplateRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.refreshindex.ESIndicesRefreshIndexAction;
import com.didiglobal.logi.elasticsearch.client.request.index.refreshindex.ESIndicesRefreshIndexRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.refreshindex.ESIndicesRefreshIndexRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.searchshards.ESIndicesSearchShardsAction;
import com.didiglobal.logi.elasticsearch.client.request.index.searchshards.ESIndicesSearchShardsRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.searchshards.ESIndicesSearchShardsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.stats.ESIndicesStatsAction;
import com.didiglobal.logi.elasticsearch.client.request.index.stats.ESIndicesStatsRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.stats.ESIndicesStatsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.updatemapping.ESIndicesUpdateMappingAction;
import com.didiglobal.logi.elasticsearch.client.request.index.updatemapping.ESIndicesUpdateMappingRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.updatemapping.ESIndicesUpdateMappingRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.index.updatesettings.ESIndicesUpdateSettingsAction;
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
import org.elasticsearch.action.*;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.threadpool.ThreadPool;


public class ESIndicesAdmin implements ESIndicesAdminClient {
    private final ElasticsearchClient client;

    public ESIndicesAdmin(ElasticsearchClient client) {
        this.client = client;
    }

    @Override
    public <Request extends ActionRequest, Response extends ActionResponse, RequestBuilder extends ActionRequestBuilder<Request, Response, RequestBuilder>> ActionFuture<Response> execute(Action<Request, Response, RequestBuilder> action, Request request) {
        return client.execute(action, request);
    }

    @Override
    public <Request extends ActionRequest, Response extends ActionResponse, RequestBuilder extends ActionRequestBuilder<Request, Response, RequestBuilder>> void execute(Action<Request, Response, RequestBuilder> action, Request request, ActionListener<Response> listener) {
        client.execute(action, request, listener);
    }

    @Override
    public <Request extends ActionRequest, Response extends ActionResponse, RequestBuilder extends ActionRequestBuilder<Request, Response, RequestBuilder>> RequestBuilder prepareExecute(Action<Request, Response, RequestBuilder> action) {
        return client.prepareExecute(action);
    }


    @Override
    public ThreadPool threadPool() {
        return client.threadPool();
    }

    @Override
    public ActionFuture<ESIndicesStatsResponse> stats(final ESIndicesStatsRequest request) {
        return execute(ESIndicesStatsAction.INSTANCE, request);
    }

    @Override
    public void stats(final ESIndicesStatsRequest request, final ActionListener<ESIndicesStatsResponse> listener) {
        execute(ESIndicesStatsAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesStatsRequestBuilder prepareStats(String... indices) {
        return new ESIndicesStatsRequestBuilder(this, ESIndicesStatsAction.INSTANCE).setIndices(indices);
    }

    @Override
    public ESIndicesStatsRequestBuilder prepareStats() {
        return new ESIndicesStatsRequestBuilder(this, ESIndicesStatsAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESIndicesSearchShardsResponse> searchShards(ESIndicesSearchShardsRequest request) {
        return execute(ESIndicesSearchShardsAction.INSTANCE, request);
    }

    @Override
    public void searchShards(ESIndicesSearchShardsRequest request, ActionListener<ESIndicesSearchShardsResponse> listener) {
        execute(ESIndicesSearchShardsAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesSearchShardsRequestBuilder prepareSearchShards(String... indices) {
        return new ESIndicesSearchShardsRequestBuilder(this, ESIndicesSearchShardsAction.INSTANCE).setIndices(indices);
    }

    @Override
    public ESIndicesSearchShardsRequestBuilder prepareSearchShards() {
        return new ESIndicesSearchShardsRequestBuilder(this, ESIndicesSearchShardsAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESIndicesGetAliasResponse> alias(ESIndicesGetAliasRequest request) {
        return execute(ESIndicesGetAliasAction.INSTANCE, request);
    }

    @Override
    public void alias(ESIndicesGetAliasRequest request, ActionListener<ESIndicesGetAliasResponse> listener) {
        execute(ESIndicesGetAliasAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesGetAliasRequestBuilder prepareAlias(String... indices) {
        return new ESIndicesGetAliasRequestBuilder(this, ESIndicesGetAliasAction.INSTANCE).setIndices(indices);
    }

    @Override
    public ESIndicesGetAliasRequestBuilder prepareAlias() {
        return new ESIndicesGetAliasRequestBuilder(this, ESIndicesGetAliasAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESIndicesPutAliasResponse> putAlias(ESIndicesPutAliasRequest request) {
        return execute(ESIndicesPutAliasAction.INSTANCE, request);
    }

    @Override
    public void putAlias(ESIndicesPutAliasRequest request, ActionListener<ESIndicesPutAliasResponse> listener) {
        execute(ESIndicesPutAliasAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesPutAliasRequestBuilder preparePutAlias() {
        return new ESIndicesPutAliasRequestBuilder(this, ESIndicesPutAliasAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESIndicesGetIndexResponse> getIndex(final ESIndicesGetIndexRequest request) {
        return execute(ESIndicesGetIndexAction.INSTANCE, request);
    }

    @Override
    public void getIndex(ESIndicesGetIndexRequest request, ActionListener<ESIndicesGetIndexResponse> listener) {
        execute(ESIndicesGetIndexAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesGetIndexRequestBuilder prepareGetIndex(String... indices) {
        return new ESIndicesGetIndexRequestBuilder(this, ESIndicesGetIndexAction.INSTANCE).setIndices(indices);
    }

    @Override
    public ESIndicesGetIndexRequestBuilder prepareGetIndex() {
        return new ESIndicesGetIndexRequestBuilder(this, ESIndicesGetIndexAction.INSTANCE);
    }

    @Override
    public ESIndicesCatIndicesRequestBuilder prepareCatIndices(String... indices) {
        return new ESIndicesCatIndicesRequestBuilder(this, ESIndicesCatIndicesAction.INSTANCE).setIndices(indices);
    }

    @Override
    public ESIndicesCatIndicesRequestBuilder prepareCatIndices() {
        return new ESIndicesCatIndicesRequestBuilder(this, ESIndicesCatIndicesAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESIndicesPutIndexResponse> putIndex(final ESIndicesPutIndexRequest request) {
        return execute(ESIndicesPutIndexAction.INSTANCE, request);
    }

    @Override
    public void putIndex(ESIndicesPutIndexRequest request, ActionListener<ESIndicesPutIndexResponse> listener) {
        execute(ESIndicesPutIndexAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesPutIndexRequestBuilder preparePutIndex(String index) {
        return new ESIndicesPutIndexRequestBuilder(this, ESIndicesPutIndexAction.INSTANCE).setIndex(index);
    }


    @Override
    public ActionFuture<ESIndicesDeleteIndexResponse> deleteIndex(final ESIndicesDeleteIndexRequest request) {
        return execute(ESIndicesDeleteIndexAction.INSTANCE, request);
    }

    @Override
    public void deleteIndex(ESIndicesDeleteIndexRequest request, ActionListener<ESIndicesDeleteIndexResponse> listener) {
        execute(ESIndicesDeleteIndexAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesDeleteIndexRequestBuilder prepareDeleteIndex(String index) {
        return new ESIndicesDeleteIndexRequestBuilder(this, ESIndicesDeleteIndexAction.INSTANCE).setIndex(index);
    }


    @Override
    public ActionFuture<ESIndicesRefreshIndexResponse> refreshIndex(final ESIndicesRefreshIndexRequest request) {
        return execute(ESIndicesRefreshIndexAction.INSTANCE, request);
    }

    @Override
    public void refreshIndex(ESIndicesRefreshIndexRequest request, ActionListener<ESIndicesRefreshIndexResponse> listener) {
        execute(ESIndicesRefreshIndexAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesRefreshIndexRequestBuilder prepareRefreshIndex(String index) {
        return new ESIndicesRefreshIndexRequestBuilder(this, ESIndicesRefreshIndexAction.INSTANCE).setIndex(index);
    }

    @Override
    public ActionFuture<ESIndicesGetTemplateResponse> getTemplate(final ESIndicesGetTemplateRequest request) {
        return execute(ESIndicesGetTemplateAction.INSTANCE, request);
    }

    @Override
    public void getTemplate(ESIndicesGetTemplateRequest request, ActionListener<ESIndicesGetTemplateResponse> listener) {
        execute(ESIndicesGetTemplateAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesGetTemplateRequestBuilder prepareGetTemplate(String... templates) {
        return new ESIndicesGetTemplateRequestBuilder(this, ESIndicesGetTemplateAction.INSTANCE).setTemplate(templates);
    }

    @Override
    public ESIndicesGetTemplateRequestBuilder prepareGetTemplate() {
        return new ESIndicesGetTemplateRequestBuilder(this, ESIndicesGetTemplateAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESIndicesPutTemplateResponse> putTemplate(final ESIndicesPutTemplateRequest request) {
        return execute(ESIndicesPutTemplateAction.INSTANCE, request);
    }

    @Override
    public void putTemplate(ESIndicesPutTemplateRequest request, ActionListener<ESIndicesPutTemplateResponse> listener) {
        execute(ESIndicesPutTemplateAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesPutTemplateRequestBuilder preparePutTemplate(String template) {
        return new ESIndicesPutTemplateRequestBuilder(this, ESIndicesPutTemplateAction.INSTANCE).setTemplate(template);
    }


    @Override
    public ActionFuture<ESIndicesDeleteTemplateResponse> deleteTemplate(final ESIndicesDeleteTemplateRequest request) {
        return execute(ESIndicesDeleteTemplateAction.INSTANCE, request);
    }

    @Override
    public void deleteTemplate(ESIndicesDeleteTemplateRequest request, ActionListener<ESIndicesDeleteTemplateResponse> listener) {
        execute(ESIndicesDeleteTemplateAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesDeleteTemplateRequestBuilder prepareDeleteTemplate(String template) {
        return new ESIndicesDeleteTemplateRequestBuilder(this, ESIndicesDeleteTemplateAction.INSTANCE).setTemplate(template);
    }


    @Override
    public ActionFuture<ESIndicesUpdateSettingsResponse> updateSettings(final ESIndicesUpdateSettingsRequest request) {
        return execute(ESIndicesUpdateSettingsAction.INSTANCE, request);
    }

    @Override
    public void updateSettings(ESIndicesUpdateSettingsRequest request, ActionListener<ESIndicesUpdateSettingsResponse> listener) {
        execute(ESIndicesUpdateSettingsAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesUpdateSettingsRequestBuilder prepareUpdateSettings(String index) {
        return new ESIndicesUpdateSettingsRequestBuilder(this, ESIndicesUpdateSettingsAction.INSTANCE).setIndex(index);
    }


    @Override
    public ActionFuture<ESIndicesExistsResponse> exists(final ESIndicesExistsRequest request) {
        return execute(ESIndicesExistsAction.INSTANCE, request);
    }

    @Override
    public void exists(ESIndicesExistsRequest request, ActionListener<ESIndicesExistsResponse> listener) {
        execute(ESIndicesExistsAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesExistsRequestBuilder prepareExists(String index) {
        return new ESIndicesExistsRequestBuilder(this, ESIndicesExistsAction.INSTANCE).setIndex(index);
    }


    @Override
    public ActionFuture<ESIndicesOpenIndexResponse> openIndex(final ESIndicesOpenIndexRequest request) {
        return execute(ESIndicesOpenIndexAction.INSTANCE, request);
    }

    @Override
    public void openIndex(ESIndicesOpenIndexRequest request, ActionListener<ESIndicesOpenIndexResponse> listener) {
        execute(ESIndicesOpenIndexAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesOpenIndexRequestBuilder prepareOpenIndex(String index) {
        return new ESIndicesOpenIndexRequestBuilder(this, ESIndicesOpenIndexAction.INSTANCE).setIndices(index);
    }


    @Override
    public ActionFuture<ESIndicesCloseIndexResponse> closeIndex(final ESIndicesCloseIndexRequest request) {
        return execute(ESIndicesCloseIndexAction.INSTANCE, request);
    }

    @Override
    public void closeIndex(ESIndicesCloseIndexRequest request, ActionListener<ESIndicesCloseIndexResponse> listener) {
        execute(ESIndicesCloseIndexAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesCloseIndexRequestBuilder prepareCloseIndex(String index) {
        return new ESIndicesCloseIndexRequestBuilder(this, ESIndicesCloseIndexAction.INSTANCE).setIndices(index);
    }


    @Override
    public ActionFuture<ESIndicesUpdateMappingResponse> updateMapping(final ESIndicesUpdateMappingRequest request) {
        return execute(ESIndicesUpdateMappingAction.INSTANCE, request);
    }

    @Override
    public void updateMapping(ESIndicesUpdateMappingRequest request, ActionListener<ESIndicesUpdateMappingResponse> listener) {
        execute(ESIndicesUpdateMappingAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesUpdateMappingRequestBuilder prepareUpdateMapping() {
        return new ESIndicesUpdateMappingRequestBuilder(this, ESIndicesUpdateMappingAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESIndicesDeleteByQueryResponse> deleteByQuery(final ESIndicesDeleteByQueryRequest request) {
        return execute(ESIndicesDeleteByQueryAction.INSTANCE, request);
    }

    @Override
    public void deleteByQuery(ESIndicesDeleteByQueryRequest request, ActionListener<ESIndicesDeleteByQueryResponse> listener) {
        execute(ESIndicesDeleteByQueryAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesDeleteByQueryRequestBuilder prepareDeleteByQuery() {
        return new ESIndicesDeleteByQueryRequestBuilder(this, ESIndicesDeleteByQueryAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESDeletePipelineResponse> deletePipeline(final ESDeletePipelineRequest request) {
        return execute(ESDeletePipelineAction.INSTANCE, request);
    }

    @Override
    public void deletePipeline(ESDeletePipelineRequest request, ActionListener<ESDeletePipelineResponse> listener) {
        execute(ESDeletePipelineAction.INSTANCE, request, listener);
    }

    @Override
    public ESDeletePipelineRequestBuilder prepareDeletePipeline() {
        return new ESDeletePipelineRequestBuilder(this, ESDeletePipelineAction.INSTANCE);
    }

    @Override
    public ActionFuture<ESPutPipelineResponse> putPipeline(ESPutPipelineRequest request) {
        return execute(ESPutPipelineAction.INSTANCE, request);
    }

    @Override
    public void putPipeline(ESPutPipelineRequest request, ActionListener<ESPutPipelineResponse> listener) {
        execute(ESPutPipelineAction.INSTANCE, request, listener);
    }

    @Override
    public ESPutPipelineRequestBuilder preparePutPipeline() {
        return new ESPutPipelineRequestBuilder(this, ESPutPipelineAction.INSTANCE);
    }

    @Override
    public ActionFuture<ESGetPipelineResponse> getPipeline(ESGetPipelineRequest request) {
        return execute(ESGetPipelineAction.INSTANCE, request);
    }

    @Override
    public void getPipeline(ESGetPipelineRequest request, ActionListener<ESGetPipelineResponse> listener) {
        execute(ESGetPipelineAction.INSTANCE, request, listener);
    }

    @Override
    public ESGetPipelineRequestBuilder prepareGetPipeline() {
        return new ESGetPipelineRequestBuilder(this, ESGetPipelineAction.INSTANCE);
    }

    @Override
    public ESPutDCDRTemplateRequestBuilder preparePutDCDRTemplate() {
        return new ESPutDCDRTemplateRequestBuilder(this, ESPutDCDRTemplateAction.INSTANCE);
    }

    @Override
    public ActionFuture<ESDeleteDCDRTemplateResponse> deleteDCDRTemplate(ESDeleteDCDRTemplateRequest request) {
        return execute(ESDeleteDCDRTemplateAction.INSTANCE, request);
    }

    @Override
    public ActionFuture<ESGetDCDRIndexResponse> getDCDRIndex(ESGetDCDRIndexRequest request) {
        return execute(ESGetDCDRIndexAction.INSTANCE, request);
    }

    @Override
    public ActionFuture<ESGetDCDRTemplateResponse> getDCDRTemplate(ESGetDCDRTemplateRequest request) {
        return execute(ESGetDCDRTemplateAction.INSTANCE, request);
    }

    @Override
    public ESDeleteDCDRIndexRequestBuilder prepareDeleteDCDRIndex() {
        return new ESDeleteDCDRIndexRequestBuilder(this, ESDeleteDCDRIndexAction.INSTANCE);
    }

    @Override
    public ESGetDCDRStatsRequestBuilder prepareGetDCDRStats() {
        return new ESGetDCDRStatsRequestBuilder(this, ESGetDCDRStatsAction.INSTANCE);
    }

    @Override
    public ActionFuture<ESGetDCDRStatsResponse> getDCDRStats(ESGetDCDRStatsRequest request) {
        return execute(ESGetDCDRStatsAction.INSTANCE, request);
    }

    @Override
    public void getDCDRStats(ESGetDCDRStatsRequest request, ActionListener<ESGetDCDRStatsResponse> listener) {
        execute(ESGetDCDRStatsAction.INSTANCE, request, listener);
    }

    @Override
    public ActionFuture<ESIndicesFreezeIndexResponse> freezeIndex(final ESIndicesFreezeIndexRequest request) {
        return execute(ESIndicesFreezeIndexAction.INSTANCE, request);
    }

    @Override
    public void freezeIndex(ESIndicesFreezeIndexRequest request, ActionListener<ESIndicesFreezeIndexResponse> listener) {
        execute(ESIndicesFreezeIndexAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesFreezeIndexRequestBuilder prepareFreezeIndex(boolean freeze, String... indices) {
        return new ESIndicesFreezeIndexRequestBuilder(this, ESIndicesFreezeIndexAction.INSTANCE).setIndices(freeze, indices);
    }

    @Override
    public ESGetSecurityRoleRequestBuilder prepareGetSecurityRole() {
        return new ESGetSecurityRoleRequestBuilder(this, ESGetSecurityRoleAction.INSTANCE);
    }

    @Override
    public ESGetSecurityUserRequestBuilder prepareGetSecurityUser() {
        return new ESGetSecurityUserRequestBuilder(this, ESGetSecurityUserAction.INSTANCE);
    }

    @Override
    public ActionFuture<ESGetSecurityRoleResponse> getSecurityRole(ESGetSecurityRoleRequest request) {
        return execute(ESGetSecurityRoleAction.INSTANCE, request);
    }

    @Override
    public ActionFuture<ESGetSecurityUserResponse> getSecurityRole(ESGetSecurityUserRequest request) {
        return execute(ESGetSecurityUserAction.INSTANCE, request);
    }

    @Override
    public ESPutSecurityRoleRequestBuilder preparePutSecurityRole() {
        return new ESPutSecurityRoleRequestBuilder(this, ESPutSecurityRoleAction.INSTANCE);
    }

    @Override
    public ESPutSecurityUserRequestBuilder preparePutSecurityUser() {
        return new ESPutSecurityUserRequestBuilder(this, ESPutSecurityUserAction.INSTANCE);
    }

    @Override
    public ActionFuture<ESPutSecurityRoleResponse> putSecurityRole(ESPutSecurityRoleRequest request) {
        return execute(ESPutSecurityRoleAction.INSTANCE, request);
    }

    @Override
    public ActionFuture<ESPutSecurityUserResponse> putSecurityUser(ESPutSecurityUserRequest request) {
        return execute(ESPutSecurityUserAction.INSTANCE, request);
    }

    @Override
    public ESDeleteSecurityRoleRequestBuilder prepareDeleteSecurityRole() {
        return new ESDeleteSecurityRoleRequestBuilder(this, ESDeleteSecurityRoleAction.INSTANCE);
    }

    @Override
    public ESDeleteSecurityUserRequestBuilder prepareDeleteSecurityUser() {
        return new ESDeleteSecurityUserRequestBuilder(this, ESDeleteSecurityUserAction.INSTANCE);
    }

    @Override
    public ActionFuture<ESDeleteSecurityRoleResponse> deleteSecurityRole(ESDeleteSecurityRoleRequest request) {
        return execute(ESDeleteSecurityRoleAction.INSTANCE, request);
    }

    @Override
    public ActionFuture<ESDeleteSecurityUserResponse> deleteSecurityUser(ESDeleteSecurityUserRequest request) {
        return execute(ESDeleteSecurityUserAction.INSTANCE, request);
    }


    @Override
    public ActionFuture<ESIndicesClearCacheResponse> clearCache(final ESIndicesClearCacheRequest request) {
        return execute(ESIndicesClearCacheAction.INSTANCE, request);
    }

    @Override
    public void clearCache(ESIndicesClearCacheRequest request, ActionListener<ESIndicesClearCacheResponse> listener) {
        execute(ESIndicesClearCacheAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesClearCacheRequestBuilder prepareClearCache(String index) {
        return new ESIndicesClearCacheRequestBuilder(this, ESIndicesClearCacheAction.INSTANCE).setIndices(index);
    }


    @Override
    public ActionFuture<ESIndicesFlushIndexResponse> flush(final ESIndicesFlushIndexRequest request) {
        return execute(ESIndicesFlushIndexAction.INSTANCE, request);
    }

    @Override
    public void flush(ESIndicesFlushIndexRequest request, ActionListener<ESIndicesFlushIndexResponse> listener) {
        execute(ESIndicesFlushIndexAction.INSTANCE, request, listener);
    }

    @Override
    public ESIndicesFlushIndexRequestBuilder prepareFlush(String index) {
        return new ESIndicesFlushIndexRequestBuilder(this, ESIndicesFlushIndexAction.INSTANCE).setIndices(index);
    }

    @Override
    public ActionFuture<ESPutDCDRIndexResponse> putDCDRIndex(final ESPutDCDRIndexRequest request) {
        return execute(ESPutDCDRIndexAction.INSTANCE, request);
    }

    @Override
    public void putDCDRIndex(ESPutDCDRIndexRequest request, ActionListener<ESPutDCDRIndexResponse> listener) {
        execute(ESPutDCDRIndexAction.INSTANCE, request, listener);
    }

    @Override
    public ESPutDCDRIndexRequestBuilder preparePutDCDRIndex(String index) {
        return new ESPutDCDRIndexRequestBuilder(this, ESPutDCDRIndexAction.INSTANCE).setIndexName(index);
    }
}
