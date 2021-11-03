package com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.logic;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.KeyWord;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.logic.Or;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.multi.NodeList;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.DslParser;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.ParserType;

public class OrParser extends DslParser {

    public OrParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        Or node = new Or(name);
        node.n = NodeList.toNodeList(parserType, (JSON) obj, false);
        return node;
    }
}
