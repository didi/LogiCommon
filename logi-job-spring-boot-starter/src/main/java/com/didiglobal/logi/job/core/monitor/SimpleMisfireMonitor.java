package com.didiglobal.logi.job.core.monitor;

import org.springframework.stereotype.Service;

/**
 * simple misfire monitor.
 *
 * @author ds
 */
@Service
public class SimpleMisfireMonitor implements MisfireMonitor {

    @Override
    public void maintain() {
        // todo
    }

    @Override
    public void stop() {
        // todo
    }

    class MisfireMonitorThread implements Runnable {

        @Override
        public void run() {
            // todo
        }
    }
}
