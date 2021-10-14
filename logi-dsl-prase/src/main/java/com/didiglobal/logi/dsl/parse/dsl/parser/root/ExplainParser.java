package com.didiglobal.logi.dsl.parse.dsl.parser.root;


import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ObjectNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.root.Explain;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

/**
 * @author D10865
 *
 * 解析explain关键字
 *
 * {"from":0,"size":1,"query":{"ids":{"types":[],"values":["472083"]}},"explain":true,"sort":[{"alarmId":{"order":"desc"}}]}
 */
public class ExplainParser extends DslParser {

    public ExplainParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        Explain node = new Explain(name);
        node.n = new ObjectNode(obj);

        return node;
    }

}
