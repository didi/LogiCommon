package com.didiglobal.logi.elasticsearch.client.query;

import com.didiglobal.logi.elasticsearch.client.response.query.query.ESQueryResponse;
import org.junit.Test;

import static com.didiglobal.logi.elasticsearch.client.TestUtils.client;

public class QueryTest {
    @Test
    public void query() throws Exception {
        ESQueryResponse response =
                client.prepareQuery("arius.gateway.join*")
//                              QueryBuilders.rangeQuery("timestamp").from(System.currentTimeMillis()))
                        .setSize(20)
                        .execute()
                        .actionGet();

        System.out.println(response);
    }


    @Test
    public void queryScrollId() throws Exception {
        ESQueryResponse response = client.prepareQueryScroll("11")
                .execute()
                .actionGet();

        System.out.println(response);
    }
}

