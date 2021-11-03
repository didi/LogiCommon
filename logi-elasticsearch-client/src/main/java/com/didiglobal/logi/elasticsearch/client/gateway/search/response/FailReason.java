package com.didiglobal.logi.elasticsearch.client.gateway.search.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.didiglobal.logi.elasticsearch.client.utils.XContentParserUtils;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentParser;

import java.io.IOException;

public class FailReason implements ToXContent {
    private static final String TYPE = "type";
    private static final String REASON = "reason";

    @JSONField(name = "type")
    private String type;

    @JSONField(name = "reason")
    private String reason;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.field("type", type);
        builder.field("reason", reason);
        return builder;
    }

    public static FailReason fromXContent(XContentParser parser) throws IOException {
        XContentParser.Token token = parser.currentToken();
        XContentParserUtils.ensureExpectedToken(XContentParser.Token.START_OBJECT, token, parser::getTokenLocation);

        String type = null;
        String reason = null;

        String currentFieldName = null;
        while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
            if (token == XContentParser.Token.FIELD_NAME) {
                currentFieldName = parser.currentName();
            } else if (token.isValue()) {
                if (TYPE.equals(currentFieldName)) {
                    type = parser.text();
                } else if (REASON.equals(currentFieldName)) {
                    reason = parser.text();
                } else {
                    parser.skipChildren();
                }
            }
        }
        FailReason failReason = new FailReason();
        failReason.setReason(reason);
        failReason.setType(type);

        return failReason;
    }
}
