package com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.key;

import com.didiglobal.logi.elasticsearch.client.parser.dsl.visitor.basic.Visitor;

public class IdentityNode extends KeyNode {

    public IdentityNode(Object obj) {
        super(obj);
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }
}
