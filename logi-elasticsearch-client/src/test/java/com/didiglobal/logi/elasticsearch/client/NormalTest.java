package com.didiglobal.logi.elasticsearch.client;

import org.junit.Test;

public class NormalTest {

    @Test
    public void testxxx() throws Exception {

//        String repStr = s;
//        System.out.println("repStr:" + repStr);
//
////        InputStream is = new ByteArrayInputStream(repStr.getBytes("UTF-8"));
//        NamedXContentRegistry registry =  new NamedXContentRegistry(new ArrayList<>());
//
//        XContentParser parser = JsonXContent.jsonXContent.createParser(registry, LoggingDeprecationHandler.INSTANCE, repStr);
//
//        SearchResponse sr = SearchResponseConvert.fromXContent(parser);
//
//        System.out.println(sr.toString());
//        System.out.print("hello");
    }


    private String s = "{\n" +
            "  \"took\": 268,\n" +
            "  \"timed_out\": false,\n" +
            "  \"_shards\": {\n" +
            "    \"total\": 24,\n" +
            "    \"successful\": 24,\n" +
            "    \"failed\": 0\n" +
            "  },\n" +
            "  \"hits\": {\n" +
            "    \"total\": 294710340,\n" +
            "    \"max_score\": 1,\n" +
            "    \"hits\": [\n" +
            "      {\n" +
            "        \"_index\": \"arius.gateway.join_2018-10-12\",\n" +
            "        \"_type\": \"type\",\n" +
            "        \"_id\": \"96255b8b-836e-4fc3-af32-b1674500ef4d\",\n" +
            "        \"_score\": 1,\n" +
            "        \"_source\": {\n" +
            "          \"ariusCreateTime\": \"2018-10-12 03:38:43.556 +0800\",\n" +
            "          \"selectFields\": \"*\",\n" +
            "          \"groupByFields\": \"\",\n" +
            "          \"responseLen\": 3849,\n" +
            "          \"isTimedOut\": \"false\",\n" +
            "          \"totalShards\": 84,\n" +
            "          \"scrollId\": \"cXVlcnlUaGVuRmV0Y2g7ODQ7ODcyODM5MjM0Oml0N2lmSHRzU2l5RDZPUm5EeEZjZ2c7MjA4NzE4MjQ1MTpqTWNlMThrWlFmNlE4d0dzcE04ZkVBOzIwODcxODI0NTI6ak1jZTE4a1pRZjZROHdHc3BNOGZFQTsxNzEzMjY4NzA1Okc5ZFUzQWtyUUg2ZzFtUmpRYWI1cGc7MTAyNDE2MzQzNDpxZ2p2dDFyU1NqYUEzTWNVUVE2STFnOzcwOTMyMTc1OTpsbGZnS3EwQ1JWMmtSZk83aDlicUNROzcwOTMyMTc2MDpsbGZnS3EwQ1JWMmtSZk83aDlicUNROzE3MTMyNjg3MDY6RzlkVTNBa3JRSDZnMW1SalFhYjVwZzs4NzI4MzkyMzU6aXQ3aWZIdHNTaXlENk9SbkR4RmNnZzsxNzE3MjQxNzk4Old2dnh4UU04UU91c01pV1o1c0t4T3c7MjA1NDM0MDU3NjpRMENrMjEyYlMxUzZERUlrU2FMSUV3OzIwMzkwMzQwMTQ6dzBwUGF4SlhTNnFkRmdpQnR4S3N0ZzsyMDM5MDM0MDE1OncwcFBheEpYUzZxZEZnaUJ0eEtzdGc7MjAzOTAzNDAxNjp3MHBQYXhKWFM2cWRGZ2lCdHhLc3RnOzE4NDMyMzUzNTk6NFp2TFlJSFVTX3VuYVpZa0R4Vm1iQTs4Nzk1NjYzOTk6dkNyRmJOa2ZSYXloeHUtVE15ay0xUTsyMTA0NDUzMDMyOmVBVHhscFUyUVBhYTkyUnJBT0RoSXc7MjEwNDQ1MzAzMzplQVR4bHBVMlFQYWE5MlJyQU9EaEl3OzE5MjMxMTU4ODU6MmJQMENKcHFUWk9rMGtLU3hrRkx3QTsxNzE3MjQxODAwOld2dnh4UU04UU91c01pV1o1c0t4T3c7MjEwNDQ1MzAzNTplQVR4bHBVMlFQYWE5MlJyQU9EaEl3OzE3MTcyNDE4MDE6V3Z2eHhRTThRT3VzTWlXWjVzS3hPdzsxODU0NTI4MzAyOll3QVZTcGxLUWhxWVUwRzJmaXo0WWc7NTU2ODY5MTU5Ok5UUWZMS2dlUzlhZFVpa24waWNnMVE7MTc3MTc2MTg0OTpzUVlRV3N6R1FvcXpNUTM1LTh0ZmlROzE3MTMyNjg3MDc6RzlkVTNBa3JRSDZnMW1SalFhYjVwZzsxMDI0MTYzNDM1OnFnanZ0MXJTU2phQTNNY1VRUTZJMWc7MTc3MTc2MTg1MDpzUVlRV3N6R1FvcXpNUTM1LTh0ZmlROzEzNjQ5MjU3ODM6ZVhpa0xoeHlUbktNTlhxaGprUXRudzsyMDMwNDc2MTI3OlJSTWRrMTV4UXhTQTdBX3NDSE1HVUE7MTc3MTc2MTg1MTpzUVlRV3N6R1FvcXpNUTM1LTh0ZmlROzEzNjQ5MjU3ODQ6ZVhpa0xoeHlUbktNTlhxaGprUXRudzsxODQzMjM1MzYxOjRadkxZSUhVU191bmFaWWtEeFZtYkE7ODQ2OTEyNjYwOi05RlJEVHZfUVIyWjZIZzJoNzhlTnc7MTM2NDkyNTc4NTplWGlrTGh4eVRuS01OWHFoamtRdG53OzIxMDQ0NTMwMzY6ZUFUeGxwVTJRUGFhOTJSckFPRGhJdzsxMzY0OTI1Nzg2OmVYaWtMaHh5VG5LTU5YcWhqa1F0bnc7ODQ2OTEyNjYxOi05RlJEVHZfUVIyWjZIZzJoNzhlTnc7MTg0MzIzNTM2Mzo0WnZMWUlIVVNfdW5hWllrRHhWbWJBOzE3MTcyNDE4MDM6V3Z2eHhRTThRT3VzTWlXWjVzS3hPdzs4Nzk1NjY0MDE6dkNyRmJOa2ZSYXloeHUtVE15ay0xUTsxMDUyMDAzMDMyOnZLVlJwTkNFUUtPekV4N0FwZ0E5Q2c7MjA1NDM0MDU4MzpRMENrMjEyYlMxUzZERUlrU2FMSUV3OzEwNTIwMDMwMzM6dktWUnBOQ0VRS096RXg3QXBnQTlDZzsyMDU0MzQwNTg0OlEwQ2syMTJiUzFTNkRFSWtTYUxJRXc7NzA5MzIxNzY1OmxsZmdLcTBDUlYya1JmTzdoOWJxQ1E7ODQ2OTEyNjYyOi05RlJEVHZfUVIyWjZIZzJoNzhlTnc7MTM2NDkyNTc4NzplWGlrTGh4eVRuS01OWHFoamtRdG53OzU1Njg2OTE2MzpOVFFmTEtnZVM5YWRVaWtuMGljZzFROzEzMTY5NTEwMjM6UWVBZHZOXzZRbE9xMVpZMGRRXzZmUTs1NTY4NjkxNjQ6TlRRZkxLZ2VTOWFkVWlrbjBpY2cxUTsyMDMwNDc2MTIzOlJSTWRrMTV4UXhTQTdBX3NDSE1HVUE7ODcyODM5MjQzOml0N2lmSHRzU2l5RDZPUm5EeEZjZ2c7MTA1MjAwMzAzNTp2S1ZScE5DRVFLT3pFeDdBcGdBOUNnOzg3MjgzOTI0NDppdDdpZkh0c1NpeUQ2T1JuRHhGY2dnOzg3MjgzOTI0NTppdDdpZkh0c1NpeUQ2T1JuRHhGY2dnOzcwOTMyMTc2NjpsbGZnS3EwQ1JWMmtSZk83aDlicUNROzIwNTQzNDA1ODU6UTBDazIxMmJTMVM2REVJa1NhTElFdzs0OTY3MDg0NzU6Tmctc25hdDNUd3VnV1RLUDhqa2NGUTsyMDMwNDc2MDg3OlJSTWRrMTV4UXhTQTdBX3NDSE1HVUE7MjAzMDQ3NjA1MDpSUk1kazE1eFF4U0E3QV9zQ0hNR1VBOzIwMzA0NzYwNDI6UlJNZGsxNXhReFNBN0Ffc0NITUdVQTsxMTYwMTk4OTIwOi1HZU02ZDVIUXo2MXBvT2dnUmYtcEE7MTg1NDUyODMwODpZd0FWU3BsS1FocVlVMEcyZml6NFlnOzExMDMyNDc0MDI6QVZBU3pPMDlTdFd0ZWdhUjFqaS02ZzsxODU0NTI4MzA5Oll3QVZTcGxLUWhxWVUwRzJmaXo0WWc7MjM0NzU1NzcxMDpmS3ZBYUV3blNiQ1pQMTdQLVE4TFpROzIzNDc1NTc3MDk6Zkt2QWFFd25TYkNaUDE3UC1ROExaUTsxNDg0MjA0MTc5Ojh4Rnl2dGhfU2IyYTdyUVVJYnB4VUE7MjM0NzU1NzcxMTpmS3ZBYUV3blNiQ1pQMTdQLVE4TFpROzIwODcxODI0NzE6ak1jZTE4a1pRZjZROHdHc3BNOGZFQTsyMzQ3NTU3NzEyOmZLdkFhRXduU2JDWlAxN1AtUThMWlE7MjM0NzU1NzcxMzpmS3ZBYUV3blNiQ1pQMTdQLVE4TFpROzEzMTY5NTEwMjY6UWVBZHZOXzZRbE9xMVpZMGRRXzZmUTsxMjU4MDMzMzIzOnRyQVU2anJwU0lLb2w2UnRaWlhmQUE7MTkyMzExNTg4OToyYlAwQ0pwcVRaT2swa0tTeGtGTHdBOzE5MjMxMTU4OTA6MmJQMENKcHFUWk9rMGtLU3hrRkx3QTsxODQ2NTE0MDQxOlBWT3VDVExQUXN5OWNMS01oMUJ0VXc7MTI1ODAzMzMyNDp0ckFVNmpycFNJS29sNlJ0WlpYZkFBOzE4NDY1MTQwNDI6UFZPdUNUTFBRc3k5Y0xLTWgxQnRVdzsyMDg3MTgyNDczOmpNY2UxOGtaUWY2UTh3R3NwTThmRUE7MjA4NzE4MjQ3NDpqTWNlMThrWlFmNlE4d0dzcE04ZkVBOzEwNTIwMDMwMzk6dktWUnBOQ0VRS096RXg3QXBnQTlDZzsxODQ2NTE0MDQzOlBWT3VDVExQUXN5OWNMS01oMUJ0VXc7MDs=\",\n" +
            "          \"failedShards\": 0,\n" +
            "          \"dslTemplate\": \"{\\\"size\\\":\\\"?\\\",\\\"query\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"source\\\":\\\"?\\\"}},{\\\"range\\\":{\\\"timestamp\\\":{\\\"include_lower\\\":\\\"?\\\",\\\"include_upper\\\":\\\"?\\\",\\\"from\\\":\\\"?\\\",\\\"to\\\":\\\"?\\\"}}},{\\\"term\\\":{\\\"area\\\":\\\"?\\\"}}]}},\\\"sort\\\":[{\\\"timestamp\\\":{\\\"order\\\":\\\"?\\\"}}]}\",\n" +
            "          \"routing\": \"null\",\n" +
            "          \"dslType\": \"scroll\",\n" +
            "          \"esCost\": 47,\n" +
            "          \"requestId\": \"96255b8b-836e-4fc3-af32-b1674500ef4d\",\n" +
            "          \"sinkTime\": 1539286723556,\n" +
            "          \"request\": \"indices:data/read/search\",\n" +
            "          \"indiceCount\": 7,\n" +
            "          \"clientNode\": \"bigdata-arius-ser531.gz01/100.69.146.51:9350\",\n" +
            "          \"internalCost\": 1,\n" +
            "          \"remoteAddr\": \"100.89.67.24\",\n" +
            "          \"orderByFields\": \"timestamp\",\n" +
            "          \"types\": \"\",\n" +
            "          \"requestType\": \"tcp\",\n" +
            "          \"searchType\": \"dsl\",\n" +
            "          \"successfulShards\": 84,\n" +
            "          \"whereFields\": \"area,source,timestamp\",\n" +
            "          \"gatewayNode\": \"bigdata-arius-ser206.gz01\",\n" +
            "          \"indiceSample\": \"gdd_data_major_2018-10-12\",\n" +
            "          \"timeStamp\": 1539275087673,\n" +
            "          \"indices\": \"gdd_data_major_2018-10-08,gdd_data_major_2018-10-07,gdd_data_major_2018-10-09,gdd_data_major_2018-10-06,gdd_data_major_2018-10-11,gdd_data_major_2018-10-10,gdd_data_major_2018-10-12\",\n" +
            "          \"dslLen\": 483,\n" +
            "          \"dslTemplateMd5\": \"F15EDFD1744798839ACEAC3F86137C26\",\n" +
            "          \"totalHits\": 0,\n" +
            "          \"appid\": 62,\n" +
            "          \"dsl\": \"{\\\"size\\\":50,\\\"query\\\":{\\\"bool\\\":{\\\"must\\\":[{\\\"term\\\":{\\\"source\\\":\\\"wanliu_order_cannelled_after_strived\\\"}},{\\\"range\\\":{\\\"timestamp\\\":{\\\"from\\\":1539274775406,\\\"to\\\":1539274785406,\\\"include_lower\\\":true,\\\"include_upper\\\":false}}},{\\\"term\\\":{\\\"area\\\":\\\"40\\\"}}]}},\\\"sort\\\":[{\\\"timestamp\\\":{\\\"order\\\":\\\"asc\\\"}}]}\",\n" +
            "          \"totalCost\": 48\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";

