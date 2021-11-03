package com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.query;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.KeyWord;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.common.key.StringNode;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.ast.query.Positive;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.DslParser;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.ParserRegister;
import com.didiglobal.logi.elasticsearch.client.parser.dsl.parser.ParserType;

/**
 * 解析boosting子查询中positive子句
 */
public class PositiveParser extends DslParser {

    public PositiveParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        Positive node = new Positive(name);

        JSONObject jsonObj = (JSONObject) obj;
        for (String key : jsonObj.keySet()) {
            node.m.m.put(new StringNode(key), ParserRegister.parse(parserType, key, jsonObj.get(key)));
        }

        return node;
    }

}
