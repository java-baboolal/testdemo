package com.oauth.data;

import java.io.Serializable;
import java.util.Date;

public class AuthenticationToken implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer token_id;
    private String token;
    private Date expiryDateTime;
    private String username;

    public AuthenticationToken() {
    }

    public AuthenticationToken(String token, Date expiryDateTime) {
        this.token = token;
        this.expiryDateTime = expiryDateTime;
    }

    public AuthenticationToken(String token) {
        this.token = token;
    }

    public AuthenticationToken(Integer token_id, String token, Date expiryDateTime, String username) {
        this.token_id = token_id;
        this.token = token;
        this.expiryDateTime = expiryDateTime;
        this.username = username;
    }

    public Integer getToken_id() {
        return token_id;
    }

    public void setToken_id(Integer token_id) {
        this.token_id = token_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(Date expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "AuthenticationToken{" +
                "token='" + token + '\'' +
                ", username=" + username +
                '}';
    }
}
