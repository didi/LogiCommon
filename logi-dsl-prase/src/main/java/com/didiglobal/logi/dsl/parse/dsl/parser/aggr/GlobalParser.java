package com.didiglobal.logi.dsl.parse.dsl.parser.aggr;

import com.didiglobal.logi.dsl.parse.dsl.ast.aggr.Global;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class GlobalParser extends DslParser {

    public GlobalParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        Global node = new Global(name);
        node.n = ValueNode.getValueNode(obj);
        return node;
    }
}
