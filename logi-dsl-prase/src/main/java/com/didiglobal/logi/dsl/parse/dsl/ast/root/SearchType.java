package com.didiglobal.logi.dsl.parse.dsl.ast.root;


import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.Node;
import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;

/**
 * @author D10865
 *
 *  存储search_type关键字的结果
 *
 *  {"index":["router_access_20180926"],"search_type":"count","ignore_unavailable":true}
 */
public class SearchType extends KeyWord {

    public Node n;

    public SearchType(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
