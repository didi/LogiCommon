package com.didiglobal.logi.elasticsearch.client.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.response.query.query.aggs.ESAggrMap;
import org.junit.Test;

public class AggsTest {

    @Test
    public void test() {
        JSONObject obj = JSON.parseObject(str);
        ESAggrMap esAggrMap = new ESAggrMap(obj);

        JSONObject ret = esAggrMap.toJson();
        System.out.println(ret.toJSONString());

        if (obj.equals(ret)) {
            System.out.println("ok");
        } else {
            System.out.println("obj:" + obj.toJSONString());
            System.out.println("ret:" + ret.toJSONString());
        }
    }

    private static String todoStr = "{\n" +
            "    \"other_aggs\": {\n" +
            "      \"buckets\": {\n" +
            "        \"all_country\": {\n" +
            "          \"doc_count\": 0,\n" +
            "          \"result_count_avg\": {\n" +
            "            \"value\": null\n" +
            "          },\n" +
            "          \"result_count_percentile\": {\n" +
            "            \"values\": {\n" +
            "              \"10.0\": \"NaN\",\n" +
            "              \"50.0\": \"NaN\"\n" +
            "            }\n" +
            "          },\n" +
            "          \"resp_time_percentile\": {\n" +
            "            \"values\": {\n" +
            "              \"50.0\": \"NaN\",\n" +
            "              \"90.0\": \"NaN\",\n" +
            "              \"99.0\": \"NaN\"\n" +
            "            }\n" +
            "          },\n" +
            "          \"truncate_count\": {\n" +
            "            \"doc_count\": 0\n" +
            "          },\n" +
            "          \"empty_result_count\": {\n" +
            "            \"doc_count\": 0\n" +
            "          }\n" +
            "        },\n" +
            "        \"other_city\": {\n" +
            "          \"doc_count\": 0,\n" +
            "          \"result_count_avg\": {\n" +
            "            \"value\": null\n" +
            "          },\n" +
            "          \"result_count_percentile\": {\n" +
            "            \"values\": {\n" +
            "              \"10.0\": \"NaN\",\n" +
            "              \"50.0\": \"NaN\"\n" +
            "            }\n" +
            "          },\n" +
            "          \"resp_time_percentile\": {\n" +
            "            \"values\": {\n" +
            "              \"50.0\": \"NaN\",\n" +
            "              \"90.0\": \"NaN\",\n" +
            "              \"99.0\": \"NaN\"\n" +
            "            }\n" +
            "          },\n" +
            "          \"truncate_count\": {\n" +
            "            \"doc_count\": 0\n" +
            "          },\n" +
            "          \"empty_result_count\": {\n" +
            "            \"doc_count\": 0\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }";


    private static String str = "{\n" +
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
            "          },\n" +
            "          \"aaaa\": {\n" +
            "            \"doc_count_error_upper_bound\": 0,\n" +
            "            \"sum_other_doc_count\": 0,\n" +
            "            \"buckets\": [\n" +
            "              {\n" +
            "                \"key\": \"normal\",\n" +
            "                \"doc_count\": 4919576\n" +
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
            "          },\n" +
            "          \"aaaa\": {\n" +
            "            \"doc_count_error_upper_bound\": 0,\n" +
            "            \"sum_other_doc_count\": 0,\n" +
            "            \"buckets\": [\n" +
            "              {\n" +
            "                \"key\": \"aggs\",\n" +
            "                \"doc_count\": 4691930\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  }";
}
