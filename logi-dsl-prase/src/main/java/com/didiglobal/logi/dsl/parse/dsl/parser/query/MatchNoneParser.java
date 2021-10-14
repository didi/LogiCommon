package com.didiglobal.logi.dsl.parse.dsl.parser.query;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.query.MatchNone;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class MatchNoneParser extends DslParser {
    public MatchNoneParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object root) throws Exception {
        MatchNone node = new MatchNone(name);
        node.n = ValueNode.getValueNode(root);
        return node;
    }
}
