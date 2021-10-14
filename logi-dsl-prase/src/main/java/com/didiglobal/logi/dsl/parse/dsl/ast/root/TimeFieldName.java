package com.didiglobal.logi.dsl.parse.dsl.ast.root;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.Node;
import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;

/**
 * @author D10865
 * 存储timeFieldName关键字的结果
 * {"timeFieldName":"logTime","title":"heima_tcp.middleware_carreramessage.heima*","fields":"[]"}
 */
public class TimeFieldName extends KeyWord {
    public Node n;

    public TimeFieldName(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
