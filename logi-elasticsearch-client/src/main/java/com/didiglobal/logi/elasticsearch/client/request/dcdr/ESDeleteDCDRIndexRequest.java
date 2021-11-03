package com.didiglobal.logi.elasticsearch.client.request.dcdr;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.dcdr.ESDeleteDCDRIndexResponse;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionRequestValidationException;

import java.util.Map;

/**
 * author weizijun
 * date：2019-07-11
 */
public class ESDeleteDCDRIndexRequest extends ESActionRequest<ESDeleteDCDRIndexRequest> {

    private String primaryIndex;
    private String replicaIndex;
    private String replicaCluster;

    public void setPrimaryIndex(String primaryIndex) {
        this.primaryIndex = primaryIndex;
    }

    public void setReplicaIndex(String replicaIndex) {
        this.replicaIndex = replicaIndex;
    }

    public void setReplicaCluster(String replicaCluster) {
        this.replicaCluster = replicaCluster;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        if (StringUtils.isEmpty(primaryIndex)) {
            throw new Exception("primaryIndex is null");
        }

        if (StringUtils.isEmpty(replicaIndex)) {
            throw new Exception("replicaIndex is null");
        }

        if (StringUtils.isEmpty(replicaCluster)) {
            throw new Exception("replicaCluster is null");
        }

        Map<String, String> param = Maps.newHashMap();
        param.put("replica_index", replicaIndex);
        param.put("replica_cluster", replicaCluster);

        String endPoint = "/_dcdr/" + primaryIndex + "/replication/delete";
        RestRequest rr = new RestRequest("DELETE", endPoint, null);
        rr.setBody(JSON.toJSONString(param));
        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESDeleteDCDRIndexResponse.class);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
