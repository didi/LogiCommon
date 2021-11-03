package com.didiglobal.logi.elasticsearch.client.gateway.search.response.aggr.loop;


public enum LoopResult {
    NORMAL("按照当前顺序执行"),
    RETURN("直接退出循环");
//    BREAK_BUCKETS("退出当前bucket列表"),
//    BREAK_AGGR("退出当前aggr");

    private String desc;

    private LoopResult(String desc) {
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }
}
