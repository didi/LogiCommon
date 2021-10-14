package com.didiglobal.logi.dsl.parse.dsl.parser.aggr;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.aggr.ReverseNested;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class ReverseNestedParser extends DslParser {

    public ReverseNestedParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        ReverseNested node = new ReverseNested(name);

        NodeMap nm = new NodeMap();
        NodeMap.toString2ValueWithField(parserType, (JSONObject) obj, nm, null);
        node.n = nm;

        return node;
    }
}
