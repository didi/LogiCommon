package com.didiglobal.logi.dsl.parse.dsl.ast.query;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;

/**
 * @author D10865
 *
 * 存储positive查询子句里的元素
 */
public class Positive extends KeyWord {

    public NodeMap m = new NodeMap();

    public Positive(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
