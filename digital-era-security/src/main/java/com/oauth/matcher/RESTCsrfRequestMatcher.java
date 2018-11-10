package com.oauth.matcher;

import com.oauth.config.RESTSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Component
public class RESTCsrfRequestMatcher implements RequestMatcher {

    @Autowired
    private RESTSecurityConfig restSecurityConfig;

    private Pattern allowedMethods = Pattern.compile("^(GET|POST|HEAD|TRACE|OPTIONS)$");

    @Override
    public boolean matches(HttpServletRequest request) {
        if (!restSecurityConfig.getExcludeCSRFUrl().isEmpty()) {
            for (String s : restSecurityConfig.getExcludeCSRFUrl()) {
                if (request.getRequestURI().equalsIgnoreCase(s)) {
                    return false;
                }
            }
        }
        return !request.getRequestURI().equalsIgnoreCase(restSecurityConfig.getLoginUrl());
    }
}
