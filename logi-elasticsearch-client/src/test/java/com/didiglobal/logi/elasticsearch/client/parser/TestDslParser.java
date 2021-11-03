package com.didiglobal.logi.elasticsearch.client.parser;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.didiglobal.logi.elasticsearch.client.parser.bean.ExtractResult;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.DslNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.KeyWord;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.DslParser;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.ParserType;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.query.QueryStringParser;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.visitor.FormatVisitor;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.visitor.GetInfoVisitor;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.visitor.OutputJsonVisitor;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.visitor.basic.OutputVisitor;
import com.didiglobal.logi.elasticsearch.client.parser.sql.EsExportParameterVisitor;
import org.junit.Test;
import org.nlpcn.es4sql.parse.ElasticSqlExprParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: D10865
 * @Description:
 * @Date: Create on 2018/5/29 下午4:41
 * @Modified By
 */
public class TestDslParser {

    @Test
    public void processAll() throws Exception {
        String fileName = TestDslParser.class.getClassLoader().getResource("dsl.txt").getFile();
        List<String> lines = Files.readAllLines(Paths.get(fileName));

        int count = 0;
        for (String l : lines) {
//            System.out.println("##########################" + count++);
//            System.out.println(l);
            parser(l);
        }
    }

    @Test
    public void test() throws Exception {
        String dsl = "SELECT city,vehicleId,orderId,hbTime,location,source from cn_wo34450_htw_routing.order.htw_2018-10-25 where appHb >= \"12345678$1540472461000\" and appHb <= \"12345678$1540473061000\" order by hbTime";
        dsl = "SELECT city,vehicleId,orderId,hbTime,location,source from cn_wo34450_htw_routing.order.htw_2018-10-25 where appHb >= 12345678$1540472461000 and appHb <= 12345678$1540473061000 order by hbTime";

        dsl = "{\"size\":0,\"aggs\":{\"vuln\":{\"filter\":{\"bool\":{\"must\":[{\"range\":{\"time_stamp\":{\"gt\":\"now-7d\",\"lte\":\"now\",\"time_zone\":\"+08:00\"}}},{\"term\":{\"tenant_id\":\"4001c00000000004001e2000000afc9b\"}}],\"must_not\":[{\"bool\":{\"filter\":[{\"term\":{\"type\":\"GundamCrackLoginEvent\"}},{\"term\":{\"login_is_elsewhere\":\"no\"}}]}}],\"filter\":{\"bool\":{\"should\":[{\"term\":{\"type\":\"GundamAttackRedis\"}},{\"term\":{\"type\":\"GundamAttackCronFile\"}},{\"term\":{\"type\":\"GundamAttackRaisePrivilege\"}},{\"term\":{\"type\":\"GundamAttackRiskProcess\"}},{\"term\":{\"type\":\"GundamAttackShellReverse\"}},{\"term\":{\"type\":\"GundamCrackForceEvent\"}},{\"term\":{\"type\":\"GundamCrackLoginEvent\"}}]}}}},\"aggs\":{\"trend\":{\"date_histogram\":{\"field\":\"time_stamp\",\"interval\":\"day\",\"format\":\"yyyy-MM-dd\",\"time_zone\":\"+08:00\",\"min_doc_count\":0,\"extended_bounds\":{\"min\":\"2019-01-02\",\"max\":\"2019-01-08\"}}}}}}}";

        ExtractResult er = DslExtractionUtil.extractDsl(dsl);
        System.out.println(er.getDslTemplateMd5());
        System.out.println(er.getNewDslTemplate());
    }

