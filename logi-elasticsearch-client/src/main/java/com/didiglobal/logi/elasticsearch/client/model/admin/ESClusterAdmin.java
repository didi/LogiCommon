package com.didiglobal.logi.elasticsearch.client.model.admin;

import com.didiglobal.logi.elasticsearch.client.request.cluster.getsetting.ESClusterGetSettingsAction;
import com.didiglobal.logi.elasticsearch.client.request.cluster.getsetting.ESClusterGetSettingsRequest;
import com.didiglobal.logi.elasticsearch.client.request.cluster.getsetting.ESClusterGetSettingsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodes.ESClusterNodesAction;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodes.ESClusterNodesRequest;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodes.ESClusterNodesRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.response.cluster.getsetting.ESClusterGetSettingsResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.nodes.ESClusterNodesResponse;
import org.elasticsearch.action.Action;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.threadpool.ThreadPool;

import com.didiglobal.logi.elasticsearch.client.request.cluster.getversion.ESClusterVersionAction;
import com.didiglobal.logi.elasticsearch.client.request.cluster.getversion.ESClusterVersionRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.cluster.health.ESClusterHealthAction;
import com.didiglobal.logi.elasticsearch.client.request.cluster.health.ESClusterHealthRequest;
import com.didiglobal.logi.elasticsearch.client.request.cluster.health.ESClusterHealthRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodessetting.ESClusterNodesSettingAction;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodessetting.ESClusterNodesSettingRequest;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodessetting.ESClusterNodesSettingRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodestats.ESClusterNodesStatsAction;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodestats.ESClusterNodesStatsRequest;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodestats.ESClusterNodesStatsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.cluster.updatesetting.ESClusterUpdateSettingsAction;
import com.didiglobal.logi.elasticsearch.client.request.cluster.updatesetting.ESClusterUpdateSettingsRequest;
import com.didiglobal.logi.elasticsearch.client.request.cluster.updatesetting.ESClusterUpdateSettingsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.response.cluster.ESClusterHealthResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.nodessetting.ESClusterNodesSettingResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.nodesstats.ESClusterNodesStatsResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.updatesetting.ESClusterUpdateSettingsResponse;

public class ESClusterAdmin implements ESClusterAdminClient {

    private final ElasticsearchClient client;

    public ESClusterAdmin(ElasticsearchClient client) {
        this.client = client;
    }

    @Override
    public <Request extends ActionRequest, Response extends ActionResponse, RequestBuilder extends ActionRequestBuilder<Request, Response, RequestBuilder>>
    ActionFuture<Response> execute(Action<Request, Response, RequestBuilder> action, Request request) {
        return client.execute(action, request);
    }

    @Override
    public <Request extends ActionRequest, Response extends ActionResponse, RequestBuilder extends ActionRequestBuilder<Request, Response, RequestBuilder>>
    void execute(Action<Request, Response, RequestBuilder> action, Request request, ActionListener<Response> listener) {
        client.execute(action, request, listener);
    }

    @Override
    public <Request extends ActionRequest, Response extends ActionResponse, RequestBuilder extends ActionRequestBuilder<Request, Response, RequestBuilder>>
    RequestBuilder prepareExecute(Action<Request, Response, RequestBuilder> action) {
        return client.prepareExecute(action);
    }

    @Override
    public ThreadPool threadPool() {
        return client.threadPool();
    }


    @Override
    public ActionFuture<ESClusterHealthResponse> health(final ESClusterHealthRequest request) {
        return execute(ESClusterHealthAction.INSTANCE, request);
    }

    @Override
    public void health(final ESClusterHealthRequest request, final ActionListener<ESClusterHealthResponse> listener) {
        execute(ESClusterHealthAction.INSTANCE, request, listener);
    }

    @Override
    public ESClusterHealthRequestBuilder prepareHealth() {
        return new ESClusterHealthRequestBuilder(this, ESClusterHealthAction.INSTANCE);
    }

    @Override
    public ESClusterVersionRequestBuilder prepareVersion() {
        return new ESClusterVersionRequestBuilder(this, ESClusterVersionAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESClusterNodesStatsResponse> nodeStats(final ESClusterNodesStatsRequest request) {
        return execute(ESClusterNodesStatsAction.INSTANCE, request);
    }

    @Override
    public void nodeStats(final ESClusterNodesStatsRequest request, final ActionListener<ESClusterNodesStatsResponse> listener) {
        execute(ESClusterNodesStatsAction.INSTANCE, request, listener);
    }

    @Override
    public ESClusterNodesStatsRequestBuilder prepareNodeStats() {
        return new ESClusterNodesStatsRequestBuilder(this, ESClusterNodesStatsAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESClusterNodesResponse> nodes(final ESClusterNodesRequest request) {
        return execute(ESClusterNodesAction.INSTANCE, request);
    }

    @Override
    public void nodes(final ESClusterNodesRequest request, final ActionListener<ESClusterNodesResponse> listener) {
        execute(ESClusterNodesAction.INSTANCE, request, listener);
    }

    @Override
    public ESClusterNodesRequestBuilder prepareNodes() {
        return new ESClusterNodesRequestBuilder(this, ESClusterNodesAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESClusterNodesSettingResponse> nodesSetting(final ESClusterNodesSettingRequest request) {
        return execute(ESClusterNodesSettingAction.INSTANCE, request);
    }

    @Override
    public void nodesSetting(final ESClusterNodesSettingRequest request, final ActionListener<ESClusterNodesSettingResponse> listener) {
        execute(ESClusterNodesSettingAction.INSTANCE, request, listener);
    }

    @Override
    public ESClusterNodesSettingRequestBuilder prepareNodesSetting() {
        return new ESClusterNodesSettingRequestBuilder(this, ESClusterNodesSettingAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESClusterGetSettingsResponse> getSetting(final ESClusterGetSettingsRequest request) {
        return execute(ESClusterGetSettingsAction.INSTANCE, request);
    }

    @Override
    public void getSetting(final ESClusterGetSettingsRequest request, final ActionListener<ESClusterGetSettingsResponse> listener) {
        execute(ESClusterGetSettingsAction.INSTANCE, request, listener);
    }

    @Override
    public ESClusterGetSettingsRequestBuilder prepareGetSettings() {
        return new ESClusterGetSettingsRequestBuilder(this, ESClusterGetSettingsAction.INSTANCE);
    }


    @Override
    public ActionFuture<ESClusterUpdateSettingsResponse> updateSetting(final ESClusterUpdateSettingsRequest request) {
        return execute(ESClusterUpdateSettingsAction.INSTANCE, request);
    }

    @Override
    public void updateSetting(final ESClusterUpdateSettingsRequest request, final ActionListener<ESClusterUpdateSettingsResponse> listener) {
        execute(ESClusterUpdateSettingsAction.INSTANCE, request, listener);
    }

    @Override
    public ESClusterUpdateSettingsRequestBuilder prepareUpdateSettings() {
        return new ESClusterUpdateSettingsRequestBuilder(this, ESClusterUpdateSettingsAction.INSTANCE);
    }
}
