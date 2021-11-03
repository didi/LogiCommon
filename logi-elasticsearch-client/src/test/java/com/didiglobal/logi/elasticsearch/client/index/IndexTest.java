package com.didiglobal.logi.elasticsearch.client.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.request.batch.BatchType;
import com.didiglobal.logi.elasticsearch.client.request.batch.ESBatchRequestBuilder;
import com.didiglobal.logi.elasticsearch.client.response.batch.ESBatchResponse;
import com.didiglobal.logi.elasticsearch.client.response.indices.frozen.ESIndicesFreezeIndexResponse;
import com.didiglobal.logi.elasticsearch.client.response.setting.common.MappingConfig;
import org.junit.Test;

import java.util.Set;

import static com.didiglobal.logi.elasticsearch.client.TestUtils.client;
import static com.didiglobal.logi.elasticsearch.client.TestUtils.index;

public class IndexTest {

    private static final String ENABLED = "enabled";
    private static final String DYNAMIC = "dynamic";
    private static final String DOC_VALUE = "doc_value";
    private static final String INDEX = "index";

    @Test
    public void testtest() throws Exception {
        String str = "{\"doc\":{\"dynamic_date_formats\":[\"strict_date_optional_time\",\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"],\"numeric_detection\":false,\"dynamic_templates\":[{\"extractLevel_fields\":{\"mapping\":{\"ignore_above\":512,\"type\":\"keyword\"},\"match_mapping_type\":\"string\",\"match\":\"extractLevel\"}},{\"message_fields\":{\"mapping\":{\"ignore_above\":2048,\"index\":\"false\",\"type\":\"keyword\",\"doc_values\":false},\"match_mapping_type\":\"string\",\"match\":\"message\"}},{\"string_fields\":{\"mapping\":{\"ignore_above\":512,\"type\":\"keyword\"},\"match_mapping_type\":\"string\",\"match\":\"*\"}},{\"timestampField\":{\"mapping\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"match_mapping_type\":\"long\",\"match\":\"*imestamp*\"}},{\"logTimeField\":{\"mapping\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"match_mapping_type\":\"long\",\"match\":\"logTime\"}},{\"sinkTimeField\":{\"mapping\":{\"format\":\"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\"type\":\"date\"},\"match_mapping_type\":\"long\",\"match\":\"sinkTime\"}}],\"_all\":{\"enabled\":false},\"properties\":{\"age\":{\"index\":false,\"type\":\"integer\"}}},\"type\":{\"properties\":{\"age\":{\"index\":false,\"type\":\"integer\",\"doc_values\":true}}}}";


        MappingConfig mappingConfig = new MappingConfig(JSONObject.parseObject(str));

        Set<String> ret = mappingConfig.checkMapping();

        System.out.printf(ret.toString());
    }

    @Test
    public void batch() throws Exception {
        try {
            client.admin().indices().prepareDeleteIndex(index).execute().get();
        } catch (Throwable t) {
        }

        assert client.admin().indices().preparePutIndex(index).execute().get().getAcknowledged();

        ESBatchRequestBuilder batcher = client.prepareBatch();

        String content = "{\"hello\":\"world\"}";
        for (int i = 0; i < 4; i++) {
            batcher.addNode(BatchType.INDEX, index, "type", "" + i, content);
        }
        ESBatchResponse response = batcher.execute().actionGet();
        System.out.println(JSON.toJSONString(response));
        assert !response.getErrors();

        assert client.admin().indices().prepareDeleteIndex(index).execute().get().getAcknowledged();
    }


    @Test
    public void open() {
        client.admin().indices().prepareOpenIndex("test_index").execute().actionGet();
    }

    @Test
    public void close() {
        client.admin().indices().prepareCloseIndex("test_index").execute().actionGet();
    }


    @Test
    public void deleteByQuery() {


        String query = "{}";
//        client.prepareQuery("test_index").setQuery(query).execute().actionGet();

        query = "{\n" +
                "  \"query\": {\n" +
                "    \"term\": {\n" +
                "      \"name\": {\n" +
                "        \"value\": \"hello2\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        client.admin().indices().prepareDeleteByQuery()
                .setIndex("zhonghua_test")
                .setType("type")
                .setHighES(true)
                .setQuery(query)
                .execute().actionGet();
    }


    @Test
    public void frozen() {
        ESIndicesFreezeIndexResponse response = client.admin().indices().prepareFreezeIndex(true, "my_test").execute().actionGet();

        System.out.printf("hello world");

        response = client.admin().indices().prepareFreezeIndex(false, "my_test").execute().actionGet();


        System.out.printf("hello world");
    }
}

