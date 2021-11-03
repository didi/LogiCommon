package com.didiglobal.logi.elasticsearch.client.response.indices.clearcache;

import com.alibaba.fastjson.annotation.JSONField;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;

public class ESIndicesClearCacheResponse extends ESActionResponse {
    @JSONField(name = "_shards")
    private Shard shards;

    public Shard getShards() {
        return shards;
    }

    public void setShards(Shard shards) {
        this.shards = shards;
    }

    public class Shard {
        @JSONField(name = "total")
        private int total;

        @JSONField(name = "successful")
        private int successful;

        @JSONField(name = "failed")
        private int failed;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSuccessful() {
            return successful;
        }

        public void setSuccessful(int successful) {
            this.successful = successful;
        }

        public int getFailed() {
            return failed;
        }

        public void setFailed(int failed) {
            this.failed = failed;
        }
    }
}
