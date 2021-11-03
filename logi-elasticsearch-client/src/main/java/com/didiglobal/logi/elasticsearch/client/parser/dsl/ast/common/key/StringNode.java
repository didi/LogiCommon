package com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.key;

import com.didiglobal.logi.elasticsearch.client.parser.dsl.visitor.basic.Visitor;

public class StringNode extends KeyNode {

    public StringNode(Object obj) {
        super(obj);
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }
}
