package com.oauth.authentication.generator;

import com.oauth.authentication.algorithm.JWTSigner;
import com.oauth.authentication.config.JWTTokenConfig;
import com.oauth.constants.CommonConstant;
import com.oauth.data.AuthenticationToken;
import com.oauth.utils.RESTSecurityDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("jwtTokenGenerator")
public class JWTTokenGenerator implements TokenGenerator {

    @Autowired
    private JWTTokenConfig jwtTokenConfig;

    @Override
    public AuthenticationToken generate() {
        Date date = RESTSecurityDateTimeUtil.addHoursToDate(jwtTokenConfig.getExpirationTime());
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(CommonConstant.ISS, jwtTokenConfig.getIss());
        claims.put(CommonConstant.SUB, jwtTokenConfig.getSub());
        claims.put(CommonConstant.EXP, date.getTime());
        JWTSigner jwt = new JWTSigner(jwtTokenConfig.getSalt());
        String token = jwt.sign(claims);
        return new AuthenticationToken(token, date);
    }
}
