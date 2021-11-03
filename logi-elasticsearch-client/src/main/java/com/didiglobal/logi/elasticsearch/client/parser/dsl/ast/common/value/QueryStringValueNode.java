package com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.value;

import com.didiglobal.logi.elasticsearch.client.parser.dsl.visitor.basic.Visitor;
import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.QSNode;

public class QueryStringValueNode extends ValueNode {
    private QSNode qsNode;

    public QSNode getQsNode() {
        return qsNode;
    }

    public void setQsNode(QSNode qsNode) {
        this.qsNode = qsNode;
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }
}
