package com.didiglobal.logi.elasticsearch.client.gateway.search.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.didiglobal.logi.elasticsearch.client.utils.XContentParserUtils;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Shards implements ToXContent {
    static final class Fields {
        public static final String _SHARDS = "_shards";
        public static final String TOTAL = "total";
        public static final String SUCCESSFUL = "successful";
        public static final String FAILED = "failed";
        public static final String FAILURES = "failures";
    }

    /**
     * 查询总shard总个数
     */
    @JSONField(name = "total")
    private Integer totalShard;
    /**
     * 成功的shard个数
     */
    @JSONField(name = "successful")
    private Integer successfulShard;
    /**
     * 失败的shard个数
     */
    @JSONField(name = "failed")
    private Integer failedShard;

    @JSONField(name = "failures")
    private List<Failure> failures;

    public Integer getTotalShard() {
        return totalShard;
    }

    public void setTotalShard(Integer totalShard) {
        this.totalShard = totalShard;
    }

    public Integer getSuccessfulShard() {
        return successfulShard;
    }

    public void setSuccessfulShard(Integer successfulShard) {
        this.successfulShard = successfulShard;
    }

    public Integer getFailedShard() {
        return failedShard;
    }

    public void setFailedShard(Integer failedShard) {
        this.failedShard = failedShard;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public List<Failure> getFailures() {
        return failures;
    }

    public void setFailures(List<Failure> failures) {
        this.failures = failures;
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject(Fields._SHARDS);
        builder.field(Fields.TOTAL, totalShard);
        builder.field(Fields.SUCCESSFUL, successfulShard);
        builder.field(Fields.FAILED, failedShard);
        if (failures != null && failures.size() > 0) {
            builder.startArray(Fields.FAILURES);
            // TODO default result is true, no need to do
            //final boolean group = params.paramAsBoolean("group_shard_failures", true); // we group by default
            for (Failure failure : failures) {
                builder.startObject();
                failure.toXContent(builder, params);
                builder.endObject();
            }
            builder.endArray();
        }
        builder.endObject();

        return builder;
    }

    public static Shards fromXContent(XContentParser parser) throws IOException {
        XContentParser.Token token = parser.currentToken();
        XContentParserUtils.ensureExpectedToken(XContentParser.Token.START_OBJECT, token, parser::getTokenLocation);

        int total = 0, successful = 0, failed = 0;
        List<Failure> failuresList = null;
        String currentFieldName = null;
        while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
            if (token == XContentParser.Token.FIELD_NAME) {
                currentFieldName = parser.currentName();
            } else if (token.isValue()) {
                if (Fields.TOTAL.equals(currentFieldName)) {
                    total = parser.intValue();
                } else if (Fields.SUCCESSFUL.equals(currentFieldName)) {
                    successful = parser.intValue();
                } else if (Fields.FAILED.equals(currentFieldName)) {
                    failed = parser.intValue();
                } else {
                    parser.skipChildren();
                }
            } else if (token == XContentParser.Token.START_ARRAY) {
                if (Fields.FAILURES.equals(currentFieldName)) {
                    failuresList = new ArrayList<>();
                    while ((token = parser.nextToken()) != XContentParser.Token.END_ARRAY) {
                        failuresList.add(Failure.fromXContent(parser));
                    }
                } else {
                    parser.skipChildren(); // skip potential inner arrays for forward compatibility
                }
            } else if (token == XContentParser.Token.START_OBJECT) {
                parser.skipChildren(); // skip potential inner arrays for forward compatibility
            }
        }

        Shards shards = new Shards();
        shards.setTotalShard(total);
        shards.setSuccessfulShard(successful);
        shards.setFailedShard(failed);
        shards.setFailures(failuresList);

        return shards;
    }
}
