package com.didiglobal.logi.dsl.parse.dsl.parser.query;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.dsl.parse.dsl.ast.query.MatchPhrasePrefix;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class MatchPhrasePrefixParser extends DslParser {
    public MatchPhrasePrefixParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        MatchPhrasePrefix node = new MatchPhrasePrefix(name);
        NodeMap nm = new NodeMap();

        NodeMap.toField4Value((JSONObject) obj, nm);

        node.n = nm;
        return node;
    }
}
