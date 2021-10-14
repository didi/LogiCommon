package com.didiglobal.logi.dsl.parse.dsl.parser.query;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.dsl.parse.dsl.ast.query.MultiMatch;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class MultiMatchParser extends DslParser {

    public MultiMatchParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        MultiMatch node = new MultiMatch(name);
        NodeMap nm = new NodeMap();

        NodeMap.toString2ValueWithField(parserType, (JSONObject) obj, nm,"fields");

        node.n = nm;
        return node;
    }
}
