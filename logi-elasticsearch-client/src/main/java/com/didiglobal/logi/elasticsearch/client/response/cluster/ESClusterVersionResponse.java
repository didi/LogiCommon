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

package com.didiglobal.logi.elasticsearch.client.response.cluster;

import com.alibaba.fastjson.annotation.JSONField;
import com.didiglobal.logi.elasticsearch.client.model.ESActionResponse;

public class ESClusterVersionResponse extends ESActionResponse {
    @JSONField(name = "cluster_name")
    private String clusterName;

    @JSONField(name = "version")
    private ESClusterVersion version;

    @JSONField(name = "tagline")
    private String tagline;

    public static class ESClusterVersion {
        @JSONField(name = "number")
        private String number;

        @JSONField(name = "build_hash")
        private String buildHash;

        @JSONField(name = "build_timestamp")
        private String buildTimestamp;

        @JSONField(name = "build_snapshot")
        private Boolean buildSnapshot;

        @JSONField(name = "lucene_version")
        private String luceneVersion;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getBuildHash() {
            return buildHash;
        }

        public void setBuildHash(String buildHash) {
            this.buildHash = buildHash;
        }

        public String getBuildTimestamp() {
            return buildTimestamp;
        }

        public void setBuildTimestamp(String buildTimestamp) {
            this.buildTimestamp = buildTimestamp;
        }

        public Boolean getBuildSnapshot() {
            return buildSnapshot;
        }

        public void setBuildSnapshot(Boolean buildSnapshot) {
            this.buildSnapshot = buildSnapshot;
        }

        public String getLuceneVersion() {
            return luceneVersion;
        }

        public void setLuceneVersion(String luceneVersion) {
            this.luceneVersion = luceneVersion;
        }
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public ESClusterVersion getVersion() {
        return version;
    }

    public void setVersion(ESClusterVersion version) {
        this.version = version;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
}
