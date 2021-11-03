package com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.aggr;

import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.Node;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.visitor.basic.Visitor;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.KeyWord;

public class AggrGeoBounds extends KeyWord {

    public Node n;

    public AggrGeoBounds(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }


}
