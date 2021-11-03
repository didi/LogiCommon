package com.didiglobal.logi.elasticsearch.client.response.model.indices;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * author weizijun
 * date：2020-07-28
 */
public class SeqNo {
    @JSONField(name = "max_seq_no")
    private long maxSeqNo;

    @JSONField(name = "local_checkpoint")
    private long localCheckpoint;

    @JSONField(name = "global_checkpoint")
    private long globalCheckpoint;

    public long getMaxSeqNo() {
        return maxSeqNo;
    }

    public void setMaxSeqNo(long maxSeqNo) {
        this.maxSeqNo = maxSeqNo;
    }

    public long getLocalCheckpoint() {
        return localCheckpoint;
    }

    public void setLocalCheckpoint(long localCheckpoint) {
        this.localCheckpoint = localCheckpoint;
    }

    public long getGlobalCheckpoint() {
        return globalCheckpoint;
    }

    public void setGlobalCheckpoint(long globalCheckpoint) {
        this.globalCheckpoint = globalCheckpoint;
    }
}
