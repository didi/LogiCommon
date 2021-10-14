package com.didiglobal.logi.dsl.parse.dsl.ast.common.value;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeList;
import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;

public class StringListNode extends ValueNode {
    public NodeList l = new NodeList();

    @Override
    public int hashCode() {
        return l.hashCode();
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }
}
