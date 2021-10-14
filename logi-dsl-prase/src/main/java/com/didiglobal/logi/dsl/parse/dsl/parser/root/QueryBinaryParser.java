package com.didiglobal.logi.dsl.parse.dsl.parser.root;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ObjectNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.root.QueryBinary;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class QueryBinaryParser extends DslParser {
    public QueryBinaryParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) {
        QueryBinary node = new QueryBinary(name);
        node.n = new ObjectNode(obj);
        return node;
    }
}
