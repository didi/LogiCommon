package com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.op;

import com.didiglobal.logi.elasticsearch.client.parser.query_string.ast.op.common.QSBinaryOpNode;
import com.didiglobal.logi.elasticsearch.client.parser.query_string.visitor.QSVisitor;

public class QSRangeNode extends QSBinaryOpNode {
    private boolean startInc;
    private boolean endInc;

    public QSRangeNode(boolean startInc, boolean endInc) {
        super("TO");
        this.startInc = startInc;
        this.endInc = endInc;
    }

    @Override
    public void accept(QSVisitor vistor) {
        vistor.visit(this);
    }

    public boolean isStartInc() {
        return startInc;
    }

    public void setStartInc(boolean startInc) {
        this.startInc = startInc;
    }

    public boolean isEndInc() {
        return endInc;
    }

    public void setEndInc(boolean endInc) {
        this.endInc = endInc;
    }
}
