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

import com.didiglobal.logi.elasticsearch.client.request.cluster.getsetting.ESClusterGetSettingsRequest;
import com.didiglobal.logi.elasticsearch.client.request.cluster.getsetting.ESClusterGetSettingsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.cluster.getversion.ESClusterVersionRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.cluster.health.ESClusterHealthRequest;
import com.didiglobal.logi.elasticsearch.client.request.cluster.health.ESClusterHealthRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodes.ESClusterNodesAction;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodes.ESClusterNodesRequest;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodes.ESClusterNodesRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodessetting.ESClusterNodesSettingRequest;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodessetting.ESClusterNodesSettingRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodestats.ESClusterNodesStatsRequest;
import com.didiglobal.logi.elasticsearch.client.request.cluster.nodestats.ESClusterNodesStatsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.request.cluster.updatesetting.ESClusterUpdateSettingsRequest;
import com.didiglobal.logi.elasticsearch.client.request.cluster.updatesetting.ESClusterUpdateSettingsRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.response.cluster.ESClusterHealthResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.ESClusterVersionResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.getsetting.ESClusterGetSettingsResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.nodes.ESClusterNodesResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.nodessetting.ESClusterNodesSettingResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.nodesstats.ESClusterNodesStatsResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.updatesetting.ESClusterUpdateSettingsResponse;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * Administrative actions/operations against indices.
 *
 * @see AdminClient#cluster()
 */
public interface ESClusterAdminClient extends ElasticsearchClient {

    ActionFuture<ESClusterHealthResponse> health(ESClusterHealthRequest request);

    void health(ESClusterHealthRequest request, ActionListener<ESClusterHealthResponse> listener);

    ESClusterHealthRequestBuilder prepareHealth();

    ESClusterVersionRequestBuilder prepareVersion();


    ActionFuture<ESClusterNodesStatsResponse> nodeStats(final ESClusterNodesStatsRequest request);

    void nodeStats(final ESClusterNodesStatsRequest request, final ActionListener<ESClusterNodesStatsResponse> listener);

    ESClusterNodesStatsRequestBuilder prepareNodeStats();


    public ActionFuture<ESClusterNodesResponse> nodes(final ESClusterNodesRequest request);

    public void nodes(final ESClusterNodesRequest request, final ActionListener<ESClusterNodesResponse> listener);

    public ESClusterNodesRequestBuilder prepareNodes();


    ActionFuture<ESClusterNodesSettingResponse> nodesSetting(final ESClusterNodesSettingRequest request);

    void nodesSetting(final ESClusterNodesSettingRequest request, final ActionListener<ESClusterNodesSettingResponse> listener);

    ESClusterNodesSettingRequestBuilder prepareNodesSetting();


    public ActionFuture<ESClusterGetSettingsResponse> getSetting(final ESClusterGetSettingsRequest request);

    public void getSetting(final ESClusterGetSettingsRequest request, final ActionListener<ESClusterGetSettingsResponse> listener);

    public ESClusterGetSettingsRequestBuilder prepareGetSettings();


    ActionFuture<ESClusterUpdateSettingsResponse> updateSetting(final ESClusterUpdateSettingsRequest request);

    void updateSetting(final ESClusterUpdateSettingsRequest request, final ActionListener<ESClusterUpdateSettingsResponse> listener);

    ESClusterUpdateSettingsRequestBuilder prepareUpdateSettings();
}
