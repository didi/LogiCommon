package com.didiglobal.logi.dsl.parse.dsl.parser.root;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.root.TerminateAfter;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class TerminateAfterParser extends DslParser {
    public TerminateAfterParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) {
        TerminateAfter node = new TerminateAfter(name);
        node.n = ValueNode.getValueNode(obj);
        return node;
    }
}
