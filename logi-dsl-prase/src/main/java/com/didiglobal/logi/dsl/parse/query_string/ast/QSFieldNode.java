package com.didiglobal.logi.dsl.parse.query_string.ast;

import com.didiglobal.logi.dsl.parse.query_string.visitor.QSVisitor;

public class QSFieldNode extends QSNode {
    public QSFieldNode(String source) {
        super(source, 0);
    }

    @Override
    public void accept(QSVisitor vistor) {
        vistor.visit(this);
    }
}
