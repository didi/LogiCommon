package com.didiglobal.logi.dsl.parse.dsl.ast.common.value;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.didiglobal.logi.dsl.parse.dsl.visitor.basic.Visitor;

public class JsonNode extends ValueNode {
    public JSON json;

    public JsonNode(Object obj) {
        this.json = (JSON) obj;
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(json, SerializerFeature.WriteMapNullValue);
    }
}
