package com.didiglobal.logi.dsl.parse.dsl.ast.query;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.Node;
import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;

/**
 * @author D10865
 *
 * 存储dis_max查询子句里的元素
 */
public class DisMax extends KeyWord {

    public Node n;

    public DisMax(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
