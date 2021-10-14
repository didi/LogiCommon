package com.didiglobal.logi.dsl.parse.dsl.ast.common;

public enum NodeType {
    FIELD("field"),
    IDENTITY("identity"),
    JSON("json"),
    KEY("key"),
    VALUE("value"),
    KEYWORD("keyword");

    private String value;

    NodeType(String value) {
        this.value = value;
    }
}
