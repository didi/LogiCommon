package com.didiglobal.logi.elasticsearch.client.response.indices.catindices;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * cat/indices接口的返回结构
 *
 */
public class CatIndexResult {

    @JSONField(name = "health")
    private String health;

    @JSONField(name = "status")
    private String status;

    @JSONField(name = "index")
    private String index;

    @JSONField(name = "pri")
    private String pri;

    @JSONField(name = "rep")
    private String rep;

    @JSONField(name = "docs.count")
    private String docsCount;

    @JSONField(name = "docs.deleted")
    private String docsDeleted;

    @JSONField(name = "store.size")
    private String storeSize;

    @JSONField(name = "pri.store.size")
    private String priStoreSize;

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getDocsCount() {
        return docsCount;
    }

    public void setDocsCount(String docsCount) {
        this.docsCount = docsCount;
    }

    public String getDocsDeleted() {
        return docsDeleted;
    }

    public void setDocsDeleted(String docsDeleted) {
        this.docsDeleted = docsDeleted;
    }

    public String getStoreSize() {
        return storeSize;
    }

    public void setStoreSize(String storeSize) {
        this.storeSize = storeSize;
    }

    public String getPriStoreSize() {
        return priStoreSize;
    }

    public void setPriStoreSize(String priStoreSize) {
        this.priStoreSize = priStoreSize;
    }
}
