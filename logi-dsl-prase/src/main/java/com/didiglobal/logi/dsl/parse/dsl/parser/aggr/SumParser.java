package com.didiglobal.logi.dsl.parse.dsl.parser.aggr;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.aggr.Sum;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;
import com.didiglobal.logi.dsl.parse.dsl.util.ConstValue;

public class SumParser extends DslParser {

    public SumParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        Sum node = new Sum(name);
        NodeMap.toString2ValueWithField(parserType, (JSONObject) obj, node.m, ConstValue.FIELD);
        return node;
    }
}
