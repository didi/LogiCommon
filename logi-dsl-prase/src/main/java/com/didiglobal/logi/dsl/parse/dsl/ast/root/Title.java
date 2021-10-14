package com.didiglobal.logi.dsl.parse.dsl.ast.root;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.Node;
import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;

/**
 * @author D10865
 * 存储title关键字的结果
 * {"timeFieldName":"logTime","title":"heima_tcp.middleware_carreramessage.heima*","fields":"[]"}
 */
public class Title extends KeyWord {
    public Node n;

    public Title(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
