package com.didiglobal.logi.elasticsearch.client.parser.dsl.parser;

public enum ParserType {
    COMMON("root"),
    QUERY("query"),
    AGGR("aggr");

    private String value;

    ParserType(String value) {
        this.value = value;
    }
}
