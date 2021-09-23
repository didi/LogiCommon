package com.didiglobal.logi.job.common.domain;

import com.didiglobal.logi.job.common.po.LogIWorkerPO;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LogIWorker {

    private String workerCode;
    private String workerName;

    private String ip;

    private Integer cpu;

    private Double cpuUsed;

    /*
     * 以M为单位
     */
    private Double memory;

    private Double memoryUsed;

    /*
     * 以M为单位
     */
    private Double jvmMemory;

    private Double jvmMemoryUsed;

    private Integer jobNum;

    private Timestamp heartbeat;

    private String appName;

    /**
     * get auv worker.
     *
     * @return auv worker
     */
    public LogIWorkerPO getWorker() {
        LogIWorkerPO logIWorkerPO = new LogIWorkerPO();
        logIWorkerPO.setWorkerCode(this.workerCode);
        logIWorkerPO.setWorkerName(this.workerName);
        logIWorkerPO.setIp(this.getIp());
        logIWorkerPO.setCpu(this.cpu);
        logIWorkerPO.setCpuUsed(this.cpuUsed);
        logIWorkerPO.setMemory(this.memory);
        logIWorkerPO.setMemoryUsed(this.memoryUsed);
        logIWorkerPO.setJvmMemory(this.jvmMemory);
        logIWorkerPO.setJvmMemoryUsed(this.jvmMemoryUsed);
        logIWorkerPO.setJobNum(this.jobNum);
        logIWorkerPO.setHeartbeat(new Timestamp(System.currentTimeMillis()));
        logIWorkerPO.setAppName(this.appName);
        return logIWorkerPO;
    }
}