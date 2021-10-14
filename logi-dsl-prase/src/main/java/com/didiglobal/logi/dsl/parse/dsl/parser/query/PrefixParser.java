package com.didiglobal.logi.dsl.parse.dsl.parser.query;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.dsl.parse.dsl.ast.query.Prefix;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class PrefixParser extends DslParser {
    public PrefixParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        Prefix node = new Prefix(name);
        NodeMap.toField4Value((JSONObject) obj, node.m);
        return node;
    }
}
