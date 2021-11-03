package com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.root;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.KeyWord;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.key.FieldNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.root.IndexConstraints;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.DslParser;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.ParserType;

/**
 * fields 解析器
 */
public class IndexConstraintsParser extends DslParser {

    public IndexConstraintsParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object root) throws Exception {
        IndexConstraints node = new IndexConstraints(name);
        NodeMap nm = new NodeMap();

        JSONObject jsonObject = (JSONObject) root;
        for (String key : jsonObject.keySet()) {
            FieldNode fieldNode = new FieldNode(key);
            nm.m.put(fieldNode, ValueNode.getValueNode(jsonObject.get(key)));
        }

        node.n = nm;
        return node;
    }


}
