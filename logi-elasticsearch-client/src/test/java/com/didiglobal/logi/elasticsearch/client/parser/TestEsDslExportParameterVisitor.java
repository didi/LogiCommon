package com.didiglobal.logi.elasticsearch.client.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.DslNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.DslParser;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.visitor.EsDslExportParameterVisitor;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.visitor.FormatVisitor;
import org.junit.Test;

/**
 * @Author: D10865
 * @Description:
 * @Date: Create on 2018/9/6 下午12:11
 * @Modified By
 */
public class TestEsDslExportParameterVisitor {

    /**
     * 获取select字段
     *
     * @throws Exception
     */
    @Test
    public void testSelectFieldExport() throws Exception {

        String[] dsls = new String[]{
                "{\"size\":0,\"aggs\":{\"1\":{\"terms\":{\"field\":\"str1\",\"size\":3},\"aggs\":{\"1_1\":{\"sum\":{\"field\":\"num1\"}}}},\"1_1_1\":{\"avg_bucket\":{\"buckets_path\":\"1>1_1\"}}}}",
                "{\"_source\":[\"indices\",\"appid\",\"dslTemplateMd5\",\"dsl\",\"ariusCreateTime\"]}",
                "{\"_source\":\"indices\"}",
                "{\"_source\":{\"include\":[\"2\",\"1\"],\"exclude\":\"3\"}}",
                "{\"_source\":{\"include\":\"2\",\"exclude\":\"3\"}}",
                "{\"from\":0,\"size\":10,\"query\":{\"bool\":{\"must\":[{\"terms\":{\"order_base.passenger_phone\":[\"13121390067\"]}},{\"terms\":{\"order_status.order_status\":[\"5\"]}}]}},\"fields\":[\"order_base.order_id\",\"order_base.district\",\"order_base.product_id\",\"order_base._create_time\"],\"sort\":[{\"order_base._create_time\":{\"order\":\"desc\"}}]}"

        };

        for (String dsl : dsls) {
            DefaultJSONParser parser = new DefaultJSONParser(dsl, ParserConfig.getGlobalInstance(), 989);
            JSONObject jsonObject = (JSONObject) parser.parse();
            DslNode node = DslParser.parse(jsonObject);

            FormatVisitor formatVisitor = new FormatVisitor();
            node.accept(formatVisitor);
            //System.out.println(formatVisitor.ret);

            EsDslExportParameterVisitor esDslExportParameterVisitor = new EsDslExportParameterVisitor();
            node.accept(esDslExportParameterVisitor);

            System.out.println(esDslExportParameterVisitor.getSelectFieldNames());
        }
    }

