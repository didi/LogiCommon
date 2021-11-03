package com.didiglobal.logi.elasticsearch.client.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.response.query.query.ESQueryResponse;
import org.junit.Test;

public class QueryResponseTest {

    @Test
    public void test() {
        ESQueryResponse response = ESQueryResponse.parserResponse(respStr, null);

        JSONObject obj = JSON.parseObject(respStr);
        JSONObject ret = response.toJson();
        System.out.println(ret.toJSONString());

        if (obj.equals(ret)) {
            System.out.println("ok");
        } else {
            System.out.println("obj:" + obj.toJSONString());
            System.out.println("ret:" + ret.toJSONString());
        }
    }


    public static String respStr = "{\n" +
            "  \"took\": 3311,\n" +
            "  \"timed_out\": false,\n" +
            "  \"_shards\": {\n" +
            "    \"total\": 4,\n" +
            "    \"successful\": 4,\n" +
            "    \"failed\": 0\n" +
            "  },\n" +
            "  \"hits\": {\n" +
            "    \"total\": 25473862,\n" +
            "    \"max_score\": 4.6772404,\n" +
            "    \"hits\": [\n" +
            "      {\n" +
            "        \"_index\": \"arius.gateway.join_2018-12-26\",\n" +
            "        \"_type\": \"type\",\n" +
            "        \"_id\": \"9da260e5-422f-4099-8ce7-0d720197bf18\",\n" +
            "        \"_score\": 4.6772404,\n" +
            "        \"_source\": {\n" +
            "          \"ariusCreateTime\": \"2018-12-26 03:30:04.289 +0800\",\n" +
            "          \"selectFields\": \"*\",\n" +
            "          \"groupByFields\": \"POWER_STATUS,OUT_OF_RANGE,ORDER_STATUS,OPERATION_STATUS\",\n" +
            "          \"responseLen\": 296,\n" +
            "          \"beforeCost\": 0,\n" +
            "          \"isTimedOut\": \"false\",\n" +
            "          \"totalShards\": 20,\n" +
            "          \"scrollId\": \"null\",\n" +
            "          \"failedShards\": 0,\n" +
            "          \"dslTemplate\": \"{\\\"size\\\":\\\"?\\\",\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"CITY_ID\\\":\\\"?\\\"}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}]}}]}}}},\\\"_source\\\":\\\"?\\\",\\\"aggs\\\":{\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}}},\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}}}}\",\n" +
            "          \"dslType\": \"aggs\",\n" +
            "          \"newDslTemplate\": \"{\\\"_source\\\":\\\"[?]\\\",\\\"aggs\\\":{\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}}}},\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"CITY_ID\\\":\\\"?\\\"}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}]}}]}}}},\\\"size\\\":\\\"?\\\"}\",\n" +
            "          \"esCost\": 3,\n" +
            "          \"requestId\": \"9da260e5-422f-4099-8ce7-0d720197bf18\",\n" +
            "          \"aggsLevel\": 1,\n" +
            "          \"sinkTime\": 1545766204289,\n" +
            "          \"indiceCount\": 1,\n" +
            "          \"internalCost\": 1,\n" +
            "          \"remoteAddr\": \"100.69.157.58\",\n" +
            "          \"orderByFields\": \"\",\n" +
            "          \"lastBucketNumber\": 0,\n" +
            "          \"method\": \"POST\",\n" +
            "          \"requestType\": \"http\",\n" +
            "          \"searchType\": \"dsl\",\n" +
            "          \"successfulShards\": 20,\n" +
            "          \"index\": \"{}\",\n" +
            "          \"whereFields\": \"CITY_ID,POWER_STATUS,OUT_OF_RANGE,ORDER_STATUS,BUSINESS_UNIT,OPERATION_STATUS\",\n" +
            "          \"gatewayNode\": \"bigdata-arius-ser200.gz01\",\n" +
            "          \"queryString\": \"null\",\n" +
            "          \"indiceSample\": \"nsky_realtime_label*\",\n" +
            "          \"timeStamp\": 1545766202841,\n" +
            "          \"indices\": \"nsky_realtime_label*\",\n" +
            "          \"dslLen\": 698,\n" +
            "          \"dslTemplateMd5\": [\n" +
            "            \"FCFFEDE7D07468304FC73A0A1427FE64\",\n" +
            "            \"V2_7C27B4C7F2CCFB17781701B7B286EF8B\"\n" +
            "          ],\n" +
            "          \"totalHits\": 0,\n" +
            "          \"appid\": 591,\n" +
            "          \"memUsed\": 0,\n" +
            "          \"dsl\": \"{\\\"size\\\":0,\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":10}},{\\\"term\\\":{\\\"CITY_ID\\\":13}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"term\\\":{\\\"POWER_STATUS\\\":2}},{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":100}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}},{\\\"term\\\":{\\\"POWER_STATUS\\\":3}},{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":1}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}}]}}}},\\\"_source\\\":false,\\\"aggs\\\":{\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":2}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":100}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":3}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":1}}},\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}}}}\",\n" +
            "          \"bucketNumber\": 5,\n" +
            "          \"totalCost\": 4,\n" +
            "          \"status\": 200\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"_index\": \"arius.gateway.join_2018-12-26\",\n" +
            "        \"_type\": \"type\",\n" +
            "        \"_id\": \"718aeda8-4eb9-44b4-b8bd-4c886950ebaa\",\n" +
            "        \"_score\": 4.6772404,\n" +
            "        \"_source\": {\n" +
            "          \"ariusCreateTime\": \"2018-12-26 03:30:00.282 +0800\",\n" +
            "          \"selectFields\": \"ua\",\n" +
            "          \"groupByFields\": \"ua\",\n" +
            "          \"responseLen\": 170,\n" +
            "          \"beforeCost\": 0,\n" +
            "          \"isTimedOut\": \"false\",\n" +
            "          \"totalShards\": 124,\n" +
            "          \"scrollId\": \"null\",\n" +
            "          \"failedShards\": 0,\n" +
            "          \"dslTemplate\": \"{\\\"_source\\\":{\\\"include\\\":\\\"?\\\"},\\\"aggs\\\":{\\\"distinct_ua\\\":{\\\"cardinality\\\":{\\\"field\\\":\\\"?\\\"}}},\\\"query\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"range\\\":{\\\"sinkTime\\\":{\\\"gte\\\":\\\"?\\\"}}},{\\\"terms\\\":{\\\"processType\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"host\\\":{\\\"value\\\":\\\"?\\\"}}},{\\\"term\\\":{\\\"url\\\":{\\\"value\\\":\\\"?\\\"}}},{\\\"term\\\":{\\\"ip\\\":{\\\"value\\\":\\\"?\\\"}}}]}}}\",\n" +
            "          \"dslType\": \"aggs\",\n" +
            "          \"newDslTemplate\": \"{\\\"_source\\\":\\\"[?]\\\",\\\"aggs\\\":{\\\"distinct_ua\\\":{\\\"cardinality\\\":{\\\"field\\\":\\\"ua\\\"}}},\\\"query\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"range\\\":{\\\"sinkTime\\\":{\\\"gte\\\":\\\"?\\\"}}},{\\\"term\\\":{\\\"host\\\":{\\\"value\\\":\\\"?\\\"}}},{\\\"term\\\":{\\\"ip\\\":{\\\"value\\\":\\\"?\\\"}}},{\\\"term\\\":{\\\"url\\\":{\\\"value\\\":\\\"?\\\"}}},{\\\"terms\\\":{\\\"processType\\\":[\\\"?\\\"]}}]}}}\",\n" +
            "          \"esCost\": 14,\n" +
            "          \"requestId\": \"718aeda8-4eb9-44b4-b8bd-4c886950ebaa\",\n" +
            "          \"aggsLevel\": 1,\n" +
            "          \"sinkTime\": 1545766200282,\n" +
            "          \"indiceCount\": 1,\n" +
            "          \"clientNode\": \"trib-common-sf-896f0-6.docker.ys/10.167.4.12:9350\",\n" +
            "          \"internalCost\": 1,\n" +
            "          \"remoteAddr\": \"10.83.19.32\",\n" +
            "          \"orderByFields\": \"\",\n" +
            "          \"lastBucketNumber\": 0,\n" +
            "          \"method\": \"POST\",\n" +
            "          \"requestType\": \"http\",\n" +
            "          \"searchType\": \"dsl\",\n" +
            "          \"successfulShards\": 124,\n" +
            "          \"index\": \"{}\",\n" +
            "          \"whereFields\": \"sinkTime,ip,host,processType,url\",\n" +
            "          \"gatewayNode\": \"bigdata-arius-ser203.gz01\",\n" +
            "          \"queryString\": \"null\",\n" +
            "          \"indiceSample\": \"sec_lad_alarm_final*\",\n" +
            "          \"timeStamp\": 1545766197783,\n" +
            "          \"indices\": \"sec_lad_alarm_final*\",\n" +
            "          \"dslLen\": 340,\n" +
            "          \"dslTemplateMd5\": [\n" +
            "            \"0D2FDBB292D7417182DCAB12E4E3052D\",\n" +
            "            \"V2_452720CA7B086723801A071204D35CFA\"\n" +
            "          ],\n" +
            "          \"totalHits\": 0,\n" +
            "          \"appid\": 889,\n" +
            "          \"memUsed\": 16,\n" +
            "          \"dsl\": \"{\\\"_source\\\":{\\\"include\\\":[\\\"ua\\\"]},\\\"aggs\\\":{\\\"distinct_ua\\\":{\\\"cardinality\\\":{\\\"field\\\":\\\"ua\\\"}}},\\\"query\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"range\\\":{\\\"sinkTime\\\":{\\\"gte\\\":\\\"now-1d\\\"}}},{\\\"terms\\\":{\\\"processType\\\":[\\\"1\\\",\\\"7\\\"]}},{\\\"term\\\":{\\\"host\\\":{\\\"value\\\":\\\"dorado.xiaojukeji.com\\\"}}},{\\\"term\\\":{\\\"url\\\":{\\\"value\\\":\\\"GET /api/v1/growth/acceptShare\\\"}}},{\\\"term\\\":{\\\"ip\\\":{\\\"value\\\":\\\"123.53.26.5\\\"}}}]}}}\",\n" +
            "          \"bucketNumber\": 1,\n" +
            "          \"totalCost\": 15,\n" +
            "          \"status\": 200\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"_index\": \"arius.gateway.join_2018-12-26\",\n" +
            "        \"_type\": \"type\",\n" +
            "        \"_id\": \"5d1a2de0-300d-4ab1-af2f-dae0919663f1\",\n" +
            "        \"_score\": 4.6772404,\n" +
            "        \"_source\": {\n" +
            "          \"ariusCreateTime\": \"2018-12-26 03:30:02.799 +0800\",\n" +
            "          \"selectFields\": \"*\",\n" +
            "          \"groupByFields\": \"POWER_STATUS,OUT_OF_RANGE,ORDER_STATUS,OPERATION_STATUS\",\n" +
            "          \"responseLen\": 296,\n" +
            "          \"beforeCost\": 0,\n" +
            "          \"isTimedOut\": \"false\",\n" +
            "          \"totalShards\": 20,\n" +
            "          \"scrollId\": \"null\",\n" +
            "          \"failedShards\": 0,\n" +
            "          \"dslTemplate\": \"{\\\"size\\\":\\\"?\\\",\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"CITY_ID\\\":\\\"?\\\"}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}]}}]}}}},\\\"_source\\\":\\\"?\\\",\\\"aggs\\\":{\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}}},\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}}}}\",\n" +
            "          \"dslType\": \"aggs\",\n" +
            "          \"newDslTemplate\": \"{\\\"_source\\\":\\\"[?]\\\",\\\"aggs\\\":{\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}}}},\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"CITY_ID\\\":\\\"?\\\"}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}]}}]}}}},\\\"size\\\":\\\"?\\\"}\",\n" +
            "          \"esCost\": 4,\n" +
            "          \"requestId\": \"5d1a2de0-300d-4ab1-af2f-dae0919663f1\",\n" +
            "          \"aggsLevel\": 1,\n" +
            "          \"sinkTime\": 1545766202799,\n" +
            "          \"indiceCount\": 1,\n" +
            "          \"clientNode\": \"trib-common-sf-896f0-7.docker.ys/10.168.240.18:9350\",\n" +
            "          \"internalCost\": 1,\n" +
            "          \"remoteAddr\": \"100.69.157.58\",\n" +
            "          \"orderByFields\": \"\",\n" +
            "          \"lastBucketNumber\": 0,\n" +
            "          \"method\": \"POST\",\n" +
            "          \"requestType\": \"http\",\n" +
            "          \"searchType\": \"dsl\",\n" +
            "          \"successfulShards\": 20,\n" +
            "          \"index\": \"{}\",\n" +
            "          \"whereFields\": \"CITY_ID,POWER_STATUS,OUT_OF_RANGE,ORDER_STATUS,BUSINESS_UNIT,OPERATION_STATUS\",\n" +
            "          \"gatewayNode\": \"bigdata-arius-ser202.gz01\",\n" +
            "          \"queryString\": \"null\",\n" +
            "          \"indiceSample\": \"nsky_realtime_label*\",\n" +
            "          \"timeStamp\": 1545766202166,\n" +
            "          \"indices\": \"nsky_realtime_label*\",\n" +
            "          \"dslLen\": 698,\n" +
            "          \"dslTemplateMd5\": [\n" +
            "            \"FCFFEDE7D07468304FC73A0A1427FE64\",\n" +
            "            \"V2_7C27B4C7F2CCFB17781701B7B286EF8B\"\n" +
            "          ],\n" +
            "          \"totalHits\": 6,\n" +
            "          \"appid\": 591,\n" +
            "          \"memUsed\": 0,\n" +
            "          \"dsl\": \"{\\\"size\\\":0,\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":10}},{\\\"term\\\":{\\\"CITY_ID\\\":27}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"term\\\":{\\\"POWER_STATUS\\\":2}},{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":100}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}},{\\\"term\\\":{\\\"POWER_STATUS\\\":3}},{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":1}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}}]}}}},\\\"_source\\\":false,\\\"aggs\\\":{\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":2}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":100}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":3}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":1}}},\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}}}}\",\n" +
            "          \"bucketNumber\": 5,\n" +
            "          \"totalCost\": 5,\n" +
            "          \"status\": 200\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"_index\": \"arius.gateway.join_2018-12-26\",\n" +
            "        \"_type\": \"type\",\n" +
            "        \"_id\": \"083bd01f-17b9-453f-a50e-9b1c5312ca46\",\n" +
            "        \"_score\": 4.6772404,\n" +
            "        \"_source\": {\n" +
            "          \"ariusCreateTime\": \"2018-12-26 03:30:03.511 +0800\",\n" +
            "          \"selectFields\": \"*,new_occupy_user\",\n" +
            "          \"groupByFields\": \"new_occupy_user\",\n" +
            "          \"responseLen\": 345,\n" +
            "          \"beforeCost\": 1,\n" +
            "          \"isTimedOut\": \"false\",\n" +
            "          \"totalShards\": 4,\n" +
            "          \"scrollId\": \"null\",\n" +
            "          \"failedShards\": 0,\n" +
            "          \"dslTemplate\": \"SELECT COUNT(*) AS count, new_occupy_user FROM ? WHERE _type = ?  AND allocate_type = ?  AND new_occupy_user IN (?) GROUP BY new_occupy_user\",\n" +
            "          \"dslType\": \"aggs\",\n" +
            "          \"newDslTemplate\": \"SELECT COUNT(*) AS count FROM ? WHERE new_occupy_user IN (?) AND allocate_type = ? AND _type = ? GROUP BY new_occupy_user\",\n" +
            "          \"esCost\": 1,\n" +
            "          \"requestId\": \"083bd01f-17b9-453f-a50e-9b1c5312ca46\",\n" +
            "          \"aggsLevel\": 2,\n" +
            "          \"sinkTime\": 1545766203511,\n" +
            "          \"indiceCount\": 1,\n" +
            "          \"clientNode\": \"trib-common-sf-896f0-11.docker.ys/10.169.84.13:9350\",\n" +
            "          \"internalCost\": 2,\n" +
            "          \"remoteAddr\": \"10.160.128.131\",\n" +
            "          \"orderByFields\": \"\",\n" +
            "          \"lastBucketNumber\": 0,\n" +
            "          \"method\": \"POST\",\n" +
            "          \"requestType\": \"http\",\n" +
            "          \"searchType\": \"sql\",\n" +
            "          \"successfulShards\": 4,\n" +
            "          \"index\": \"{}\",\n" +
            "          \"whereFields\": \"_type,allocate_type,new_occupy_user\",\n" +
            "          \"gatewayNode\": \"bigdata-arius-ser516.gz01\",\n" +
            "          \"queryString\": \"null\",\n" +
            "          \"indiceSample\": \"cn_mysql_menasor_2018-12-26\",\n" +
            "          \"timeStamp\": 1545766201337,\n" +
            "          \"indices\": \"cn_mysql_menasor_2018-12-26\",\n" +
            "          \"dslLen\": 195,\n" +
            "          \"dslTemplateMd5\": [\n" +
            "            \"C8614D5359CF3F762E100811A1301B77\",\n" +
            "            \"V2_A6B7843D56AD3803280A379ED186A086\"\n" +
            "          ],\n" +
            "          \"totalHits\": 0,\n" +
            "          \"appid\": 1577,\n" +
            "          \"memUsed\": 0,\n" +
            "          \"dsl\": \"select count(*) as count, new_occupy_user from cn_mysql_menasor_2018-12-26 where _type='r_allocate_info' and allocate_type=2 and new_occupy_user in (154868,163634,132750) group by new_occupy_user\",\n" +
            "          \"bucketNumber\": 200,\n" +
            "          \"totalCost\": 3,\n" +
            "          \"status\": 200\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"_index\": \"arius.gateway.join_2018-12-26\",\n" +
            "        \"_type\": \"type\",\n" +
            "        \"_id\": \"17d90d93-b483-4878-8a58-7e4dc17ea749\",\n" +
            "        \"_score\": 4.6772404,\n" +
            "        \"_source\": {\n" +
            "          \"ariusCreateTime\": \"2018-12-26 03:30:03.788 +0800\",\n" +
            "          \"selectFields\": \"*\",\n" +
            "          \"groupByFields\": \"a,b,r,la,time,rp,ra\",\n" +
            "          \"responseLen\": 164,\n" +
            "          \"beforeCost\": 0,\n" +
            "          \"isTimedOut\": \"false\",\n" +
            "          \"totalShards\": 4,\n" +
            "          \"scrollId\": \"null\",\n" +
            "          \"failedShards\": 0,\n" +
            "          \"dslTemplate\": \"{\\\"size\\\":\\\"?\\\",\\\"query\\\":{\\\"bool\\\":{\\\"must_not\\\":{\\\"term\\\":{\\\"vp\\\":\\\"?\\\"}},\\\"must\\\":[{\\\"term\\\":{\\\"m\\\":\\\"?\\\"}},{\\\"range\\\":{\\\"time\\\":{\\\"lt\\\":\\\"?\\\",\\\"gte\\\":\\\"?\\\"}}}]}},\\\"aggs\\\":{\\\"date_agg\\\":{\\\"date_histogram\\\":{\\\"field\\\":\\\"?\\\",\\\"interval\\\":\\\"?\\\",\\\"min_doc_count\\\":\\\"?\\\",\\\"extended_bounds\\\":{\\\"min\\\":\\\"?\\\",\\\"max\\\":\\\"?\\\"}},\\\"aggs\\\":{\\\"server_ip\\\":{\\\"terms\\\":{\\\"field\\\":\\\"?\\\",\\\"size\\\":\\\"?\\\"},\\\"aggs\\\":{\\\"client_ip\\\":{\\\"terms\\\":{\\\"field\\\":\\\"?\\\",\\\"size\\\":\\\"?\\\"},\\\"aggs\\\":{\\\"server_port\\\":{\\\"terms\\\":{\\\"field\\\":\\\"?\\\",\\\"size\\\":\\\"?\\\"},\\\"aggs\\\":{\\\"max_rtt\\\":{\\\"max\\\":{\\\"field\\\":\\\"?\\\"}},\\\"max_b\\\":{\\\"max\\\":{\\\"field\\\":\\\"?\\\"}},\\\"max_a\\\":{\\\"max\\\":{\\\"field\\\":\\\"?\\\"}}}}}}}}}}}}\",\n" +
            "          \"dslType\": \"aggs\",\n" +
            "          \"newDslTemplate\": \"{\\\"aggs\\\":{\\\"date_agg\\\":{\\\"aggs\\\":{\\\"server_ip\\\":{\\\"aggs\\\":{\\\"client_ip\\\":{\\\"aggs\\\":{\\\"server_port\\\":{\\\"aggs\\\":{\\\"max_a\\\":{\\\"max\\\":{\\\"field\\\":\\\"a\\\"}},\\\"max_b\\\":{\\\"max\\\":{\\\"field\\\":\\\"b\\\"}},\\\"max_rtt\\\":{\\\"max\\\":{\\\"field\\\":\\\"r\\\"}}},\\\"terms\\\":{\\\"field\\\":\\\"rp\\\",\\\"size\\\":\\\"?\\\"}}},\\\"terms\\\":{\\\"field\\\":\\\"la\\\",\\\"size\\\":\\\"?\\\"}}},\\\"terms\\\":{\\\"field\\\":\\\"ra\\\",\\\"size\\\":\\\"?\\\"}}},\\\"date_histogram\\\":{\\\"extended_bounds\\\":{\\\"min\\\":\\\"?\\\",\\\"max\\\":\\\"?\\\"},\\\"field\\\":\\\"time\\\",\\\"interval\\\":\\\"?\\\",\\\"min_doc_count\\\":\\\"?\\\"}}},\\\"query\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"range\\\":{\\\"time\\\":{\\\"lt\\\":\\\"?\\\",\\\"gte\\\":\\\"?\\\"}}},{\\\"term\\\":{\\\"m\\\":\\\"?\\\"}}],\\\"must_not\\\":{\\\"term\\\":{\\\"vp\\\":\\\"?\\\"}}}},\\\"size\\\":\\\"?\\\"}\",\n" +
            "          \"esCost\": 2,\n" +
            "          \"requestId\": \"17d90d93-b483-4878-8a58-7e4dc17ea749\",\n" +
            "          \"aggsLevel\": 5,\n" +
            "          \"sinkTime\": 1545766203788,\n" +
            "          \"indiceCount\": 1,\n" +
            "          \"clientNode\": \"trib-common-sf-896f0-2.docker.ys/10.169.72.21:9350\",\n" +
            "          \"internalCost\": 2,\n" +
            "          \"remoteAddr\": \"10.86.80.33\",\n" +
            "          \"orderByFields\": \"\",\n" +
            "          \"lastBucketNumber\": 0,\n" +
            "          \"method\": \"POST\",\n" +
            "          \"requestType\": \"http\",\n" +
            "          \"searchType\": \"dsl\",\n" +
            "          \"successfulShards\": 4,\n" +
            "          \"index\": \"{}\",\n" +
            "          \"whereFields\": \"vp,time,m\",\n" +
            "          \"gatewayNode\": \"bigdata-arius-ser203.gz01\",\n" +
            "          \"queryString\": \"null\",\n" +
            "          \"indiceSample\": \"cn_dba_automarket.exclude.dba_2018-12-26\",\n" +
            "          \"timeStamp\": 1545766201058,\n" +
            "          \"indices\": \"cn_dba_automarket.exclude.dba_2018-12-26\",\n" +
            "          \"dslLen\": 575,\n" +
            "          \"dslTemplateMd5\": [\n" +
            "            \"5604CF5D0DE28E59CB6CAC6ED2520E23\",\n" +
            "            \"V2_78218B0F2F6D22C2DD11EC9C2FE0B1B4\"\n" +
            "          ],\n" +
            "          \"totalHits\": 0,\n" +
            "          \"appid\": 711,\n" +
            "          \"memUsed\": 0,\n" +
            "          \"dsl\": \"{\\\"size\\\":0,\\\"query\\\":{\\\"bool\\\":{\\\"must_not\\\":{\\\"term\\\":{\\\"vp\\\":3306}},\\\"must\\\":[{\\\"term\\\":{\\\"m\\\":\\\"c\\\"}},{\\\"range\\\":{\\\"time\\\":{\\\"lt\\\":1545766011127,\\\"gte\\\":1545766001127}}}]}},\\\"aggs\\\":{\\\"date_agg\\\":{\\\"date_histogram\\\":{\\\"field\\\":\\\"time\\\",\\\"interval\\\":\\\"1s\\\",\\\"min_doc_count\\\":1,\\\"extended_bounds\\\":{\\\"min\\\":1545766001127,\\\"max\\\":1545766011127}},\\\"aggs\\\":{\\\"server_ip\\\":{\\\"terms\\\":{\\\"field\\\":\\\"ra\\\",\\\"size\\\":1000},\\\"aggs\\\":{\\\"client_ip\\\":{\\\"terms\\\":{\\\"field\\\":\\\"la\\\",\\\"size\\\":1000},\\\"aggs\\\":{\\\"server_port\\\":{\\\"terms\\\":{\\\"field\\\":\\\"rp\\\",\\\"size\\\":100},\\\"aggs\\\":{\\\"max_rtt\\\":{\\\"max\\\":{\\\"field\\\":\\\"r\\\"}},\\\"max_b\\\":{\\\"max\\\":{\\\"field\\\":\\\"b\\\"}},\\\"max_a\\\":{\\\"max\\\":{\\\"field\\\":\\\"a\\\"}}}}}}}}}}}}\",\n" +
            "          \"bucketNumber\": 375000,\n" +
            "          \"totalCost\": 4,\n" +
            "          \"status\": 200\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"_index\": \"arius.gateway.join_2018-12-26\",\n" +
            "        \"_type\": \"type\",\n" +
            "        \"_id\": \"a6e6f2d6-7761-4cfd-b7b8-17fd65c0eaec\",\n" +
            "        \"_score\": 4.6772404,\n" +
            "        \"_source\": {\n" +
            "          \"ariusCreateTime\": \"2018-12-26 03:30:03.996 +0800\",\n" +
            "          \"selectFields\": \"*\",\n" +
            "          \"groupByFields\": \"POWER_STATUS,OUT_OF_RANGE,ORDER_STATUS,OPERATION_STATUS\",\n" +
            "          \"responseLen\": 303,\n" +
            "          \"beforeCost\": 0,\n" +
            "          \"isTimedOut\": \"false\",\n" +
            "          \"totalShards\": 20,\n" +
            "          \"scrollId\": \"null\",\n" +
            "          \"failedShards\": 0,\n" +
            "          \"dslTemplate\": \"{\\\"size\\\":\\\"?\\\",\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"CITY_ID\\\":\\\"?\\\"}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}]}}]}}}},\\\"_source\\\":\\\"?\\\",\\\"aggs\\\":{\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}}},\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}}}}\",\n" +
            "          \"dslType\": \"aggs\",\n" +
            "          \"newDslTemplate\": \"{\\\"_source\\\":\\\"[?]\\\",\\\"aggs\\\":{\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}}}},\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"CITY_ID\\\":\\\"?\\\"}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}]}}]}}}},\\\"size\\\":\\\"?\\\"}\",\n" +
            "          \"esCost\": 4,\n" +
            "          \"requestId\": \"a6e6f2d6-7761-4cfd-b7b8-17fd65c0eaec\",\n" +
            "          \"aggsLevel\": 1,\n" +
            "          \"sinkTime\": 1545766203996,\n" +
            "          \"indiceCount\": 1,\n" +
            "          \"clientNode\": \"trib-common-sf-896f0-12.docker.ys/10.169.88.11:9350\",\n" +
            "          \"internalCost\": 1,\n" +
            "          \"remoteAddr\": \"100.69.157.58\",\n" +
            "          \"orderByFields\": \"\",\n" +
            "          \"lastBucketNumber\": 0,\n" +
            "          \"method\": \"POST\",\n" +
            "          \"requestType\": \"http\",\n" +
            "          \"searchType\": \"dsl\",\n" +
            "          \"successfulShards\": 20,\n" +
            "          \"index\": \"{}\",\n" +
            "          \"whereFields\": \"CITY_ID,POWER_STATUS,OUT_OF_RANGE,ORDER_STATUS,BUSINESS_UNIT,OPERATION_STATUS\",\n" +
            "          \"gatewayNode\": \"bigdata-arius-ser203.gz01\",\n" +
            "          \"queryString\": \"null\",\n" +
            "          \"indiceSample\": \"nsky_realtime_label*\",\n" +
            "          \"timeStamp\": 1545766201995,\n" +
            "          \"indices\": \"nsky_realtime_label*\",\n" +
            "          \"dslLen\": 699,\n" +
            "          \"dslTemplateMd5\": [\n" +
            "            \"FCFFEDE7D07468304FC73A0A1427FE64\",\n" +
            "            \"V2_7C27B4C7F2CCFB17781701B7B286EF8B\"\n" +
            "          ],\n" +
            "          \"totalHits\": 164,\n" +
            "          \"appid\": 591,\n" +
            "          \"memUsed\": 0,\n" +
            "          \"dsl\": \"{\\\"size\\\":0,\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":10}},{\\\"term\\\":{\\\"CITY_ID\\\":110}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"term\\\":{\\\"POWER_STATUS\\\":2}},{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":100}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}},{\\\"term\\\":{\\\"POWER_STATUS\\\":3}},{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":1}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}}]}}}},\\\"_source\\\":false,\\\"aggs\\\":{\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":2}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":100}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":3}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":1}}},\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}}}}\",\n" +
            "          \"bucketNumber\": 5,\n" +
            "          \"totalCost\": 5,\n" +
            "          \"status\": 200\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"_index\": \"arius.gateway.join_2018-12-26\",\n" +
            "        \"_type\": \"type\",\n" +
            "        \"_id\": \"c9ddface-abe4-4501-9131-1af966fe822a\",\n" +
            "        \"_score\": 4.6772404,\n" +
            "        \"_source\": {\n" +
            "          \"ariusCreateTime\": \"2018-12-26 03:30:04.948 +0800\",\n" +
            "          \"selectFields\": \"*\",\n" +
            "          \"groupByFields\": \"POWER_STATUS,OUT_OF_RANGE,ORDER_STATUS,OPERATION_STATUS\",\n" +
            "          \"responseLen\": 296,\n" +
            "          \"beforeCost\": 0,\n" +
            "          \"isTimedOut\": \"false\",\n" +
            "          \"totalShards\": 20,\n" +
            "          \"scrollId\": \"null\",\n" +
            "          \"failedShards\": 0,\n" +
            "          \"dslTemplate\": \"{\\\"size\\\":\\\"?\\\",\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"CITY_ID\\\":\\\"?\\\"}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}]}}]}}}},\\\"_source\\\":\\\"?\\\",\\\"aggs\\\":{\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}}},\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}}}}\",\n" +
            "          \"dslType\": \"aggs\",\n" +
            "          \"newDslTemplate\": \"{\\\"_source\\\":\\\"[?]\\\",\\\"aggs\\\":{\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}}}},\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"CITY_ID\\\":\\\"?\\\"}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}]}}]}}}},\\\"size\\\":\\\"?\\\"}\",\n" +
            "          \"esCost\": 3,\n" +
            "          \"requestId\": \"c9ddface-abe4-4501-9131-1af966fe822a\",\n" +
            "          \"aggsLevel\": 1,\n" +
            "          \"sinkTime\": 1545766204948,\n" +
            "          \"indiceCount\": 1,\n" +
            "          \"clientNode\": \"trib-common-sf-896f0-4.docker.ys/10.168.48.10:9350\",\n" +
            "          \"internalCost\": 2,\n" +
            "          \"remoteAddr\": \"100.69.157.58\",\n" +
            "          \"orderByFields\": \"\",\n" +
            "          \"lastBucketNumber\": 0,\n" +
            "          \"method\": \"POST\",\n" +
            "          \"requestType\": \"http\",\n" +
            "          \"searchType\": \"dsl\",\n" +
            "          \"successfulShards\": 20,\n" +
            "          \"index\": \"{}\",\n" +
            "          \"whereFields\": \"CITY_ID,POWER_STATUS,OUT_OF_RANGE,ORDER_STATUS,BUSINESS_UNIT,OPERATION_STATUS\",\n" +
            "          \"gatewayNode\": \"bigdata-arius-ser203.gz01\",\n" +
            "          \"queryString\": \"null\",\n" +
            "          \"indiceSample\": \"nsky_realtime_label*\",\n" +
            "          \"timeStamp\": 1545766203206,\n" +
            "          \"indices\": \"nsky_realtime_label*\",\n" +
            "          \"dslLen\": 699,\n" +
            "          \"dslTemplateMd5\": [\n" +
            "            \"FCFFEDE7D07468304FC73A0A1427FE64\",\n" +
            "            \"V2_7C27B4C7F2CCFB17781701B7B286EF8B\"\n" +
            "          ],\n" +
            "          \"totalHits\": 1,\n" +
            "          \"appid\": 591,\n" +
            "          \"memUsed\": 0,\n" +
            "          \"dsl\": \"{\\\"size\\\":0,\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":10}},{\\\"term\\\":{\\\"CITY_ID\\\":240}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"term\\\":{\\\"POWER_STATUS\\\":2}},{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":100}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}},{\\\"term\\\":{\\\"POWER_STATUS\\\":3}},{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":1}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}}]}}}},\\\"_source\\\":false,\\\"aggs\\\":{\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":2}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":100}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":3}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":1}}},\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}}}}\",\n" +
            "          \"bucketNumber\": 5,\n" +
            "          \"totalCost\": 5,\n" +
            "          \"status\": 200\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"_index\": \"arius.gateway.join_2018-12-26\",\n" +
            "        \"_type\": \"type\",\n" +
            "        \"_id\": \"b10b2d52-5a09-4125-a3f9-936599bc77cd\",\n" +
            "        \"_score\": 4.6772404,\n" +
            "        \"_source\": {\n" +
            "          \"ariusCreateTime\": \"2018-12-26 03:30:01.748 +0800\",\n" +
            "          \"selectFields\": \"*\",\n" +
            "          \"groupByFields\": \"city_id\",\n" +
            "          \"responseLen\": 6431,\n" +
            "          \"beforeCost\": 0,\n" +
            "          \"isTimedOut\": \"false\",\n" +
            "          \"totalShards\": 1,\n" +
            "          \"scrollId\": \"null\",\n" +
            "          \"failedShards\": 0,\n" +
            "          \"dslTemplate\": \"{\\\"aggregations\\\":{\\\"groupby_city_id\\\":{\\\"terms\\\":{\\\"field\\\":\\\"?\\\",\\\"size\\\":\\\"?\\\"}}},\\\"query\\\":{\\\"bool\\\":{\\\"must\\\":{\\\"range\\\":{\\\"bind_time\\\":{\\\"include_lower\\\":\\\"?\\\",\\\"include_upper\\\":\\\"?\\\",\\\"from\\\":\\\"?\\\",\\\"to\\\":\\\"?\\\"}}}}},\\\"size\\\":\\\"?\\\"}\",\n" +
            "          \"dslType\": \"aggs\",\n" +
            "          \"newDslTemplate\": \"{\\\"aggregations\\\":{\\\"groupby_city_id\\\":{\\\"terms\\\":{\\\"field\\\":\\\"city_id\\\",\\\"size\\\":\\\"?\\\"}}},\\\"query\\\":{\\\"bool\\\":{\\\"must\\\":{\\\"range\\\":{\\\"bind_time\\\":{\\\"include_lower\\\":\\\"?\\\",\\\"include_upper\\\":\\\"?\\\",\\\"from\\\":\\\"?\\\",\\\"to\\\":\\\"?\\\"}}}}},\\\"size\\\":\\\"?\\\"}\",\n" +
            "          \"esCost\": 3,\n" +
            "          \"requestId\": \"b10b2d52-5a09-4125-a3f9-936599bc77cd\",\n" +
            "          \"aggsLevel\": 1,\n" +
            "          \"sinkTime\": 1545766201748,\n" +
            "          \"indiceCount\": 1,\n" +
            "          \"clientNode\": \"tribe-back-sf-82b6f-8.docker.ys/10.168.40.51:9350\",\n" +
            "          \"internalCost\": 2,\n" +
            "          \"remoteAddr\": \"100.90.185.61\",\n" +
            "          \"orderByFields\": \"\",\n" +
            "          \"lastBucketNumber\": 0,\n" +
            "          \"method\": \"POST\",\n" +
            "          \"requestType\": \"http\",\n" +
            "          \"searchType\": \"dsl\",\n" +
            "          \"successfulShards\": 1,\n" +
            "          \"index\": \"{}\",\n" +
            "          \"whereFields\": \"bind_time\",\n" +
            "          \"gatewayNode\": \"bigdata-arius-ser204.gz01\",\n" +
            "          \"queryString\": \"null\",\n" +
            "          \"indiceSample\": \"axb_monitor_test_2018-12-26\",\n" +
            "          \"timeStamp\": 1545766200006,\n" +
            "          \"indices\": \"axb_monitor_test_2018-12-26\",\n" +
            "          \"dslLen\": 222,\n" +
            "          \"dslTemplateMd5\": [\n" +
            "            \"FBC65DCA47742CFE57AF0EAA8A618D8E\",\n" +
            "            \"V2_8A34DBE60820B8A29B3B567FE830FC63\"\n" +
            "          ],\n" +
            "          \"totalHits\": 4729,\n" +
            "          \"appid\": 413,\n" +
            "          \"memUsed\": 0,\n" +
            "          \"dsl\": \"{\\\"aggregations\\\":{\\\"groupby_city_id\\\":{\\\"terms\\\":{\\\"field\\\":\\\"city_id\\\",\\\"size\\\":1000}}},\\\"query\\\":{\\\"bool\\\":{\\\"must\\\":{\\\"range\\\":{\\\"bind_time\\\":{\\\"from\\\":1545764280000,\\\"include_lower\\\":true,\\\"include_upper\\\":false,\\\"to\\\":1545764400000}}}}},\\\"size\\\":0}\",\n" +
            "          \"bucketNumber\": 337,\n" +
            "          \"totalCost\": 5,\n" +
            "          \"status\": 200\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"_index\": \"arius.gateway.join_2018-12-26\",\n" +
            "        \"_type\": \"type\",\n" +
            "        \"_id\": \"08bcdd53-2adc-401e-8cd5-2dee67e521ad\",\n" +
            "        \"_score\": 4.6772404,\n" +
            "        \"_source\": {\n" +
            "          \"ariusCreateTime\": \"2018-12-26 03:30:02.201 +0800\",\n" +
            "          \"selectFields\": \"*\",\n" +
            "          \"groupByFields\": \"extra.business_type_detailed,extra.dict_path_complete\",\n" +
            "          \"responseLen\": 6017,\n" +
            "          \"isTimedOut\": \"false\",\n" +
            "          \"totalShards\": 4,\n" +
            "          \"scrollId\": \"null\",\n" +
            "          \"failedShards\": 0,\n" +
            "          \"dslTemplate\": \"{\\\"size\\\":\\\"?\\\",\\\"query\\\":{\\\"range\\\":{\\\"create_time\\\":{\\\"include_lower\\\":\\\"?\\\",\\\"include_upper\\\":\\\"?\\\",\\\"from\\\":\\\"?\\\",\\\"to\\\":\\\"?\\\"}}},\\\"aggregations\\\":{\\\"extra.business_type_detailed\\\":{\\\"terms\\\":{\\\"field\\\":\\\"?\\\",\\\"size\\\":\\\"?\\\"},\\\"aggregations\\\":{\\\"extra.dict_path_complete\\\":{\\\"terms\\\":{\\\"field\\\":\\\"?\\\",\\\"size\\\":\\\"?\\\"}}}}}}\",\n" +
            "          \"routing\": \"null\",\n" +
            "          \"dslType\": \"aggs\",\n" +
            "          \"newDslTemplate\": \"{\\\"aggregations\\\":{\\\"extra.business_type_detailed\\\":{\\\"aggregations\\\":{\\\"extra.dict_path_complete\\\":{\\\"terms\\\":{\\\"field\\\":\\\"extra.dict_path_complete\\\",\\\"size\\\":\\\"?\\\"}}},\\\"terms\\\":{\\\"field\\\":\\\"extra.business_type_detailed\\\",\\\"size\\\":\\\"?\\\"}}},\\\"query\\\":{\\\"range\\\":{\\\"create_time\\\":{\\\"include_lower\\\":\\\"?\\\",\\\"include_upper\\\":\\\"?\\\",\\\"from\\\":\\\"?\\\",\\\"to\\\":\\\"?\\\"}}},\\\"size\\\":\\\"?\\\"}\",\n" +
            "          \"esCost\": 3,\n" +
            "          \"requestId\": \"08bcdd53-2adc-401e-8cd5-2dee67e521ad\",\n" +
            "          \"aggsLevel\": 2,\n" +
            "          \"sinkTime\": 1545766202201,\n" +
            "          \"action\": \"indices:data/read/search\",\n" +
            "          \"indiceCount\": 1,\n" +
            "          \"clientNode\": \"bigdata-arius-ser552.gz01/100.69.159.19:9350\",\n" +
            "          \"internalCost\": 2,\n" +
            "          \"remoteAddr\": \"100.90.208.13\",\n" +
            "          \"orderByFields\": \"\",\n" +
            "          \"lastBucketNumber\": 0,\n" +
            "          \"types\": \"type\",\n" +
            "          \"requestType\": \"tcp\",\n" +
            "          \"searchType\": \"dsl\",\n" +
            "          \"successfulShards\": 4,\n" +
            "          \"index\": \"{}\",\n" +
            "          \"whereFields\": \"create_time\",\n" +
            "          \"gatewayNode\": \"bigdata-arius-ser511.gz01\",\n" +
            "          \"indiceSample\": \"qa_arch_lmc_tpcc_fangzhou_worksheet_2018-12-26\",\n" +
            "          \"timeStamp\": 1545766200010,\n" +
            "          \"indices\": \"qa_arch_lmc_tpcc_fangzhou_worksheet_2018-12-26\",\n" +
            "          \"dslLen\": 388,\n" +
            "          \"dslTemplateMd5\": [\n" +
            "            \"DF7371E8E7F7B95DBF8C387A8BF423E6\",\n" +
            "            \"V2_3B6FB4FEED59C7A5695A8292E2EB908B\"\n" +
            "          ],\n" +
            "          \"totalHits\": 352,\n" +
            "          \"appid\": 2,\n" +
            "          \"memUsed\": 0,\n" +
            "          \"dsl\": \"{\\\"size\\\":0,\\\"query\\\":{\\\"range\\\":{\\\"create_time\\\":{\\\"from\\\":1545765300,\\\"to\\\":1545765600,\\\"include_lower\\\":true,\\\"include_upper\\\":false}}},\\\"aggregations\\\":{\\\"extra.business_type_detailed\\\":{\\\"terms\\\":{\\\"field\\\":\\\"extra.business_type_detailed\\\",\\\"size\\\":0},\\\"aggregations\\\":{\\\"extra.dict_path_complete\\\":{\\\"terms\\\":{\\\"field\\\":\\\"extra.dict_path_complete\\\",\\\"size\\\":0}}}}}}\",\n" +
            "          \"bucketNumber\": 71570,\n" +
            "          \"totalCost\": 5\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"_index\": \"arius.gateway.join_2018-12-26\",\n" +
            "        \"_type\": \"type\",\n" +
            "        \"_id\": \"985581cb-16c8-4230-a856-f9d8bb25c303\",\n" +
            "        \"_score\": 4.6772404,\n" +
            "        \"_source\": {\n" +
            "          \"ariusCreateTime\": \"2018-12-26 03:30:02.699 +0800\",\n" +
            "          \"selectFields\": \"*\",\n" +
            "          \"groupByFields\": \"POWER_STATUS,OUT_OF_RANGE,ORDER_STATUS,OPERATION_STATUS\",\n" +
            "          \"responseLen\": 300,\n" +
            "          \"beforeCost\": 0,\n" +
            "          \"isTimedOut\": \"false\",\n" +
            "          \"totalShards\": 20,\n" +
            "          \"scrollId\": \"null\",\n" +
            "          \"failedShards\": 0,\n" +
            "          \"dslTemplate\": \"{\\\"size\\\":\\\"?\\\",\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"CITY_ID\\\":\\\"?\\\"}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}]}}]}}}},\\\"_source\\\":\\\"?\\\",\\\"aggs\\\":{\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}}},\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}}}}\",\n" +
            "          \"dslType\": \"aggs\",\n" +
            "          \"newDslTemplate\": \"{\\\"_source\\\":\\\"[?]\\\",\\\"aggs\\\":{\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}}}},\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"CITY_ID\\\":\\\"?\\\"}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}}]}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":\\\"?\\\"}},{\\\"term\\\":{\\\"POWER_STATUS\\\":\\\"?\\\"}}]}}]}}}},\\\"size\\\":\\\"?\\\"}\",\n" +
            "          \"esCost\": 3,\n" +
            "          \"requestId\": \"985581cb-16c8-4230-a856-f9d8bb25c303\",\n" +
            "          \"aggsLevel\": 1,\n" +
            "          \"sinkTime\": 1545766202699,\n" +
            "          \"indiceCount\": 1,\n" +
            "          \"clientNode\": \"trib-common-sf-896f0-14.docker.ys/10.169.72.24:9350\",\n" +
            "          \"internalCost\": 2,\n" +
            "          \"remoteAddr\": \"100.69.157.58\",\n" +
            "          \"orderByFields\": \"\",\n" +
            "          \"lastBucketNumber\": 0,\n" +
            "          \"method\": \"POST\",\n" +
            "          \"requestType\": \"http\",\n" +
            "          \"searchType\": \"dsl\",\n" +
            "          \"successfulShards\": 20,\n" +
            "          \"index\": \"{}\",\n" +
            "          \"whereFields\": \"CITY_ID,POWER_STATUS,OUT_OF_RANGE,ORDER_STATUS,BUSINESS_UNIT,OPERATION_STATUS\",\n" +
            "          \"gatewayNode\": \"bigdata-arius-ser202.gz01\",\n" +
            "          \"queryString\": \"null\",\n" +
            "          \"indiceSample\": \"nsky_realtime_label*\",\n" +
            "          \"timeStamp\": 1545766202076,\n" +
            "          \"indices\": \"nsky_realtime_label*\",\n" +
            "          \"dslLen\": 698,\n" +
            "          \"dslTemplateMd5\": [\n" +
            "            \"FCFFEDE7D07468304FC73A0A1427FE64\",\n" +
            "            \"V2_7C27B4C7F2CCFB17781701B7B286EF8B\"\n" +
            "          ],\n" +
            "          \"totalHits\": 116,\n" +
            "          \"appid\": 591,\n" +
            "          \"memUsed\": 0,\n" +
            "          \"dsl\": \"{\\\"size\\\":0,\\\"query\\\":{\\\"bool\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"BUSINESS_UNIT\\\":10}},{\\\"term\\\":{\\\"CITY_ID\\\":22}}]}},{\\\"bool\\\":{\\\"should\\\":[{\\\"term\\\":{\\\"POWER_STATUS\\\":2}},{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":100}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}},{\\\"term\\\":{\\\"POWER_STATUS\\\":3}},{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":1}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}}]}}}},\\\"_source\\\":false,\\\"aggs\\\":{\\\"POWER_LOW\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":2}}},\\\"RIDING_STATUS\\\":{\\\"filter\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"OPERATION_STATUS\\\":100}},{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}]}}},\\\"POWER_LACK\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"POWER_STATUS\\\":3}}},\\\"OUT_OF_RANGE\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"OUT_OF_RANGE\\\":1}}},\\\"ORDER_IN_PROGRESS\\\":{\\\"filter\\\":{\\\"term\\\":{\\\"ORDER_STATUS\\\":1}}}}}\",\n" +
            "          \"bucketNumber\": 5,\n" +
            "          \"totalCost\": 5,\n" +
            "          \"status\": 200\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"aggregations\": {\n" +
            "    \"xxx\": {\n" +
            "      \"doc_count_error_upper_bound\": 0,\n" +
            "      \"sum_other_doc_count\": 0,\n" +
            "      \"buckets\": [\n" +
            "        {\n" +
            "          \"key\": \"aggs\",\n" +
            "          \"doc_count\": 9462180\n" +
            "        },\n" +
            "        {\n" +
            "          \"key\": \"scroll\",\n" +
            "          \"doc_count\": 8671497\n" +
            "        },\n" +
            "        {\n" +
            "          \"key\": \"normal\",\n" +
            "          \"doc_count\": 5627751\n" +
            "        },\n" +
            "        {\n" +
            "          \"key\": \"msearch\",\n" +
            "          \"doc_count\": 13543\n" +
            "        },\n" +
            "        {\n" +
            "          \"key\": \"mget\",\n" +
            "          \"doc_count\": 5360\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"NAME\": {\n" +
            "      \"doc_count_error_upper_bound\": 124110,\n" +
            "      \"sum_other_doc_count\": 5604404,\n" +
            "      \"buckets\": [\n" +
            "        {\n" +
            "          \"key\": \"dquality_metrics_2018-12-26\",\n" +
            "          \"doc_count\": 4919576,\n" +
            "          \"xxx\": {\n" +
            "            \"doc_count_error_upper_bound\": 0,\n" +
            "            \"sum_other_doc_count\": 0,\n" +
            "            \"buckets\": [\n" +
            "              {\n" +
            "                \"key\": \"{}\",\n" +
            "                \"doc_count\": 4919572\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"key\": \"man_opph_ordercenter_binlog*\",\n" +
            "          \"doc_count\": 4691930,\n" +
            "          \"xxx\": {\n" +
            "            \"doc_count_error_upper_bound\": 7346,\n" +
            "            \"sum_other_doc_count\": 735034,\n" +
            "            \"buckets\": [\n" +
            "              {\n" +
            "                \"key\": \"{\\\"man_opph_ordercenter_binlog_201812\\\":1}\",\n" +
            "                \"doc_count\": 3800249\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": \"{\\\"man_opph_ordercenter_binlog_201812\\\":10}\",\n" +
            "                \"doc_count\": 156647\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  }\n" +
            "}";

}
