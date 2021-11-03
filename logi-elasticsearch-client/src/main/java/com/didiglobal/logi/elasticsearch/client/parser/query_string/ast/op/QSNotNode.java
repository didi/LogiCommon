package com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.op;

import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.op.common.QSSingleOpNode;
import com.didiglobal.logi.elasticsearch.client.parser.query_string.visitor.QSVisitor;

public class QSNotNode extends QSSingleOpNode {

    public QSNotNode(String source) {
        super(source);
    }

    @Override
    public void accept(QSVisitor vistor) {
        vistor.visit(this);
    }
}
