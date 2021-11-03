package com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.root;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.KeyWord;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.Node;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.key.StringNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.multi.NodeList;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.root.Rescore;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.DslParser;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.ParserType;

public class RescoreParser extends DslParser {

    public RescoreParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object root) throws Exception {
        Rescore node = new Rescore(name);

        if (root instanceof JSONObject) {
            node.n = parserObj((JSONObject) root);
        } else {
            JSONArray array = (JSONArray) root;
            NodeList nl = new NodeList();
            for (Object obj : array) {
                nl.l.add(parserObj((JSONObject) obj));
            }
            node.n = nl;
        }

        return node;
    }

    private Node parserObj(JSONObject root) throws Exception {
        NodeMap nm = new NodeMap();
        for (String key : root.keySet()) {
            StringNode keyNode = new StringNode(key);
            Object obj = root.get(key);

            if (key.equalsIgnoreCase("query")) {
                nm.m.put(keyNode, parserQuery((JSONObject) obj));
            } else {
                nm.m.put(keyNode, ValueNode.getValueNode(obj));
            }
        }
        return nm;
    }


    private Node parserQuery(JSONObject root) throws Exception {
        NodeMap nm = new NodeMap();

        for (String key : root.keySet()) {
            StringNode keyNode = new StringNode(key);
            Object obj = root.get(key);

            if (key.equalsIgnoreCase("rescore_query")) {
                NodeMap tmp = new NodeMap();
                NodeMap.toString2Node(ParserType.QUERY, (JSONObject) obj, tmp);
                nm.m.put(keyNode, tmp);
            } else {
                nm.m.put(keyNode, ValueNode.getValueNode(obj));
            }
        }

        return nm;
    }
}
