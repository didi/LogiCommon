package com.didiglobal.logi.elasticsearch.client;

import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestUtils {
    public static String index = "elasticsearch-didi-internal-client-test";
    public static String index2 = "elasticsearch-didi-internal-client-test-2";

    private static ESClient oldClient, newClient;

    private static String oldIp = "10.179.100.148";
    private static String newIp = "10.179.21.130";
    private static String local = "127.0.0.1";

    static {
        try {
            oldClient = new ESClient();
            oldClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(oldIp), 9200));
            oldClient.start();

            newClient = new ESClient();
            newClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(newIp), 9200));
            newClient.start();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

//        client = oldClient;
        client = newClient;
    }

    public static ESClient client;


    public static String indexConfig = "\n" +
            "{\n" +
            "    \"aliases\": {},\n" +
            "    \"mappings\": {\n" +
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
            "                \"dynamic\": \"true\",\n" +
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
            "    },\n" +
            "    \"settings\": {\n" +
            "      \"index\": {\n" +
            "        \"mapping\": {\n" +
            "          \"ignore_malformed\": \"true\"\n" +
            "        },\n" +
            "        \"refresh_interval\": \"30s\",\n" +
            "        \"translog\": {\n" +
            "          \"sync_interval\": \"1m\",\n" +
            "          \"durability\": \"async\"\n" +
            "        },\n" +
            "        \"max_result_window\": \"10000\",\n" +
            "        \"creation_date\": \"1545494410601\",\n" +
            "        \"requests\": {\n" +
            "          \"cache\": {\n" +
            "            \"enable\": \"true\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"unassigned\": {\n" +
            "          \"node_left\": {\n" +
            "            \"delayed_timeout\": \"180m\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"priority\": \"5\",\n" +
            "        \"number_of_replicas\": \"0\",\n" +
            "        \"uuid\": \"Vv5HQRCNTBif9YhMZv2lXQ\",\n" +
            "        \"version\": {\n" +
            "          \"created\": \"2030399\"\n" +
            "        },\n" +
            "        \"codec\": \"best_compression\",\n" +
            "        \"routing\": {\n" +
            "          \"allocation\": {\n" +
            "            \"include\": {\n" +
            "              \"rack\": \"r1,r2,r3\"\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        \"search\": {\n" +
            "          \"slowlog\": {\n" +
            "            \"threshold\": {\n" +
            "              \"fetch\": {\n" +
            "                \"warn\": \"1s\",\n" +
            "                \"trace\": \"200ms\",\n" +
            "                \"debug\": \"500ms\",\n" +
            "                \"info\": \"800ms\"\n" +
            "              },\n" +
            "              \"query\": {\n" +
            "                \"warn\": \"10s\",\n" +
            "                \"trace\": \"500ms\",\n" +
            "                \"debug\": \"1s\",\n" +
            "                \"info\": \"5s\"\n" +
            "              }\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        \"number_of_shards\": \"1\",\n" +
            "        \"merge\": {\n" +
            "          \"scheduler\": {\n" +
            "            \"max_thread_count\": \"1\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"optimize_auto_generated_id\": \"true\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"warmers\": {}\n" +
            "  }";


    public static String TemplateConfig = "{\n" +
            "    \"order\": 10,\n" +
            "    \"template\": \"arius.gateway.join*\",\n" +
            "    \"settings\": {\n" +
            "      \"index\": {\n" +
            "        \"number_of_shards\": \"1\",\n" +
            "        \"routing\": {\n" +
            "          \"allocation\": {\n" +
            "            \"include\": {\n" +
            "              \"rack\": \"r1,r2,r3\"\n" +
            "            }\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    \"mappings\": {\n" +
            "      \"type\": {\n" +
            "        \"dynamic_date_formats\": [\n" +
            "          \"strict_date_optional_time\",\n" +
            "          \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\"\n" +
            "        ],\n" +
            "        \"numeric_detection\": true,\n" +
            "        \"dynamic_templates\": [\n" +
            "          {\n" +
            "            \"object_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"dynamic\": false,\n" +
            "                \"type\": \"object\",\n" +
            "                \"enabled\": false\n" +
            "              },\n" +
            "              \"match_mapping_type\": \"object\",\n" +
            "              \"match\": \"*\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"message_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"no\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match_mapping_type\": \"string\",\n" +
            "              \"match\": \"message\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"string_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"ignore_above\": 512,\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match_mapping_type\": \"string\",\n" +
            "              \"match\": \"*\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"timestampField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match_mapping_type\": \"long\",\n" +
            "              \"match\": \"*imestamp*\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"logTimeField\": {\n" +
            "              \"mapping\": {\n" +
            "                \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "                \"type\": \"date\"\n" +
            "              },\n" +
            "              \"match_mapping_type\": \"long\",\n" +
            "              \"match\": \"logTime\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"extractLevel_fields\": {\n" +
            "              \"mapping\": {\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"match_mapping_type\": \"string\",\n" +
            "              \"match\": \"extractLevel\"\n" +
            "            }\n" +
            "          }\n" +
            "        ],\n" +
            "        \"_all\": {\n" +
            "          \"enabled\": false\n" +
            "        },\n" +
            "        \"properties\": {\n" +
            "          \"ariusCreateTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"selectFields\": {\n" +
            "            \"ignore_above\": 512,\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"_arius_query_request_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"totalHitsList\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"_arius_query_response_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"responseLen\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"beforeCost\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"isTimedOut\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_exception_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"dslTemplate\": {\n" +
            "            \"ignore_above\": 1024,\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"fetchCost\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_response_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"successShardsList\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"action\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"_arius_query_request_indices_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"indiceCount\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"_arius_query_search_response_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"internalCost\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_search_response_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"orderByFields\": {\n" +
            "            \"ignore_above\": 512,\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"_arius_fetch_message_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"method\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"totalShardsList\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"isTimeoutList\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"searchType\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"_arius_query_request_before_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"dltag\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"_arius_query_request_before_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"index\": {\n" +
            "            \"ignore_above\": 512,\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"whereFields\": {\n" +
            "            \"ignore_above\": 512,\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"_arius_query_request_node_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"queryString\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"_arius_query_response_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_request_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_response_length_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"dslLen\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"totalHits\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"_arius_query_request_node_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"_arius_query_request_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"memUsed\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"bucketNumber\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"dsl\": {\n" +
            "            \"ignore_above\": 1024,\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"totalCost\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"status\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_search_request_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"groupByFields\": {\n" +
            "            \"ignore_above\": 512,\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"failedShardsList\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"totalShards\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"failedShards\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"scrollId\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"dslType\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"routing\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"_arius_query_request_indices_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_response_length_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"newDslTemplate\": {\n" +
            "            \"ignore_above\": 512,\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"esCost\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"aggsLevel\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"requestId\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"sinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_request_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_exception_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"clientNode\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"remoteAddr\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"lastBucketNumber\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_search_scroll_request_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"types\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"requestType\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"fetchSize\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"dltagCount\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"esCostList\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"fetchMetric\": {\n" +
            "            \"properties\": {\n" +
            "              \"parseMessageCost\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"fetchMissingCnt\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"getConsumeCost\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"fetchOffsetCnt\": {\n" +
            "                \"type\": \"long\"\n" +
            "              },\n" +
            "              \"brokerHost\": {\n" +
            "                \"index\": \"not_analyzed\",\n" +
            "                \"type\": \"string\"\n" +
            "              },\n" +
            "              \"fetchMetricInfo\": {\n" +
            "                \"dynamic\": \"true\",\n" +
            "                \"properties\": {\n" +
            "                  \"cost\": {\n" +
            "                    \"type\": \"long\"\n" +
            "                  },\n" +
            "                  \"partition\": {\n" +
            "                    \"type\": \"long\"\n" +
            "                  },\n" +
            "                  \"count\": {\n" +
            "                    \"type\": \"long\"\n" +
            "                  },\n" +
            "                  \"topic\": {\n" +
            "                    \"ignore_above\": 512,\n" +
            "                    \"index\": \"not_analyzed\",\n" +
            "                    \"type\": \"string\"\n" +
            "                  }\n" +
            "                }\n" +
            "              },\n" +
            "              \"partitionTotalCost\": {\n" +
            "                \"type\": \"long\"\n" +
            "              }\n" +
            "            }\n" +
            "          },\n" +
            "          \"successfulShards\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"gatewayNode\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"userName\": {\n" +
            "            \"ignore_above\": 512,\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_search_request_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"_arius_fetch_message_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_search_scroll_request_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"indiceSample\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"timeStamp\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"indices\": {\n" +
            "            \"ignore_above\": 1024,\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"dslTemplateMd5\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"scrollIdList\": {\n" +
            "            \"index\": \"not_analyzed\",\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_response_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"_arius_query_tcp_search_response_flinkTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          },\n" +
            "          \"appid\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"_arius_query_search_response_logTime\": {\n" +
            "            \"format\": \"yyyy-MM-dd HH:mm:ss Z||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS Z||yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss,SSS||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss,SSS Z||yyyy/MM/dd HH:mm:ss,SSS Z||epoch_millis\",\n" +
            "            \"type\": \"date\"\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    \"aliases\": {}\n" +
            "  }";

}
