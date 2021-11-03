package com.didiglobal.logi.job.core.job;

import lombok.Data;

import java.util.List;

/**
 * job context.
 *
 * @author ds
 */
@Data
public class JobContext {
    private String          params;
    private List<String>    allWorkerCodes;
    private String          currentWorkerCode;

    public JobContext(){}
    public JobContext(String params,  List<String> allWorkerCodes, String currentWorkerCode){
        this.params             = params;
        this.allWorkerCodes     = allWorkerCodes;
        this.currentWorkerCode  = currentWorkerCode;
    }
}
