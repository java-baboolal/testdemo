package com.oauth.json;

public class AuthenticationDetail {

    private String username;
    private String accessToken;
    private Long expiresIn;

    public AuthenticationDetail() {
    }

    public AuthenticationDetail(String username, String accessToken, Long expiresIn) {
        this.accessToken = accessToken;
        this.username = username;
        this.expiresIn = expiresIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

}
