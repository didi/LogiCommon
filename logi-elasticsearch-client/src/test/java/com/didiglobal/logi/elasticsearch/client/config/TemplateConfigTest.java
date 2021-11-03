package com.didiglobal.logi.elasticsearch.client.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.model.type.ESVersion;
import com.didiglobal.logi.elasticsearch.client.response.setting.template.TemplateConfig;
import org.junit.Test;

public class TemplateConfigTest {

    @Test
    public void test() throws Exception {
        JSONObject obj233 = JSON.parseObject(config233);
        JSONObject obj651 = JSON.parseObject(config651);

        check(obj233, new TemplateConfig(obj233).toJson(ESVersion.ES233));
        check(obj651, new TemplateConfig(obj233).toJson(ESVersion.ES651));

        check(obj233, new TemplateConfig(obj651).toJson(ESVersion.ES233));
        check(obj651, new TemplateConfig(obj651).toJson(ESVersion.ES651));

    }

    private void check(JSONObject o1, JSONObject o2) {
        if (o1.toJSONString().equals(o2.toJSONString())) {
            System.out.println("ok");
        } else {
            System.out.println("in:" + o1.toJSONString());
            System.out.println("ou:" + o2.toJSONString());
        }
    }

    private static final String config233 = "{\n" +
            "    \"order\" : 2147483647,\n" +
            "    \"template\" : \".watches*\"\n," +
            "    \"settings\" : {\n" +
            "      \"index\" : {\n" +
            "        \"format\" : \"6\",\n" +
            "        \"number_of_shards\" : \"1\",\n" +
            "        \"priority\" : \"800\",\n" +
            "        \"auto_expand_replicas\" : \"0-1\",\n" +
            "        \"number_of_replicas\" : \"0\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"mappings\" : {\n" +
            "      \"doc\" : {\n" +
            "        \"dynamic\" : \"strict\",\n" +
            "        \"properties\" : {\n" +
            "          \"status\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"enabled\" : false,\n" +
            "            \"dynamic\" : true\n" +
            "          },\n" +
            "          \"trigger\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"enabled\" : false,\n" +
            "            \"dynamic\" : true\n" +
            "          },\n" +
            "          \"input\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"enabled\" : false,\n" +
            "            \"dynamic\" : true\n" +
            "          },\n" +
            "          \"condition\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"enabled\" : false,\n" +
            "            \"dynamic\" : true\n" +
            "          },\n" +
            "          \"throttle_period\" : {\n" +
            "            \"type\" : \"keyword\",\n" +
            "            \"index\" : false,\n" +
            "            \"doc_values\" : false\n" +
            "          },\n" +
            "          \"throttle_period_in_millis\" : {\n" +
            "            \"type\" : \"long\",\n" +
            "            \"index\" : false,\n" +
            "            \"doc_values\" : false\n" +
            "          },\n" +
            "          \"transform\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"enabled\" : false,\n" +
            "            \"dynamic\" : true\n" +
            "          },\n" +
            "          \"actions\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"enabled\" : false,\n" +
            "            \"dynamic\" : true\n" +
            "          },\n" +
            "          \"metadata\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"dynamic\" : true\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    \"aliases\" : { }\n" +
            "  }";

    private static final String config651 = "{\n" +
            "    \"order\" : 2147483647,\n" +
            "    \"index_patterns\" : [\n" +
            "      \".watches*\"\n" +
            "    ],\n" +
            "    \"settings\" : {\n" +
            "      \"index\" : {\n" +
            "        \"format\" : \"6\",\n" +
            "        \"number_of_shards\" : \"1\",\n" +
            "        \"priority\" : \"800\",\n" +
            "        \"auto_expand_replicas\" : \"0-1\",\n" +
            "        \"number_of_replicas\" : \"0\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"mappings\" : {\n" +
            "      \"doc\" : {\n" +
            "        \"dynamic\" : \"strict\",\n" +
            "        \"properties\" : {\n" +
            "          \"status\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"enabled\" : false,\n" +
            "            \"dynamic\" : true\n" +
            "          },\n" +
            "          \"trigger\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"enabled\" : false,\n" +
            "            \"dynamic\" : true\n" +
            "          },\n" +
            "          \"input\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"enabled\" : false,\n" +
            "            \"dynamic\" : true\n" +
            "          },\n" +
            "          \"condition\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"enabled\" : false,\n" +
            "            \"dynamic\" : true\n" +
            "          },\n" +
            "          \"throttle_period\" : {\n" +
            "            \"type\" : \"keyword\",\n" +
            "            \"index\" : false,\n" +
            "            \"doc_values\" : false\n" +
            "          },\n" +
            "          \"throttle_period_in_millis\" : {\n" +
            "            \"type\" : \"long\",\n" +
            "            \"index\" : false,\n" +
            "            \"doc_values\" : false\n" +
            "          },\n" +
            "          \"transform\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"enabled\" : false,\n" +
            "            \"dynamic\" : true\n" +
            "          },\n" +
            "          \"actions\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"enabled\" : false,\n" +
            "            \"dynamic\" : true\n" +
            "          },\n" +
            "          \"metadata\" : {\n" +
            "            \"type\" : \"object\",\n" +
            "            \"dynamic\" : true\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    \"aliases\" : { }\n" +
            "  }";
}
