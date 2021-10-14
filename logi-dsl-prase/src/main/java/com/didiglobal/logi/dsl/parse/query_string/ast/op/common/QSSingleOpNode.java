package com.didiglobal.logi.dsl.parse.query_string.ast.op.common;

import com.didiglobal.logi.dsl.parse.query_string.ast.QSNode;
import com.didiglobal.logi.dsl.parse.query_string.parser.ParseException;

public abstract class QSSingleOpNode extends QSOPNode {
    private QSNode node;

    public QSSingleOpNode(String source) {
        super(source, 1);
    }

    public QSNode getNode() {
        return node;
    }

    public void setNode(QSNode node) throws ParseException {
        addValue();
        this.node = node;
    }
}
