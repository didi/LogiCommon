package com.didiglobal.logi.dsl.parse.dsl.ast.common.key;

import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;

public class IdentityNode extends KeyNode {

    public IdentityNode(Object obj) {
        super(obj);
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }
}
