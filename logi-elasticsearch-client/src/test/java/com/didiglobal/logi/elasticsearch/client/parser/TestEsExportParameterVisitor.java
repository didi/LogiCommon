package com.didiglobal.logi.elasticsearch.client.parser;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.didiglobal.logi.elasticsearch.client.parser.bean.ExtractResult;
import com.didiglobal.logi.elasticsearch.client.parser.sql.EsExportParameterVisitor;
import com.didiglobal.logi.elasticsearch.client.parser.sql.EsSqlFormatVisitor;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.nlpcn.es4sql.parse.ElasticSqlExprParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Author: D10865
 * @Description:
 * @Date: Create on 2018/8/16 下午3:35
 * @Modified By
 */
public class TestEsExportParameterVisitor {

    @Test
    public void testGetAllFieldInfo() {

        String sql = "select count(*) as counts from endpoint_security_da_2018-04-11, endpoint_security_da_2018-04-12 where m_errors.raw <> null and eventtime >= '1523376000000' and eventtime < '1523462400000' group by m_errors.raw limit 10";//"select a,c,b,d, COUNT(c), AVG(b) from xx where a=0 and b=1 and c=2 or d=2 and e=3 and f=10";
        sql = "select /*! ROUTINGS(/swan/bizlog/bigdata/DP_FE_SNAPSHOT_COLLECTOR) */ * from dquality_metrics_20*,dquality_metrics_21*,dquality_metrics_22* where datasourceId = 1 and topic=\"/swan/bizlog/bigdata/DP_FE_SNAPSHOT_COLLECTOR\" and type=2 and sinkTime >= 1522250100000 order by timestamp asc limit 1";
        sql = "select *,sum(f) from         a where b    > 3 and    c <1  group by a having e>0  limit 1,10 order by z";

        sql = "select /*! HIGHLIGHT(datasource_name,pre_tags:['<em>'], post_tags:['<em/>']) */ /*! HIGHLIGHT(datasource_title,pre_tags:['<em>'], post_tags:['<em/>']) */ /*! HIGHLIGHT(datasource_intro,pre_tags:['<em>'], post_tags:['<em/>']) */ /*! HIGHLIGHT(username,pre_tags:['<em>'], post_tags:['<em/>']) */ /*! HIGHLIGHT(real_name,pre_tags:['<em>'], post_tags:['<em/>']) */ * from ?  where status <> -1 and (datasource_name like '%soda_tianji_shop_tmp%' or datasource_title like '%soda_tianji_shop_tmp%' or datasource_intro like '%soda_tianji_shop_tmp%' or username like '%soda_tianji_shop_tmp%' or real_name like '%soda_tianji_shop_tmp%')  limit 0,10";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(sql);

        System.out.println(extractResult.getDslTemplate());
        System.out.println("tableName -> " + extractResult.getIndices());
        System.out.println("select Fields -> " + extractResult.getSelectFields());
        System.out.println("where Fields -> " + extractResult.getWhereFields());
        System.out.println("group by Fields -> " + extractResult.getGroupByFields());
        System.out.println("order by Fields -> " + extractResult.getSortByFields());
    }

