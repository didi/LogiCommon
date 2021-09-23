package com.didiglobal.logi.job.core;

import com.didiglobal.logi.job.common.domain.LogIWorker;
import com.didiglobal.logi.job.utils.ThreadUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

public class WorkerSingleton {
    private static final int CPU_INTERVAL = 1;
    private static final Logger logger = LoggerFactory.getLogger(WorkerSingleton.class);
    private volatile LogIWorker logIWorker;

    private WorkerSingleton() {
    }

    /**
     * 获取单例对象方法 dcl模式.
     *
     * @return 单例运行器
     */
    public static WorkerSingleton getInstance() {
        return Singleton.singleton;
    }

    /**
     * 更新运行器机器指标，如cpu/memory/jvmMemory.
     *
     * @return 更新后的单例机器
     */
    public WorkerSingleton updateInstanceMetrics() {
        Singleton.updateWorkerMetrics();
        return Singleton.singleton;
    }

    public LogIWorker getLogIWorker() {
        return logIWorker;
    }

    public void setLogIWorker(LogIWorker logIWorker) {
        this.logIWorker = logIWorker;
    }

    private static class Singleton {
        static WorkerSingleton singleton = new WorkerSingleton();

        static {
            LogIWorker logIWorker = new LogIWorker();
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                logger.error("class=SimpleWorkerFactory||method=||url=||msg=", e);
            }

            logIWorker.setWorkerCode(inetAddress == null ? "INVALID_CODE"
                    : inetAddress.getHostAddress() + "_" + inetAddress.getHostName());
            logIWorker.setWorkerName(inetAddress == null ? "INVALID_NAME" : inetAddress.getHostName());
            logIWorker.setIp(inetAddress.getHostAddress());
            singleton.setLogIWorker(logIWorker);
        }

        public static WorkerSingleton updateWorkerMetrics() {
            LogIWorker logIWorker = singleton.getLogIWorker();

            SystemInfo systemInfo = new SystemInfo();
            // cpu
            CentralProcessor processor = systemInfo.getHardware().getProcessor();
            // 时间间隔后，根据对比计算cpu使用率
            long[] prevTicks = processor.getSystemCpuLoadTicks();
            ThreadUtil.sleep(CPU_INTERVAL, TimeUnit.SECONDS);
            long[] ticks = processor.getSystemCpuLoadTicks();

            long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
                    - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
            long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
                    - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
            long softIrq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
                    - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
            // long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
            //     - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
            long steal = 0;
            long csys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
                    - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
            long user = ticks[CentralProcessor.TickType.USER.getIndex()]
                    - prevTicks[CentralProcessor.TickType.USER.getIndex()];
            long ioWait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
                    - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
            long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
                    - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
            long totalCpu = user + nice + csys + idle + ioWait + irq + softIrq + steal;
            logIWorker.setCpu(processor.getLogicalProcessorCount());
            logIWorker.setCpuUsed(totalCpu == 0 ? null : 1.0 - (idle * 1.0 / totalCpu));

            // memory
            GlobalMemory memory = systemInfo.getHardware().getMemory();
            Double totalMemory = memory.getTotal() * 1.0 / 1024 / 1024;
            DecimalFormat df = new DecimalFormat("#.000");
            logIWorker.setMemory(Double.valueOf(df.format(totalMemory)));
            Double memoryUsed = (memory.getTotal() - memory.getAvailable()) * 1.0 / memory.getTotal();
            logIWorker.setMemoryUsed(Double.valueOf(df.format(memoryUsed)));

            Runtime runtime = Runtime.getRuntime();
            Double jvmMemory = runtime.totalMemory() * 1.0 / 1024 / 1024;
            logIWorker.setJvmMemory(Double.valueOf(df.format(jvmMemory)));
            Double jvmMemoryUsed = (runtime.totalMemory() - runtime.freeMemory()) * 1.0
                    / runtime.totalMemory();
            logIWorker.setJvmMemoryUsed(Double.valueOf(df.format(jvmMemoryUsed)));

            // logIWorker.setJobNum();

            logIWorker.setHeartbeat(new Timestamp(System.currentTimeMillis()));
            singleton.setLogIWorker(logIWorker);
            return singleton;
        }
    }
}