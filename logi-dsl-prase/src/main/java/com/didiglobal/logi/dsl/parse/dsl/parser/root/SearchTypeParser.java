package com.didiglobal.logi.dsl.parse.dsl.parser.root;


import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.key.StringNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.root.SearchType;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

/**
 * @author D10865
 *
 * 解析search_type关键字
 *
 * {"index":["router_access_20180926"],"search_type":"count","ignore_unavailable":true}
 */
public class SearchTypeParser extends DslParser {

    public SearchTypeParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        SearchType node = new SearchType(name);
        node.n = new StringNode(obj);

        return node;
    }
}
