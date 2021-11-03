package com.didiglobal.logi.elasticsearch.client.parser.dsl.ast;

import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.Node;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.visitor.basic.Visitor;

public class DslNode extends Node {
    public NodeMap m = new NodeMap();

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }

}
