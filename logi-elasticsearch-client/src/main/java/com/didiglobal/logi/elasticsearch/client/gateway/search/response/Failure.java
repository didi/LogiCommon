package com.didiglobal.logi.elasticsearch.client.gateway.search.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.didiglobal.logi.elasticsearch.client.utils.XContentParserUtils;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;

public class Failure implements ToXContent {
    private static final String _INDEX = "_index";
    private static final String _SHARD = "_shard";
    private static final String _NODE = "_node";
    private static final String REASON = "reason";
    private static final String STATUS = "status";
    private static final String PRIMARY = "primary";

    @JSONField(name = "shard")
    private int shard;

    @JSONField(name = "index")
    private String index;

    @JSONField(name = "node")
    private String node;

    @JSONField(name = "reason")
    private FailReason reason;

    private boolean primary;

    private RestStatus status;

    public int getShard() {
        return shard;
    }

    public void setShard(int shard) {
        this.shard = shard;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public FailReason getReason() {
        return reason;
    }

    public void setReason(FailReason reason) {
        this.reason = reason;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public RestStatus getStatus() {
        return status;
    }

    public void setStatus(RestStatus status) {
        this.status = status;
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.field("shard", shard);
        builder.field("index", index);
        if (node != null) {
            builder.field("node", node);
        }

        if (reason != null) {
            builder.field("reason");
            builder.startObject();
            reason.toXContent(builder, params);
            builder.endObject();
        }
        return builder;
    }

    public static Failure fromXContent(XContentParser parser) throws IOException {
        XContentParser.Token token = parser.currentToken();
        XContentParserUtils.ensureExpectedToken(XContentParser.Token.START_OBJECT, token, parser::getTokenLocation);

        String shardIndex = null, nodeId = null;
        int shardId = -1;
        boolean primary = false;
        RestStatus status = null;
        FailReason reason = null;

        String currentFieldName = null;
        while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
            if (token == XContentParser.Token.FIELD_NAME) {
                currentFieldName = parser.currentName();
            } else if (token.isValue()) {
                if (_INDEX.equals(currentFieldName)) {
                    shardIndex = parser.text();
                } else if (_SHARD.equals(currentFieldName)) {
                    shardId = parser.intValue();
                } else if (_NODE.equals(currentFieldName)) {
                    nodeId = parser.text();
                } else if (STATUS.equals(currentFieldName)) {
                    status = RestStatus.valueOf(parser.text());
                } else if (PRIMARY.equals(currentFieldName)) {
                    primary = parser.booleanValue();
                }
            } else if (token == XContentParser.Token.START_OBJECT) {
                if (REASON.equals(currentFieldName)) {
                    reason = FailReason.fromXContent(parser);
                } else {
                    parser.skipChildren(); // skip potential inner objects for forward compatibility
                }
            } else if (token == XContentParser.Token.START_ARRAY) {
                parser.skipChildren(); // skip potential inner arrays for forward compatibility
            }
        }
        Failure failure = new Failure();
        failure.setShard(shardId);
        failure.setIndex(shardIndex);
        failure.setNode(nodeId);
        failure.setReason(reason);
        failure.setStatus(status);
        failure.setPrimary(primary);

        return failure;
    }


}
