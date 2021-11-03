package com.didiglobal.logi.elasticsearch.client.request.dcdr;

/**
 * author weizijun
 * date：2020-01-16
 */

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * {
 * "primary_index" : "foundation_fd.data-online.arius.gateway_2020-01-14",
 * "replica_index" : "foundation_fd.data-online.arius.gateway_2020-01-14",
 * "replica_cluster" : "ecm-arius-dcdr",
 * "replication_state" : true,
 * "shard_id" : 0,
 * "syncing" : false,
 * "recovering" : false,
 * "closed" : false,
 * "available_send_bulk_number" : 10,
 * "primary_global_checkpoint" : 34127709,
 * "primary_max_seq_no" : 34127709,
 * "replica_global_checkpoint" : -1,
 * "replica_max_seq_no" : -1,
 * "time_since_update_replica_checkpoint" : -1,
 * "total_send_time_millis" : 0,
 * "successful_send_requests" : 0,
 * "failed_send_requests" : 0,
 * "operations_send" : 0,
 * "bytes_send" : 0,
 * "time_since_last_send_millis" : 0,
 * "commit_translog_offset" : "574g/55t/0s",
 * "current_translog_offset" : "574g/55t/0s",
 * "in_sync_translog_offset" : [
 * [ ]
 * ],
 * "success_recover_count" : 0,
 * "failed_recover_count" : 0,
 * "recover_total_time_millis" : 667437
 * }
 */
public class DCDRStats {


    /**
     * 主索引名字
     */
    @JSONField(name = "primary_index")
    private String primaryIndex;

    /**
     * 从索引名字
     */
    @JSONField(name = "replica_index")
    private String replicaIndex;

    /**
     * 从集群
     */
    @JSONField(name = "replica_cluster")
    private String replicaCluster;

    /**
     * 链路状态
     */
    @JSONField(name = "replication_state")
    private boolean replicationState;

    /**
     * shardId
     */
    @JSONField(name = "shard_id")
    private int shardId;

    /**
     * 向当前节点发送request获取
     */
    @JSONField(name = "primary_global_checkpoint")
    private long primaryGlobalCheckpoint;

    /**
     * 向当前节点发送request获取
     */
    @JSONField(name = "primary_max_seq_no")
    private long primaryMaxSeqNo;

    /**
     * 每次send——translog的response中获取
     */
    @JSONField(name = "replica_global_checkpoint")
    private long replicaGlobalCheckpoint;

    /**
     * 每次send——translog的response中获取
     */
    @JSONField(name = "replica_max_seq_no")
    private long replicaMaxSeqNo;

    /**
     * 上一次更新checkpoint的时间
     */
    @JSONField(name = "time_since_update_replica_checkpoint")
    private long timeSinceUpdateReplicaCheckPoint;

    /**
     * send的时候获取
     */
    @JSONField(name = "total_send_time_millis")
    private long totalSendTimeMillis;

    /**
     * send的时候获取
     */
    @JSONField(name = "successful_send_requests")
    private long successfulSendRequests;

    /**
     * send的时候获取
     */
    @JSONField(name = "failed_send_requests")
    private long failedSendRequests;

    /**
     * send的时候获取
     */
    @JSONField(name = "operations_send")
    private long operationsSends;

    /**
     * send的时候获取
     */
    @JSONField(name = "bytes_send")
    private long bytesSend;

    /**
     * send的时候获取
     */
    @JSONField(name = "time_since_last_send_millis")
    private long timeSinceLastSendMillis;

    /**
     * tranlog位点信息
     */
    @JSONField(name = "commit_translog_offset")
    private String commitOffsetStr;

    @JSONField(name = "current_translog_offset")
    private String currentOffsetStr;

    @JSONField(name = "in_sync_translog_offset")
    private List<List<String>> inSyncOffset;

    /**
     * dcdr info
     */
    @JSONField(name = "syncing")
    private boolean syncing;

    @JSONField(name = "recovering")
    private boolean recovering;

    @JSONField(name = "closed")
    private boolean closed;

    @JSONField(name = "available_send_bulk_number")
    private int availableSendBulkNumber;

    /**
     * recover
     */
    @JSONField(name = "success_recover_count")
    private long successRecoverCount;

    @JSONField(name = "failed_recover_count")
    private long failedRecoverCount;

    @JSONField(name = "recover_total_time_millis")
    private long recoverTotalTimeMillis;

    public String getPrimaryIndex() {
        return primaryIndex;
    }

    public void setPrimaryIndex(String primaryIndex) {
        this.primaryIndex = primaryIndex;
    }

    public String getReplicaIndex() {
        return replicaIndex;
    }

    public void setReplicaIndex(String replicaIndex) {
        this.replicaIndex = replicaIndex;
    }

    public String getReplicaCluster() {
        return replicaCluster;
    }

