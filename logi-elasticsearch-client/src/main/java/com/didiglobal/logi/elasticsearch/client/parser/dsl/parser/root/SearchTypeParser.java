package com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.root;


import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.KeyWord;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.key.StringNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.root.SearchType;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.DslParser;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.ParserType;

/**
 * 解析search_type关键字
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
