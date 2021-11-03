package com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.root;

import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.KeyWord;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.value.ObjectNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.root.From;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.DslParser;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.ParserType;

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
