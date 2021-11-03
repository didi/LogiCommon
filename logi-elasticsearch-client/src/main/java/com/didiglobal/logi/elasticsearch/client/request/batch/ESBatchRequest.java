/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.didiglobal.logi.elasticsearch.client.request.batch;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.elasticsearch.client.model.ESActionRequest;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;
import com.didiglobal.logi.elasticsearch.client.model.RestRequest;
import com.didiglobal.logi.elasticsearch.client.model.RestResponse;
import com.didiglobal.logi.elasticsearch.client.response.batch.ESBatchResponse;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionRequestValidationException;

import java.util.ArrayList;
import java.util.List;

public class ESBatchRequest extends ESActionRequest<ESBatchRequest> {

    private static final int REQUEST_OVERHEAD = 50;

    private List<BatchNode> batchNodes = new ArrayList<>();

    private long sizeInBytes = 0;

    public ESBatchRequest() {

    }

    public ESBatchRequest(ESActionRequest request) {
        super(request);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }

    public void addNode(BatchNode batchNode) {
        batchNodes.add(batchNode);
        sizeInBytes += (batchNode.getContent() != null ? batchNode.getContent().length() : 0) + REQUEST_OVERHEAD;
    }


    public void addNode(BatchType batchType, String index, String type, String content) {
        addNode(batchType, index, type, null, content);
    }

    public void addNode(BatchType batchType, String index, String type, String id, String content) {
        addNode(new BatchNode(batchType, index, type, id, content));
    }

    /**
     * The number of actions in the bulk request.
     * @return int
     */
    public int numberOfActions() {
        return batchNodes.size();
    }

    /**
     * The estimated size in bytes of the bulk request.
     * @return long
     */
    public long estimatedSizeInBytes() {
        return sizeInBytes;
    }

    /**
     * The list of requests in this bulk request.
     * @return List
     */
    public List<BatchNode> requests() {
        return this.batchNodes;
    }

    @Override
    public RestRequest toRequest() throws Exception {
        String endpoint = "/_bulk";
        StringBuilder sb = new StringBuilder();

        for (BatchNode node : batchNodes) {
            sb.append(node.toMessage());
        }

        RestRequest rr = new RestRequest("POST", endpoint, null);
        rr.setBody(sb.toString());

        return rr;
    }

    @Override
    public ESActionResponse toResponse(RestResponse response) throws Exception {
        String respStr = response.getResponseContent();
        return JSON.parseObject(respStr, ESBatchResponse.class);
    }
}
