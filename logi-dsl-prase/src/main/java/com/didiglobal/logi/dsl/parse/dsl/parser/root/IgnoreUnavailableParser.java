package com.didiglobal.logi.dsl.parse.dsl.parser.root;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ObjectNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.root.IgnoreUnavailable;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

/**
 * @author D10865
 * 解析index关键字
 *
 * {"index":["arius_dsl_log_2018-09-20"],"ignore_unavailable":true}
 */
public class IgnoreUnavailableParser extends DslParser {

    public IgnoreUnavailableParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        IgnoreUnavailable node = new IgnoreUnavailable(name);
        node.n = new ObjectNode(obj);

        return node;
    }

}
