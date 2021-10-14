package com.didiglobal.logi.dsl.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.DslNode;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.visitor.EsDslExportParameterVisitor;
import com.didiglobal.logi.dsl.parse.dsl.visitor.FormatVisitor;
import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.OutputVisitor;
import org.junit.Test;

public class ChangeField {
    @Test
    public void testMatch() throws Exception {
        String dslContent = "{\"highlight\":{\"highlight_query\":{\"bool\":{\"must\":{\"bool\":{\"should\":{\"match\":{\"question\":{\"query\":\"route\",\"type\":\"boolean\"}}}}},\"filter\":[{\"term\":{\"channel_id\":\"2\"}},{\"term\":{\"canonical_country_code\":\"BR\"}},{\"term\":{\"organization_id\":\"1\"}}]}},\"fields\":{\"question\":{}}}}";

        JSONObject jsonObject = JSON.parseObject(dslContent);
        DslNode node = DslParser.parse(jsonObject);
        FormatVisitor formatVisitor = new FormatVisitor();
        node.accept(formatVisitor);

        OutputVisitor outputVisitor = new TransformVisitor();
        node.accept(outputVisitor);

        System.out.println(outputVisitor.ret.toString());

        EsDslExportParameterVisitor esDslExportParameterVisitor = new EsDslExportParameterVisitor();
        node.accept(esDslExportParameterVisitor);

        String dslTemplate = formatVisitor.ret.toString();
        System.out.println(dslTemplate);
    }
}
