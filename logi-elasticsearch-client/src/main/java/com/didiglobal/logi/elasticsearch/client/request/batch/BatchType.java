package com.didiglobal.logi.elasticsearch.client.request.batch;

public enum BatchType {

    CREATE("create"),
    INDEX("index"),
    UPDATE("update"),
    DELETE("delete");

    private String str;

    private BatchType(String s) {
        this.str = s;
    }

    public String getStr() {
        return str;
    }
}
