package com.didiglobal.logi.elasticsearch.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.gateway.direct.DirectRequest;
import com.didiglobal.logi.elasticsearch.client.gateway.direct.DirectResponse;
import com.didiglobal.logi.elasticsearch.client.gateway.document.*;
import com.didiglobal.logi.elasticsearch.client.gateway.search.*;
import com.didiglobal.logi.elasticsearch.client.request.batch.BatchNode;
import com.didiglobal.logi.elasticsearch.client.request.batch.BatchType;
import com.didiglobal.logi.elasticsearch.client.request.batch.ESBatchRequest;
import com.didiglobal.logi.elasticsearch.client.request.index.putalias.PutAliasNode;
import com.didiglobal.logi.elasticsearch.client.request.index.putalias.PutAliasType;
import com.didiglobal.logi.elasticsearch.client.request.index.stats.IndicesStatsLevel;
import com.didiglobal.logi.elasticsearch.client.request.ingest.Pipeline;
import com.didiglobal.logi.elasticsearch.client.response.batch.ESBatchResponse;
import com.didiglobal.logi.elasticsearch.client.response.cat.ESCatResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.ESClusterHealthResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.getsetting.ESClusterGetSettingsResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.nodesstats.ESClusterNodesStatsResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.updatesetting.ESClusterUpdateSettingsResponse;
import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESGetDCDRStatsResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.deleteindex.ESIndicesDeleteIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.deletetemplate.ESIndicesDeleteTemplateResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.getalias.ESIndicesGetAliasResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.getindex.ESIndicesGetIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.gettemplate.ESIndicesGetTemplateResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.putalias.ESIndicesPutAliasResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.putindex.ESIndicesPutIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.puttemplate.ESIndicesPutTemplateResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.searchshards.ESIndicesSearchShardsResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.stats.ESIndicesStatsResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.updatemapping.ESIndicesUpdateMappingResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.updatesettings.ESIndicesUpdateSettingsResponse;
import com.didiglobal.logi.elasticsearch.client.response.ingest.ESDeletePipelineResponse;
import com.didiglobal.logi.elasticsearch.client.response.ingest.ESGetPipelineResponse;
import com.didiglobal.logi.elasticsearch.client.response.ingest.ESPutPipelineResponse;
import com.didiglobal.logi.elasticsearch.client.response.query.query.ESQueryResponse;
import com.didiglobal.logi.elasticsearch.client.response.setting.common.TypeConfig;
import com.didiglobal.logi.elasticsearch.client.response.setting.template.TemplateConfig;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ClientTest {

    private String index = "zhonghua_test";

    private static ESClient oldClient;
    private static ESClient newClient;
    private static ESClient client;
    private static ESClient gatewayClient;

    private static String oldIp = "10.179.100.148";
    private static String newIp = "10.179.101.239";

    static {
        try {
            oldClient = new ESClient();
            oldClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(oldIp), 9200));
//            oldClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.179.28.230"), 9200));
//            oldClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.179.51.136"), 9200));
            oldClient.start();

            newClient = new ESClient();
            newClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(newIp), 9200));
            newClient.start();

            Header header = new BasicHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(String.format("%s:%s", "1079", "admin").getBytes()));
            gatewayClient = new ESClient();
            gatewayClient.setHeader(header);
            gatewayClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.179.117.215"), 8200));
            gatewayClient.start();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        client = newClient;
    }


    @Test
    public void updateSettins() throws Exception {
        ESIndicesUpdateSettingsResponse resp = client.admin().indices()
                .prepareUpdateSettings(index).addSettings("number_of_replicas", "0").execute().get();

        System.out.println(resp);
    }


    @Test
    public void updateMapping() throws Exception {

        String typeConfigStr = "{\n" +
                "        \"properties\": {\n" +
                "          \"coldDay\": {\n" +
                "            \"type\": \"long\"\n" +
                "          },\n" +
                "          \"name\": {\n" +
                "            \"type\": \"string\"\n" +
                "          },\n" +
                "          \"cccdDay\": {\n" +
                "            \"type\": \"long\"\n" +
                "          },\n" +
                "        }\n" +
                "      }";

        TypeConfig typeConfig = new TypeConfig(JSONObject.parseObject(typeConfigStr));

        ESIndicesUpdateMappingResponse resp = client.admin().indices().prepareUpdateMapping()
                .setIndex(index)
                .setType("type")
                .setTypeConfig(typeConfig).execute().get();

        System.out.println(JSON.toJSONString(resp));
    }


    @Test
    public void putAlias() throws Exception {
        PutAliasNode putAliasNode = new PutAliasNode();
        putAliasNode.setIndex(index);
        putAliasNode.setAlias("helloworld_test");
        putAliasNode.setType(PutAliasType.REMOVE);
        ESIndicesPutAliasResponse resp = client.admin().indices().preparePutAlias().addPutAliasNode(putAliasNode).execute().get();

        System.out.println(resp);
    }


    @Test
    public void cat() throws Exception {
        ESCatResponse resp = client.prepareCat().setUri("shards").setClazz(JSONObject.class).execute().get();

        System.out.println(resp.toString());
    }


    @Test
    public void putIndex() throws Exception {
        ESIndicesPutIndexResponse resp = client.admin().indices().preparePutIndex(index).execute().get();

        System.out.println(resp);
    }


    @Test
    public void deleteIndex() throws Exception {
        ESIndicesDeleteIndexResponse resp = client.admin().indices().prepareDeleteIndex(index).execute().get();

        System.out.println(resp);
    }


    @Test
    public void deleteTemplate() throws Exception {
        Map<String, String> settings = new HashMap<>();
        settings.put("index.number_of_shards", "1");

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setTemplate(index);
        templateConfig.setOrder(10l);
        templateConfig.setSetttings(settings);

        ESIndicesPutTemplateResponse response = client.admin().indices().preparePutTemplate(index).setTemplateConfig(templateConfig).execute().get();
        System.out.println(response);


        ESIndicesDeleteTemplateResponse resp = client.admin().indices().prepareDeleteTemplate(index).execute().get();


        System.out.println(resp);
    }


    @Test
    public void getSetting() throws Exception {
        ESClusterGetSettingsResponse resp = client.admin().cluster().prepareGetSettings().execute().get();

        System.out.println(resp.getFlatPersistent().get("cluster.routing.rebalance.enable"));
        System.out.println(resp.getFlatTransient().get("cluster.routing.rebalance.enable"));
        System.out.println(JSON.toJSONString(resp));
    }


    @Test
    public void updateSetting() throws Exception {
        ESClusterUpdateSettingsResponse resp = client.admin().cluster().prepareUpdateSettings()
                .addPersistent("xpack.monitoring.collection.enabled", "false")
                .execute().get();


        System.out.println(resp);

        resp = client.admin().cluster().prepareUpdateSettings()
                .addPersistent("xpack.monitoring.collection.enabled", "true")
                .execute().get();


        System.out.println(resp);

    }


    @Test
    public void putTemplate() throws Exception {
        ESIndicesGetTemplateResponse response = client.admin().indices().prepareGetTemplate("arius.gateway.join").execute().get();
        TemplateConfig templateConfig = response.getMultiTemplatesConfig().getSingleConfig();


//        ESClient client = new ESClient();
//        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.179.20.63"), 9200));
//        client.start();
        ESIndicesPutTemplateResponse resp = client.admin().indices().preparePutTemplate("zhonghua.arius.gateway.join").setTemplateConfig(templateConfig).execute().get();


        System.out.println(resp);

    }


    @Test
    public void getTemplate() throws Exception {
        ESIndicesGetTemplateResponse response = client.admin().indices().prepareGetTemplate("*").execute().get();

        System.out.println(response);

    }


    @Test
    public void getIndex() throws Exception {

        ESIndicesGetIndexResponse response = client.admin().indices().prepareGetIndex("*").settings(true).execute().get();

        System.out.println(response);

    }


    @Test
    public void alias() throws Exception {

        ESIndicesGetAliasResponse response = client.admin().indices().prepareAlias(null).execute().get();

        System.out.println(response);
    }


    @Test
    public void batch() throws Exception {

        ESBatchRequest request = new ESBatchRequest();

        String content = "{\"drop_away_data\":\"\",\"binlogTime\":\"2018-08-14 11:18:34.575 +0800\",\"award_data\":\"\",\"contract_id\":\"3667944655252643863\",\"goods\":{\"province_default_city_id\":892170201172557909,\"sku_id\":2486374730354132029,\"brand_logo_url\":\"//am.didistatic.com/static/am/prod/recharge/10010.png\",\"area_type\":1,\"ssu_id\":2551671462693176595,\"ssu_name\":\"联通200元话费\",\"brand_id\":2410895620415947782},\"task_id\":\"4883486989981321654\",\"order_time\":\"1498282277\",\"real_price\":\"19990\",\"uid\":\"2364899024752099744\",\"pay_type\":\"3\",\"id\":\"4883487045564514386\",\"canada_category_ratio\":\"0.0\",\"division_data\":\"\",\"order_type\":\"10\",\"service_finish_time\":\"1498282416\",\"contract_no\":\"\",\"store_id\":\"3230340008998602851\",\"pop_task_id\":\"4883486994818140604\",\"settle_ratio\":\"1.0\",\"create_time\":\"1498322414\",\"total_price\":\"19990\",\"coupon_data\":\"\",\"pop_id\":\"3230035633657612300\",\"task_date\":\"20170624\",\"biz_data\":\"{\\\"source_type\\\":1,\\\"recharge_mobile\\\":\\\"15555554639\\\",\\\"recharge_mobile_city_id\\\":\\\"892170211373190395\\\",\\\"am_channel\\\":10001,\\\"user_current_city_id\\\":892170201172557909,\\\"user_current_city_name\\\":\\\"合肥市\\\"}\",\"sale_price\":\"20000\",\"settle_time\":\"1498325359\",\"pay_time\":\"1498282290\",\"pop_service_fee\":\"0\",\"settle_price\":\"19880\",\"comment\":\"update_es\",\"order_id\":\"4883318699085545739\",\"pop_market_expense\":\"10\",\"supplier_id\":\"3667500784272564243\",\"city_id\":\"892170199360604217\",\"status\":\"6\"}";
        String index = "batch_test";
        String type = "hehe";
        for (int i = 0; i < 4; i++) {
            BatchNode batchNode = new BatchNode(BatchType.INDEX, index, type, "" + i, content);
            batchNode.setRouting("routing");
            request.addNode(batchNode);
        }

        ESBatchResponse response = client.batch(request).actionGet();

        System.out.println(response);

    }


    @Test
    public void query6() throws Exception {

        ESQueryResponse response =
                client.prepareQuery("*")
//                              QueryBuilders.rangeQuery("timestamp").from(System.currentTimeMillis()))
                        .setSize(20)
                        .execute()
                        .actionGet();

        System.out.println(response);

    }

    @Test
    public void query() throws Exception {


        ESQueryResponse response =
                client.prepareQuery("arius.gateway.join_2018-07-13")
//                              QueryBuilders.rangeQuery("timestamp").from(System.currentTimeMillis()))
                        .setSize(20)
                        .execute()
                        .actionGet();

        System.out.println(response);

    }


    @Test
    public void indicesStat() throws Exception {

        ESIndicesStatsResponse response =
                client.admin()
                        .indices()
                        .prepareStats("arius.gateway.join*").setLevel(IndicesStatsLevel.SHARDS).execute().get();

        System.out.println(response);

//        GetIndexTemplatesResponse response = client.admin().indices().prepareGetTemplates("template_name")
//                .execute().actionGet();
    }


    @Test
    public void indicesSearchShards() throws Exception {

        ESIndicesSearchShardsResponse response =
                client.admin()
                        .indices()
                        .prepareSearchShards("arius.gateway.join_*").execute().get();

        System.out.println(response);

//        GetIndexTemplatesResponse response = client.admin().indices().prepareGetTemplates("template_name")
//                .execute().actionGet();
    }


    @Test
    public void healthCluster() throws Exception {
        ESClusterHealthResponse response =
                client.admin()
                        .cluster()
                        .prepareHealth().execute().get();

        System.out.println(response);

//        GetIndexTemplatesResponse response = client.admin().indices().prepareGetTemplates("template_name")
//                .execute().actionGet();
    }

    @Test
    public void clusterNodeStats() throws Exception {
        ESClusterNodesStatsResponse response =
                client.admin().cluster().prepareNodeStats()
                        .execute().get();

        System.out.println(response);

//        GetIndexTemplatesResponse response = client.admin().indices().prepareGetTemplates("template_name")
//                .execute().actionGet();
    }

    @Test
    public void directActionTest() throws UnknownHostException {
        DirectRequest directRequest = new DirectRequest("GET", "/_cluster/health");
        DirectResponse directResponse = client.direct(directRequest).actionGet();
        System.out.println(directResponse.getResponseContent());
    }

    @Test
    public void directActionAsyncTest() throws UnknownHostException {
        DirectRequest directRequest = new DirectRequest("GET", "/_cluster/health");
        client.direct(directRequest, new ActionListener<DirectResponse>() {
            @Override
            public void onResponse(DirectResponse directResponse) {
                System.out.println(directResponse);
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void searchTest() throws Exception {
        ESSearchRequest esSearchRequest = new ESSearchRequest();
        esSearchRequest.indices("foundation_fd.data-online.arius.gateway*");
        ESSearchResponse ESSearchResponse = client.search(esSearchRequest).actionGet();
        System.out.println(ESSearchResponse);

        esSearchRequest.putParam("filter_path", "_shards");
        ESSearchResponse ESSearchResponse2 = client.search(esSearchRequest).actionGet();
        assertNull(ESSearchResponse2.getHits());

        ESSearchRequest esSearchRequest3 = new ESSearchRequest();
        esSearchRequest3.indices("foundation_fd.data-online.arius.gateway*");
        esSearchRequest3.source("{\"profile\":true}");
        ESSearchResponse ESSearchResponse3 = client.search(esSearchRequest3).actionGet();
        System.out.println(ESSearchResponse3);

        XContentBuilder builder = XContentFactory.jsonBuilder().prettyPrint();
        builder.startObject();
        ESSearchResponse3.toXContent(builder, new ToXContent.MapParams(new HashMap<>()));
        builder.endObject();
        System.out.println(builder.string());

    }

    @Test
    public void clearScrollTest() throws UnknownHostException {
        ESSearchRequest esSearchRequest = new ESSearchRequest();
        esSearchRequest.indices("foundation_fd.data-online.arius.gateway*");
        Map<String, String> params = new HashMap<>();
        params.put("scroll", "1m");
        esSearchRequest.setParams(params);
        ESSearchResponse ESSearchResponse = client.search(esSearchRequest).actionGet();

        System.out.println(ESSearchResponse.getScrollId());

        ESClearScrollRequest esClearScrollRequest = new ESClearScrollRequest();
        List<String> scrolls = new ArrayList<>();
        scrolls.add(ESSearchResponse.getScrollId());
        esClearScrollRequest.setScrollIds(scrolls);

        ESClearScrollResponse esClearScrollResponse = client.clearScroll(esClearScrollRequest).actionGet();
        System.out.println(esClearScrollResponse);
    }

    @Test
    public void multiSearchTest() throws UnknownHostException {
        ESMultiSearchRequest esMultiSearchRequest = new ESMultiSearchRequest();

        ESSearchRequest esSearchRequest = new ESSearchRequest();
        esSearchRequest.indices("foundation_fd.data-online.arius.gateway*");
        esMultiSearchRequest.add(esSearchRequest);

        ESSearchRequest esSearchRequest2 = new ESSearchRequest();
        esSearchRequest2.indices("foundation_fd.data-online.arius.gateway");
        esMultiSearchRequest.add(esSearchRequest2);

        ESMultiSearchResponse esMultiSearchResponse = client.multiSearch(esMultiSearchRequest).actionGet();
        System.out.println(esMultiSearchResponse);
    }

    @Test
    public void getTest() throws UnknownHostException {
        ESGetRequest esGetRequest = new ESGetRequest();
        esGetRequest.index("dsl_query_limit");
        esGetRequest.type("type");
        esGetRequest.id("1079_V2_CBE831F865920F5EF6579C7D3F323C57");

        ESGetResponse esGetResponse = client.get(esGetRequest).actionGet();
        System.out.println(esGetResponse);
    }

    @Test
    public void multiGetTest() throws UnknownHostException {
        ESMultiGetRequest esMultiGetRequest = new ESMultiGetRequest();
        esMultiGetRequest.add("dsl_query_limit", "type", "1079_V2_CBE831F865920F5EF6579C7D3F323C57");
        esMultiGetRequest.add("dsl_query_limit", "type", "1079_V2_CBE831F865920F5EF6579C7D3F323C");
        esMultiGetRequest.add("dsl_query_limi", "type", "1079_V2_CBE831F865920F5EF6579C7D3F323C57");

        ESMultiGetResponse esMultiGetResponse = client.multiGet(esMultiGetRequest).actionGet();
        System.out.println(esMultiGetResponse);
    }

    @Test
    public void indexTest() throws IOException {
        ESIndexRequest request = new ESIndexRequest();
        request.index("test_index");
        request.type("type");
        request.id("1");
        request.source("{\"a\":1}");

        ESIndexResponse response = client.index(request).actionGet();

        XContentBuilder builder = XContentFactory.jsonBuilder().prettyPrint();
        response.toXContent(builder, ToXContent.EMPTY_PARAMS);

        System.out.println(builder.string());

    }

    @Test
    public void updateTest() throws IOException {
        ESIndexRequest request = new ESIndexRequest();
        request.index("test_index");
        request.type("type");
        request.id("1");
        request.source("{\"a\":1}");


        client.index(request).actionGet();

        ESDeleteRequest deleteRequest = new ESDeleteRequest();
        deleteRequest.index("test_index");
        deleteRequest.type("type");
        deleteRequest.id("1");

        ESDeleteResponse response = client.delete(deleteRequest).actionGet();

        XContentBuilder builder = XContentFactory.jsonBuilder().prettyPrint();
        response.toXContent(builder, ToXContent.EMPTY_PARAMS);

        System.out.println(builder.string());

        response = client.delete(deleteRequest).actionGet();

        builder = XContentFactory.jsonBuilder().prettyPrint();
        response.toXContent(builder, ToXContent.EMPTY_PARAMS);

        System.out.println(builder.string());

    }

    public void deleteTest() throws IOException {
        ESUpdateRequest request = new ESUpdateRequest();
        request.index("test_index");
        request.type("type");
        request.id("1");
        request.source("{\"doc\":{\"a\":2}}");

        ESUpdateResponse response = client.update(request).actionGet();

        XContentBuilder builder = XContentFactory.jsonBuilder().prettyPrint();
        response.toXContent(builder, ToXContent.EMPTY_PARAMS);

        System.out.println(builder.string());

    }

    @Test
    public void sqlTest() {
        ESQueryResponse response = gatewayClient.prepareSQL("select * from foundation_fd.data-online.arius.gateway*").get();
        System.out.println(response.toString());
    }

    @Test
    public void pipelineTest() {
        String pipelineId = "test_pipeline";
        Pipeline pipeline = new Pipeline();
        //设置描述信息
        pipeline.setDescription(pipelineId);
        // pipeline.setThrottle(100);

        // 设置处理器列表
        List<JSONObject> processors = JSON.parseArray("[{\"set\":{\"field\":\"foo\",\"value\":\"bar\"}}]", JSONObject.class);
        pipeline.setProcessors(processors);

        //写入pipeline
        ESPutPipelineResponse putPipelineResponse = newClient.admin().indices().preparePutPipeline()
                .setPipelineId(pipelineId).setPipeline(pipeline).get();
        assertEquals(putPipelineResponse.getAcknowledged(), true);

        // 获取pipeline
        ESGetPipelineResponse esGetPipelineResponse = newClient.admin().indices().prepareGetPipeline().setPipelineId(pipelineId).get();
        assertEquals(esGetPipelineResponse.getPipelineMap().size(), 1);
        Pipeline getPipeline = esGetPipelineResponse.getPipelineMap().get(pipelineId);
        assertEquals(pipeline.getDescription(), getPipeline.getDescription());
        // assertEquals(pipeline.getThrottle(), getPipeline.getThrottle());
        assertEquals(pipeline.getProcessors().iterator().next().getJSONObject("set").getString("field"), "foo");
        assertEquals(pipeline.getProcessors().iterator().next().getJSONObject("set").getString("value"), "bar");

        // 删除pipeline
        ESDeletePipelineResponse esDeletePipelineResponse = newClient.admin().indices().prepareDeletePipeline().setPipelineId(pipelineId).get();
        assertEquals(esDeletePipelineResponse.getAcknowledged(), true);
    }

    @Test
    public void dcdrTest() {
        ESGetDCDRStatsResponse response = newClient.admin().indices().prepareGetDCDRStats().get();
        System.out.println(response);
    }

//    public void monitor() {
//        // GET _cluster/health
//        ClusterHealthResponse response = transportClient.admin().cluster().prepareHealth().setTimeout(CLIENT_TO_WITH_S).execute().actionGet(CLIENT_TO_WITH_MILLS);
//        response.isTimedOut();
//        ClusterHealthStatus healthStatus = response.getStatus();
//        response.getUnassignedShards();
//        response.getNumberOfPendingTasks();
//        response.getNumberOfDataNodes();
//        response.getNumberOfNodes();
//
//
//
//
//        //  拆分成两个
//        //  GET */_stats
//        //  GET */_search_shards
//        IndicesStatsResponse response = client.admin().indices().prepareStats().execute().actionGet(CLIENT_TO_WITH_MILLS);
//        Map<String, IndexStats> indexStatsMap = response.getIndices();
//        for (Map.Entry<String, IndexStats> entry : indexStatsMap.entrySet()) {
//            String index = entry.getKey();
//            IndexStats indexStats = entry.getValue();
//            CommonStats primaries = indexStats.getPrimaries();
//
//            // TO json
//            String primariesStr = XContentHelper.toString(primaries, ToXContent.EMPTY_PARAMS);
//
//            ShardStats[] shards = indexStats.getShards();
//            for(ShardStats shard : shards) {
//                ShardRouting shardRouting = shard.getShardRouting();
//                shardRouting.currentNodeId();
//            }
//        }
//
//
//
//        // GET _nodes/nodestats
//        NodesStatsResponse response = client.admin().cluster().prepareNodesStats().all().setTimeout(CLIENT_TO_WITH_S).execute().actionGet(CLIENT_TO_WITH_MILLS);
//        for (Map.Entry<String, NodeStats> entry : nodeStatsMap.entrySet()) {
//            String node = entry.getKey();
//            NodeStats nodeStats = entry.getValue();
//            String nodeId = nodeStats.getNode().getId();
//
//            Map<String, String> mapParam = Maps.newHashMap();
//            //返回索引级别信息
//            mapParam.put("level", "indices");
//            ToXContent.MapParams params = new ToXContent.MapParams(mapParam);
//            String nodeStatsStr = XContentHelper.toString(nodeStats, params);
//            Map map = JSON.parseObject(nodeStatsStr, Map.class);
//
//            base.setNode(nodeStats.getNode().getName());
//            base.setPort(String.valueOf(nodeStats.getNode().getAddress().getPort()));
//
//        }
//
//
//        ESBulkProcessor.Listener listener = new DefaultBulkProcessorListener();
//        ESBulkProcessor.Builder builder = ESBulkProcessor.builder(client, listener);
//        builder.setName("monitor-send-es-processor-1").setConcurrentRequests(20).setBulkActions(100)
//                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB)).setFlushInterval(TimeValue.timeValueSeconds(10));
//        return builder.build();
//
//        IndexRequestBuilder indexRequestBuilder = client.prepareIndex(indexName, esIndexType);
//        indexRequestBuilder.setSource(JSON.toJSONString(record));
//
//        bulkProcessor.add(indexRequestBuilder.request());
//
//
//    }
//
//
//    @Test
//    public void tool() {
//
//        // 统计各个索引的容量
//        IndicesStatsResponse response = client.admin().indices().prepareStats().execute().actionGet(CLIENT_TO_WITH_MILLS);
//        for (Map.Entry<String, IndexStats> entry : indexStatsMap.entrySet()) {
//            // 索引名
//            String index = entry.getKey();
//            CommonStats totalInfo = entry.getValue().getTotal();
//            StoreStats storeStats = totalInfo.getStore();
//        }
//
//
//
//        SearchResponse scrollResp = client.
//                prepareSearch(indice + "*")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(boolQueryBuilder)
//                .setScroll(new TimeValue(600000))
//                .addSort(SortBuilders.fieldSort("_doc"))
//                .addFields("appid").addFields("|indices").addFields("indices")
//                .setSize(10000)
//                .execute()
//                .actionGet();
//        scrollResp.getHits().getHits()
//        SearchHitField searchHitField = n.getFields().get("appid").getValue();
//
//
//
//
//
//        PutIndexTemplateRequestBuilder requestBuilder = client.admin().indices().preparePutTemplate(templatename).setOrder(order)
//                // 设置order
//                .setTemplate(contentmap.get("template").toString())
//                // 设置匹配template模式
//                .setSettings((Map<String, Object>) objSettings)
//                // 设置settings
//                .setAliases((Map) aliasSettings);
//        // 设置aliase
//            requestBuilder.addMapping(key, (Map<String, Object>) mappings.get(key));
//        PutIndexTemplateResponse response = requestBuilder.execute().actionGet();
//        if (!response.isAcknowledged()) {
//
//
//
//
//
//
//
//
//    }
}
