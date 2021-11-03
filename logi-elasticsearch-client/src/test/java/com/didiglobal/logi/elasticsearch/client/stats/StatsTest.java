package com.didiglobal.logi.elasticsearch.client.stats;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.request.index.stats.IndicesStatsLevel;
import com.didiglobal.logi.elasticsearch.client.response.cluster.ESClusterHealthResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.nodes.ClusterNodeInfo;
import com.didiglobal.logi.elasticsearch.client.response.cluster.nodes.ESClusterNodesResponse;
import com.didiglobal.logi.elasticsearch.client.response.cluster.nodesstats.ESClusterNodesStatsResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.searchshards.ESIndicesSearchShardsResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.stats.ESIndicesStatsResponse;
import com.didiglobal.logi.elasticsearch.client.response.model.http.HttpInfo;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import static com.didiglobal.logi.elasticsearch.client.TestUtils.client;
import static com.didiglobal.logi.elasticsearch.client.TestUtils.index;

public class StatsTest {

    @Test
    public void indicesStat() throws Exception {
        try {
            client.admin().indices().preparePutIndex(index).execute().get().getAcknowledged();
        } catch (Throwable t) {
        }

        ESIndicesStatsResponse response = client.admin().indices().prepareStats(index).setLevel(IndicesStatsLevel.SHARDS).execute().get();
        System.out.println(response);

        assert client.admin().indices().prepareDeleteIndex(index).execute().get().getAcknowledged();
    }


    @Test
    public void indicesSearchShards() throws Exception {
        try {
            client.admin().indices().preparePutIndex(index).execute().get().getAcknowledged();
        } catch (Throwable t) {
        }

        ESIndicesSearchShardsResponse response = client.admin().indices().prepareSearchShards(index).execute().get();
        System.out.println(response);

        assert client.admin().indices().prepareDeleteIndex(index).execute().get().getAcknowledged();
    }


    @Test
    public void healthCluster() throws Exception {
        ESClusterHealthResponse response = client.admin().cluster().prepareHealth().execute().get();
        System.out.println(response);
    }

    @Test
    public void clusterNodeStats() throws Exception {
        ESClusterNodesStatsResponse response = client.admin().cluster().prepareNodeStats().level("shards").execute().get();
        System.out.println(response);
    }

    @Test
    public void clusterNodes() throws Exception {
        ESClusterNodesResponse resp = client.admin().cluster().prepareNodes().addFlag("http").execute().get();
        System.out.println(JSON.toJSONString(resp));

        String cluster = "hello";

        if (resp == null) {
            throw new Exception("get node info get null, cluster:" + cluster);
        }

        Map<String, ClusterNodeInfo> clusterNodeInfoMap = resp.getNodes();
        if (clusterNodeInfoMap == null) {
            throw new Exception("get node info clusterNodeInfo is null, cluster:" + cluster);
        }

        Map<String, Integer> ret = new HashMap<String, Integer>();
        for (String nodeName : clusterNodeInfoMap.keySet()) {
            ClusterNodeInfo clusterNodeInfo = clusterNodeInfoMap.get(nodeName);

            if (clusterNodeInfo == null) {
                throw new Exception("clusterNodeInfo is null, cluster:" + cluster + ", node:" + nodeName);
            }

            HttpInfo httpInfo = clusterNodeInfo.getHttpInfo();
            if (httpInfo == null) {
                throw new Exception("http info is null, cluster:" + cluster + ", node:" + nodeName);
            }

            String publishAddress = httpInfo.getPublishAddress();
            if (publishAddress == null) {
                throw new Exception("publish address is null, cluster:" + cluster + ", node:" + nodeName);
            }

            try {
                int indexStart = publishAddress.indexOf(":");
                int indexEnd = publishAddress.indexOf(",");
                if (indexEnd < 0) {
                    indexEnd = publishAddress.length();
                }

                int port = Integer.valueOf(publishAddress.substring(indexStart + 1, indexEnd));
                ret.put(nodeName, port);
            } catch (Throwable t) {
                throw new Exception("get port error, cluster:" + cluster + ", node:" + nodeName + ", publishAddress" + publishAddress);
            }
        }
    }


    @Test
    public void test() throws Exception {
        System.out.println(LocalDateTime.of(1970, 1, 1, 0, 0, 0,
                0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

    }
}

