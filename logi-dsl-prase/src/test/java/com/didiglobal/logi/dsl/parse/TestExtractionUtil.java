package com.didiglobal.logi.dsl.parse;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.didiglobal.logi.dsl.parse.bean.ExtractResult;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.StopWatch;
import org.junit.Test;
import org.nlpcn.es4sql.parse.ElasticSqlExprParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: D10865
 * @Description:
 * @Date: Create on 2018/5/29 下午4:41
 * @Modified By
 *
 * 从测试文件中读取查询语句，格式化结果，字段提取结果
 */
public class TestExtractionUtil {

    @Test
    public void formatSql() throws Exception {
        String fileName = "sql2.txt";
        String filePath = TestExtractionUtil.class.getClassLoader().getResource(fileName).getFile();

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String sql : lines) {
            SQLExprParser parser = new ElasticSqlExprParser(sql);
            SQLExpr expr = parser.expr();
            SQLQueryExpr sqlExpr = (SQLQueryExpr) expr;


        }
    }



    /**
     * 保存dsl提取结果到文件
     *
     */
    @Test
    public void saveDslExtractResultToFile() throws IOException {
        boolean isNeedDeleteExistsFile = true;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("load file content");

        String fileName = "dsl.txt";
        String filePath = TestExtractionUtil.class.getClassLoader().getResource(fileName).getFile();
        String writeFilePath = "/Users/didi/dsl_extract_result.txt";

        Path writePath = Paths.get(writeFilePath);
        File file = new File(writeFilePath);

        if (isNeedDeleteExistsFile) {
            Files.deleteIfExists(writePath);
        }

        if (!Files.exists(writePath)) {
            Files.createFile(writePath);
        }

        if (!Files.exists(Paths.get(filePath))) {
            return;
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        FileChannel fileChannel = fileOutputStream.getChannel();

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        stopWatch.stop().start("save dsl result");

        for (String line : lines) {
            ExtractResult extractResult = DslExtractionUtilV2.extractDsl(line);
            extractResult.writeToFile(fileChannel);
        }

        fileChannel.close();
        fileOutputStream.flush();
        fileOutputStream.close();

        System.out.println(stopWatch.stop());
    }

    /**
     * 加载dsl文件，并比较结果
     *
     */
    @Test
    public void loadDslFileCompareResult() throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("load file content");

        String filePath = "/Users/didi/dsl_extract_result.txt";

        if (!Files.exists(Paths.get(filePath))) {
            return;
        }

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (int i = 0; i < lines.size(); i += 8) {
            String dsl = lines.get(i + 1);
            ExtractResult extractResult = DslExtractionUtil.extractDsl(dsl);
            if (!lines.get(i + 2).equals(extractResult.getDslTemplate())) {
                System.out.println("dsl template not equal " + dsl);
            }
            if (!getItemContent(lines.get(i + 3)).equals(extractResult.getDslTemplateMd5())) {
                System.out.println("dsl template md5 not equal " + dsl);
            }
            if (!getItemContent(lines.get(i + 4)).equals(extractResult.getSelectFields())) {
                System.out.println("select field not equal " + dsl);
            }
            if (!getItemContent(lines.get(i + 5)).equals(extractResult.getWhereFields())) {
                System.out.println("where field not equal " + dsl);
            }
            if (!getItemContent(lines.get(i + 6)).equals(extractResult.getGroupByFields())) {
                System.out.println("group by field not equal " + dsl);
            }
            if (!getItemContent(lines.get(i + 7)).equals(extractResult.getSortByFields())) {
                System.out.println("sort by field not equal " + dsl);
            }
            System.out.println(dsl);
        }

        System.out.println(stopWatch.stop());
    }

    /**
     * 加载线下dsl查询模板，并比较结果
     *
     * @throws IOException
     */
    @Test
    public void loadOnlineDslThenCompareResult() throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("load file content");

        String filePath = "/Users/didi/dsl_20180907.txt";

        if (!Files.exists(Paths.get(filePath))) {
            return;
        }

        boolean isEqual = true;
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (int i = 0; i < lines.size(); i += 4) {
            isEqual = true;
            String dsl = lines.get(i + 1);
            // 新版本
            //ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
            // 老版本
            ExtractResult extractResult = DslExtractionUtil.extractDsl(dsl);
            if (!lines.get(i + 2).equals(extractResult.getDslTemplate())) {
                System.out.println("dsl template not equal");
                isEqual = false;
            }
            if (!getItemContent(lines.get(i + 3)).equals(extractResult.getDslTemplateMd5())) {
                System.out.println("dsl template md5 not equal");
                isEqual = false;
            }
            if (!isEqual) {
                System.out.println("-----------------------");
                System.out.println("old dslTemplateMd5 : " + getItemContent(lines.get(i + 3)));
                System.out.println("new dslTemplateMd5 : " + extractResult.getDslTemplateMd5());
                System.out.println("old dslTemplate : " + lines.get(i + 2));
                System.out.println("new dslTemplate : " + extractResult.getDslTemplate());
                System.out.println();
            }
            //System.out.println(dsl);
        }

        System.out.println(stopWatch.stop());
    }

    /**
     * 获取[]具体内容
     *
     * @param item
     * @return
     */
    private String getItemContent(String item) {
        if (StringUtils.isBlank(item)) {
            return "";
        }
        int startFlgIndex = item.indexOf("[");
        int endFlgIndex = item.indexOf("]");

        if (startFlgIndex > 0 && endFlgIndex > 0) {
            return item.substring(startFlgIndex + 1, endFlgIndex);
        }
        return item;
    }

    @Test
    public void testFormat() {
        String json = "{\"query\":{\"bool\":{\"must\":[{\"bool\":{\"minimum_should_match\":1,\"should\":[{\"wildcard\":{\"jsonMsg.pageid\":\"page.xiaojukeji.com/market/ddPage_0av68uQy.html*\"}},{\"wildcard\":{\"jsonMsg.pageid\":\"didi.kuaidadi.com/market/ddPage_0av68uQy.html*\"}}]}},{\"term\":{\"jsonMsg.eventid\":\"OMGH5PageView\"}},{\"range\":{\"logtime\":{\"gte\":\"now-6d/d\",\"lte\":\"now/d\",\"time_zone\":\"+08:00\"}}}]}},\"size\":0,\"aggs\":{\"c_pv\":{\"terms\":{\"field\":\"jsonMsg.v\",\"size\":50},\"aggs\":{\"c_uv\":{\"cardinality\":{\"field\":\"jsonMsg.fp\"}}}}}}\n{}";
        JSONObject jsonObject = JSON.parseObject(json);
    }

    @Test
    public void testFormatMsearch() {
        String dsl = "{\"ignore_unavailable\":true,\"index\":[\"test_datamarket_beatles_2018-09-10\"]}{\"highlight\":{\"pre_tags\":[\"@kibana-highlighted-field@\"],\"post_tags\":[\"@/kibana-highlighted-field@\"],\"fields\":{\"*\":{}},\"require_field_match\":false,\"fragment_size\":2147483647},\"size\":500,\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must_not\":[],\"must\":[{\"range\":{\"sinkTime\":{\"format\":\"epoch_millis\",\"gte\":1536595200000,\"lte\":1536681599999}}}]}},\"query\":{\"query_string\":{\"query\":\"_type:new2\",\"analyze_wildcard\":true}}}},\"fielddata_fields\":[\"collectTime\",\"logTime\",\"cleanTime\",\"sinkTime\",\"ariusCreateTime\"],\"script_fields\":{},\"sort\":[{\"_score\":{\"unmapped_type\":\"boolean\",\"order\":\"desc\"}}],\"fields\":[\"*\",\"_source\"],\"aggs\":{\"2\":{\"date_histogram\":{\"field\":\"sinkTime\",\"interval\":\"30m\",\"time_zone\":\"Asia/Shanghai\",\"min_doc_count\":0,\"extended_bounds\":{\"min\":1536595200000,\"max\":1536681599999}}}}}";

        dsl = "{\"query\":{\"filtered\":{\"query\":{\"query_string\":{\"query\":\"type:alert AND groups:(\\\"DSink_ES_Alarm\\\" OR \\\"Arius-vip\\\" OR \\\"Arius-important\\\" OR \\\"Arius-Dev\\\")\",\"analyze_wildcard\":false,\"lowercase_expanded_terms\":false}},\"filter\":{\"bool\":{\"must\":[{\"query\":{\"match\":{\"groups\":{\"query\":\"Arius-vip\",\"type\":\"phrase\"}}}},{\"query\":{\"query_string\":{\"query\":\"*\",\"analyze_wildcard\":false,\"lowercase_expanded_terms\":false}}},{\"range\":{\"etime\":{\"gte\":1541920664398,\"lte\":1544512664398,\"format\":\"epoch_millis\"}}}],\"must_not\":[{\"query\":{\"match\":{\"groups\":{\"query\":\"arius-ops\",\"type\":\"phrase\"}}}}]}}}},\"size\":0,\"aggs\":{\"2\":{\"date_histogram\":{\"field\":\"etime\",\"interval\":\"1d\",\"time_zone\":\"Asia/Shanghai\",\"min_doc_count\":1,\"extended_bounds\":{\"min\":1541920664396,\"max\":1544512664396}}}}}";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
        System.out.println(dsl);
        System.out.println(extractResult.getDslTemplate());
    }

    @Test
    public void testNoramlDsl() {
        String dsl = "{\"size\":30,\"query\":{\"constant_score\":{\"filter\":{\"bool\":{\"should\":[{\"bool\":{\"must\":[{\"terms\":{\"status\":[10]}},{\"terms\":{\"order_type\":[11]}}]}},{\"bool\":{\"must\":[{\"term\":{\"_status\":1}},{\"terms\":{\"channel\":[2]}},{\"terms\":{\"product_id\":[33]}},{\"terms\":{\"sub_status\":[2]}}]}}]}}}},\"from\":0,\"sort\":[{\"order_time\":{\"order\":\"desc\"}}]}";

        ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
        System.out.println(JSONObject.toJSONString(extractResult));
    }

    public static List<String> parseJsonString(String dslContent) {
        List<String> dslLists = Lists.newArrayList();

        for (;;) {

            DefaultJSONParser parser = new DefaultJSONParser(dslContent, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
            Object object = parser.parse();
            if (object instanceof JSONObject) {
                dslLists.add(((JSONObject)object).toJSONString());
                int pos = parser.getLexer().pos();
                if (pos <= 0) {
                    break;
                }
                dslContent = dslContent.substring(pos);
                parser.getLexer().close();
            } else {
                parser.getLexer().close();
                break;
            }
        }

        return dslLists;
    }


    @Test
    public void testGetGroupByFields() {
        String[] dsls = new String[] {
                "{\"query\":{\"term\":{\"user\":\"kimchy\"}}}",
                "{\"from\":0,\"size\":10,\"query\":{\"bool\":{\"must\":[{\"terms\":{\"order_base.passenger_phone\":[\"13121390067\"]}},{\"terms\":{\"order_status.order_status\":[\"5\"]}}]}},\"fields\":[\"order_base.order_id\",\"order_base.district\",\"order_base.product_id\",\"order_base._create_time\"],\"sort\":[{\"order_base._create_time\":{\"order\":\"desc\"}}]}"

        };
        for (String dsl : dsls) {
            System.out.println(DslExtractionUtilV2.extractDsl(dsl).getGroupByFields());
        }
    }


    @Test
    public void testGetWhereFields() {
        String[] dsls = new String[] {
                "{\"query\":{\"bool\":{\"must\":[{\"match\":{\"McompanyId\":{\"query\":\"35855\",\"type\":\"phrase\"}}},{\"range\":{\"Date\":{\"gte\":\"2018-07-26\",\"lte\":\"2018-07-26\"}}},null,{\"bool\":{\"should\":[{\"match\":{\"Department\":{\"query\":\"136927\",\"type\":\"phrase\"}}}]}}]}},\"sort\":[{\"SuccAssignOrders\":{\"order\":\"desc\"}}],\"from\":0,\"size\":\"10\"}"

                //"{\"query\":{\"match\":{\"message\":\"this is a test\"}}}",
                //"{\"query\":{\"term\":{\"user\":\"kimchy\"}}}",
                //"{\"from\":0,\"size\":10,\"query\":{\"bool\":{\"must\":[{\"terms\":{\"order_base.passenger_phone\":[\"13121390067\"]}},{\"terms\":{\"order_status.order_status\":[\"5\"]}}]}},\"fields\":[\"order_base.order_id\",\"order_base.district\",\"order_base.product_id\",\"order_base._create_time\"],\"sort\":[{\"order_base._create_time\":{\"order\":\"desc\"}}]}"

        };

        for (String dsl : dsls) {
            System.out.println(DslExtractionUtilV2.extractDsl(dsl).getDslTemplate());
        }

        //System.out.println(DslExtractionUtilV2.tokenWords("{\"query\":{\"term\":{\"user\":\"kimchy\"}}}"));
    }





    @Test
    public void testSortedMap() {
        Map.Entry<String, Object> entry = null;
        Object value = null;
        String key = null;

        //String dsl = "{\"from\":0,\"size\":10,\"query\":{\"bool\":{\"must\":{\"bool\":{\"must\":[{\"match\":{\"order_base.product_id\":{\"query\":\"3\",\"type\":\"phrase\"}}},{\"match\":{\"order_base.is_pay\":{\"query\":\"1\",\"type\":\"phrase\"}}},{\"match\":{\"order_base.district\":{\"query\":\"0592\",\"type\":\"phrase\"}}},{\"match\":{\"order_base.type\":{\"query\":\"1\",\"type\":\"phrase\"}}},{\"bool\":{\"should\":[{\"match\":{\"order_base.driver_id\":{\"query\":\"565203449883895\",\"type\":\"phrase\"}}}]}},{\"range\":{\"order_base._birth_time\":{\"from\":\"2018-08-04T00:00:00+0800\",\"to\":\"2018-08-07T08:41:57+0800\",\"include_lower\":true,\"include_upper\":true}}}]}}}},\"sort\":[{\"order_base._birth_time\":{\"order\":\"desc\"}}]}";
        String dsl = "{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":{\"bool\":{\"must\":[{\"range\":{\"eventtime\":{\"from\":\"1533571200000\",\"to\":null,\"include_lower\":true,\"include_upper\":true}}},{\"range\":{\"eventtime\":{\"from\":null,\"to\":\"1533657600000\",\"include_lower\":true,\"include_upper\":false}}}]}}}},\"_source\":{\"includes\":[\"SUM\"],\"excludes\":[]},\"aggregations\":{\"eventtime\":{\"date_histogram\":{\"field\":\"eventtime\",\"interval\":\"1800s\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},\"aggregations\":{\"client.id.raw\":{\"terms\":{\"field\":\"client.id.raw\",\"size\":0},\"aggregations\":{\"eventBytePerSecond\":{\"sum\":{\"field\":\"m_headers.content_length\"}}}}}}}}";
        ExtractResult extractResult = DslExtractionUtil.extractDsl(dsl);
        System.out.println(extractResult.getDslTemplate());
        System.out.println(extractResult.getDslTemplateMd5());

        //"{\"size\":100,\"from\":0}";
//        Map<String, Object> map = JSON.parseObject(dsl, SortedMap.class);
//        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            entry = iterator.next();
//            key = entry.getKey();
//            common = entry.getValue();
//            System.out.println("key -> " + key + ", common -> " + common);
//        }

        //dsl = "{\"from\":0,\"size\":100}";
        dsl = "{\"from\":0,\"size\":10,\"query\":{\"bool\":{\"must\":{\"bool\":{\"must\":[{\"match\":{\"order_base.is_pay\":{\"query\":\"1\",\"type\":\"phrase\"}}},{\"match\":{\"order_base.district\":{\"query\":\"0592\",\"type\":\"phrase\"}}},{\"match\":{\"order_base.type\":{\"query\":\"1\",\"type\":\"phrase\"}}},{\"match\":{\"order_base.product_id\":{\"query\":\"3\",\"type\":\"phrase\"}}},{\"bool\":{\"should\":[{\"match\":{\"order_base.driver_id\":{\"query\":\"565203449883895\",\"type\":\"phrase\"}}}]}},{\"range\":{\"order_base._birth_time\":{\"from\":\"2018-08-04T00:00:00+0800\",\"to\":\"2018-08-07T08:41:57+0800\",\"include_lower\":true,\"include_upper\":true}}}]}}}},\"sort\":[{\"order_base._birth_time\":{\"order\":\"desc\"}}]}";
        //extractResult = DslExtractionUtil.extractDsl(dsl);
        System.out.println(extractResult.getDslTemplate());

        //        map = JSON.parseObject(dsl, SortedMap.class);
//        iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            entry = iterator.next();
//            key = entry.getKey();
//            common = entry.getValue();
//            System.out.println("key -> " + key + ", common -> " + common);
//        }

        Map<String, String> map = new TreeMap<>();
        map.put("123","a");
        map.put("456","b");
        map.put("457","c");
        map.put("789","a");
        System.out.println(map);
    }

    @Test
    public void getDslTemplate() {
        ExtractResult extractResult = DslExtractionUtil.extractDsl("");
        System.out.println(extractResult.getDslTemplate());
        System.out.println(extractResult.getDslTemplateMd5());
    }

    @Test
    public void testFormatDsl() {
        String[] dsls = new String[] {
//                "{\"size\":0,\"query\":{\"match\":{\"$reff\":\"@\"}},\"aggs\":{\"ip\":{\"terms\":{\"field\":\"ipsrc\"}}}}",
//                "{\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"terms\":{\"driver_id\":[564838915185658,563667491627009,564074212229120,564605878545470,563725635035136]}}]}}}},\"_source\":[\"driver_id\",\"driver_grade\"],\"from\":0,\"size\":5}",
//                "{\"from\":0,\"size\":1,\"query\":{\"bool\":{\"must\":{\"bool\":{\"must\":[{\"match\":{\"order_status.driver_phone\":{\"query\":\"13331889036\",\"type\":\"phrase\"}}},{\"range\":{\"order_base._birth_time\":{\"from\":\"2018-05-08T00:13:47+0800\",\"to\":\"2018-05-23T00:13:47+0800\",\"include_lower\":true,\"include_upper\":true}}}]}}}},\"sort\":[{\"order_base._birth_time\":{\"order\":\"desc\"}}]}",
//                "{\"query\":{\"bool\":{\"must\":[{\"term\":{\"Date\":\"2018-05-23\"}},{\"bool\":{\"should\":[{\"match\":{\"DriverId\":{\"query\":565898325135947,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950087982284,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542152936823,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":563949291962375,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565115539226786,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565245311983882,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950059162651,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950128267046,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565602119912695,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950165420932,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542182754022,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567949956878064,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542244272858,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542249517770,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542230688849,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":566446101109070,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950125052172,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":564320261964247,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542268528038,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950165580666,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565593346089802,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542230211377,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542269825292,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542228222433,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":566415750141502,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565292065494606,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542245410136,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542286337968,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":564307957452802,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542284174887,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542276851997,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950111984159,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542346959468,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565660807271928,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542301427997,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565281518659795,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542365199858,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565707242214665,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542299364534,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950149299623,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950019458045,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542411616629,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":566441573160338,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":564096456724480,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":564065403342848,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542238220768,\"type\":\"phrase\"}}}]}},{\"range\":{\"DistinctFlowFee\":{\"gt\":1324}}}]}},\"from\":0,\"size\":10}",
//                "{\"size\":0,\"query\":{\"constant_score\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"file.sha256.raw\":\"aac6653ac952f9427664dd3cb287e40c7b1f86817d0939132840a300011e6a0d\"}},{\"term\":{\"client.id.raw\":\"VjFfMTAwMDAwMV9QTEhLRTNBQzdJNDNJU1E5U1RHMDJCU0lCMA\"}},{\"term\":{\"file.isread\":true}},{\"exists\":{\"field\":\"process.id\"}},{\"exists\":{\"field\":\"process.starttime\"}}]}}}},\"aggs\":{\"unique_pid\":{\"terms\":{\"field\":\"process.id\",\"size\":10000},\"aggs\":{\"unique_start\":{\"terms\":{\"field\":\"process.starttime\",\"size\":10000}}}}},\"sort\":[{\"eventtime\":{\"order\":\"asc\"}}]}",
//                "{\"query\":{\"bool\":{\"must\":[{\"term\":{\"Date\":\"2018-05-22\"}},{\"term\":{\"DriverId\":\"564828514167609\"}}]}},\"from\":0,\"size\":10}",
//                "{\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"order_base.driver_id\":\"564215494873088\"}},{\"range\":{\"order_status.assigned_time\":{\"gte\":\"2018-05-2300:00:00\"}}},{\"range\":{\"order_status.assigned_time\":{\"lte\":\"2018-05-2323:59:59\"}}},{\"term\":{\"order_status.order_status\":5}},{\"term\":{\"order_base.type\":0}}]}}}},\"_source\":[\"order_base.departure_time\",\"order_status.assigned_time\",\"order_base.order_id\",\"order_status.finished_time\",\"order_base._birth_time\",\"order_base.starting_name\",\"order_base._create_time\",\"order_base.travel_id\",\"order_base.product_id\",\"order_base.is_pay\",\"order_extra.is_platform_paid\",\"order_base.type\",\"order_base.dest_name\",\"order_sep.basic_fee\",\"order_base.total_fee\",\"order_base.driver_id\",\"order_status.extra_type_25\",\"order_status.extra_type_22\"],\"from\":0,\"size\":10000}",
//                "{\"query\":{\"bool\":{\"must\":[{\"term\":{\"Date\":\"2018-05-21\"}},{\"term\":{\"CityId\":11}},{\"bool\":{\"should\":[{\"match\":{\"CarLevel\":{\"query\":500,\"type\":\"phrase\"}}},{\"match\":{\"CarLevel\":{\"query\":600,\"type\":\"phrase\"}}},{\"match\":{\"CarLevel\":{\"query\":800,\"type\":\"phrase\"}}},{\"match\":{\"CarLevel\":{\"query\":900,\"type\":\"phrase\"}}}]}}]}},\"from\":0,\"size\":10}",
//                "{\"size\":0,\"query\":{\"constant_score\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"file.sha256.raw\":\"ff693c1672748b503b3d90eeb7edbc1da8eb43e011b16b5a66b3e358e637d154\"}},{\"term\":{\"client.id.raw\":\"VjFfMTAwMDAwMV9HSVA0RTBURThDNU5TNkpBMjdVSU1KUjFCNg\"}},{\"term\":{\"file.isread\":true}},{\"exists\":{\"field\":\"process.id\"}},{\"exists\":{\"field\":\"process.starttime\"}}]}}}},\"aggs\":{\"unique_pid\":{\"terms\":{\"field\":\"process.id\",\"size\":10000},\"aggs\":{\"unique_start\":{\"terms\":{\"field\":\"process.starttime\",\"size\":10000}}}}},\"sort\":[{\"eventtime\":{\"order\":\"asc\"}}]}",
//                    "{}\n{\"query\":{\"match_all\":{}}}\n{}\n{\"query\":{\"match_all\":{}}}\n",
//                    "{\"index\":[\".marvel-es*\"],\"ignore_unavailable\":true}{\"size\":500,\"sort\":[{\"_score\":{\"order\":\"desc\",\"unmapped_type\":\"boolean\"}}],\"highlight\":{\"pre_tags\":[\"@kibana-highlighted-field@\"],\"post_tags\":[\"@/kibana-highlighted-field@\"],\"fields\":{\"*\":{}},\"require_field_match\":false,\"fragment_size\":2147483647},\"query\":{\"query_string\":{\"query\":\"*\",\"analyze_wildcard\":true}},\"fields\":[\"*\",\"_source\"],\"script_fields\":{},\"fielddata_fields\":[]}"
//
        "{\"size\":10,\"query\":{\"match\":{\"title\":\"elasticsearch\"}},\"search_after\":[1463538857,\"tweet#654323\"],\"sort\":[{\"date\":\"asc\"},{\"_uid\":\"desc\"}]}",
                "{\"min_score\":0.5,\"query\":{\"term\":{\"user\":\"kimchy\"}}}",
                "{\"query\":{\"bool\":{\"should\":[{\"match\":{\"name.first\":{\"query\":\"shay\",\"_name\":\"first\"}}},{\"match\":{\"name.last\":{\"query\":\"banon\",\"_name\":\"last\"}}}],\"filter\":{\"terms\":{\"name.last\":[\"banon\",\"kimchy\"],\"_name\":\"test\"}}}}}",
                "{\"size\":10,\"query\":{\"match\":{\"title\":\"elasticsearch\"}},\"sort\":[{\"date\":\"asc\"},{\"_uid\":\"desc\"}]}"


        };

        for (String dsl : dsls) {
            ExtractResult extractResult = DslExtractionUtil.extractDsl(dsl);
            System.out.println(extractResult.getDslTemplate());
        }
    }

    @Test
    public void testFormatSql() {
        String[] sqls = new String[]{
                "select /*! ROUTINGS(/swan/bizlog/bigdata/DP_FE_SNAPSHOT_COLLECTOR) */ * from dquality_metrics_20*,dquality_metrics_21*,dquality_metrics_22* where datasourceId = 1 and topic=\"/swan/bizlog/bigdata/DP_FE_SNAPSHOT_COLLECTOR\" and type=2 and sinkTime >= 1522250100000 order by timestamp asc limit 1",
//                "select client.app_version from endpoint_security_da* where client.id.raw='VjFfMTAwMDAwMV9QMFBLQTNES0czQlNMMTBNMVA4TUM4MFMzQg' order by eventtime desc limit 1",
//                "SELECT status, COUNT(1) FROM mysql_market_order_*/order_info WHERE order_type=2 AND order_time>=1522249396 AND order_time<1522249996 GROUP BY status",
//                "SELECT * FROM shunfengche_beatles.im.broker* where optype=\"sendmsg\" and oids=\"935492226\" ORDER BY  timestamp asc",
//                "SELECT status, COUNT(1) FROM mysql_market_order_*/order_info WHERE order_type=10 AND order_time>=1522250445 AND order_time<1522251045 GROUP BY status",
//                "select clientHost,min(logTime) as startTime ,max(logTime) as endTime from hbase_regionserver_log_2018-03-27 where clientHost like '%bigdata-ys-hbase%' \nand message = 'SingleIndexWriteFailureException'\n",
//                "select  * from man_opph_ordercenter_binlog_*/opph_ordercenter_policy\nwhere product_order_id in('577259747568113677')\n",
//                "select * from sofa_order/order  where order_list.user_id= and order_list.status in (1,2,3,4,5,6,7,8) order by order_list.create_time desc limit 0,10",
//                "select count(*) as counts from endpoint_security_da_2018-04-11, endpoint_security_da_2018-04-12 where m_errors.raw <> null and eventtime >= '1523376000000' and eventtime < '1523462400000' group by m_errors.raw limit 10",
//                "select sum(refund_amount) refund_amount from binlog_aliproxy_2018-04-11/refund where (create_time between '2018-04-11 00:00:00' and '2018-04-11 23:59:59' or done_time between '2018-04-11 00:00:00' and '2018-04-11 23:59:59') group by status",
//                "select count(*) from zeus_ticket_binlog_201801,zeus_ticket_binlog_201802,zeus_ticket_binlog_201803,zeus_ticket_binlog_201804,zeus_ticket_binlog_201706,zeus_ticket_binlog_201707,zeus_ticket_binlog_201708,zeus_ticket_binlog_201709,zeus_ticket_binlog_201710,zeus_ticket_binlog_201711,zeus_ticket_binlog_201712 where create_time >= '2018-04-14T00:00:00+08:00' and create_time < '2018-04-14T01:00:00+08:00'",
                "select count(*) from zeus_ticket_binlog_201801,zeus_ticket_binlog_201802,zeus_ticket_binlog_201803,zeus_ticket_binlog_201804,zeus_ticket_binlog_201706,zeus_ticket_binlog_201707,zeus_ticket_binlog_201708,zeus_ticket_binlog_201709,zeus_ticket_binlog_201710,zeus_ticket_binlog_201711,zeus_ticket_binlog_201712",
//                 "select client.id.raw from endpoint_security_da* group by client.id.raw limit 30000",
                "select dict_id,content from uemc_knowledge_binlog_201805/s_event limit 100",
//                "select  * from dquality_other_2018-06-0* order by sinkTime desc",
//                "select  * from dquality_other_2018-06-0* ",
//                "select * from dos_order_201807 where order_base.order_id=17604478018453",
//                "select * from us01_mysql_br_ark_callcenter/g_service_worksheet where  (order_id = '87961099119359' )  order by id desc limit 0,100"

                "select a,b,c from dquality_other_2018-06-0",
                "select c,b,a from dquality_other_2018-06-0",
                "select * from dquality_other_2018-06-0"
        };

        for (String dsl : sqls) {
            ExtractResult extractResult = DslExtractionUtilV2.extractDsl(dsl);
            System.out.println(extractResult);
        }
    }

    @Test
    public void formatBetchMark() {
        String[] dsls = new String[] {
//                "select /*! ROUTINGS(/swan/bizlog/bigdata/DP_FE_SNAPSHOT_COLLECTOR) */ * from dquality_metrics_20* where datasourceId = 1 and topic=\"/swan/bizlog/bigdata/DP_FE_SNAPSHOT_COLLECTOR\" and type=2 and sinkTime >= 1522250100000 order by timestamp asc limit 1",
//                "select client.app_version from endpoint_security_da* where client.id.raw='VjFfMTAwMDAwMV9QMFBLQTNES0czQlNMMTBNMVA4TUM4MFMzQg' order by eventtime desc limit 1",
//                "SELECT status, COUNT(1) FROM mysql_market_order_*/order_info WHERE order_type=2 AND order_time>=1522249396 AND order_time<1522249996 GROUP BY status",
//                "SELECT * FROM shunfengche_beatles.im.broker* where optype=\"sendmsg\" and oids=\"935492226\" ORDER BY  timestamp asc",
//                "SELECT status, COUNT(1) FROM mysql_market_order_*/order_info WHERE order_type=10 AND order_time>=1522250445 AND order_time<1522251045 GROUP BY status",
//                "select clientHost,min(logTime) as startTime ,max(logTime) as endTime from hbase_regionserver_log_2018-03-27 where clientHost like '%bigdata-ys-hbase%' \nand message = 'SingleIndexWriteFailureException'\n",
//                "select  * from man_opph_ordercenter_binlog_*/opph_ordercenter_policy\nwhere product_order_id in('577259747568113677')\n",
//                "select * from sofa_order/order  where order_list.user_id= and order_list.status in (1,2,3,4,5,6,7,8) order by order_list.create_time desc limit 0,10",
//                "select count(*) as counts from endpoint_security_da_2018-04-11, endpoint_security_da_2018-04-12 where m_errors.raw <> null and eventtime >= '1523376000000' and eventtime < '1523462400000' group by m_errors.raw limit 10",
//                "select sum(refund_amount) refund_amount from binlog_aliproxy_2018-04-11/refund where (create_time between '2018-04-11 00:00:00' and '2018-04-11 23:59:59' or done_time between '2018-04-11 00:00:00' and '2018-04-11 23:59:59') group by status",
//                "select count(*) from zeus_ticket_binlog_201801,zeus_ticket_binlog_201802,zeus_ticket_binlog_201803,zeus_ticket_binlog_201804,zeus_ticket_binlog_201706,zeus_ticket_binlog_201707,zeus_ticket_binlog_201708,zeus_ticket_binlog_201709,zeus_ticket_binlog_201710,zeus_ticket_binlog_201711,zeus_ticket_binlog_201712 where create_time >= '2018-04-14T00:00:00+08:00' and create_time < '2018-04-14T01:00:00+08:00'",
//                "select count(*) from zeus_ticket_binlog_201801,zeus_ticket_binlog_201802,zeus_ticket_binlog_201803,zeus_ticket_binlog_201804,zeus_ticket_binlog_201706,zeus_ticket_binlog_201707,zeus_ticket_binlog_201708,zeus_ticket_binlog_201709,zeus_ticket_binlog_201710,zeus_ticket_binlog_201711,zeus_ticket_binlog_201712",

        };

        long recordCount = 0;
        long tick = System.currentTimeMillis();
        for (int i = 0; i < 10000; ++i) {
            for (String dsl : dsls) {
                //ExtractResultV2 extractResult = DslExtractionUtilV2.extractDsl(dsl);
                ExtractResult extractResult = DslExtractionUtil.extractDsl(dsl);
                ++recordCount;
            }
        }
        long cost = System.currentTimeMillis() - tick;
        double eachCost = cost / (float)recordCount;
        System.out.println("cost " + cost + "ms, recordCount: " + recordCount + ", eachCost: " + eachCost);
    }

    @Test
    public void queryBetchMark() {
        String[] dsls = new String[] {
                "select /*! ROUTINGS(/swan/bizlog/bigdata/DP_FE_SNAPSHOT_COLLECTOR) */ * from dquality_metrics_20* where datasourceId = 1 and topic=\"/swan/bizlog/bigdata/DP_FE_SNAPSHOT_COLLECTOR\" and type=2 and sinkTime >= 1522250100000 order by timestamp asc limit 1",
                "select client.app_version from endpoint_security_da* where client.id.raw='VjFfMTAwMDAwMV9QMFBLQTNES0czQlNMMTBNMVA4TUM4MFMzQg' order by eventtime desc limit 1",
                "SELECT status, COUNT(1) FROM mysql_market_order_*/order_info WHERE order_type=2 AND order_time>=1522249396 AND order_time<1522249996 GROUP BY status",
                "SELECT * FROM shunfengche_beatles.im.broker* where optype=\"sendmsg\" and oids=\"935492226\" ORDER BY  timestamp asc",
                "SELECT status, COUNT(1) FROM mysql_market_order_*/order_info WHERE order_type=10 AND order_time>=1522250445 AND order_time<1522251045 GROUP BY status",
                "select clientHost,min(logTime) as startTime ,max(logTime) as endTime from hbase_regionserver_log_2018-03-27 where clientHost like '%bigdata-ys-hbase%' \nand message = 'SingleIndexWriteFailureException'\n",
                "select  * from man_opph_ordercenter_binlog_*/opph_ordercenter_policy\nwhere product_order_id in('577259747568113677')\n",
                "select * from sofa_order/order  where order_list.user_id= and order_list.status in (1,2,3,4,5,6,7,8) order by order_list.create_time desc limit 0,10",
                "select count(*) as counts from endpoint_security_da_2018-04-11, endpoint_security_da_2018-04-12 where m_errors.raw <> null and eventtime >= '1523376000000' and eventtime < '1523462400000' group by m_errors.raw limit 10",
                "select sum(refund_amount) refund_amount from binlog_aliproxy_2018-04-11/refund where (create_time between '2018-04-11 00:00:00' and '2018-04-11 23:59:59' or done_time between '2018-04-11 00:00:00' and '2018-04-11 23:59:59') group by status",
                "select count(*) from zeus_ticket_binlog_201801,zeus_ticket_binlog_201802,zeus_ticket_binlog_201803,zeus_ticket_binlog_201804,zeus_ticket_binlog_201706,zeus_ticket_binlog_201707,zeus_ticket_binlog_201708,zeus_ticket_binlog_201709,zeus_ticket_binlog_201710,zeus_ticket_binlog_201711,zeus_ticket_binlog_201712 where create_time >= '2018-04-14T00:00:00+08:00' and create_time < '2018-04-14T01:00:00+08:00'",
                "select count(*) from zeus_ticket_binlog_201801,zeus_ticket_binlog_201802,zeus_ticket_binlog_201803,zeus_ticket_binlog_201804,zeus_ticket_binlog_201706,zeus_ticket_binlog_201707,zeus_ticket_binlog_201708,zeus_ticket_binlog_201709,zeus_ticket_binlog_201710,zeus_ticket_binlog_201711,zeus_ticket_binlog_201712",
                "{\"size\":0,\"query\":{\"match\":{\"$reff\":\"@\"}},\"aggs\":{\"ip\":{\"terms\":{\"field\":\"ipsrc\"}}}}",
                "{\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"terms\":{\"driver_id\":[564838915185658,563667491627009,564074212229120,564605878545470,563725635035136]}}]}}}},\"_source\":[\"driver_id\",\"driver_grade\"],\"from\":0,\"size\":5}",
                "{\"from\":0,\"size\":1,\"query\":{\"bool\":{\"must\":{\"bool\":{\"must\":[{\"match\":{\"order_status.driver_phone\":{\"query\":\"13331889036\",\"type\":\"phrase\"}}},{\"range\":{\"order_base._birth_time\":{\"from\":\"2018-05-08T00:13:47+0800\",\"to\":\"2018-05-23T00:13:47+0800\",\"include_lower\":true,\"include_upper\":true}}}]}}}},\"sort\":[{\"order_base._birth_time\":{\"order\":\"desc\"}}]}",
                "{\"query\":{\"bool\":{\"must\":[{\"term\":{\"Date\":\"2018-05-23\"}},{\"bool\":{\"should\":[{\"match\":{\"DriverId\":{\"query\":565898325135947,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950087982284,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542152936823,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":563949291962375,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565115539226786,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565245311983882,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950059162651,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950128267046,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565602119912695,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950165420932,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542182754022,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567949956878064,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542244272858,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542249517770,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542230688849,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":566446101109070,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950125052172,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":564320261964247,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542268528038,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950165580666,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565593346089802,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542230211377,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542269825292,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542228222433,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":566415750141502,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565292065494606,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542245410136,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542286337968,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":564307957452802,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542284174887,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542276851997,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950111984159,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542346959468,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565660807271928,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542301427997,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565281518659795,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542365199858,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":565707242214665,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542299364534,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950149299623,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":567950019458045,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542411616629,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":566441573160338,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":564096456724480,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":564065403342848,\"type\":\"phrase\"}}},{\"match\":{\"DriverId\":{\"query\":580542238220768,\"type\":\"phrase\"}}}]}},{\"range\":{\"DistinctFlowFee\":{\"gt\":1324}}}]}},\"from\":0,\"size\":10}",
                "{\"size\":0,\"query\":{\"constant_score\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"file.sha256.raw\":\"aac6653ac952f9427664dd3cb287e40c7b1f86817d0939132840a300011e6a0d\"}},{\"term\":{\"client.id.raw\":\"VjFfMTAwMDAwMV9QTEhLRTNBQzdJNDNJU1E5U1RHMDJCU0lCMA\"}},{\"term\":{\"file.isread\":true}},{\"exists\":{\"field\":\"process.id\"}},{\"exists\":{\"field\":\"process.starttime\"}}]}}}},\"aggs\":{\"unique_pid\":{\"terms\":{\"field\":\"process.id\",\"size\":10000},\"aggs\":{\"unique_start\":{\"terms\":{\"field\":\"process.starttime\",\"size\":10000}}}}},\"sort\":[{\"eventtime\":{\"order\":\"asc\"}}]}",
                "{\"query\":{\"bool\":{\"must\":[{\"term\":{\"Date\":\"2018-05-22\"}},{\"term\":{\"DriverId\":\"564828514167609\"}}]}},\"from\":0,\"size\":10}",
                "{\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"order_base.driver_id\":\"564215494873088\"}},{\"range\":{\"order_status.assigned_time\":{\"gte\":\"2018-05-2300:00:00\"}}},{\"range\":{\"order_status.assigned_time\":{\"lte\":\"2018-05-2323:59:59\"}}},{\"term\":{\"order_status.order_status\":5}},{\"term\":{\"order_base.type\":0}}]}}}},\"_source\":[\"order_base.departure_time\",\"order_status.assigned_time\",\"order_base.order_id\",\"order_status.finished_time\",\"order_base._birth_time\",\"order_base.starting_name\",\"order_base._create_time\",\"order_base.travel_id\",\"order_base.product_id\",\"order_base.is_pay\",\"order_extra.is_platform_paid\",\"order_base.type\",\"order_base.dest_name\",\"order_sep.basic_fee\",\"order_base.total_fee\",\"order_base.driver_id\",\"order_status.extra_type_25\",\"order_status.extra_type_22\"],\"from\":0,\"size\":10000}",
                "{\"query\":{\"bool\":{\"must\":[{\"term\":{\"Date\":\"2018-05-21\"}},{\"term\":{\"CityId\":11}},{\"bool\":{\"should\":[{\"match\":{\"CarLevel\":{\"query\":500,\"type\":\"phrase\"}}},{\"match\":{\"CarLevel\":{\"query\":600,\"type\":\"phrase\"}}},{\"match\":{\"CarLevel\":{\"query\":800,\"type\":\"phrase\"}}},{\"match\":{\"CarLevel\":{\"query\":900,\"type\":\"phrase\"}}}]}}]}},\"from\":0,\"size\":10}",
                "{\"size\":0,\"query\":{\"constant_score\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"file.sha256.raw\":\"ff693c1672748b503b3d90eeb7edbc1da8eb43e011b16b5a66b3e358e637d154\"}},{\"term\":{\"client.id.raw\":\"VjFfMTAwMDAwMV9HSVA0RTBURThDNU5TNkpBMjdVSU1KUjFCNg\"}},{\"term\":{\"file.isread\":true}},{\"exists\":{\"field\":\"process.id\"}},{\"exists\":{\"field\":\"process.starttime\"}}]}}}},\"aggs\":{\"unique_pid\":{\"terms\":{\"field\":\"process.id\",\"size\":10000},\"aggs\":{\"unique_start\":{\"terms\":{\"field\":\"process.starttime\",\"size\":10000}}}}},\"sort\":[{\"eventtime\":{\"order\":\"asc\"}}]}"

        };

        // 老版本提取
        long recordCount = 0;
        long tick = System.currentTimeMillis();
        for (int i = 0; i < 100; ++i) {
            for (String dsl : dsls) {
                DslExtractionUtil.extractDsl(dsl);
                ++recordCount;
            }
        }
        long cost = System.currentTimeMillis() - tick;
        double eachCost = cost / (float)recordCount;
        System.out.println("cost " + cost + "ms, recordCount: " + recordCount + ", eachCost: " + eachCost + "ms");

        // 新版本提取
        recordCount = 0;
        tick = System.currentTimeMillis();
        for (int i = 0; i < 100; ++i) {
            for (String dsl : dsls) {
                DslExtractionUtilV2.extractDsl(dsl);
                ++recordCount;
            }
        }
        cost = System.currentTimeMillis() - tick;
        eachCost = cost / (float)recordCount;
        System.out.println("cost " + cost + "ms, recordCount: " + recordCount + ", eachCost: " + eachCost + "ms");
    }

}