    @Test
    public void testFormatSql() throws Exception {

        String filePath = TestEsExportParameterVisitor.class.getClassLoader().getResource("sql.txt").getPath();
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                if (StringUtils.isNotBlank(line)) {
                    System.out.println(line);
                    ExtractResult extractResultV2 = DslExtractionUtilV2.extractDsl(line);
                    System.out.println(extractResultV2.getDslTemplate());
                    System.out.println("select [" + extractResultV2.getSelectFields() + "]");
                    System.out.println("table [" + extractResultV2.getIndices() + "]");
                    System.out.println("where [" + extractResultV2.getWhereFields() + "]");
                    System.out.println("group by [" + extractResultV2.getGroupByFields() + "]");
                    System.out.println("sort by [" + extractResultV2.getSortByFields() + "]");
                    //System.out.println(DslExtractionUtilV2.extractDsl(line));
                    System.out.println();

                }
            }
        }
    }

    @Test
    public void testFormatDsl() throws Exception {

        String filePath = TestEsExportParameterVisitor.class.getClassLoader().getResource("dsl_1.txt").getPath();
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                if (StringUtils.isNotBlank(line)) {
                    System.out.println(line);
                    ExtractResult extractResultV2 = DslExtractionUtilV2.extractDsl(line);
                    System.out.println(extractResultV2.getDslTemplate());
                    System.out.println("select [" + extractResultV2.getSelectFields() + "]");
                    System.out.println("where [" + extractResultV2.getWhereFields() + "]");
                    System.out.println("group by [" + extractResultV2.getGroupByFields() + "]");
                    System.out.println("sort by [" + extractResultV2.getSortByFields() + "]");
                    //System.out.println(DslExtractionUtilV2.extractDsl(line));
                    System.out.println();

                }
            }
        }
    }

    @Test
    public void test() {
        String dsl = "{\"from\":0,\"size\":0,\"aggregations\":{\"sinkTime\":{\"terms\":{\"field\":\"sinkTime\",\"size\":10000},\"aggregations\":{\"domain\":{\"terms\":{\"field\":\"domain\",\"size\":0},\"aggregations\":{\"attack_type_str\":{\"terms\":{\"field\":\"attack_type_str\",\"size\":0},\"aggregations\":{\"client_ip\":{\"terms\":{\"field\":\"client_ip\",\"size\":0}}}}}}}}}}";

        ExtractResult extractResult = DslExtractionUtil.extractDsl(dsl);
        System.out.println(extractResult.getDslTemplate());
        System.out.println(extractResult.getDslTemplateMd5());
    }

    @Test
    public void testSql() {
        String[] sqls = new String[]{
                "select /*! ROUTINGS(complete-test) */ sum(count) from dquality_metrics_2018-08-30 where datasourceId = 13 and topic=\"complete-test\" and type = 2 and subType = 0 and timestamp >= 1535617380000 and timestamp <= 1535617380000 and tag<>\"rmq\"",
                "select /*! ROUTINGS(complete-test) */ sum(count) from dquality_metrics_2018-08-30 where datasourceId = 13 and topic=\"complete-test\" and type = 2 and subType = 0 and timestamp >= 1535617260000 and timestamp <= 1535617260000 and tag<>\"rmq\""
        };

        for (String sql : sqls) {
            ExtractResult extractResult = DslExtractionUtilV2.extractDsl(sql);
            System.out.println("[" + extractResult.getDslTemplate() + "]");
            System.out.println(extractResult.getDslTemplateMd5());
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
//                "select count(*) as counts from endpoint_security_da_2018-04-11, endpoint_security_da_2018-04-12 where m_errors.raw <> null and eventtime >= '1523376000000' and eventtime < '1523462400000' group by m_errors.raw limit 10",
//                "select a,c,b,d, COUNT(c), AVG(b) from xx where a=0 and b=1 and c=2 or d=2 and e=3 and f=10",
//                "select /*! ROUTINGS(/swan/bizlog/bigdata/DP_FE_SNAPSHOT_COLLECTOR) */ * from dquality_metrics_20*,dquality_metrics_21*,dquality_metrics_22* where datasourceId = 1 and topic=\"/swan/bizlog/bigdata/DP_FE_SNAPSHOT_COLLECTOR\" and type=2 and sinkTime >= 1522250100000 order by timestamp asc limit 1",
//                "select *,sum(f) from         a where b    > 3 and    c <1  group by a having e>0  limit 1,10 order by z",
                "select /*! HIGHLIGHT(datasource_name,pre_tags:['<em>'], post_tags:['<em/>']) */ /*! HIGHLIGHT(datasource_title,pre_tags:['<em>'], post_tags:['<em/>']) */ /*! HIGHLIGHT(datasource_intro,pre_tags:['<em>'], post_tags:['<em/>']) */ /*! HIGHLIGHT(username,pre_tags:['<em>'], post_tags:['<em/>']) */ /*! HIGHLIGHT(real_name,pre_tags:['<em>'], post_tags:['<em/>']) */ * from ?  where status <> -1 and (datasource_name like '%soda_tianji_shop_tmp%' or datasource_title like '%soda_tianji_shop_tmp%' or datasource_intro like '%soda_tianji_shop_tmp%' or username like '%soda_tianji_shop_tmp%' or real_name like '%soda_tianji_shop_tmp%')  limit 0,10"

        };

        for (String sql : dsls) {
            SQLExprParser parser = new ElasticSqlExprParser(sql);
            SQLExpr expr = parser.expr();
            SQLQueryExpr sqlExpr = (SQLQueryExpr) expr;

            // 提取查询模板
            SQLASTOutputVisitor esSqlOutputVisitor = new EsSqlFormatVisitor(new StringBuilder());
            esSqlOutputVisitor.setPrettyFormat(false);
            sqlExpr.accept(esSqlOutputVisitor);

            // 提取字段
            EsExportParameterVisitor esExportParameterVisitor = new EsExportParameterVisitor();
            sqlExpr.accept(esExportParameterVisitor);


            System.out.println(esExportParameterVisitor.getWhereFieldsNames());
            System.out.println(esExportParameterVisitor.getRangeFilterFieldsNames());
            System.out.println(esExportParameterVisitor.getTermFilterFieldsNames());
            System.out.println("=========");
        }
    }

}
