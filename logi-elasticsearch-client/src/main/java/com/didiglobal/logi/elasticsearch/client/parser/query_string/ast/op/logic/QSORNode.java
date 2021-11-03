package com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.op.logic;

import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.op.common.QSBinaryOpNode;
import com.didiglobal.logi.elasticsearch.client.parser.query_string.visitor.QSVisitor;

public class QSORNode extends QSBinaryOpNode {

    public QSORNode(String source) {
        super(source);
    }

    @Override
    public void accept(QSVisitor vistor) {
        vistor.visit(this);
    }
}
