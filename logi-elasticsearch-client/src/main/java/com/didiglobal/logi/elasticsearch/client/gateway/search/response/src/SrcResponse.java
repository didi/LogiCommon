package com.didiglobal.logi.elasticsearch.client.gateway.search.response.src;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;

import java.lang.reflect.Type;

public class SrcResponse<T> {
    /**
     * 索引名称
     */
    @JSONField(name = "_index")
    private String index;
    /**
     * type名称
     */
    @JSONField(name = "_type")
    private String type;
    /**
     * 主键id
     */
    @JSONField(name = "_id")
    private String id;
    /**
     * 版本
     */
    @JSONField(name = "_version")
    private Long version;
    /**
     * 是否找到
     */
    private boolean found;
    /**
     * source
     */
    @JSONField(name = "_source")
    private T source;

    public SrcResponse() {
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    public static SrcResponse parserResponse(String str, Class clazz) {
        ParameterizedTypeImpl parameterizedType = new ParameterizedTypeImpl(new Type[]{clazz}, null, SrcResponse.class);
        return JSON.parseObject(str, parameterizedType);
    }
}
