package com.didiglobal.logi.dsl.parse.dsl.ast.root;


import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.Node;
import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;

/**
 * @author D10865
 *
 * 存储explain关键字的结果
 *
 * {"from":0,"size":1,"query":{"ids":{"types":[],"values":["472083"]}},"explain":true,"sort":[{"alarmId":{"order":"desc"}}]}
 *
 */
public class Explain extends KeyWord {
    public Node n;

    public Explain(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
