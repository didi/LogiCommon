package com.didiglobal.logi.dsl.parse.dsl.ast.common.key;

import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;

public class StringNode extends KeyNode {

    public StringNode(Object obj) {
        super(obj);
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }
}
