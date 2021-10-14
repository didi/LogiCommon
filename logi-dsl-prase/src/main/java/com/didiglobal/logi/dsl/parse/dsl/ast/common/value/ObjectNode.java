package com.didiglobal.logi.dsl.parse.dsl.ast.common.value;

import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;

public class ObjectNode extends ValueNode {
    public Object value;

    public ObjectNode(Object obj) {
        value = obj;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
