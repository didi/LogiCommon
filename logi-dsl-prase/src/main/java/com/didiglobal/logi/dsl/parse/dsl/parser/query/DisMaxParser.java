package com.didiglobal.logi.dsl.parse.dsl.parser.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.key.StringNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeList;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.query.DisMax;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

/**
 * @author D10865
 *
 * 解析dis_max查询子句
 *
{
    "query": {
        "dis_max": {
            "tie_breaker": 0.7,
            "boost": 1.2,
            "queries": [
            {
                "term": {
                    "age": 34
                    }
            },
            {
                "term": {
                    "age": 35
                    }
            }
            ]
        }
    }
}
 */
public class DisMaxParser extends DslParser {

    public DisMaxParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        DisMax node = new DisMax(name);
        NodeMap nm = new NodeMap();
        node.n = nm;

        JSONObject jsonObj = (JSONObject) obj;
        for(String key : jsonObj.keySet()) {
            if ("tie_breaker".equalsIgnoreCase(key) || "boost".equalsIgnoreCase(key)) {
                nm.m.put(new StringNode(key), ValueNode.getValueNode(jsonObj.get(key)));

            } else if ("queries".equalsIgnoreCase(key)) {
                nm.m.put(new StringNode(key), NodeList.toNodeList(parserType, (JSON) jsonObj.get(key), false));

            } else {
                // 未知的key 默认处理
                nm.m.put(new StringNode(key), ValueNode.getValueNode(jsonObj.get(key)));
            }
        }

        return node;
    }

}
