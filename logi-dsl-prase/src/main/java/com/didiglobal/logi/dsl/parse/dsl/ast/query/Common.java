package com.didiglobal.logi.dsl.parse.dsl.ast.query;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.Node;
import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;
/**
 * @author D10865
 *
 * 存储common查询子句里的元素
 */
public class Common extends KeyWord {

    public Node n;

    public Common(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
