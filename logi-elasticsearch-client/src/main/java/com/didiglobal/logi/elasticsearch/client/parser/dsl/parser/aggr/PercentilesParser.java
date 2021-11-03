package com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.aggr;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.aggr.Percentiles;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.KeyWord;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.DslParser;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.ParserType;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.util.ConstValue;

public class PercentilesParser extends DslParser {

    public PercentilesParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        Percentiles node = new Percentiles(name);

        NodeMap nm = new NodeMap();
        NodeMap.toString2ValueWithField(parserType, (JSONObject) obj, nm, ConstValue.FIELD);
        node.n = nm;

        return node;
    }
}
