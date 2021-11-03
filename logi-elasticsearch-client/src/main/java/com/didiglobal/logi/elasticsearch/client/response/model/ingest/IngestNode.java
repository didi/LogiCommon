package com.didiglobal.logi.elasticsearch.client.response.model.ingest;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;

/**
 * author weizijun
 * date：2019-11-01
 */
public class IngestNode {
    @JSONField(name = "total")
    private IngestProcessorStats total;

    @JSONField(name = "pipelines")
    private Map<String, PipelineStats> pipelines;

    public IngestProcessorStats getTotal() {
        return total;
    }

    public void setTotal(IngestProcessorStats total) {
        this.total = total;
    }

    public Map<String, PipelineStats> getPipelines() {
        return pipelines;
    }

    public void setPipelines(Map<String, PipelineStats> pipelines) {
        this.pipelines = pipelines;
    }
}