    @Test
    public void testSqlFormat() throws Exception {
        String sql = "SELECT city,vehicleId,orderId,hbTime,location,source from cn_wo34450_htw_routing.order.htw_2018-10-25 where appHb >= 12345678$1540472461000 and appHb <= 12345678$1540473061000 order by hbTime";

        //sql = "select sum(remit_cancel + cancel) as sqcount from dpecosys_service_service_score_data_source_2018-10-15 where (car_level = 500 or car_level = 600 or car_level = 800) and area = 23";

        //sql = "select * from b where a > 100";

        //sql = "select order_base.passenger_id,order_base.product_id, order_base.passenger_phone, order_base.extra_info from dos_order_201808 where order_base.extra_info <> \"\" LIMIT 1000";

        //sql = "select * from `us01_credit_detail_log_201811` where a in (1,2,3) group by a order by c desc        limit 1";

        //sql = "select /*! ROUTINGS(/user/bigdata-dp/swan/us/bizlog/us01_route-common_us01-pre-v_route-broker-go_online-service_route-sys_map_32934) */ * from us01_dquality_2018-10-25,us01_dquality_2018-10-26 where datasourceId = 65 and topic=\"/user/bigdata-dp/swan/us/bizlog/us01_route-common_us01-pre-v_route-broker-go_online-service_route-sys_map_32934\" and type=2 group by a order by timestamp desc limit 1";

        sql = "select sum(remit_cancel + cancel) as sqcount，a from b where d between c and e";
        sql = "select * from dos_order_201809 where order_base.order_id = '17611132371390' LIMIT 1";

        SQLExprParser parser = new ElasticSqlExprParser(sql);
        SQLExpr expr = parser.expr();
        SQLQueryExpr sqlExpr = (SQLQueryExpr) expr;

        // 提取查询模板
//        SQLASTOutputVisitor esSqlOutputVisitor = new EsSqlFormatVisitor(new StringBuilder());
//        esSqlOutputVisitor.setPrettyFormat(false);
//        sqlExpr.accept(esSqlOutputVisitor);
//
//        String sqlTemplate = esSqlOutputVisitor.getAppender().toString();
//        System.out.println(sqlTemplate);

        // 提取字段
        EsExportParameterVisitor esExportParameterVisitor = new EsExportParameterVisitor();
        sqlExpr.accept(esExportParameterVisitor);

        System.out.println(esExportParameterVisitor.getTableName());
        System.out.println(esExportParameterVisitor.getSelectFieldNames());
        System.out.println(esExportParameterVisitor.getWhereFieldsNames());
        System.out.println(esExportParameterVisitor.getGroupByFieldNames());
        System.out.println(esExportParameterVisitor.getOrderByFieldNames());
    }

    @Test
    public void testOne() throws Exception {
        String sql = "{\"index\" : \"cll_test_binlog_kafka_2017_11\"}";//"{\"index\":[\"arius_dsl_log_2018-09-20\"],\"ignore_unavailable\":true}";

        System.out.println(sql);
        parser(sql);
    }

    @Test
    public void testQueryString() throws Exception {
        String str = "{\"fuzziness\":\"AUTO\",\"phrase_slop\":0,\"query\":\"\\\"notify\\\"\",\"analyze_wildcard\":true,\"tie_breaker\":0.0,\"use_dis_max\":true,\"fuzzy_prefix_length\":0,\"default_operator\":\"or\",\"fuzzy_max_expansions\":50,\"auto_generate_phrase_queries\":false,\"boost\":1.0,\"fields\":[\"message^1.0\", \"helloworld^1.0\"],\"escape\":false}\n";
        str = "{\"default_field\":\"Actions.Response\",\"query\":\"\\\"\\\\\\\"receive_level\\\\\\\":\\\\\\\"1100\\\\\\\"\\\"\"}";

        QueryStringParser queryStringParser = new QueryStringParser(ParserType.QUERY);
        KeyWord node = queryStringParser.parse("query_string", JSON.parseObject(str));

        OutputVisitor ov = new OutputVisitor();
        node.accept(ov);
        if (!JSON.parseObject(str).toString().equals(ov.ret.toString())) {
            System.out.println(ov.ret);
            System.out.println(JSON.parseObject(str));
            System.out.println("not equal");
        }


        GetInfoVisitor giv = new GetInfoVisitor();
        node.accept(giv);
        System.out.println(giv.fields);
    }

    @Test
    public void testxxxx() throws Exception {
        QueryStringParser queryStringParser = new QueryStringParser(ParserType.QUERY);

        String fileName = TestDslParser.class.getClassLoader().getResource("query").getFile();
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        for (String l : lines) {
            KeyWord node = queryStringParser.parse("query_string", JSON.parseObject(l));

            OutputVisitor ov = new OutputVisitor();
            node.accept(ov);

            if (!JSON.parseObject(l).toString().equals(ov.ret.toString())) {
                System.out.println(ov.ret);
                System.out.println(JSON.parseObject(l));
                System.out.println("not equal");
            }


            GetInfoVisitor giv = new GetInfoVisitor();
            node.accept(giv);
            System.out.println(ov.ret);
            System.out.println(giv.fields);
            System.out.println();
        }
    }

    /**
     * 测试explain关键字
     *
     * @throws Exception
     */
    @Test
    public void testExplainDsl() throws Exception {
        String dsl = "{\"from\":0,\"size\":1,\"query\":{\"ids\":{\"types\":[],\"values\":[\"472083\"]}},\"explain\":true,\"sort\":[{\"alarmId\":{\"order\":\"desc\"}}]}";
        parser(dsl);
    }

    /**
     * 测试range关键字
     *
     * @throws Exception
     */
    @Test
    public void testRangeDsl() throws Exception {
        String dsl = "{\"query\":{\"range\":{\"date\":{\"lte\":\"2018-09-11\"}}}}";

        parser(dsl);
    }

