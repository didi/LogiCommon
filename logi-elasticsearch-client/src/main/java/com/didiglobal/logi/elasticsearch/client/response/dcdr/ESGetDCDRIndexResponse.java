package com.didiglobal.logi.elasticsearch.client.response.dcdr;

import com.alibaba.fastjson.annotation.JSONField;
import com.didiglobal.logi.elasticsearch.client.request.dcdr.DCDRIndex;
import com.didiglobal.logi.elasticsearch.client.response.ESAcknowledgedResponse;

import java.util.List;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetDCDRIndexResponse extends ESAcknowledgedResponse {

    @JSONField(name = "dcdrs")
    private List<DCDRIndex> dcdrs;

    public List<DCDRIndex> getDcdrs() {
        return dcdrs;
    }

    public void setDcdrs(List<DCDRIndex> dcdrs) {
        this.dcdrs = dcdrs;
    }
}
