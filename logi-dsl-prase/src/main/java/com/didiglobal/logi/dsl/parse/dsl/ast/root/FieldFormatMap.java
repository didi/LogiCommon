package com.didiglobal.logi.dsl.parse.dsl.ast.root;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.Node;
import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;

/**
 * @author D10865
 * 存储fieldFormatMap关键字的结果
 * {"fieldFormatMap":"{\"_source\":{\"id\":\"_source\"}}","timeFieldName":"logTime","title":"cn_wo21072_automarket_devcon-customer.automarket*","fields":"[]"}
 */
public class FieldFormatMap extends KeyWord {
    public Node n;

    public FieldFormatMap(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
