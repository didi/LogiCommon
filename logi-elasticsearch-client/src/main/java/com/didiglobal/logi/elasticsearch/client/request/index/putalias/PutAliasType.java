package com.didiglobal.logi.elasticsearch.client.request.index.putalias;

public enum PutAliasType {
    REMOVE("remove"),
    ADD("add");

    private String str;

    private PutAliasType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
