package com.didiglobal.logi.elasticsearch.client.response.model.indices;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * author weizijun
 * date：2020-07-28
 */
public class UserData {
    @JSONField(name = "local_checkpoint")
    private long localCheckpoint;

    @JSONField(name = "max_unsafe_auto_id_timestamp")
    private long maxUnsafeAutoIdTimestamp;

    @JSONField(name = "translog_uuid")
    private String translogUuid;

    @JSONField(name = "history_uuid")
    private String historyUuid;

    @JSONField(name = "sync_id")
    private String syncId;

    @JSONField(name = "translog_generation")
    private long translogGeneration;

    @JSONField(name = "max_seq_no")
    private long maxSeqNo;

    public long getLocalCheckpoint() {
        return localCheckpoint;
    }

    public void setLocalCheckpoint(long localCheckpoint) {
        this.localCheckpoint = localCheckpoint;
    }

    public long getMaxUnsafeAutoIdTimestamp() {
        return maxUnsafeAutoIdTimestamp;
    }

    public void setMaxUnsafeAutoIdTimestamp(long maxUnsafeAutoIdTimestamp) {
        this.maxUnsafeAutoIdTimestamp = maxUnsafeAutoIdTimestamp;
    }

    public String getTranslogUuid() {
        return translogUuid;
    }

    public void setTranslogUuid(String translogUuid) {
        this.translogUuid = translogUuid;
    }

    public String getHistoryUuid() {
        return historyUuid;
    }

    public void setHistoryUuid(String historyUuid) {
        this.historyUuid = historyUuid;
    }

    public String getSyncId() {
        return syncId;
    }

    public void setSyncId(String syncId) {
        this.syncId = syncId;
    }

    public long getTranslogGeneration() {
        return translogGeneration;
    }

    public void setTranslogGeneration(long translogGeneration) {
        this.translogGeneration = translogGeneration;
    }

    public long getMaxSeqNo() {
        return maxSeqNo;
    }

    public void setMaxSeqNo(long maxSeqNo) {
        this.maxSeqNo = maxSeqNo;
    }
}
