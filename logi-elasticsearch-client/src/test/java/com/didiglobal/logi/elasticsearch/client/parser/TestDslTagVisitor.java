package com.didiglobal.logi.elasticsearch.client.parser;

import com.didiglobal.logi.elasticsearch.client.parser.bean.ExtractResult;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: D10865
 * @Description:
 * @Date: Create on 2019/1/8 下午6:24
 * @Modified By
 * <p>
 * 测试dsl标签遍历器
 */
public class TestDslTagVisitor {

    /**
     * 测试DSL查询语句 query中带了Wildcard，且前缀*号
     */
    @Test
    public void testDslWildcardTagNotMatch() {

        String dsl = "{\"size\":10,\"query\":{\"bool\":{\"must_not\":[{\"wildcard\":{\"dslTemplateMd5\":{\"value\":\"V2_*\"}}}]}},\"_source\":[\"timeStamp\"],\"sort\":[{\"appid\":{\"order\":\"asc\"}},{\"searchCount\":{\"order\":\"desc\"}}]}";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
        Assert.assertTrue(extractResult.getTags().isEmpty());

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 query中带了Wildcard，且前缀*号
     */
    @Test
    public void testDslWildcardTagMatch() {

        String dsl = "{\"size\":10,\"query\":{\"bool\":{\"must_not\":[{\"wildcard\":{\"dslTemplateMd5\":{\"value\":\"*V2*\"}}}]}},\"_source\":[\"timeStamp\"],\"sort\":[{\"appid\":{\"order\":\"asc\"}},{\"searchCount\":{\"order\":\"desc\"}}]}";

        dsl = "{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"should\":[{\"bool\":{\"should\":{\"match\":{\"point_label\":{\"query\":\"投诉率\",\"type\":\"boolean\",\"boost\":2.0,\"minimum_should_match\":\"80%\"}}}}},{\"bool\":{\"should\":{\"match\":{\"point_name\":{\"query\":\"投诉率\",\"type\":\"boolean\",\"boost\":2.0,\"minimum_should_match\":\"80%\"}}}}},{\"bool\":{\"should\":{\"wildcard\":{\"point_name\":{\"wildcard\":\"*投诉率*\",\"boost\":2.0}}}}},{\"bool\":{\"should\":{\"match\":{\"bundle_label\":{\"query\":\"投诉率\",\"type\":\"boolean\",\"boost\":1.0,\"minimum_should_match\":\"80%\"}}}}},{\"bool\":{\"should\":{\"term\":{\"owner\":{\"value\":\"投诉率\",\"boost\":1.0}}}}},{\"bool\":{\"should\":{\"term\":{\"owner_cn\":{\"value\":\"投诉率\",\"boost\":1.0}}}}},{\"bool\":{\"should\":{\"match\":{\"description\":{\"query\":\"投诉率\",\"type\":\"boolean\",\"boost\":1.0,\"minimum_should_match\":\"80%\"}}}}}]}},\"sort\":[{\"_score\":{}},{\"created_time\":{\"order\":\"desc\"}}]}";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("wildcard pre*"));

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 aggs嵌套层数过深 大于等于3层
     */
    @Test
    public void testDslAggsDeepNestNotMatch() {
        String dsl = "{\"size\":0,\"aggs\":{\"1\":{\"terms\":{\"field\":\"method\",\"size\":3},\"aggs\":{\"1_1\":{\"sum\":{\"field\":\"esCost\"}}}},\"2\":{\"terms\":{\"field\":\"method\",\"size\":3},\"aggs\":{\"2_1\":{\"sum\":{\"field\":\"esCost\"}}}}}}";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
        Assert.assertTrue(extractResult.getTags().isEmpty());

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 aggs嵌套层数过深 大于等于3层
     */
    @Test
    public void testDslAggsDeepNestMatch() {
        String dsl = "{\"size\":0,\"aggs\":{\"1\":{\"terms\":{\"field\":\"str1\",\"size\":3},\"aggs\":{\"1_1\":{\"sum\":{\"field\":\"num1\"},\"aggs\":{\"1_1_1\":{\"sum\":{\"field\":\"num2\"}}}}}}}}";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("aggs deep nest"));

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 aggs中带了significant_terms
     */
    @Test
    public void testDslAggsSignificantTermsMatch() {
        String dsl = "{\"query\":{\"terms\":{\"force\":[\"British Transport Police\"]}},\"aggregations\":{\"significant_crime_types\":{\"significant_terms\":{\"field\":\"crime_type\"}}}}";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("aggs significant_terms"));

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 query中带了script
     */
    @Test
    public void testDslScriptMatch() {
        String dsl = "{\"query\":{\"bool\":{\"filter\":{\"script\":{\"script\":{\"source\":\"doc['num1'].value > 1\",\"lang\":\"painless\"}}}}}}";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("script"));

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 query中带了regexp
     */
    @Test
    public void testDslRegexpMatch() {
        String dsl = "{\"query\":{\"regexp\":{\"name.first\":\"s.*y\"}}}";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("regexp"));

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 aggs中带了cardinality，且不在第一层嵌套
     */
    @Test
    public void testDslAggsCardinalityNotMatch() {
        String dsl = "{\"aggs\":{\"type_count\":{\"cardinality\":{\"field\":\"type\"}}}}";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
        Assert.assertTrue(extractResult.getTags().isEmpty());

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 aggs中带了cardinality，且不在第一层嵌套
     */
    @Test
    public void testDslAggsCardinalityMatch() {
        String dsl = "{\"aggs\":{\"type_count\":{\"cardinality\":{\"field\":\"type\"}}}}";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("aggs cardinality"));

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 查询语句超过5k
     */
    @Test
    public void testDslDslLengthTooLargeMatch() {
        StringBuilder sb = new StringBuilder(5 * 1024);
        for (int i = 0; i < 5 * 1024; ++i) {
            sb.append("A");
        }
        String dsl = String.format("{\"size\":1,\"query\":{\"query_string\":{\"query\":\"selectFields:%s\"}}}", sb.toString());

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("dsl length more 5k"));

        System.out.println(extractResult.getTags());
    }


    //--------------------------SQL-------------------------------------//

    /**
     * 测试SQL查询语句 query中带了Wildcard，且前缀*号
     */
    @Test
    public void testSQLWildcardTagNotMatch() {
        String sql = "select * from A where search_tags like 'iphone%'";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(sql);
        Assert.assertTrue(extractResult.getTags().isEmpty());

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 query中带了Wildcard，且前缀*号
     */
    @Test
    public void testSQLWildcardTagMatch() {
        String sql = "select * from A where search_tags like '%iphone%'";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(sql);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("wildcard pre*"));

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 aggs嵌套层数过深 大于等于3层
     */
    @Test
    public void testSQLAggsDeepNestNotMatch() {
        String sql = "select * from A group by B,C";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(sql);
        Assert.assertTrue(extractResult.getTags().isEmpty());

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 aggs嵌套层数过深 大于等于3层
     */
    @Test
    public void testSQLAggsDeepNestMatch() {
        String sql = "select * from A group by B,C,D";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(sql);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("aggs deep nest"));

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 aggs中带了significant_terms
     */
    @Test
    public void testSQLAggsSignificantTermsMatch() {
        // 暂时没有找到对应的SQL
    }

    /**
     * 测试DSL查询语句 query中带了script
     */
    @Test
    public void testSQLScriptMatch() {
        String sql = "SELECT insert_time FROM elast*/online where script('doc[\"insert_time\"].date.hourOfDay==16')";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(sql);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("script"));

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 query中带了regexp
     */
    @Test
    public void testSQLRegexpMatch() {
        String sql = "select * from account where name = regexp('ha.*', 'INTERSECTION|COMPLEMENT|EMPTY', 10000)";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(sql);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("regexp"));

        System.out.println(extractResult.getTags());
    }


    /**
     * 测试DSL查询语句 aggs中带了cardinality，且不在第一层嵌套
     */
    @Test
    public void testSQLAggsCardinalityMatch() {
        String sql = "SELECT COUNT(DISTINCT color) FROM cars";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(sql);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("aggs cardinality"));

        System.out.println(extractResult.getTags());
    }

    /**
     * 测试DSL查询语句 查询语句超过5k
     */
    @Test
    public void testSQLDslLengthTooLargeMatch() {
        StringBuilder sb = new StringBuilder(5 * 1024);
        for (int i = 0; i < 5 * 1024; ++i) {
            sb.append("A");
        }
        String sql = String.format("select A from B where C=%s", sb.toString());

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(sql);
        Assert.assertFalse(extractResult.getTags().isEmpty());
        Assert.assertTrue(extractResult.getTags().contains("dsl length more 5k"));

        System.out.println(extractResult.getTags());
    }


}