    private String str = "{\n" +
            "  \"took\": 3,\n" +
            "  \"timed_out\": false,\n" +
            "  \"_shards\": {\n" +
            "    \"total\": 1,\n" +
            "    \"successful\": 1,\n" +
            "    \"failed\": 0\n" +
            "  },\n" +
            "  \"hits\": {\n" +
            "    \"total\": 1790,\n" +
            "    \"max_score\": 1,\n" +
            "    \"hits\": [\n" +
            "      {\n" +
            "        \"_index\": \"arius.template.field\",\n" +
            "        \"_type\": \"type\",\n" +
            "        \"_id\": \"4505\",\n" +
            "        \"_score\": 1,\n" +
            "        \"_source\": {\n" +
            "          \"clusterName\": \"bigdata-arius-vip\",\n" +
            "          \"fieldCount\": 41,\n" +
            "          \"id\": 4505,\n" +
            "          \"name\": \"cn_mysql_woater_test\",\n" +
            "          \"templateFieldMap\": {\n" +
            "            \"cluster\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"api_id\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"datasource_title\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"api_url\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"real_name\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"es_kibana_date\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"type\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"title\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"manage_type\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"auto_id\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"topic_title\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"api_name\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"datasource_intro\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"topic_intro\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"api_store\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"status\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"username\": \"2018-09-28 07:00:02.020 +0800\",\n" +
            "            \"dashboard_id\": \"2018-09-28 07:00:02.020 +0800\"\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"aggregations\": {\n" +
            "    \"xxxx\": {\n" +
            "      \"doc_count_error_upper_bound\": 0,\n" +
            "      \"sum_other_doc_count\": 1203,\n" +
            "      \"buckets\": [\n" +
            "        {\n" +
            "          \"key\": \"bigdata-arius-olap\",\n" +
            "          \"doc_count\": 205,\n" +
            "          \"xxx\": {\n" +
            "            \"doc_count_error_upper_bound\": 0,\n" +
            "            \"sum_other_doc_count\": 125,\n" +
            "            \"buckets\": [\n" +
            "              {\n" +
            "                \"key\": 11,\n" +
            "                \"doc_count\": 12\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 0,\n" +
            "                \"doc_count\": 11\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 15,\n" +
            "                \"doc_count\": 10\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 8,\n" +
            "                \"doc_count\": 8\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 19,\n" +
            "                \"doc_count\": 8\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 25,\n" +
            "                \"doc_count\": 8\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 17,\n" +
            "                \"doc_count\": 7\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 10,\n" +
            "                \"doc_count\": 6\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 13,\n" +
            "                \"doc_count\": 6\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 9,\n" +
            "                \"doc_count\": 4\n" +
            "              }\n" +
            "            ]\n" +
            "          },\n" +
            "          \"cccc\": {\n" +
            "            \"doc_count_error_upper_bound\": 0,\n" +
            "            \"sum_other_doc_count\": 125,\n" +
            "            \"buckets\": [\n" +
            "              {\n" +
            "                \"key\": 11,\n" +
            "                \"doc_count\": 12\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 0,\n" +
            "                \"doc_count\": 11\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 15,\n" +
            "                \"doc_count\": 10\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 8,\n" +
            "                \"doc_count\": 8\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 19,\n" +
            "                \"doc_count\": 8\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 25,\n" +
            "                \"doc_count\": 8\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 17,\n" +
            "                \"doc_count\": 7\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 10,\n" +
            "                \"doc_count\": 6\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 13,\n" +
            "                \"doc_count\": 6\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 9,\n" +
            "                \"doc_count\": 4\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"key\": \"bigdata-arius-log5\",\n" +
            "          \"doc_count\": 203,\n" +
            "          \"xxx\": {\n" +
            "            \"doc_count_error_upper_bound\": 0,\n" +
            "            \"sum_other_doc_count\": 64,\n" +
            "            \"buckets\": [\n" +
            "              {\n" +
            "                \"key\": 18,\n" +
            "                \"doc_count\": 21\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 20,\n" +
            "                \"doc_count\": 19\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 17,\n" +
            "                \"doc_count\": 18\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 19,\n" +
            "                \"doc_count\": 17\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 21,\n" +
            "                \"doc_count\": 16\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 22,\n" +
            "                \"doc_count\": 13\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 16,\n" +
            "                \"doc_count\": 10\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 23,\n" +
            "                \"doc_count\": 10\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 25,\n" +
            "                \"doc_count\": 8\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 26,\n" +
            "                \"doc_count\": 7\n" +
            "              }\n" +
            "            ]\n" +
            "          },\n" +
            "          \"cccc\": {\n" +
            "            \"doc_count_error_upper_bound\": 0,\n" +
            "            \"sum_other_doc_count\": 64,\n" +
            "            \"buckets\": [\n" +
            "              {\n" +
            "                \"key\": 18,\n" +
            "                \"doc_count\": 21\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 20,\n" +
            "                \"doc_count\": 19\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 17,\n" +
            "                \"doc_count\": 18\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 19,\n" +
            "                \"doc_count\": 17\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 21,\n" +
            "                \"doc_count\": 16\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 22,\n" +
            "                \"doc_count\": 13\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 16,\n" +
            "                \"doc_count\": 10\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 23,\n" +
            "                \"doc_count\": 10\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 25,\n" +
            "                \"doc_count\": 8\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 26,\n" +
            "                \"doc_count\": 7\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"key\": \"bigdata-arius-vip\",\n" +
            "          \"doc_count\": 179,\n" +
            "          \"xxx\": {\n" +
            "            \"doc_count_error_upper_bound\": 0,\n" +
            "            \"sum_other_doc_count\": 124,\n" +
            "            \"buckets\": [\n" +
            "              {\n" +
            "                \"key\": 0,\n" +
            "                \"doc_count\": 14\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 22,\n" +
            "                \"doc_count\": 6\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 21,\n" +
            "                \"doc_count\": 5\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 38,\n" +
            "                \"doc_count\": 5\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 61,\n" +
            "                \"doc_count\": 5\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 32,\n" +
            "                \"doc_count\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 35,\n" +
            "                \"doc_count\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 40,\n" +
            "                \"doc_count\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 41,\n" +
            "                \"doc_count\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 42,\n" +
            "                \"doc_count\": 4\n" +
            "              }\n" +
            "            ]\n" +
            "          },\n" +
            "          \"cccc\": {\n" +
            "            \"doc_count_error_upper_bound\": 0,\n" +
            "            \"sum_other_doc_count\": 124,\n" +
            "            \"buckets\": [\n" +
            "              {\n" +
            "                \"key\": 0,\n" +
            "                \"doc_count\": 14\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 22,\n" +
            "                \"doc_count\": 6\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 21,\n" +
            "                \"doc_count\": 5\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 38,\n" +
            "                \"doc_count\": 5\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 61,\n" +
            "                \"doc_count\": 5\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 32,\n" +
            "                \"doc_count\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 35,\n" +
            "                \"doc_count\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 40,\n" +
            "                \"doc_count\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 41,\n" +
            "                \"doc_count\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"key\": 42,\n" +
            "                \"doc_count\": 4\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
