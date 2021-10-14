package com.didiglobal.logi.dsl.parse.dsl.parser.logic;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeList;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.logic.MustNot;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class MustNotParser extends DslParser {

    public MustNotParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        MustNot node = new MustNot(name);
        node.n = NodeList.toNodeList(parserType, (JSON) obj, false);
        return node;
    }
}
