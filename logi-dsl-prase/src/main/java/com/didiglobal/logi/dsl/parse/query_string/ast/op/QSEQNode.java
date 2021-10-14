package com.didiglobal.logi.dsl.parse.query_string.ast.op;

import com.didiglobal.logi.dsl.parse.query_string.ast.op.common.QSBinaryOpNode;
import com.didiglobal.logi.dsl.parse.query_string.visitor.QSVisitor;

public class QSEQNode extends QSBinaryOpNode {
    public QSEQNode() {
        super(":");
    }

    @Override
    public void accept(QSVisitor vistor) {
        vistor.visit(this);
    }
}
