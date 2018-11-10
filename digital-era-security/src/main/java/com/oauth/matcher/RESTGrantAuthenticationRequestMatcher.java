package com.oauth.matcher;

import com.oauth.config.RESTSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RESTGrantAuthenticationRequestMatcher implements RequestMatcher {

    @Autowired
    private RESTSecurityConfig restSecurityConfig;

    @Override
    public boolean matches(HttpServletRequest request) {
        if (!restSecurityConfig.getExcludeAuthenticationUrls().isEmpty()) {
            for (String url : restSecurityConfig.getExcludeAuthenticationUrls()) {
                if (request.getRequestURI().equalsIgnoreCase(url)) {
                    return true;
                }
            }

        }
        return false;
    }
}
