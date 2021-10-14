package com.didiglobal.logi.dsl.parse.dsl.parser.query;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.key.StringNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.query.Boosting;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserRegister;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

/**
 * @author D10865
 *
 * 解析boosting查询子句，例如
{
    "query": {
        "boosting" : {
            "positive" : {
                "term" : {
                    "field1" : "value1"
                 }
            },
            "negative" : {
                "term" : {
                    "field2" : "value2"
                  }
            },
            "negative_boost" : 0.2
        }
    }
}
 */
public class BoostingParser extends DslParser {

    public BoostingParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        // 构造一个Boosting节点
        Boosting node = new Boosting(name);
        NodeMap nm = new NodeMap();
        node.n = nm;

        JSONObject jsonObj = (JSONObject) obj;
        for(String key : jsonObj.keySet()) {
            if ("positive".equalsIgnoreCase(key) || "negative".equalsIgnoreCase(key)) {
                nm.m.put(new StringNode(key), ParserRegister.parse(parserType, key, jsonObj.get(key)));

            } else if ("negative_boost".equalsIgnoreCase(key) || "boost".equalsIgnoreCase(key)) {
                nm.m.put(new StringNode(key), ValueNode.getValueNode(jsonObj.get(key)));

            } else {
                // 未知的key 默认处理
                nm.m.put(new StringNode(key), ValueNode.getValueNode(jsonObj.get(key)));
            }
        }

        return node;
    }

}
