package com.didiglobal.logi.dsl.parse.dsl.parser.root;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ObjectNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.root.From;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class FromParser extends DslParser {

    public FromParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        From node = new From(name);
        node.v = new ObjectNode(obj);
        return node;
    }
}