    public void setReplicaCluster(String replicaCluster) {
        this.replicaCluster = replicaCluster;
    }

    public boolean isReplicationState() {
        return replicationState;
    }

    public void setReplicationState(boolean replicationState) {
        this.replicationState = replicationState;
    }

    public int getShardId() {
        return shardId;
    }

    public void setShardId(int shardId) {
        this.shardId = shardId;
    }

    public long getPrimaryGlobalCheckpoint() {
        return primaryGlobalCheckpoint;
    }

    public void setPrimaryGlobalCheckpoint(long primaryGlobalCheckpoint) {
        this.primaryGlobalCheckpoint = primaryGlobalCheckpoint;
    }

    public long getPrimaryMaxSeqNo() {
        return primaryMaxSeqNo;
    }

    public void setPrimaryMaxSeqNo(long primaryMaxSeqNo) {
        this.primaryMaxSeqNo = primaryMaxSeqNo;
    }

    public long getReplicaGlobalCheckpoint() {
        return replicaGlobalCheckpoint;
    }

    public void setReplicaGlobalCheckpoint(long replicaGlobalCheckpoint) {
        this.replicaGlobalCheckpoint = replicaGlobalCheckpoint;
    }

    public long getReplicaMaxSeqNo() {
        return replicaMaxSeqNo;
    }

    public void setReplicaMaxSeqNo(long replicaMaxSeqNo) {
        this.replicaMaxSeqNo = replicaMaxSeqNo;
    }

    public long getTotalSendTimeMillis() {
        return totalSendTimeMillis;
    }

    public void setTotalSendTimeMillis(long totalSendTimeMillis) {
        this.totalSendTimeMillis = totalSendTimeMillis;
    }

    public long getSuccessfulSendRequests() {
        return successfulSendRequests;
    }

    public void setSuccessfulSendRequests(long successfulSendRequests) {
        this.successfulSendRequests = successfulSendRequests;
    }

    public long getFailedSendRequests() {
        return failedSendRequests;
    }

    public void setFailedSendRequests(long failedSendRequests) {
        this.failedSendRequests = failedSendRequests;
    }

    public long getOperationsSends() {
        return operationsSends;
    }

    public void setOperationsSends(long operationsSends) {
        this.operationsSends = operationsSends;
    }

    public long getBytesSend() {
        return bytesSend;
    }

    public void setBytesSend(long bytesSend) {
        this.bytesSend = bytesSend;
    }

    public long getTimeSinceLastSendMillis() {
        return timeSinceLastSendMillis;
    }

    public void setTimeSinceLastSendMillis(long timeSinceLastSendMillis) {
        this.timeSinceLastSendMillis = timeSinceLastSendMillis;
    }

    public String getCommitOffsetStr() {
        return commitOffsetStr;
    }

    public void setCommitOffsetStr(String commitOffsetStr) {
        this.commitOffsetStr = commitOffsetStr;
    }

    public String getCurrentOffsetStr() {
        return currentOffsetStr;
    }

    public void setCurrentOffsetStr(String currentOffsetStr) {
        this.currentOffsetStr = currentOffsetStr;
    }

    public List<List<String>> getInSyncOffset() {
        return inSyncOffset;
    }

    public void setInSyncOffset(List<List<String>> inSyncOffset) {
        this.inSyncOffset = inSyncOffset;
    }

    public boolean isSyncing() {
        return syncing;
    }

    public void setSyncing(boolean syncing) {
        this.syncing = syncing;
    }

    public boolean isRecovering() {
        return recovering;
    }

    public void setRecovering(boolean recovering) {
        this.recovering = recovering;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getAvailableSendBulkNumber() {
        return availableSendBulkNumber;
    }

    public void setAvailableSendBulkNumber(int availableSendBulkNumber) {
        this.availableSendBulkNumber = availableSendBulkNumber;
    }

    public long getSuccessRecoverCount() {
        return successRecoverCount;
    }

    public void setSuccessRecoverCount(long successRecoverCount) {
        this.successRecoverCount = successRecoverCount;
    }

    public long getFailedRecoverCount() {
        return failedRecoverCount;
    }

    public void setFailedRecoverCount(long failedRecoverCount) {
        this.failedRecoverCount = failedRecoverCount;
    }

    public long getRecoverTotalTimeMillis() {
        return recoverTotalTimeMillis;
    }

    public void setRecoverTotalTimeMillis(long recoverTotalTimeMillis) {
        this.recoverTotalTimeMillis = recoverTotalTimeMillis;
    }

    public long getTimeSinceUpdateReplicaCheckPoint() {
        return timeSinceUpdateReplicaCheckPoint;
    }

    public void setTimeSinceUpdateReplicaCheckPoint(long timeSinceUpdateReplicaCheckPoint) {
        this.timeSinceUpdateReplicaCheckPoint = timeSinceUpdateReplicaCheckPoint;
    }
}
