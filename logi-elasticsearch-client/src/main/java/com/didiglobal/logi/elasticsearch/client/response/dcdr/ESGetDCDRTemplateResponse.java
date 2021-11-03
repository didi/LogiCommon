package com.didiglobal.logi.elasticsearch.client.response.dcdr;

import com.alibaba.fastjson.annotation.JSONField;
import com.didiglobal.logi.elasticsearch.client.request.dcdr.DCDRTemplate;
import com.didiglobal.logi.elasticsearch.client.response.ESAcknowledgedResponse;

import java.util.List;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESGetDCDRTemplateResponse extends ESAcknowledgedResponse {

    @JSONField(name = "dcdrs")
    private List<DCDRTemplate> dcdrs;

    public List<DCDRTemplate> getDcdrs() {
        return dcdrs;
    }

    public void setDcdrs(List<DCDRTemplate> dcdrs) {
        this.dcdrs = dcdrs;
    }
}
