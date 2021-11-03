package com.didiglobal.logi.elasticsearch.client.request.security;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class SecurityRoleIndex {

    @JSONField(name = "names")
    private List<String> names;

    @JSONField(name = "privileges")
    private List<String> privileges;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }
}