    /**
     * 测试script关键字
     *
     * @throws Exception
     */
    @Test
    public void testScriptDsl() throws Exception {
        String dsl = "{\"filter\":{\"bool\":{\"filter\":{\"and\":[{\"range\":{\"dictItems.entrance_year\":{\"lt\":2016,\"gte\":2014}}}]},\"must\":[{\"query_string\":{\"default_field\":\"bizType\",\"query\":\"ZKC OR SODA OR SFC\"}},{\"term\":{\"cmd\":\"ZK_STUDENT_CHECK\"}}]}},\"size\":10,\"query\":{\"range\":{\"timestamp\":{\"lt\":1529663715753,\"gte\":1529662715743}}},\"from\":0}";
        System.out.println(dsl);
        parser(dsl);
    }

    /**
     * 测试boosting关键字
     *
     * @throws Exception
     */
    @Test
    public void testBoostingDsl() throws Exception {
        //String dsl = "{\"query\":{\"boosting\":{\"positive\":{\"term\":{\"field1\":\"value1\"}},\"negative\":{\"term\":{\"field2\":\"value2\"}},\"negative_boost\":0.2}}}";
        String dsl = "{\"query\":{\"boosting\":{\"positive\":{\"match\":{\"field2\":{\"query\":\"one\",\"operator\":\"OR\",\"prefix_length\":0,\"max_expansions\":50,\"fuzzy_transpositions\":true,\"lenient\":false,\"zero_terms_query\":\"NONE\",\"boost\":1.0}}},\"negative\":{\"match\":{\"field1\":{\"query\":\"two\",\"operator\":\"OR\",\"prefix_length\":0,\"max_expansions\":50,\"fuzzy_transpositions\":true,\"lenient\":false,\"zero_terms_query\":\"NONE\",\"boost\":1.0}}},\"negative_boost\":2.0,\"boost\":1.0}}}";
        System.out.println(dsl);
        parser(dsl);
    }

    /**
     * 测试common关键字
     *
     * @throws Exception
     */
    @Test
    public void testCommonDsl() throws Exception {
        String dsl = "{\"query\":{\"common\":{\"body\":{\"query\":\"this is bonsai cool\",\"cutoff_frequency\":0.001}}}}";
        System.out.println(dsl);
        parser(dsl);
    }

    /**
     * 测试dis_max关键字
     *
     * @throws Exception
     */
    @Test
    public void testDisMaxDsl() throws Exception {
        String dsl = "{\"query\":{\"dis_max\":{\"tie_breaker\":0.7,\"boost\":1.2,\"queries\":[{\"term\":{\"age\":34}},{\"term\":{\"age\":35}}]}}}";
        System.out.println(dsl);
        parser(dsl);
    }

    /**
     * 测试scroll查询
     *
     * @throws Exception
     */
    @Test
    public void testScrollDsl() throws Exception {
        String dsl = "{\"scroll\":\"60s\",\"scroll_id\":\"cXVlcnlBbmRGZXRjaDsxOzEyNjEzOTpXYW9YS2dlQVM1YU9hZFJXVFNZa2x3OzA7\"}";
        System.out.println(dsl);
        parser(dsl);
    }

    public void parser(String dsl) throws Exception {
        List<JSONObject> l = new ArrayList<>();

        while (true) {
            DefaultJSONParser parser = new DefaultJSONParser(dsl, ParserConfig.getGlobalInstance(), 989);
            Object obj = parser.parse();

            l.add((JSONObject) obj);

            if (parser.getLexer().pos() == 0) {
                break;
            }

            dsl = dsl.substring(parser.getLexer().pos());
            parser.getLexer().close();
        }

        for (JSONObject obj : l) {
            parser(obj);
        }


    }


    public void parser(JSONObject jsonObject) throws Exception {
        DslNode node = DslParser.parse(jsonObject);

        OutputVisitor visitor = new OutputVisitor();
        node.accept(visitor);
        System.out.println("src:" + jsonObject);

        if (!visitor.ret.toString().equals(jsonObject.toJSONString())) {
            System.out.println("dst:" + visitor.ret);
            throw new Exception("not equal");
        }

        System.out.println("-----------------");

        FormatVisitor formatVisitor = new FormatVisitor();
        node.accept(formatVisitor);
        System.out.println("format:" + formatVisitor.ret);

        System.out.println("-----------------");

        GetInfoVisitor getInfoVisitor = new GetInfoVisitor();
        node.accept(getInfoVisitor);
        System.out.println("fields:" + getInfoVisitor.fields);

        System.out.println("-----------------");

        OutputJsonVisitor outputJsonVisitor = new OutputJsonVisitor();
        node.accept(outputJsonVisitor);
        if (outputJsonVisitor.haveJson) {
            System.out.println(jsonObject.toJSONString());
            System.out.println();
        }
    }

}
