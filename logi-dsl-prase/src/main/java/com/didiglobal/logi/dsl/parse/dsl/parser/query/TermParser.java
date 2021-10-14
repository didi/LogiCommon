package com.didiglobal.logi.dsl.parse.dsl.parser.query;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.dsl.parse.dsl.ast.query.Term;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class TermParser extends DslParser {
    public TermParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        Term node = new Term(name);
        NodeMap.toField4Value((JSONObject) obj, node.m);
        return node;
    }
}
