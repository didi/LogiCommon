package com.didiglobal.logi.dsl.parse.dsl.parser.script;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.script.Script;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

// TODO  支持脚本
public class ScriptParser extends DslParser {

    public ScriptParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        Script node = new Script(name);

        if(!(obj instanceof JSON)) {
            node.n = ValueNode.getValueNode(obj);
            return node;
        }

        NodeMap nm = new NodeMap();
        NodeMap.toString2ValueWithField(parserType, (JSONObject) obj, nm, null);
        node.n = nm;

        return node;
    }
}