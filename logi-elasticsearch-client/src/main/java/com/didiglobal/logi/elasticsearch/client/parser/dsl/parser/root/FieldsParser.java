package com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.root;

import com.alibaba.fastjson.JSONArray;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.KeyWord;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.key.StringNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.multi.NodeList;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.root.Fields;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.DslParser;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.ParserType;

/**
 * fields 解析器
 */
public class FieldsParser extends DslParser {

    public FieldsParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object root) throws Exception {
        Fields node = new Fields(name);

        if (root instanceof JSONArray) {
            node.n = new NodeList();
            NodeList.toFieldList((JSONArray) root, (NodeList) node.n);

        } else if (root instanceof String) {
            node.n = new StringNode(root);
        }

        return node;
    }


}
