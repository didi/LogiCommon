package com.didiglobal.logi.dsl.parse.dsl.parser.root;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.Node;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.key.StringNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeList;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.multi.NodeMap;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.root.Highlight;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserRegister;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;
import com.didiglobal.logi.dsl.parse.dsl.parser.query.HighlightQueryParser;

public class HighlightParser extends DslParser {

    public HighlightParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) throws Exception {
        Highlight node = new Highlight(name);

        NodeMap nm = new NodeMap();
        JSONObject jsonObject = (JSONObject) obj;
        for (String key : jsonObject.keySet()) {
            if (key.equalsIgnoreCase("fields")) {
                Object o = jsonObject.get(key);
                if (o instanceof JSONObject) {
                    NodeMap tmpNM = new NodeMap();
                    NodeMap.toField4Value((JSONObject) o, tmpNM);
                    nm.m.put(new StringNode(key), tmpNM);
                } else {
                    JSONArray a = (JSONArray) o;
                    NodeList nl = new NodeList();
                    for(Object oo : a) {
                        NodeMap tmpNM = new NodeMap();
                        NodeMap.toField4Value((JSONObject) oo, tmpNM);
                        nl.l.add(tmpNM);
                    }
                    nm.m.put(new StringNode(key), nl);

                }
            } else if (key.equalsIgnoreCase("highlight_query")) {
                Object o = jsonObject.get(key);
                Node n = ParserRegister.parse(ParserType.COMMON, key, o);
                nm.m.put(new StringNode(key), n);
            } else {
                nm.m.put(new StringNode(key), ValueNode.getValueNode(jsonObject.get(key)));
            }

        }
        node.n = nm;

        return node;
    }

    public static void registe() {
        ParserRegister.registe(ParserType.COMMON, "highlight_query", new HighlightQueryParser(ParserType.QUERY));
        HighlightQueryParser.registe();
    }


}
