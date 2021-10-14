package com.didiglobal.logi.dsl.parse.dsl.parser.root;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.root.IndicesBoost;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class IndicesBoostParser extends DslParser {
    public IndicesBoostParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) {
        IndicesBoost node = new IndicesBoost(name);
        node.n = ValueNode.getValueNode(obj);
        return node;
    }
}
