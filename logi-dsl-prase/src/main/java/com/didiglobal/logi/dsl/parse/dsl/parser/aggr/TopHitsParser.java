package com.didiglobal.logi.dsl.parse.dsl.parser.aggr;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.aggr.TopHits;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.Node;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.key.StringNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserRegister;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class TopHitsParser extends DslParser {

    public TopHitsParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        TopHits node = new TopHits(name);


        if(!(obj instanceof JSONObject)) {
            throw new Exception("not json object, obj:" + obj);
        }

        JSONObject jsonObject = (JSONObject) obj;

        for(String key : jsonObject.keySet()) {
            if(key.equalsIgnoreCase(TopHits.SORT_STR)) {
                Node n = ParserRegister.parse(parserType, key, jsonObject.get(key));
                node.m.m.put(new StringNode(key), n);

            } else {
                node.m.m.put(new StringNode(key), ValueNode.getValueNode(jsonObject.get(key)));
            }
        }

        return node;
    }
}
