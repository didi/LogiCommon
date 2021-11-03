package com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.aggr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.aggr.AggrTerms;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.KeyWord;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.key.FieldNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.key.StringNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.multi.NodeList;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.value.JsonNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.DslParser;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.ParserType;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.util.ConstValue;

public class AggTermsParser extends DslParser {

    public AggTermsParser(ParserType type) {
        super(type);
    }

    private static final String ORDER_STR = "order";

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        AggrTerms node = new AggrTerms(name);
        NodeMap.toString2ValueWithField(parserType, (JSONObject) obj, node.m, ConstValue.FIELD);

        processOrder(node.m);

        return node;
    }


    // 处理order的情况
    //  "order":{"_count":"desc"}  "order":[{"_count":"desc"}]
    private void processOrder(NodeMap nm) {
        StringNode orderKey = new StringNode(ORDER_STR);
        if (nm.m.containsKey(orderKey)) {
            JSON json = ((JsonNode) nm.m.get(orderKey)).json;

            if (json instanceof JSONArray) {
                NodeList nl = new NodeList();

                JSONArray array = (JSONArray) json;
                for (Object o : array) {
                    nl.l.add(processOneOrder((JSONObject) o));
                }

                nm.m.put(orderKey, nl);
            } else {
                nm.m.put(orderKey, processOneOrder((JSONObject) json));
            }
        }
    }

    private NodeMap processOneOrder(JSONObject root) {
        NodeMap nm = new NodeMap();
        for (String key : root.keySet()) {
            nm.m.put(new FieldNode(key), ValueNode.getValueNode(root.get(key)));
        }

        return nm;
    }
}
