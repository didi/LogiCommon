package com.didiglobal.logi.dsl.parse.dsl.parser.root;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ObjectNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.root.TimeFieldName;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

/**
 * @author D10865
 * 解析timeFieldName关键字
 * {"timeFieldName":"logTime","title":"heima_tcp.middleware_carreramessage.heima*","fields":"[]"}
 */
public class TimeFieldNameParser extends DslParser {

    public TimeFieldNameParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        TimeFieldName node = new TimeFieldName(name);
        node.n = new ObjectNode(obj);

        return node;
    }

}
