package com.oauth.data;

import java.util.List;

public class RoleUrlMapping {

    private String role;
    private List<String> urls;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "RoleUrlMapping{" +
                "role='" + role + '\'' +
                ", urls=" + urls +
                '}';
    }
}
