package com.didiglobal.logi.elasticsearch.client.request.security;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class SecurityUser {

    @JSONField(name = "password")
    private String password;

    @JSONField(name = "roles")
    private List<String> roles;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
