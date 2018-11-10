package com.oauth.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UUIDTokenConfig {

    /**
     * This is the expiry date time of the uuid token generation.
     */
    @Value("${spring.security.rest.authentication.uuid.exp:10}")
    private Integer exp;

    public Integer getExpirationTime() {
        return exp;
    }

    public void setExpirationTime(Integer expirationTime) {
        this.exp = expirationTime;
    }
}
