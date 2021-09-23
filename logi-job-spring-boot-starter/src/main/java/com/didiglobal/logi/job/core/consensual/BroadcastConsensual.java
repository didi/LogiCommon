package com.didiglobal.logi.job.core.consensual;

import com.didiglobal.logi.job.common.domain.LogITask;
import org.springframework.stereotype.Service;

/**
 * 随机算法.
 *
 * @author ds
 */
@Service
public class BroadcastConsensual extends AbstractConsensual {

    @Override
    public String getName() {
        return ConsensualEnum.BROADCAST.name();
    }

    @Override
    public boolean tryClaim(LogITask logITask) {
        return true;
    }
}
