package com.didiglobal.logi.dsl.parse.query_string.ast.op.logic;

import com.didiglobal.logi.dsl.parse.query_string.ast.op.common.QSSingleOpNode;
import com.didiglobal.logi.dsl.parse.query_string.parser.Token;
import com.didiglobal.logi.dsl.parse.query_string.visitor.QSVisitor;

public class QSParenNode extends QSSingleOpNode {

    private Token boost;

    public QSParenNode(String source) {
        super(source);
    }

    public Token getBoost() {
        return boost;
    }

    public void setBoost(Token boost) {
        this.boost = boost;
    }

    @Override
    public void accept(QSVisitor vistor) {
        vistor.visit(this);
    }
}
