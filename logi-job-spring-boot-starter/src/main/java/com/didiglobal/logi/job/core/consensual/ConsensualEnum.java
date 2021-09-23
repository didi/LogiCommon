package com.didiglobal.logi.job.core.consensual;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务执行策略，实现为全局而不是任务的主要考虑：每个任务配置成本高.
 *
 * @author ds
 */
public enum ConsensualEnum {
    RANDOM("随机抢占"),
    BROADCAST("广播");

    private static Map<String, ConsensualEnum> map = new HashMap<>(8);

    static {
        map.put(RANDOM.name(), RANDOM);
        map.put(BROADCAST.name(), BROADCAST);
    }

    private String desc;

    ConsensualEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public static ConsensualEnum getByName(String name) {
        return map.get(name);
    }
}
