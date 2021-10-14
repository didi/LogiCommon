package com.didiglobal.logi.dsl.parse.query_string.ast.op;

import com.didiglobal.logi.dsl.parse.query_string.ast.op.common.QSSingleOpNode;
import com.didiglobal.logi.dsl.parse.query_string.visitor.QSVisitor;

public class QSPlusNode extends QSSingleOpNode {
    public QSPlusNode(String source) {
        super(source);
    }

    @Override
    public void accept(QSVisitor vistor) {
        vistor.visit(this);
    }
}
