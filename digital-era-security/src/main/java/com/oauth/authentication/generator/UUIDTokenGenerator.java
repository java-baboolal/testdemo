package com.oauth.authentication.generator;

import com.oauth.authentication.config.JWTTokenConfig;
import com.oauth.authentication.config.UUIDTokenConfig;
import com.oauth.data.AuthenticationToken;
import com.oauth.utils.RESTSecurityDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component("uuidTokenGenerator")
public class UUIDTokenGenerator implements TokenGenerator {

    @Autowired
    private UUIDTokenConfig uuidTokenConfig;

    @Override
    public AuthenticationToken generate() {
        Date date = RESTSecurityDateTimeUtil.addHoursToDate(uuidTokenConfig.getExpirationTime());
        String token = UUID.randomUUID().toString();
        return new AuthenticationToken(token, date);
    }
}