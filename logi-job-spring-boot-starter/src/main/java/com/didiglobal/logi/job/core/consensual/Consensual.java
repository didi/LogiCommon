package com.didiglobal.logi.job.core.consensual;

import com.didiglobal.logi.job.common.domain.LogITask;

/**
 * 任务执行策略，实现为全局而不是任务的主要考虑：每个任务配置成本高.
 *
 * @author ds
 */
public interface Consensual {

    String getName();

    /**
     * 节点能否执行任务.
     * @param logITask 任务
     * @return 是否能认领
     */
    boolean canClaim(LogITask logITask);
}
