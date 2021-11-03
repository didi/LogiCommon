package com.didiglobal.logi.elasticsearch.client.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.model.type.ESVersion;
import com.didiglobal.logi.elasticsearch.client.response.setting.common.MappingConfig;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MappingConfigTest {

    @Test
    public void delType() throws Exception {
        String str = "{\n" +
                "  \"doc\": {\n" +
                "    \"properties\": {\n" +
                "      \"ccr_auto_follow_stats\": {\n" +
                "        \"properties\": {\n" +
                "          \"recent_auto_follow_errors\": {\n" +
                "            \"type\": \"nested\",\n" +
                "            \"properties\": {\n" +
                "              \"auto_follow_exception\": {\n" +
                "                \"properties\": {\n" +
                "                  \"reason\": {\n" +
                "                    \"type\": \"text\"\n" +
                "                  },\n" +
                "                  \"type\": {\n" +
                "                    \"type\": \"keyword\"\n" +
                "                  }\n" +
                "                }\n" +
                "              },\n" +
                "              \"leader_index\": {\n" +
                "                \"type\": \"keyword\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"number_of_failed_follow_indices\": {\n" +
                "            \"type\": \"long\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"number_of_failed_follow_indices\": {\n" +
                "        \"type\": \"long\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        JSONObject obj = JSON.parseObject(str);
        MappingConfig mappingConfig = new MappingConfig(obj);
        assert obj.equals(mappingConfig.toJson());
        assertEquals(mappingConfig.toJson(ESVersion.ES760).toJSONString(), "{\"properties\":{\"ccr_auto_follow_stats\":{\"properties\":{\"recent_auto_follow_errors\":{\"type\":\"nested\",\"properties\":{\"auto_follow_exception\":{\"properties\":{\"reason\":{\"type\":\"text\"},\"type\":{\"type\":\"keyword\"}}},\"leader_index\":{\"type\":\"keyword\"}}},\"number_of_failed_follow_indices\":{\"type\":\"long\"}}},\"number_of_failed_follow_indices\":{\"type\":\"long\"}}}");

        List<String> fields;
        fields = new ArrayList<>();
        fields.add("number_of_failed_follow_indices");
        mappingConfig.delFields(fields);

        fields = new ArrayList<>();
        fields.add("ccr_auto_follow_stats");
        fields.add("number_of_failed_follow_indices");
        mappingConfig.delFields(fields);


        str = "{\n" +
                "  \"doc\": {\n" +
                "    \"properties\": {\n" +
                "      \"ccr_auto_follow_stats\": {\n" +
                "        \"properties\": {\n" +
                "          \"recent_auto_follow_errors\": {\n" +
                "            \"type\": \"nested\",\n" +
                "            \"properties\": {\n" +
                "              \"auto_follow_exception\": {\n" +
                "                \"properties\": {\n" +
                "                  \"reason\": {\n" +
                "                    \"type\": \"text\"\n" +
                "                  },\n" +
                "                  \"type\": {\n" +
                "                    \"type\": \"keyword\"\n" +
                "                  }\n" +
                "                }\n" +
                "              },\n" +
                "              \"leader_index\": {\n" +
                "                \"type\": \"keyword\"\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        assert JSON.parseObject(str).equals(mappingConfig.toJson());
        assertEquals(mappingConfig.toJson(ESVersion.ES760), JSON.parseObject("{\n" +
                "  \"properties\": {\n" +
                "    \"ccr_auto_follow_stats\": {\n" +
                "      \"properties\": {\n" +
                "        \"recent_auto_follow_errors\": {\n" +
                "          \"type\": \"nested\",\n" +
                "          \"properties\": {\n" +
                "            \"auto_follow_exception\": {\n" +
                "              \"properties\": {\n" +
                "                \"reason\": {\n" +
                "                  \"type\": \"text\"\n" +
                "                },\n" +
                "                \"type\": {\n" +
                "                  \"type\": \"keyword\"\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"leader_index\": {\n" +
                "              \"type\": \"keyword\"\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}"));

        fields = new ArrayList<>();
        fields.add("ccr_auto_follow_stats");
        fields.add("recent_auto_follow_errors");
        fields.add("auto_follow_exception");
        mappingConfig.delFields(fields);
        assert JSON.parseObject(str).equals(mappingConfig.toJson());
        assertEquals(mappingConfig.toJson(ESVersion.ES760), JSON.parseObject("{\n" +
                "  \"properties\": {\n" +
                "    \"ccr_auto_follow_stats\": {\n" +
                "      \"properties\": {\n" +
                "        \"recent_auto_follow_errors\": {\n" +
                "          \"type\": \"nested\",\n" +
                "          \"properties\": {\n" +
                "            \"auto_follow_exception\": {\n" +
                "              \"properties\": {\n" +
                "                \"reason\": {\n" +
                "                  \"type\": \"text\"\n" +
                "                },\n" +
                "                \"type\": {\n" +
                "                  \"type\": \"keyword\"\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"leader_index\": {\n" +
                "              \"type\": \"keyword\"\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}"));

        str = "{\n" +
                "  \"doc\": {\n" +
                "    \"properties\": {\n" +
                "      \"ccr_auto_follow_stats\": {\n" +
                "        \"properties\": {\n" +
                "          \"recent_auto_follow_errors\": {\n" +
                "            \"properties\": {\n" +
                "              \"auto_follow_exception\": {\n" +
                "                \"properties\": {\n" +
                "                  \"reason\": {\n" +
                "                    \"type\": \"text\"\n" +
                "                  },\n" +
                "                  \"type\": {\n" +
                "                    \"type\": \"keyword\"\n" +
                "                  }\n" +
                "                }\n" +
                "              },\n" +
                "              \"leader_index\": {\n" +
                "                \"type\": \"keyword\"\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        fields = new ArrayList<>();
        fields.add("ccr_auto_follow_stats");
        fields.add("recent_auto_follow_errors");
        mappingConfig.delFields(fields);
        assert JSON.parseObject(str).equals(mappingConfig.toJson());
        assertEquals(mappingConfig.toJson(ESVersion.ES760), JSON.parseObject("{\n" +
                "  \"properties\": {\n" +
                "    \"ccr_auto_follow_stats\": {\n" +
                "      \"properties\": {\n" +
                "        \"recent_auto_follow_errors\": {\n" +
                "          \"properties\": {\n" +
                "            \"auto_follow_exception\": {\n" +
                "              \"properties\": {\n" +
                "                \"reason\": {\n" +
                "                  \"type\": \"text\"\n" +
                "                },\n" +
                "                \"type\": {\n" +
                "                  \"type\": \"keyword\"\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"leader_index\": {\n" +
                "              \"type\": \"keyword\"\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}"));


        str = "{\n" +
                "  \"doc\": {\n" +
                "    \"properties\": {\n" +
                "      \"ccr_auto_follow_stats\": {\n" +
                "        \"properties\": {\n" +
                "          \"recent_auto_follow_errors\": {\n" +
                "            \"properties\": {\n" +
                "              \"leader_index\": {\n" +
                "                \"type\": \"keyword\"\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        fields = new ArrayList<>();
        fields.add("ccr_auto_follow_stats");
        fields.add("recent_auto_follow_errors");
        fields.add("auto_follow_exception");
        fields.add("reason");
        mappingConfig.delFields(fields);

        fields = new ArrayList<>();
        fields.add("ccr_auto_follow_stats");
        fields.add("recent_auto_follow_errors");
        fields.add("auto_follow_exception");
        fields.add("type");
        mappingConfig.delFields(fields);
        assert JSON.parseObject(str).equals(mappingConfig.toJson());


        str = "{\"doc\":{\"properties\":{}}}";

        fields = new ArrayList<>();
        fields.add("ccr_auto_follow_stats");
        fields.add("recent_auto_follow_errors");
        fields.add("leader_index");
        mappingConfig.delFields(fields);
        System.out.println(mappingConfig.toJson().toJSONString());
        assert JSON.parseObject(str).equals(mappingConfig.toJson());
    }


    @Test
    public void test() throws Exception {
        MappingConfig mappingConfig;
        mappingConfig = new MappingConfig(JSON.parseObject(mapping_STR));
        assert check(mappingConfig.toJson(), JSON.parseObject(mapping_STR));

        mappingConfig = new MappingConfig(JSON.parseObject(test1));
        assert check(mappingConfig.toJson(), JSON.parseObject(test1));

        {
            mappingConfig = new MappingConfig(JSON.parseObject(test1));
            assertEquals(mappingConfig.toJson(ESVersion.ES760).toJSONString(), "{\"numeric_detection\":true,\"properties\":{\"ariusCreateTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"selectFields\":{\"ignore_above\":512,\"type\":\"keyword\"},\"_arius_query_request_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"totalHitsList\":{\"type\":\"long\"},\"_arius_query_response_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"responseLen\":{\"type\":\"long\"},\"beforeCost\":{\"type\":\"long\"},\"isTimedOut\":{\"type\":\"keyword\"},\"_arius_query_tcp_exception_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"dslTemplate\":{\"ignore_above\":1024,\"type\":\"keyword\"},\"fetchCost\":{\"type\":\"long\"},\"_arius_query_tcp_response_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"successShardsList\":{\"type\":\"long\"},\"action\":{\"type\":\"keyword\"},\"_arius_query_request_indices_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"indiceCount\":{\"type\":\"long\"},\"_arius_query_search_response_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"internalCost\":{\"type\":\"long\"},\"_arius_query_tcp_search_response_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"orderByFields\":{\"ignore_above\":512,\"type\":\"keyword\"},\"_arius_fetch_message_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"method\":{\"type\":\"keyword\"},\"totalShardsList\":{\"type\":\"long\"},\"isTimeoutList\":{\"type\":\"keyword\"},\"searchType\":{\"type\":\"keyword\"},\"_arius_query_request_before_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"dltag\":{\"type\":\"keyword\"},\"_arius_query_request_before_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"index\":{\"ignore_above\":512,\"type\":\"keyword\"},\"whereFields\":{\"ignore_above\":512,\"type\":\"keyword\"},\"_arius_query_request_node_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"queryString\":{\"type\":\"keyword\"},\"_arius_query_response_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"_arius_query_tcp_request_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"cleanTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"_arius_query_tcp_response_length_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"dslLen\":{\"type\":\"long\"},\"totalHits\":{\"type\":\"long\"},\"_arius_query_request_node_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"dslTag\":{\"ignore_above\":512,\"type\":\"keyword\"},\"_arius_query_request_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"memUsed\":{\"type\":\"long\"},\"bucketNumber\":{\"type\":\"long\"},\"dsl\":{\"ignore_above\":1024,\"type\":\"keyword\"},\"totalCost\":{\"type\":\"long\"},\"flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"status\":{\"type\":\"long\"},\"_arius_query_tcp_search_request_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"groupByFields\":{\"ignore_above\":512,\"type\":\"keyword\"},\"dslLevel\":{\"type\":\"long\"},\"failedShardsList\":{\"type\":\"long\"},\"totalShards\":{\"type\":\"long\"},\"failedShards\":{\"type\":\"long\"},\"scrollId\":{\"type\":\"keyword\"},\"dslType\":{\"type\":\"keyword\"},\"routing\":{\"type\":\"keyword\"},\"_arius_query_request_indices_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"_arius_query_tcp_response_length_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"newDslTemplate\":{\"ignore_above\":512,\"type\":\"keyword\"},\"esCost\":{\"type\":\"long\"},\"aggsLevel\":{\"type\":\"long\"},\"requestId\":{\"type\":\"keyword\"},\"sinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"_arius_query_tcp_request_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"_arius_query_tcp_exception_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"clientNode\":{\"type\":\"keyword\"},\"remoteAddr\":{\"type\":\"keyword\"},\"collectTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"lastBucketNumber\":{\"type\":\"long\"},\"_arius_query_tcp_search_scroll_request_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"types\":{\"type\":\"keyword\"},\"requestType\":{\"type\":\"keyword\"},\"fetchSize\":{\"type\":\"long\"},\"dltagCount\":{\"type\":\"long\"},\"esCostList\":{\"type\":\"long\"},\"successfulShards\":{\"type\":\"long\"},\"fetchMetric\":{\"properties\":{\"parseMessageCost\":{\"type\":\"long\"},\"fetchMissingCnt\":{\"type\":\"long\"},\"getConsumeCost\":{\"type\":\"long\"},\"fetchOffsetCnt\":{\"type\":\"long\"},\"brokerHost\":{\"type\":\"keyword\"},\"fetchMetricInfo\":{\"properties\":{\"cost\":{\"type\":\"long\"},\"partition\":{\"type\":\"long\"},\"count\":{\"type\":\"long\"},\"topic\":{\"ignore_above\":512,\"type\":\"keyword\"}}},\"partitionTotalCost\":{\"type\":\"long\"}}},\"gatewayNode\":{\"type\":\"keyword\"},\"userName\":{\"ignore_above\":512,\"type\":\"keyword\"},\"_arius_query_tcp_search_request_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"_arius_fetch_message_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"_arius_query_tcp_search_scroll_request_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"indiceSample\":{\"type\":\"keyword\"},\"logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"timeStamp\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"indices\":{\"ignore_above\":1024,\"type\":\"keyword\"},\"dslTemplateMd5\":{\"type\":\"keyword\"},\"scrollIdList\":{\"type\":\"keyword\"},\"_arius_query_tcp_response_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"_arius_query_tcp_search_response_flinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"appid\":{\"type\":\"long\"},\"_arius_query_search_response_logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"}}}");
        }
    }

    private boolean check(JSONObject o1, JSONObject o2) {
        if (o1.equals(o2)) {
            System.out.println("ok");
            return true;
        } else {
            System.out.println("in:" + o1.toJSONString());
            System.out.println("ou:" + o2.toJSONString());
            return false;
        }
    }

    @Test
    public void checkHighVersionMapping() throws Exception {
        {
            String mappings = "{\n" +
                    "  \"evejt\": {\n" +
                    "    \"dynamic\": \"false\",\n" +
                    "    \"_all\": {\n" +
                    "      \"enabled\": true,\n" +
                    "      \"omit_norms\": true\n" +
                    "    },\n" +
                    "    \"_ttl\": {\n" +
                    "      \"enabled\": true\n" +
                    "    },\n" +
                    "    \"dynamic_date_formats\": [\n" +
                    "      \"strict_date_optional_time\",\n" +
                    "      \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
                    "    ],\n" +
                    "    \"dynamic_templates\": [\n" +
                    "      {\n" +
                    "        \"label_long\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"type\": \"long\"\n" +
                    "          },\n" +
                    "          \"path_match\": \"label.long.*\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"label_float\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"type\": \"float\"\n" +
                    "          },\n" +
                    "          \"path_match\": \"label.float.*\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"label_string\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"fields\": {\n" +
                    "              \"raw\": {\n" +
                    "                \"ignore_above\": 256,\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              }\n" +
                    "            },\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"path_match\": \"label.string.*\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"traceid_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"traceid\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"request_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"request\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"orderId_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"orderId\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"json_annotations_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"type\": \"nested\"\n" +
                    "          },\n" +
                    "          \"match\": \"json_annotations\",\n" +
                    "          \"match_mapping_type\": \"object\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"body_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"analyzed\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"body\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"out_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"out\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"thriftRpcCount_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"type\": \"integer\"\n" +
                    "          },\n" +
                    "          \"match\": \"thriftRpcCount\",\n" +
                    "          \"match_mapping_type\": \"*\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"jsonMsg_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"type\": \"object\"\n" +
                    "          },\n" +
                    "          \"match\": \"jsonMsg\",\n" +
                    "          \"match_mapping_type\": \"object\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"rpcTotal_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"type\": \"integer\"\n" +
                    "          },\n" +
                    "          \"match\": \"rpcTotal\",\n" +
                    "          \"match_mapping_type\": \"*\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"sink_flag_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"sink_flag\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"httpRpcCount_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"type\": \"integer\"\n" +
                    "          },\n" +
                    "          \"match\": \"httpRpcCount\",\n" +
                    "          \"match_mapping_type\": \"*\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"in_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"in\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"errmsg_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"errmsg\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"message_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"message\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"busiPayments_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"busiPayments\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"args_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"args\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"logID_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"logID\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"projectName_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"projectName\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"proc_time_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"type\": \"double\"\n" +
                    "          },\n" +
                    "          \"match\": \"proc_time\",\n" +
                    "          \"match_mapping_type\": \"*\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"longToboolean_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"type\": \"boolean\"\n" +
                    "          },\n" +
                    "          \"match\": \"is*\",\n" +
                    "          \"match_mapping_type\": \"long\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"longToboolean_fields1\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"type\": \"boolean\"\n" +
                    "          },\n" +
                    "          \"match\": \"enableDebugMode\",\n" +
                    "          \"match_mapping_type\": \"long\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"stringno_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"response*\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"stringno1_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"req*\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"stringno2_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"args*\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"stringno3_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"no\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"errmsg*\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"string_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"ignore_above\": \"256\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"*\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"object_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"dynamic\": false,\n" +
                    "            \"type\": \"object\",\n" +
                    "            \"enabled\": false\n" +
                    "          },\n" +
                    "          \"match\": \"*\",\n" +
                    "          \"match_mapping_type\": \"object\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"extractLevel_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"extractLevel\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"timestampField\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
                    "            \"type\": \"date\"\n" +
                    "          },\n" +
                    "          \"match\": \"*imestamp*\",\n" +
                    "          \"match_mapping_type\": \"long\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"logTimeField\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
                    "            \"type\": \"date\"\n" +
                    "          },\n" +
                    "          \"match\": \"logTime\",\n" +
                    "          \"match_mapping_type\": \"long\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"sinkTimeField\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
                    "            \"type\": \"date\"\n" +
                    "          },\n" +
                    "          \"match\": \"sinkTime\",\n" +
                    "          \"match_mapping_type\": \"long\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"extract_level_fields\": {\n" +
                    "          \"mapping\": {\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"match\": \"extract_level\",\n" +
                    "          \"match_mapping_type\": \"string\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"numeric_detection\": true,\n" +
                    "    \"properties\": {\n" +
                    "      \"agent_start\": {\n" +
                    "        \"type\": \"date\",\n" +
                    "        \"format\": \"strict_date_optional_time||epoch_millis\"\n" +
                    "      },\n" +
                    "      \"aggregation_count\": {\n" +
                    "        \"type\": \"long\"\n" +
                    "      },\n" +
                    "      \"category\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"index\": \"not_analyzed\"\n" +
                    "      },\n" +
                    "      \"class\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"index\": \"not_analyzed\"\n" +
                    "      },\n" +
                    "      \"cleanTime\": {\n" +
                    "        \"type\": \"date\",\n" +
                    "        \"fielddata\": {\n" +
                    "          \"loading\": \"eager\"\n" +
                    "        },\n" +
                    "        \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
                    "      },\n" +
                    "      \"client\": {\n" +
                    "        \"properties\": {\n" +
                    "          \"app_ver\": {\n" +
                    "            \"properties\": {\n" +
                    "              \"major\": {\n" +
                    "                \"type\": \"integer\"\n" +
                    "              },\n" +
                    "              \"minor\": {\n" +
                    "                \"type\": \"integer\"\n" +
                    "              },\n" +
                    "              \"patch\": {\n" +
                    "                \"type\": \"integer\"\n" +
                    "              }\n" +
                    "            }\n" +
                    "          },\n" +
                    "          \"app_version\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"fields\": {\n" +
                    "              \"raw\": {\n" +
                    "                \"type\": \"string\",\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"ignore_above\": 256\n" +
                    "              }\n" +
                    "            }\n" +
                    "          },\n" +
                    "          \"content_ver\": {\n" +
                    "            \"properties\": {\n" +
                    "              \"major\": {\n" +
                    "                \"type\": \"integer\"\n" +
                    "              },\n" +
                    "              \"minor\": {\n" +
                    "                \"type\": \"integer\"\n" +
                    "              },\n" +
                    "              \"patch\": {\n" +
                    "                \"type\": \"integer\"\n" +
                    "              }\n" +
                    "            }\n" +
                    "          },\n" +
                    "          \"content_version\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"fields\": {\n" +
                    "              \"raw\": {\n" +
                    "                \"type\": \"string\",\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"ignore_above\": 256\n" +
                    "              }\n" +
                    "            }\n" +
                    "          },\n" +
                    "          \"e_workplaceid\": {\n" +
                    "            \"type\": \"long\"\n" +
                    "          },\n" +
                    "          \"id\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"fields\": {\n" +
                    "              \"raw\": {\n" +
                    "                \"type\": \"string\",\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"ignore_above\": 256\n" +
                    "              }\n" +
                    "            }\n" +
                    "          },\n" +
                    "          \"name\": {\n" +
                    "            \"ignore_above\": 256,\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"analyzed\"\n" +
                    "          },\n" +
                    "          \"platform\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"sensor\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"fields\": {\n" +
                    "              \"raw\": {\n" +
                    "                \"type\": \"string\",\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"ignore_above\": 256\n" +
                    "              }\n" +
                    "            }\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"collectTime\": {\n" +
                    "        \"type\": \"date\",\n" +
                    "        \"fielddata\": {\n" +
                    "          \"loading\": \"eager\"\n" +
                    "        },\n" +
                    "        \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
                    "      },\n" +
                    "      \"e_geoip\": {\n" +
                    "        \"properties\": {\n" +
                    "          \"asn\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"asn_code\": {\n" +
                    "            \"type\": \"integer\"\n" +
                    "          },\n" +
                    "          \"city\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"connection_type\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"continent\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"country\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"dma_code\": {\n" +
                    "            \"type\": \"integer\"\n" +
                    "          },\n" +
                    "          \"domain\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"ip\": {\n" +
                    "            \"type\": \"ip\"\n" +
                    "          },\n" +
                    "          \"location\": {\n" +
                    "            \"type\": \"geo_point\"\n" +
                    "          },\n" +
                    "          \"postal_code\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"region\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"region_code\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"timezone\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"timezone_raw\": {\n" +
                    "            \"type\": \"integer\"\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"e_id_flag\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"fields\": {\n" +
                    "          \"raw\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 256\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"e_id_gap\": {\n" +
                    "        \"type\": \"long\"\n" +
                    "      },\n" +
                    "      \"e_isvpn\": {\n" +
                    "        \"type\": \"boolean\"\n" +
                    "      },\n" +
                    "      \"e_receive_delay\": {\n" +
                    "        \"type\": \"long\"\n" +
                    "      },\n" +
                    "      \"e_time_gap\": {\n" +
                    "        \"type\": \"long\"\n" +
                    "      },\n" +
                    "      \"e_token_age\": {\n" +
                    "        \"type\": \"float\"\n" +
                    "      },\n" +
                    "      \"events_id\": {\n" +
                    "        \"type\": \"long\"\n" +
                    "      },\n" +
                    "      \"eventtime\": {\n" +
                    "        \"type\": \"date\",\n" +
                    "        \"fielddata\": {\n" +
                    "          \"loading\": \"eager\"\n" +
                    "        },\n" +
                    "        \"format\": \"strict_date_optional_time||epoch_millis\"\n" +
                    "      },\n" +
                    "      \"eventtimestring\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"fields\": {\n" +
                    "          \"raw\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 256\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"id\": {\n" +
                    "        \"type\": \"long\"\n" +
                    "      },\n" +
                    "      \"label\": {\n" +
                    "        \"dynamic\": \"false\",\n" +
                    "        \"properties\": {\n" +
                    "          \"float\": {\n" +
                    "            \"type\": \"object\",\n" +
                    "            \"dynamic\": \"true\"\n" +
                    "          },\n" +
                    "          \"long\": {\n" +
                    "            \"type\": \"object\",\n" +
                    "            \"dynamic\": \"true\"\n" +
                    "          },\n" +
                    "          \"string\": {\n" +
                    "            \"type\": \"object\",\n" +
                    "            \"dynamic\": \"true\"\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"logTime\": {\n" +
                    "        \"type\": \"date\",\n" +
                    "        \"fielddata\": {\n" +
                    "          \"loading\": \"eager\"\n" +
                    "        },\n" +
                    "        \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
                    "      },\n" +
                    "      \"m_batch_flag\": {\n" +
                    "        \"type\": \"integer\"\n" +
                    "      },\n" +
                    "      \"m_child_id\": {\n" +
                    "        \"type\": \"integer\"\n" +
                    "      },\n" +
                    "      \"m_errors\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"fields\": {\n" +
                    "          \"raw\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 1024\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"m_headers\": {\n" +
                    "        \"properties\": {\n" +
                    "          \"all\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"fields\": {\n" +
                    "              \"raw\": {\n" +
                    "                \"type\": \"string\",\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"ignore_above\": 1024\n" +
                    "              }\n" +
                    "            }\n" +
                    "          },\n" +
                    "          \"content_length\": {\n" +
                    "            \"type\": \"long\"\n" +
                    "          },\n" +
                    "          \"user_agent\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"fields\": {\n" +
                    "              \"raw\": {\n" +
                    "                \"type\": \"string\",\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"ignore_above\": 256\n" +
                    "              }\n" +
                    "            }\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"m_host\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"fields\": {\n" +
                    "          \"raw\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 256\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"m_parent_msg_id\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"fields\": {\n" +
                    "          \"raw\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 256\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"m_partition\": {\n" +
                    "        \"type\": \"integer\"\n" +
                    "      },\n" +
                    "      \"m_raw\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"fields\": {\n" +
                    "          \"raw\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 256\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"m_rule_ver\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"fields\": {\n" +
                    "          \"raw\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 256\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"m_tags\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"fields\": {\n" +
                    "          \"raw\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 256\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"m_token\": {\n" +
                    "        \"properties\": {\n" +
                    "          \"renewtime\": {\n" +
                    "            \"type\": \"date\",\n" +
                    "            \"format\": \"strict_date_optional_time||epoch_millis\"\n" +
                    "          },\n" +
                    "          \"sub\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"m_topic\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"fields\": {\n" +
                    "          \"raw\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 256\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"ErrCode\": {\n" +
                    "        \"ignore_above\": 2048,\n" +
                    "        \"type\": \"string\"\n" +
                    "      },\n" +
                    "      \"msg_id\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"fields\": {\n" +
                    "          \"raw\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 256\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"receivetime\": {\n" +
                    "        \"type\": \"date\",\n" +
                    "        \"format\": \"strict_date_optional_time||epoch_millis\"\n" +
                    "      },\n" +
                    "      \"sendtime\": {\n" +
                    "        \"type\": \"date\",\n" +
                    "        \"format\": \"strict_date_optional_time||epoch_millis\"\n" +
                    "      },\n" +
                    "      \"sinkTime\": {\n" +
                    "        \"type\": \"date\",\n" +
                    "        \"fielddata\": {\n" +
                    "          \"loading\": \"eager\"\n" +
                    "        },\n" +
                    "        \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
                    "      },\n" +
                    "      \"type\": {\n" +
                    "        \"type\": \"string\",\n" +
                    "        \"fields\": {\n" +
                    "          \"raw\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 256\n" +
                    "          }\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";

            MappingConfig mappingConfig = new MappingConfig(JSON.parseObject(mappings));
            JSONObject newMappings = mappingConfig.toJson(ESVersion.ES651);
            assertEquals(newMappings.toJSONString(), "{\"evejt\":{\"numeric_detection\":true,\"dynamic\":\"false\",\"properties\":{\"m_partition\":{\"type\":\"integer\"},\"m_rule_ver\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"agent_start\":{\"format\":\"strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"eventtime\":{\"format\":\"strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"type\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"m_host\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"e_geoip\":{\"properties\":{\"continent\":{\"type\":\"keyword\"},\"country\":{\"type\":\"keyword\"},\"connection_type\":{\"type\":\"keyword\"},\"city\":{\"type\":\"keyword\"},\"timezone\":{\"type\":\"keyword\"},\"ip\":{\"type\":\"ip\"},\"timezone_raw\":{\"type\":\"integer\"},\"asn_code\":{\"type\":\"integer\"},\"domain\":{\"type\":\"keyword\"},\"dma_code\":{\"type\":\"integer\"},\"location\":{\"type\":\"geo_point\"},\"postal_code\":{\"type\":\"keyword\"},\"region\":{\"type\":\"keyword\"},\"asn\":{\"type\":\"keyword\"},\"region_code\":{\"type\":\"keyword\"}}},\"sinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"m_errors\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":1024,\"type\":\"keyword\"}}},\"client\":{\"properties\":{\"e_workplaceid\":{\"type\":\"long\"},\"content_version\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"app_version\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"app_ver\":{\"properties\":{\"patch\":{\"type\":\"integer\"},\"major\":{\"type\":\"integer\"},\"minor\":{\"type\":\"integer\"}}},\"name\":{\"type\":\"text\"},\"sensor\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"id\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"platform\":{\"type\":\"keyword\"},\"content_ver\":{\"properties\":{\"patch\":{\"type\":\"integer\"},\"major\":{\"type\":\"integer\"},\"minor\":{\"type\":\"integer\"}}}}},\"m_tags\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"id\":{\"type\":\"long\"},\"msg_id\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"class\":{\"type\":\"keyword\"},\"m_token\":{\"properties\":{\"renewtime\":{\"format\":\"strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"sub\":{\"type\":\"keyword\"}}},\"receivetime\":{\"format\":\"strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"e_isvpn\":{\"type\":\"boolean\"},\"collectTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"m_batch_flag\":{\"type\":\"integer\"},\"m_child_id\":{\"type\":\"integer\"},\"m_topic\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"aggregation_count\":{\"type\":\"long\"},\"label\":{\"dynamic\":\"false\",\"properties\":{\"string\":{\"dynamic\":\"true\",\"type\":\"object\"},\"float\":{\"dynamic\":\"true\",\"type\":\"object\"},\"long\":{\"dynamic\":\"true\",\"type\":\"object\"}}},\"events_id\":{\"type\":\"long\"},\"e_id_gap\":{\"type\":\"long\"},\"m_parent_msg_id\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"m_headers\":{\"properties\":{\"all\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":1024,\"type\":\"keyword\"}}},\"content_length\":{\"type\":\"long\"},\"user_agent\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}}}},\"e_time_gap\":{\"type\":\"long\"},\"eventtimestring\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"ErrCode\":{\"type\":\"text\"},\"e_token_age\":{\"type\":\"float\"},\"cleanTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"e_receive_delay\":{\"type\":\"long\"},\"m_raw\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"e_id_flag\":{\"type\":\"text\",\"fields\":{\"raw\":{\"ignore_above\":256,\"type\":\"keyword\"}}},\"category\":{\"type\":\"keyword\"},\"sendtime\":{\"format\":\"strict_date_optional_time||epoch_millis\",\"type\":\"date\"}}}}");
        }

        {
            String mappings = "{\n" +
                    "      \"_default_\": {\n" +
                    "        \"_all\": {\n" +
                    "          \"enabled\": false\n" +
                    "        },\n" +
                    "        \"dynamic_date_formats\": [\n" +
                    "          \"strict_date_optional_time\",\n" +
                    "          \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
                    "        ],\n" +
                    "        \"dynamic_templates\": [\n" +
                    "          {\n" +
                    "            \"traceid_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"traceid\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"request_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"request\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"orderId_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"orderId\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"json_annotations_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"nested\"\n" +
                    "              },\n" +
                    "              \"match\": \"json_annotations\",\n" +
                    "              \"match_mapping_type\": \"object\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"body_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"analyzed\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"body\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"out_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"out\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"thriftRpcCount_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"integer\"\n" +
                    "              },\n" +
                    "              \"match\": \"thriftRpcCount\",\n" +
                    "              \"match_mapping_type\": \"*\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"jsonMsg_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"object\"\n" +
                    "              },\n" +
                    "              \"match\": \"jsonMsg\",\n" +
                    "              \"match_mapping_type\": \"object\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"rpcTotal_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"integer\"\n" +
                    "              },\n" +
                    "              \"match\": \"rpcTotal\",\n" +
                    "              \"match_mapping_type\": \"*\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"sink_flag_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"sink_flag\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"httpRpcCount_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"integer\"\n" +
                    "              },\n" +
                    "              \"match\": \"httpRpcCount\",\n" +
                    "              \"match_mapping_type\": \"*\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"in_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"in\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"errmsg_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"errmsg\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"message_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"message\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"busiPayments_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"busiPayments\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"args_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"args\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"logID_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"logID\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"projectName_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"projectName\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"proc_time_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"double\"\n" +
                    "              },\n" +
                    "              \"match\": \"proc_time\",\n" +
                    "              \"match_mapping_type\": \"*\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"longToboolean_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"boolean\"\n" +
                    "              },\n" +
                    "              \"match\": \"is*\",\n" +
                    "              \"match_mapping_type\": \"long\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"longToboolean_fields1\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"boolean\"\n" +
                    "              },\n" +
                    "              \"match\": \"enableDebugMode\",\n" +
                    "              \"match_mapping_type\": \"long\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"string_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"ignore_above\": 512,\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"*\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"stringno_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"response*\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"stringno1_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"req*\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"stringno2_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"args*\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"stringno3_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"errmsg*\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"appName_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"originalAppName\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"extractLevel_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"extractLevel\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          }\n" +
                    "        ],\n" +
                    "        \"properties\": {\n" +
                    "          \"cleanTime\": {\n" +
                    "            \"type\": \"date\",\n" +
                    "            \"fielddata\": {\n" +
                    "              \"loading\": \"eager\"\n" +
                    "            },\n" +
                    "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
                    "          },\n" +
                    "          \"collectTime\": {\n" +
                    "            \"type\": \"date\",\n" +
                    "            \"fielddata\": {\n" +
                    "              \"loading\": \"eager\"\n" +
                    "            },\n" +
                    "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
                    "          },\n" +
                    "          \"logTime\": {\n" +
                    "            \"type\": \"date\",\n" +
                    "            \"fielddata\": {\n" +
                    "              \"loading\": \"eager\"\n" +
                    "            },\n" +
                    "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
                    "          },\n" +
                    "          \"sinkTime\": {\n" +
                    "            \"type\": \"date\",\n" +
                    "            \"fielddata\": {\n" +
                    "              \"loading\": \"eager\"\n" +
                    "            },\n" +
                    "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"hubble\": {\n" +
                    "        \"_all\": {\n" +
                    "          \"enabled\": false\n" +
                    "        },\n" +
                    "        \"dynamic_date_formats\": [\n" +
                    "          \"strict_date_optional_time\",\n" +
                    "          \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
                    "        ],\n" +
                    "        \"dynamic_templates\": [\n" +
                    "          {\n" +
                    "            \"traceid_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"traceid\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"request_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"request\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"orderId_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"orderId\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"json_annotations_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"nested\"\n" +
                    "              },\n" +
                    "              \"match\": \"json_annotations\",\n" +
                    "              \"match_mapping_type\": \"object\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"body_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"analyzed\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"body\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"out_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"out\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"thriftRpcCount_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"integer\"\n" +
                    "              },\n" +
                    "              \"match\": \"thriftRpcCount\",\n" +
                    "              \"match_mapping_type\": \"*\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"jsonMsg_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"object\"\n" +
                    "              },\n" +
                    "              \"match\": \"jsonMsg\",\n" +
                    "              \"match_mapping_type\": \"object\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"rpcTotal_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"integer\"\n" +
                    "              },\n" +
                    "              \"match\": \"rpcTotal\",\n" +
                    "              \"match_mapping_type\": \"*\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"sink_flag_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"sink_flag\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"httpRpcCount_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"integer\"\n" +
                    "              },\n" +
                    "              \"match\": \"httpRpcCount\",\n" +
                    "              \"match_mapping_type\": \"*\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"in_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"in\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"errmsg_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"errmsg\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"message_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"message\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"busiPayments_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"busiPayments\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"args_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"args\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"logID_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"logID\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"projectName_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"projectName\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"proc_time_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"double\"\n" +
                    "              },\n" +
                    "              \"match\": \"proc_time\",\n" +
                    "              \"match_mapping_type\": \"*\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"longToboolean_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"boolean\"\n" +
                    "              },\n" +
                    "              \"match\": \"is*\",\n" +
                    "              \"match_mapping_type\": \"long\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"longToboolean_fields1\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"type\": \"boolean\"\n" +
                    "              },\n" +
                    "              \"match\": \"enableDebugMode\",\n" +
                    "              \"match_mapping_type\": \"long\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"string_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"ignore_above\": 512,\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"*\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"stringno_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"response*\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"stringno1_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"req*\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"stringno2_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"args*\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"stringno3_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"errmsg*\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"appName_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"no\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"originalAppName\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"extractLevel_fields\": {\n" +
                    "              \"mapping\": {\n" +
                    "                \"index\": \"not_analyzed\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              \"match\": \"extractLevel\",\n" +
                    "              \"match_mapping_type\": \"string\"\n" +
                    "            }\n" +
                    "          }\n" +
                    "        ],\n" +
                    "        \"properties\": {\n" +
                    "          \"HTTP_DIDI_HEADER_SPANID\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"doc_values\": false\n" +
                    "          },\n" +
                    "          \"aegis_es_cur_topic\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"doc_values\": false\n" +
                    "          },\n" +
                    "          \"aegis_url\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"appName\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"bytes_sent\": {\n" +
                    "            \"type\": \"long\"\n" +
                    "          },\n" +
                    "          \"cleanTime\": {\n" +
                    "            \"type\": \"date\",\n" +
                    "            \"fielddata\": {\n" +
                    "              \"loading\": \"eager\"\n" +
                    "            },\n" +
                    "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
                    "          },\n" +
                    "          \"clientHost\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"collectTime\": {\n" +
                    "            \"type\": \"date\",\n" +
                    "            \"fielddata\": {\n" +
                    "              \"loading\": \"eager\"\n" +
                    "            },\n" +
                    "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
                    "          },\n" +
                    "          \"host\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"doc_values\": false\n" +
                    "          },\n" +
                    "          \"http_cookie\": {\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"http_didi_header_omgid\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"doc_values\": false\n" +
                    "          },\n" +
                    "          \"http_referer\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"doc_values\": false,\n" +
                    "            \"ignore_above\": 256\n" +
                    "          },\n" +
                    "          \"http_user_agent\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"doc_values\": false,\n" +
                    "            \"ignore_above\": 256\n" +
                    "          },\n" +
                    "          \"http_wsgsig\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"http_x_forwarded_for\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"doc_values\": false\n" +
                    "          },\n" +
                    "          \"imei\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"kOffset\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 512\n" +
                    "          },\n" +
                    "          \"kPartition\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 512\n" +
                    "          },\n" +
                    "          \"kTopic\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 512\n" +
                    "          },\n" +
                    "          \"limit_rate\": {\n" +
                    "            \"type\": \"long\",\n" +
                    "            \"include_in_all\": false\n" +
                    "          },\n" +
                    "          \"logID\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"no\"\n" +
                    "          },\n" +
                    "          \"logName\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 2048\n" +
                    "          },\n" +
                    "          \"logOffset\": {\n" +
                    "            \"type\": \"long\"\n" +
                    "          },\n" +
                    "          \"logTime\": {\n" +
                    "            \"type\": \"date\",\n" +
                    "            \"fielddata\": {\n" +
                    "              \"loading\": \"eager\"\n" +
                    "            },\n" +
                    "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
                    "          },\n" +
                    "          \"message\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"no\"\n" +
                    "          },\n" +
                    "          \"odinLeaf\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"omgid\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"omgtra\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"openid\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"operationid\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"doc_values\": false\n" +
                    "          },\n" +
                    "          \"projectName\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"no\"\n" +
                    "          },\n" +
                    "          \"remote_addr\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"remote_port\": {\n" +
                    "            \"type\": \"integer\"\n" +
                    "          },\n" +
                    "          \"request\": {\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"request_length\": {\n" +
                    "            \"type\": \"long\"\n" +
                    "          },\n" +
                    "          \"request_time\": {\n" +
                    "            \"type\": \"double\",\n" +
                    "            \"precision_step\": 1,\n" +
                    "            \"include_in_all\": false\n" +
                    "          },\n" +
                    "          \"schema\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"fielddata\": {\n" +
                    "              \"format\": \"doc_values\"\n" +
                    "            },\n" +
                    "            \"include_in_all\": false\n" +
                    "          },\n" +
                    "          \"scheme\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"server_addr\": {\n" +
                    "            \"type\": \"ip\",\n" +
                    "            \"fielddata\": {\n" +
                    "              \"format\": \"doc_values\"\n" +
                    "            },\n" +
                    "            \"precision_step\": 1,\n" +
                    "            \"include_in_all\": false\n" +
                    "          },\n" +
                    "          \"sinkTime\": {\n" +
                    "            \"type\": \"date\",\n" +
                    "            \"fielddata\": {\n" +
                    "              \"loading\": \"eager\"\n" +
                    "            },\n" +
                    "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
                    "          },\n" +
                    "          \"status\": {\n" +
                    "            \"type\": \"long\",\n" +
                    "            \"precision_step\": 1,\n" +
                    "            \"include_in_all\": false\n" +
                    "          },\n" +
                    "          \"thttp_user_agent\": {\n" +
                    "            \"type\": \"string\"\n" +
                    "          },\n" +
                    "          \"time_local\": {\n" +
                    "            \"type\": \"date\",\n" +
                    "            \"precision_step\": 3,\n" +
                    "            \"format\": \"dd/MMM/yyyy:HH:mm:ss Z\"\n" +
                    "          },\n" +
                    "          \"uniqueKey\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\",\n" +
                    "            \"ignore_above\": 512\n" +
                    "          },\n" +
                    "          \"upstream_addr\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          },\n" +
                    "          \"upstream_response_time\": {\n" +
                    "            \"type\": \"double\"\n" +
                    "          },\n" +
                    "          \"upstream_status\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"index\": \"not_analyzed\"\n" +
                    "          }\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }";

            {
                MappingConfig mappingConfig = new MappingConfig(JSON.parseObject(mappings));
                JSONObject newMappings = mappingConfig.toJson(ESVersion.ES651);
                assertEquals(newMappings.toJSONString(), "{\"hubble\":{\"properties\":{\"schema\":{\"type\":\"keyword\"},\"request\":{\"type\":\"text\"},\"scheme\":{\"type\":\"keyword\"},\"http_wsgsig\":{\"type\":\"keyword\"},\"upstream_addr\":{\"type\":\"keyword\"},\"logOffset\":{\"type\":\"long\"},\"odinLeaf\":{\"type\":\"keyword\"},\"aegis_url\":{\"type\":\"keyword\"},\"kOffset\":{\"ignore_above\":512,\"type\":\"keyword\"},\"thttp_user_agent\":{\"type\":\"text\"},\"http_didi_header_omgid\":{\"type\":\"keyword\",\"doc_values\":false},\"http_user_agent\":{\"ignore_above\":256,\"type\":\"keyword\",\"doc_values\":false},\"upstream_status\":{\"type\":\"keyword\"},\"request_time\":{\"type\":\"double\"},\"sinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"host\":{\"type\":\"keyword\",\"doc_values\":false},\"aegis_es_cur_topic\":{\"type\":\"keyword\",\"doc_values\":false},\"limit_rate\":{\"type\":\"long\"},\"http_cookie\":{\"type\":\"text\"},\"omgtra\":{\"type\":\"keyword\"},\"collectTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"remote_addr\":{\"type\":\"keyword\"},\"kPartition\":{\"ignore_above\":512,\"type\":\"keyword\"},\"HTTP_DIDI_HEADER_SPANID\":{\"type\":\"keyword\",\"doc_values\":false},\"clientHost\":{\"type\":\"keyword\"},\"appName\":{\"type\":\"keyword\"},\"openid\":{\"type\":\"keyword\"},\"uniqueKey\":{\"ignore_above\":512,\"type\":\"keyword\"},\"remote_port\":{\"type\":\"integer\"},\"time_local\":{\"format\":\"dd/MMM/yyyy:HH:mm:ss Z\",\"type\":\"date\"},\"omgid\":{\"type\":\"keyword\"},\"message\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"bytes_sent\":{\"type\":\"long\"},\"server_addr\":{\"type\":\"ip\"},\"kTopic\":{\"ignore_above\":512,\"type\":\"keyword\"},\"logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"cleanTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"logName\":{\"ignore_above\":2048,\"type\":\"keyword\"},\"request_length\":{\"type\":\"long\"},\"http_referer\":{\"ignore_above\":256,\"type\":\"keyword\",\"doc_values\":false},\"http_x_forwarded_for\":{\"type\":\"keyword\",\"doc_values\":false},\"imei\":{\"type\":\"keyword\"},\"operationid\":{\"type\":\"keyword\",\"doc_values\":false},\"logID\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"upstream_response_time\":{\"type\":\"double\"},\"projectName\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"status\":{\"type\":\"long\"}}},\"_default_\":{\"properties\":{\"cleanTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"collectTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"sinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"}}}}");
            }

            {
                MappingConfig mappingConfig = new MappingConfig(JSON.parseObject(mappings));
                JSONObject newMappings = mappingConfig.toJson(ESVersion.ES760);
                assertEquals(newMappings.toJSONString(), "{\"properties\":{\"schema\":{\"type\":\"keyword\"},\"request\":{\"type\":\"text\"},\"scheme\":{\"type\":\"keyword\"},\"http_wsgsig\":{\"type\":\"keyword\"},\"upstream_addr\":{\"type\":\"keyword\"},\"logOffset\":{\"type\":\"long\"},\"odinLeaf\":{\"type\":\"keyword\"},\"aegis_url\":{\"type\":\"keyword\"},\"kOffset\":{\"ignore_above\":512,\"type\":\"keyword\"},\"thttp_user_agent\":{\"type\":\"text\"},\"http_didi_header_omgid\":{\"type\":\"keyword\",\"doc_values\":false},\"http_user_agent\":{\"ignore_above\":256,\"type\":\"keyword\",\"doc_values\":false},\"upstream_status\":{\"type\":\"keyword\"},\"request_time\":{\"type\":\"double\"},\"sinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"host\":{\"type\":\"keyword\",\"doc_values\":false},\"aegis_es_cur_topic\":{\"type\":\"keyword\",\"doc_values\":false},\"limit_rate\":{\"type\":\"long\"},\"http_cookie\":{\"type\":\"text\"},\"omgtra\":{\"type\":\"keyword\"},\"collectTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"remote_addr\":{\"type\":\"keyword\"},\"kPartition\":{\"ignore_above\":512,\"type\":\"keyword\"},\"HTTP_DIDI_HEADER_SPANID\":{\"type\":\"keyword\",\"doc_values\":false},\"clientHost\":{\"type\":\"keyword\"},\"appName\":{\"type\":\"keyword\"},\"openid\":{\"type\":\"keyword\"},\"uniqueKey\":{\"ignore_above\":512,\"type\":\"keyword\"},\"remote_port\":{\"type\":\"integer\"},\"time_local\":{\"format\":\"dd/MMM/yyyy:HH:mm:ss Z\",\"type\":\"date\"},\"omgid\":{\"type\":\"keyword\"},\"message\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"bytes_sent\":{\"type\":\"long\"},\"server_addr\":{\"type\":\"ip\"},\"kTopic\":{\"ignore_above\":512,\"type\":\"keyword\"},\"logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"cleanTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"logName\":{\"ignore_above\":2048,\"type\":\"keyword\"},\"request_length\":{\"type\":\"long\"},\"http_referer\":{\"ignore_above\":256,\"type\":\"keyword\",\"doc_values\":false},\"http_x_forwarded_for\":{\"type\":\"keyword\",\"doc_values\":false},\"imei\":{\"type\":\"keyword\"},\"operationid\":{\"type\":\"keyword\",\"doc_values\":false},\"logID\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"upstream_response_time\":{\"type\":\"double\"},\"projectName\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"status\":{\"type\":\"long\"}}}");
            }
        }
    }

    @Test
    public void checkLowVersionMapping() throws Exception {
        String mappings = "{\"properties\":{\"schema\":{\"type\":\"keyword\"},\"request\":{\"type\":\"text\"},\"scheme\":{\"type\":\"keyword\"},\"http_wsgsig\":{\"type\":\"keyword\"},\"upstream_addr\":{\"type\":\"keyword\"},\"logOffset\":{\"type\":\"long\"},\"odinLeaf\":{\"type\":\"keyword\"},\"aegis_url\":{\"type\":\"keyword\"},\"kOffset\":{\"ignore_above\":512,\"type\":\"keyword\"},\"thttp_user_agent\":{\"type\":\"text\"},\"http_didi_header_omgid\":{\"type\":\"keyword\",\"doc_values\":false},\"http_user_agent\":{\"ignore_above\":256,\"type\":\"keyword\",\"doc_values\":false},\"upstream_status\":{\"type\":\"keyword\"},\"request_time\":{\"type\":\"double\"},\"sinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"host\":{\"type\":\"keyword\",\"doc_values\":false},\"aegis_es_cur_topic\":{\"type\":\"keyword\",\"doc_values\":false},\"limit_rate\":{\"type\":\"long\"},\"http_cookie\":{\"type\":\"text\"},\"omgtra\":{\"type\":\"keyword\"},\"collectTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"remote_addr\":{\"type\":\"keyword\"},\"kPartition\":{\"ignore_above\":512,\"type\":\"keyword\"},\"HTTP_DIDI_HEADER_SPANID\":{\"type\":\"keyword\",\"doc_values\":false},\"clientHost\":{\"type\":\"keyword\"},\"appName\":{\"type\":\"keyword\"},\"openid\":{\"type\":\"keyword\"},\"uniqueKey\":{\"ignore_above\":512,\"type\":\"keyword\"},\"remote_port\":{\"type\":\"integer\"},\"time_local\":{\"format\":\"dd/MMM/yyyy:HH:mm:ss Z\",\"type\":\"date\"},\"omgid\":{\"type\":\"keyword\"},\"message\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"bytes_sent\":{\"type\":\"long\"},\"server_addr\":{\"type\":\"ip\"},\"kTopic\":{\"ignore_above\":512,\"type\":\"keyword\"},\"logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"cleanTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"logName\":{\"ignore_above\":2048,\"type\":\"keyword\"},\"request_length\":{\"type\":\"long\"},\"http_referer\":{\"ignore_above\":256,\"type\":\"keyword\",\"doc_values\":false},\"http_x_forwarded_for\":{\"type\":\"keyword\",\"doc_values\":false},\"imei\":{\"type\":\"keyword\"},\"operationid\":{\"type\":\"keyword\",\"doc_values\":false},\"logID\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"upstream_response_time\":{\"type\":\"double\"},\"projectName\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"status\":{\"type\":\"long\"}}}";
        MappingConfig mappingConfig = new MappingConfig(JSON.parseObject(mappings));

        {
            JSONObject newMappings = mappingConfig.toJson();
            assertEquals(newMappings.toJSONString(), "{\"properties\":{\"schema\":{\"type\":\"keyword\"},\"request\":{\"type\":\"text\"},\"scheme\":{\"type\":\"keyword\"},\"http_wsgsig\":{\"type\":\"keyword\"},\"upstream_addr\":{\"type\":\"keyword\"},\"logOffset\":{\"type\":\"long\"},\"odinLeaf\":{\"type\":\"keyword\"},\"aegis_url\":{\"type\":\"keyword\"},\"kOffset\":{\"ignore_above\":512,\"type\":\"keyword\"},\"thttp_user_agent\":{\"type\":\"text\"},\"http_didi_header_omgid\":{\"type\":\"keyword\",\"doc_values\":false},\"http_user_agent\":{\"ignore_above\":256,\"type\":\"keyword\",\"doc_values\":false},\"upstream_status\":{\"type\":\"keyword\"},\"request_time\":{\"type\":\"double\"},\"sinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"host\":{\"type\":\"keyword\",\"doc_values\":false},\"aegis_es_cur_topic\":{\"type\":\"keyword\",\"doc_values\":false},\"limit_rate\":{\"type\":\"long\"},\"http_cookie\":{\"type\":\"text\"},\"omgtra\":{\"type\":\"keyword\"},\"collectTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"remote_addr\":{\"type\":\"keyword\"},\"kPartition\":{\"ignore_above\":512,\"type\":\"keyword\"},\"HTTP_DIDI_HEADER_SPANID\":{\"type\":\"keyword\",\"doc_values\":false},\"clientHost\":{\"type\":\"keyword\"},\"appName\":{\"type\":\"keyword\"},\"openid\":{\"type\":\"keyword\"},\"uniqueKey\":{\"ignore_above\":512,\"type\":\"keyword\"},\"remote_port\":{\"type\":\"integer\"},\"time_local\":{\"format\":\"dd/MMM/yyyy:HH:mm:ss Z\",\"type\":\"date\"},\"omgid\":{\"type\":\"keyword\"},\"message\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"bytes_sent\":{\"type\":\"long\"},\"server_addr\":{\"type\":\"ip\"},\"kTopic\":{\"ignore_above\":512,\"type\":\"keyword\"},\"logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"cleanTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"logName\":{\"ignore_above\":2048,\"type\":\"keyword\"},\"request_length\":{\"type\":\"long\"},\"http_referer\":{\"ignore_above\":256,\"type\":\"keyword\",\"doc_values\":false},\"http_x_forwarded_for\":{\"type\":\"keyword\",\"doc_values\":false},\"imei\":{\"type\":\"keyword\"},\"operationid\":{\"type\":\"keyword\",\"doc_values\":false},\"logID\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"upstream_response_time\":{\"type\":\"double\"},\"projectName\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"status\":{\"type\":\"long\"}}}");
        }

        {
            JSONObject newMappings = mappingConfig.toJson(ESVersion.ES651);
            assertEquals(newMappings.toJSONString(), "{\"type\":{\"properties\":{\"schema\":{\"type\":\"keyword\"},\"request\":{\"type\":\"text\"},\"scheme\":{\"type\":\"keyword\"},\"http_wsgsig\":{\"type\":\"keyword\"},\"upstream_addr\":{\"type\":\"keyword\"},\"logOffset\":{\"type\":\"long\"},\"odinLeaf\":{\"type\":\"keyword\"},\"aegis_url\":{\"type\":\"keyword\"},\"kOffset\":{\"ignore_above\":512,\"type\":\"keyword\"},\"thttp_user_agent\":{\"type\":\"text\"},\"http_didi_header_omgid\":{\"type\":\"keyword\",\"doc_values\":false},\"http_user_agent\":{\"ignore_above\":256,\"type\":\"keyword\",\"doc_values\":false},\"upstream_status\":{\"type\":\"keyword\"},\"request_time\":{\"type\":\"double\"},\"sinkTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"host\":{\"type\":\"keyword\",\"doc_values\":false},\"aegis_es_cur_topic\":{\"type\":\"keyword\",\"doc_values\":false},\"limit_rate\":{\"type\":\"long\"},\"http_cookie\":{\"type\":\"text\"},\"omgtra\":{\"type\":\"keyword\"},\"collectTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"remote_addr\":{\"type\":\"keyword\"},\"kPartition\":{\"ignore_above\":512,\"type\":\"keyword\"},\"HTTP_DIDI_HEADER_SPANID\":{\"type\":\"keyword\",\"doc_values\":false},\"clientHost\":{\"type\":\"keyword\"},\"appName\":{\"type\":\"keyword\"},\"openid\":{\"type\":\"keyword\"},\"uniqueKey\":{\"ignore_above\":512,\"type\":\"keyword\"},\"remote_port\":{\"type\":\"integer\"},\"time_local\":{\"format\":\"dd/MMM/yyyy:HH:mm:ss Z\",\"type\":\"date\"},\"omgid\":{\"type\":\"keyword\"},\"message\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"bytes_sent\":{\"type\":\"long\"},\"server_addr\":{\"type\":\"ip\"},\"kTopic\":{\"ignore_above\":512,\"type\":\"keyword\"},\"logTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"cleanTime\":{\"format\":\"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\",\"type\":\"date\"},\"logName\":{\"ignore_above\":2048,\"type\":\"keyword\"},\"request_length\":{\"type\":\"long\"},\"http_referer\":{\"ignore_above\":256,\"type\":\"keyword\",\"doc_values\":false},\"http_x_forwarded_for\":{\"type\":\"keyword\",\"doc_values\":false},\"imei\":{\"type\":\"keyword\"},\"operationid\":{\"type\":\"keyword\",\"doc_values\":false},\"logID\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"upstream_response_time\":{\"type\":\"double\"},\"projectName\":{\"index\":false,\"type\":\"keyword\",\"doc_values\":false},\"status\":{\"type\":\"long\"}}}}");
        }
    }


    private String test1 = "{\n" +
            "      \"_default_\": {\n" +
            "        \"_all\": {\n" +
            "          \"enabled\": false\n" +
            "        },\n" +
            "        \"dynamic_date_formats\": [\n" +
            "          \"strict_date_optional_time\",\n" +
            "          \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "        ],\n" +
            "        \"dynamic_templates\": [\n" +
            "          {\n" +
            "            \"appName_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"originalAppName\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"extractLevel_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"extractLevel\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"traceid_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"traceid\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"request_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"request\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"orderId_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"orderId\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"json_annotations_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"nested\"\n" +
            "              },\n" +
            "              \"match\": \"json_annotations\",\n" +
            "              \"match_mapping_type\": \"object\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"body_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"body\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"out_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"out\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"thriftRpcCount_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"integer\"\n" +
            "              },\n" +
            "              \"match\": \"thriftRpcCount\",\n" +
            "              \"match_mapping_type\": \"*\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"jsonMsg_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"object\"\n" +
            "              },\n" +
            "              \"match\": \"jsonMsg\",\n" +
            "              \"match_mapping_type\": \"object\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"rpcTotal_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"integer\"\n" +
            "              },\n" +
            "              \"match\": \"rpcTotal\",\n" +
            "              \"match_mapping_type\": \"*\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"sink_flag_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"sink_flag\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"httpRpcCount_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"integer\"\n" +
            "              },\n" +
            "              \"match\": \"httpRpcCount\",\n" +
            "              \"match_mapping_type\": \"*\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"in_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"in\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"errmsg_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"errmsg\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"message_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"message\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"busiPayments_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"busiPayments\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"args_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"args\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"projectName_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"projectName\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"proc_time_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"double\"\n" +
            "              },\n" +
            "              \"match\": \"proc_time\",\n" +
            "              \"match_mapping_type\": \"*\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"longToboolean_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"boolean\"\n" +
            "              },\n" +
            "              \"match\": \"is*\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"longToboolean_fields1\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"boolean\"\n" +
            "              },\n" +
            "              \"match\": \"enableDebugMode\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"stringno_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"response*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"stringno1_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"req*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"stringno2_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"args*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"stringno3_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"errmsg*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"string_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"ignore_above\": 512,\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          }\n" +
            "        ],\n" +
            "        \"numeric_detection\": true,\n" +
            "        \"properties\": {\n" +
            "          \"cleanTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"fielddata\": {\n" +
            "              \"loading\": \"eager\"\n" +
            "            },\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
            "          },\n" +
            "          \"collectTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"fielddata\": {\n" +
            "              \"loading\": \"eager\"\n" +
            "            },\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
            "          },\n" +
            "          \"logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"fielddata\": {\n" +
            "              \"loading\": \"eager\"\n" +
            "            },\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
            "          },\n" +
            "          \"sinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"fielddata\": {\n" +
            "              \"loading\": \"eager\"\n" +
            "            },\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
            "          }\n" +
            "        }\n" +
            "      },\n" +
            "      \"type\": {\n" +
            "        \"_all\": {\n" +
            "          \"enabled\": false\n" +
            "        },\n" +
            "        \"dynamic_date_formats\": [\n" +
            "          \"strict_date_optional_time\",\n" +
            "          \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "        ],\n" +
            "        \"dynamic_templates\": [\n" +
            "          {\n" +
            "            \"object_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"dynamic\": false,\n" +
            "                \"type\": \"object\",\n" +
            "                \"enabled\": false\n" +
            "              },\n" +
            "              \"match\": \"*\",\n" +
            "              \"match_mapping_type\": \"object\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"message_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"message\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"string_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"ignore_above\": 512,\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"timestampField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"*imestamp*\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"logTimeField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"logTime\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"extractLevel_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"extractLevel\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"appName_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"originalAppName\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"traceid_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"traceid\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"request_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"request\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"orderId_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"orderId\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"json_annotations_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"nested\"\n" +
            "              },\n" +
            "              \"match\": \"json_annotations\",\n" +
            "              \"match_mapping_type\": \"object\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"body_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"body\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"out_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"out\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"thriftRpcCount_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"integer\"\n" +
            "              },\n" +
            "              \"match\": \"thriftRpcCount\",\n" +
            "              \"match_mapping_type\": \"*\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"jsonMsg_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"object\"\n" +
            "              },\n" +
            "              \"match\": \"jsonMsg\",\n" +
            "              \"match_mapping_type\": \"object\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"rpcTotal_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"integer\"\n" +
            "              },\n" +
            "              \"match\": \"rpcTotal\",\n" +
            "              \"match_mapping_type\": \"*\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"sink_flag_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"sink_flag\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"httpRpcCount_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"integer\"\n" +
            "              },\n" +
            "              \"match\": \"httpRpcCount\",\n" +
            "              \"match_mapping_type\": \"*\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"in_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"in\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"errmsg_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"errmsg\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"busiPayments_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"busiPayments\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"args_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"args\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"projectName_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"projectName\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"proc_time_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"double\"\n" +
            "              },\n" +
            "              \"match\": \"proc_time\",\n" +
            "              \"match_mapping_type\": \"*\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"longToboolean_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"boolean\"\n" +
            "              },\n" +
            "              \"match\": \"is*\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"longToboolean_fields1\": {\n" +
            "              \"mapping\": {\n" +
            "                \"type\": \"boolean\"\n" +
            "              },\n" +
            "              \"match\": \"enableDebugMode\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"stringno_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"response*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"stringno1_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"req*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"stringno2_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"args*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"stringno3_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"errmsg*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          }\n" +
            "        ],\n" +
            "        \"numeric_detection\": true,\n" +
            "        \"properties\": {\n" +
            "          \"_arius_fetch_message_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_fetch_message_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_request_before_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_request_before_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_request_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_request_indices_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_request_indices_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_request_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_request_node_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_request_node_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_response_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_response_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_search_response_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_search_response_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_exception_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_exception_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_request_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_request_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_response_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_response_length_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_response_length_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_response_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_search_request_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_search_request_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_search_response_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_search_response_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_search_scroll_request_flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_search_scroll_request_logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"action\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"aggsLevel\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"appid\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"ariusCreateTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"beforeCost\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"bucketNumber\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"cleanTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"fielddata\": {\n" +
            "              \"loading\": \"eager\"\n" +
            "            },\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
            "          },\n" +
            "          \"clientNode\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"collectTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"fielddata\": {\n" +
            "              \"loading\": \"eager\"\n" +
            "            },\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
            "          },\n" +
            "          \"dltag\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"dltagCount\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"dsl\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 1024\n" +
            "          },\n" +
            "          \"dslLen\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"dslLevel\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"dslTag\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 512\n" +
            "          },\n" +
            "          \"dslTemplate\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 1024\n" +
            "          },\n" +
            "          \"dslTemplateMd5\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"dslType\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"esCost\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"esCostList\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"failedShards\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"failedShardsList\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"fetchCost\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"fetchMetric\": {\n" +
            "            \"properties\": {\n" +
            "              \"brokerHost\": {\n" +
            "                \"type\": \"string\",\n" +
            "                \"index\": \"not_analyzed\"\n" +
            "              },\n" +
            "              \"fetchMetricInfo\": {\n" +
            "                \"properties\": {\n" +
            "                  \"cost\": {\n" +
            "                    \"type\": \"long\"\n" +
            "                  },\n" +
            "                  \"count\": {\n" +
            "                    \"type\": \"long\"\n" +
            "                  },\n" +
            "                  \"partition\": {\n" +
            "                    \"type\": \"long\"\n" +
            "                  },\n" +
            "                  \"topic\": {\n" +
            "                    \"type\": \"string\",\n" +
            "                    \"index\": \"not_analyzed\",\n" +
            "                    \"ignore_above\": 512\n" +
            "                  }\n" +
            "                }\n" +
            "              },\n" +
            "              \"fetchMissingCnt\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"fetchOffsetCnt\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"getConsumeCost\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"parseMessageCost\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"partitionTotalCost\": {\n" +
            "                \"type\": \"long\"\n" +
            "              }\n" +
            "            }\n" +
            "          },\n" +
            "          \"fetchSize\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"flinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"gatewayNode\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"groupByFields\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 512\n" +
            "          },\n" +
            "          \"index\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 512\n" +
            "          },\n" +
            "          \"indiceCount\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"indiceSample\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"indices\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 1024\n" +
            "          },\n" +
            "          \"internalCost\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"isTimedOut\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"isTimeoutList\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"lastBucketNumber\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"logTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"fielddata\": {\n" +
            "              \"loading\": \"eager\"\n" +
            "            },\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||strict_date_optional_time||epoch_millis\"\n" +
            "          },\n" +
            "          \"memUsed\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"method\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"newDslTemplate\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 512\n" +
            "          },\n" +
            "          \"orderByFields\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 512\n" +
            "          },\n" +
            "          \"queryString\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"remoteAddr\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"requestId\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"requestType\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"responseLen\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"routing\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"scrollId\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"scrollIdList\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"searchType\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"selectFields\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 512\n" +
            "          },\n" +
            "          \"sinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"fielddata\": {\n" +
            "              \"loading\": \"eager\"\n" +
            "            },\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"status\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"successShardsList\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"successfulShards\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"timeStamp\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"totalCost\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"totalHits\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"totalHitsList\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"totalShards\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"totalShardsList\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"types\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\"\n" +
            "          },\n" +
            "          \"userName\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 512\n" +
            "          },\n" +
            "          \"whereFields\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 512\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    }";


    private static String mapping_STR = "{\n" +
            "      \"preview_nsky_htw_bike_position\": {\n" +
            "        \"_all\": {\n" +
            "          \"enabled\": false\n" +
            "        },\n" +
            "        \"dynamic_date_formats\": [\n" +
            "          \"strict_date_optional_time\",\n" +
            "          \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "        ],\n" +
            "        \"dynamic_templates\": [\n" +
            "          {\n" +
            "            \"object_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"dynamic\": false,\n" +
            "                \"type\": \"object\",\n" +
            "                \"enabled\": false\n" +
            "              },\n" +
            "              \"match\": \"*\",\n" +
            "              \"match_mapping_type\": \"object\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"message_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"message\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"string_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"ignore_above\": 512,\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"timestampField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"*imestamp*\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"logTimeField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"logTime\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"sinkTimeField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"sinkTime\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"extractLevel_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"extractLevel\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          }\n" +
            "        ],\n" +
            "        \"numeric_detection\": true,\n" +
            "        \"properties\": {\n" +
            "          \"mongoId\": {\n" +
            "            \"type\": \"nested\"\n" +
            "          },\n" +
            "          \"tagDetails\": {\n" +
            "            \"type\": \"nested\"\n" +
            "          },\n" +
            "          \"typeVehiclePos\": {\n" +
            "            \"type\": \"nested\"\n" +
            "          },\n" +
            "          \"vehiclePos\": {\n" +
            "            \"type\": \"geo_point\"\n" +
            "          }\n" +
            "        }\n" +
            "      },\n" +
            "      \"_default_\": {\n" +
            "        \"_all\": {\n" +
            "          \"enabled\": false\n" +
            "        },\n" +
            "        \"dynamic_date_formats\": [\n" +
            "          \"strict_date_optional_time\",\n" +
            "          \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "        ],\n" +
            "        \"dynamic_templates\": [\n" +
            "          {\n" +
            "            \"object_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"dynamic\": false,\n" +
            "                \"type\": \"object\",\n" +
            "                \"enabled\": false\n" +
            "              },\n" +
            "              \"match\": \"*\",\n" +
            "              \"match_mapping_type\": \"object\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"message_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"message\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"string_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"ignore_above\": 512,\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"timestampField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"*imestamp*\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"logTimeField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"logTime\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"sinkTimeField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"sinkTime\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"extractLevel_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"extractLevel\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          }\n" +
            "        ],\n" +
            "        \"numeric_detection\": true\n" +
            "      },\n" +
            "      \"prod_nsky_htw_bike_position\": {\n" +
            "        \"_all\": {\n" +
            "          \"enabled\": false\n" +
            "        },\n" +
            "        \"dynamic_date_formats\": [\n" +
            "          \"strict_date_optional_time\",\n" +
            "          \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "        ],\n" +
            "        \"dynamic_templates\": [\n" +
            "          {\n" +
            "            \"object_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"dynamic\": false,\n" +
            "                \"type\": \"object\",\n" +
            "                \"enabled\": false\n" +
            "              },\n" +
            "              \"match\": \"*\",\n" +
            "              \"match_mapping_type\": \"object\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"message_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"message\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"string_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"ignore_above\": 512,\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"timestampField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"*imestamp*\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"logTimeField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"logTime\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"sinkTimeField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"sinkTime\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"extractLevel_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"extractLevel\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          }\n" +
            "        ],\n" +
            "        \"numeric_detection\": true,\n" +
            "        \"properties\": {\n" +
            "          \"ariusCreateTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"assetBikeModelNo\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"batteryLevel\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"batteryLevelTime\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"batteryVoltage\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"batteryVoltageContinuousLowCount\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"bikeSupplier\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"bizType\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"cityId\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"deleteFlag\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"geoHash\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 512\n" +
            "          },\n" +
            "          \"gmtModify\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"gmtOperationStart\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"hasOrderStatus\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"hbTime\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"inOpTime\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"inStore\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"lastOrderCityId\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"lastOrderTime\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"lat\": {\n" +
            "            \"type\": \"double\"\n" +
            "          },\n" +
            "          \"lng\": {\n" +
            "            \"type\": \"double\"\n" +
            "          },\n" +
            "          \"locationSource\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"locationType\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"lockId\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"lockModel\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"lockModelNo\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"lockSim\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"lockStatus\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"mongoId\": {\n" +
            "            \"type\": \"nested\",\n" +
            "            \"properties\": {\n" +
            "              \"counter\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"date\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"machineIdentifier\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"processIdentifier\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"time\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"timeSecond\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"timestamp\": {\n" +
            "                \"type\": \"date\",\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "              }\n" +
            "            }\n" +
            "          },\n" +
            "          \"notFoundReportCount\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"opBrand\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"opExecutionRegionId\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"opStatus\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"operationStatus\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"putGmtCreate\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"ignore_above\": 512\n" +
            "          },\n" +
            "          \"sinkTime\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"status\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"tagDetails\": {\n" +
            "            \"type\": \"nested\",\n" +
            "            \"properties\": {\n" +
            "              \"tagId\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"tagMarkedTime\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"tagName\": {\n" +
            "                \"type\": \"string\",\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"ignore_above\": 512\n" +
            "              },\n" +
            "              \"tagType\": {\n" +
            "                \"type\": \"long\"\n" +
            "              }\n" +
            "            }\n" +
            "          },\n" +
            "          \"tags\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"timestamp\": {\n" +
            "            \"type\": \"date\",\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "          },\n" +
            "          \"typeVehiclePos\": {\n" +
            "            \"type\": \"nested\",\n" +
            "            \"properties\": {\n" +
            "              \"accuracy\": {\n" +
            "                \"type\": \"double\"\n" +
            "              },\n" +
            "              \"hbTime\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"lat\": {\n" +
            "                \"type\": \"double\"\n" +
            "              },\n" +
            "              \"lng\": {\n" +
            "                \"type\": \"double\"\n" +
            "              },\n" +
            "              \"locationSource\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"locationType\": {\n" +
            "                \"type\": \"long\"\n" +
            "              }\n" +
            "            }\n" +
            "          },\n" +
            "          \"vehicleId\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"vehicleLastLocationTime\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"vehiclePos\": {\n" +
            "            \"type\": \"geo_point\"\n" +
            "          },\n" +
            "          \"vehicleStatus\": {\n" +
            "            \"type\": \"long\"\n" +
            "          }\n" +
            "        }\n" +
            "      },\n" +
            "      \"type\": {\n" +
            "        \"_all\": {\n" +
            "          \"enabled\": false\n" +
            "        },\n" +
            "        \"dynamic_date_formats\": [\n" +
            "          \"strict_date_optional_time\",\n" +
            "          \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "        ],\n" +
            "        \"dynamic_templates\": [\n" +
            "          {\n" +
            "            \"object_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"dynamic\": false,\n" +
            "                \"type\": \"object\",\n" +
            "                \"enabled\": false\n" +
            "              },\n" +
            "              \"match\": \"*\",\n" +
            "              \"match_mapping_type\": \"object\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"message_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"message\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"string_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"ignore_above\": 512,\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"*\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"timestampField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"*imestamp*\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"logTimeField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"logTime\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"sinkTimeField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match\": \"sinkTime\",\n" +
            "              \"match_mapping_type\": \"long\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"extractLevel_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match\": \"extractLevel\",\n" +
            "              \"match_mapping_type\": \"string\"\n" +
            "            }\n" +
            "          }\n" +
            "        ],\n" +
            "        \"numeric_detection\": true\n" +
            "      }\n" +
            "    }";
}
