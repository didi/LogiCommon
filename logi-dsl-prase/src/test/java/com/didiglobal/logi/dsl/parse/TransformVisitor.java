package com.didiglobal.logi.dsl.parse;

import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.dsl.parse.dsl.ast.query.Match;
import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.OutputVisitor;

public class TransformVisitor extends OutputVisitor {
    public boolean isMatchUsedPhrase = false;

//    @Override
//    public void visit(NodeMap node) {
//        JSONObject root = new JSONObject();
//
//        for(KeyNode n : node.m.keySet()) {
//            n.accept(this);
//            String key = (String) ret;
//            node.m.get(n).accept(this);
//            Object value = ret;
//
//            if (key.equals("match") && isMatchUsedPhrase == true) {
//                root.put("match_phrase", value);
//                isMatchUsedPhrase = false;
//            } else {
//                root.put(key, value);
//            }
//        }
//
//        this.ret = root;
//    }

//    @Override
//    public void visit(Match node) {
//        super.visit(node);
//
//        JSONObject obj = (JSONObject) this.ret;
//        for (String key : obj.keySet()) {
//            Object value = obj.get(key);
//            if (value instanceof JSONObject) {
//                Object type = ((JSONObject) value).get("type");
//                if (type != null && type.equals("phrase")) {
//                    node.setName("match_phrase");
//                    ((JSONObject) value).remove("type");
//                    isMatchUsedPhrase = true;
//                    break;
//                }
//            }
//        }
//    }

    /**
     * match不再支持type，旧版本的三种type中，phrase改为match_phrase查询，
     * phrase_prefix改为match_phrase_prefix查询
     * @param node
     */
    @Override
    public void visit(Match node) {
        super.visit(node);

        JSONObject obj = (JSONObject) this.ret;
        for (String key : obj.keySet()) {
            Object value = obj.get(key);
            if (value instanceof JSONObject) {
                Object type = ((JSONObject) value).get("type");
                ((JSONObject) value).remove("type");
                if (type != null) {
                    if (type.equals("phrase")) {
                        node.setName("match_phrase");
                    } else if (type.equals("phrase_prefix") || type.equals("phrasePrefix")) {
                        node.setName("match_phrase_prefix");
                    }

                }
            }
        }
    }

}