    /**
     * 获取where字段
     *
     * @throws Exception
     */
    @Test
    public void testWhereFieldExport() throws Exception {
        String[] dsls = new String[]{
                "{\"from\":0,\"size\":10,\"query\":{\"bool\":{\"must\":[{\"terms\":{\"order_base.passenger_phone\":[\"13121390067\"]}},{\"terms\":{\"order_status.order_status\":[\"5\"]}}]}},\"fields\":[\"order_base.order_id\",\"order_base.district\",\"order_base.product_id\",\"order_base._create_time\"],\"sort\":[{\"order_base._create_time\":{\"order\":\"desc\"}}]}",
                "{\"query\":{\"filtered\":{\"query\":{\"query_string\":{\"query\":\"*\",\"analyze_wildcard\":false,\"lowercase_expanded_terms\":false}},\"filter\":{\"bool\":{\"must\":[{\"query\":{\"query_string\":{\"query\":\"topic: us01_cashier_us01-v_pay_gs_common-plat_18920_clean\",\"analyze_wildcard\":false,\"lowercase_expanded_terms\":false}}},{\"range\":{\"timestamp\":{\"gte\":1534387738516,\"lte\":1534402138516,\"format\":\"epoch_millis\"}}}],\"must_not\":[{\"query\":{\"match\":{\"groupID\":{\"query\":\"dsink_es_us01_log$group1\",\"type\":\"phrase\"}}}}]}}}},\"size\":0,\"aggs\":{\"2\":{\"date_histogram\":{\"field\":\"timestamp\",\"interval\":\"1m\",\"time_zone\":\"Asia/Shanghai\",\"min_doc_count\":1,\"extended_bounds\":{\"min\":1534387738513,\"max\":1534402138513}},\"aggs\":{\"3\":{\"terms\":{\"field\":\"groupID\",\"size\":5,\"order\":{\"4\":\"desc\"}},\"aggs\":{\"1\":{\"max\":{\"field\":\"maxMsgSize\"}},\"4\":{\"max\":{\"field\":\"avgMsgSize\"}}}}}}}}\n"

        };

        for (String dsl : dsls) {
            DefaultJSONParser parser = new DefaultJSONParser(dsl, ParserConfig.getGlobalInstance(), 989);
            JSONObject jsonObject = (JSONObject) parser.parse();
            DslNode node = DslParser.parse(jsonObject);

            FormatVisitor formatVisitor = new FormatVisitor();
            node.accept(formatVisitor);
            //System.out.println(formatVisitor.ret);

            EsDslExportParameterVisitor esDslExportParameterVisitor = new EsDslExportParameterVisitor();
            node.accept(esDslExportParameterVisitor);

            System.out.println(esDslExportParameterVisitor.getWhereFieldsNames());
            System.out.println(esDslExportParameterVisitor.getRangeFilterFieldsNames());
            System.out.println(esDslExportParameterVisitor.getTermFilterFieldsNames());
            System.out.println("=========");
        }
    }

    /**
     * 获取group by字段
     *
     * @throws Exception
     */
    @Test
    public void testGroupByFieldExport() throws Exception {
        String[] dsls = new String[]{
                "{\"size\":0,\"aggs\":{\"1\":{\"terms\":{\"field\":\"str1\",\"size\":3},\"aggs\":{\"1_1\":{\"sum\":{\"field\":\"num1\"}}}},\"1_1_1\":{\"avg_bucket\":{\"buckets_path\":\"1>1_1\"}}}}",

        };

        for (String dsl : dsls) {
            DefaultJSONParser parser = new DefaultJSONParser(dsl, ParserConfig.getGlobalInstance(), 989);
            JSONObject jsonObject = (JSONObject) parser.parse();
            DslNode node = DslParser.parse(jsonObject);

            FormatVisitor formatVisitor = new FormatVisitor();
            node.accept(formatVisitor);
            //System.out.println(formatVisitor.ret);

            EsDslExportParameterVisitor esDslExportParameterVisitor = new EsDslExportParameterVisitor();
            node.accept(esDslExportParameterVisitor);

            System.out.println(esDslExportParameterVisitor.getGroupByFieldNames());
        }
    }

    /**
     * 获取sort by字段
     *
     * @throws Exception
     */
    @Test
    public void testSortByFieldExport() throws Exception {
        String[] dsls = new String[]{
                "{\"from\":0,\"size\":10,\"query\":{\"bool\":{\"must\":[{\"terms\":{\"order_base.passenger_phone\":[\"13121390067\"]}},{\"terms\":{\"order_status.order_status\":[\"5\"]}}]}},\"fields\":[\"order_base.order_id\",\"order_base.district\",\"order_base.product_id\",\"order_base._create_time\"],\"sort\":[{\"order_base._create_time\":{\"order\":\"desc\"}}]}"

        };

        for (String dsl : dsls) {
            DefaultJSONParser parser = new DefaultJSONParser(dsl, ParserConfig.getGlobalInstance(), 989);
            JSONObject jsonObject = (JSONObject) parser.parse();
            DslNode node = DslParser.parse(jsonObject);

            FormatVisitor formatVisitor = new FormatVisitor();
            node.accept(formatVisitor);
            //System.out.println(formatVisitor.ret);

            EsDslExportParameterVisitor esDslExportParameterVisitor = new EsDslExportParameterVisitor();
            node.accept(esDslExportParameterVisitor);

            System.out.println(esDslExportParameterVisitor.getOrderByFieldNames());
        }
    }

}
