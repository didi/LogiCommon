package com.didiglobal.logi.elasticsearch.client.response.model.indices;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * author hanbj
 * date：2020-09-28
 */
public class Commit {
    @JSONField(name = "id")
    private String id;

    @JSONField(name = "generation")
    private long generation;

    @JSONField(name = "user_data")
    private UserData userData;

    @JSONField(name = "num_docs")
    private long numDocs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getGeneration() {
        return generation;
    }

    public void setGeneration(long generation) {
        this.generation = generation;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public long getNumDocs() {
        return numDocs;
    }

    public void setNumDocs(long numDocs) {
        this.numDocs = numDocs;
    }
}
