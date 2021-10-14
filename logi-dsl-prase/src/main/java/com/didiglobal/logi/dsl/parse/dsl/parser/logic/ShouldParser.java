package com.didiglobal.logi.dsl.parse.dsl.parser.logic;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeList;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.logic.Should;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class ShouldParser extends DslParser {

    public ShouldParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        Should node = new Should(name);
        node.n = NodeList.toNodeList(parserType, (JSON) obj, false);
        return node;
    }
}
