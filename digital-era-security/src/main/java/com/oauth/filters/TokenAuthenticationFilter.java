package com.oauth.filters;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.util.NestedServletException;

import com.oauth.config.RESTSecurityConfig;
import com.oauth.data.AuthenticationToken;
import com.oauth.data.RoleUrlMapping;
import com.oauth.data.User;
import com.oauth.interfaces.RESTAuthenticationToken;
import com.oauth.interfaces.RESTRoleUrlMapping;
import com.oauth.interfaces.RESTUserDetail;
import com.oauth.service.RESTSecurityUserDetails;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final static Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    @Autowired
    private RESTAuthenticationToken RESTAuthenticationToken;
    @Autowired
    private RESTUserDetail userDetailDAO;
    @Autowired
    private RESTRoleUrlMapping roleUrlMappingDAO;
    @Autowired
    private RESTSecurityConfig restSecurityConfig;

    private String token = null;

    public TokenAuthenticationFilter() {
        super("/");
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws AuthenticationException, IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
       this.token = getAccessTokenFromCookieOrHeader(request);
        
        log.info("Token successfully received inside token validator", token);
        if (grantAuthentication(req, res)) {
            log.info("This is a permit all authentication request");
        } else if (token != null) {
            Authentication authResult;
            try {
                authResult = attemptAuthentication(request, response);
                if (authResult == null) {
                    notAuthenticated(request, response, new LockedException("User Not found"));
                    return;
                }
            } catch (AuthenticationException failed) {
                notAuthenticated(request, response, failed);
                return;
            }
            try {
                successfulAuthentication(request, response, chain, authResult);
            } catch (NestedServletException e) {
                if (e.getCause() instanceof AccessDeniedException) {
                    unsuccessfulAuthentication(request, response, new LockedException("Forbidden"));
                }
            }
        } else {
            throw new AuthenticationServiceException(MessageFormat.format("Error | {0}", "Bad Token"));
        }
        chain.doFilter(request, response);
    }

    private boolean grantAuthentication(ServletRequest req, ServletResponse res) {
        if (!restSecurityConfig.getExcludeAuthenticationUrls().isEmpty()) {
            for (String url : restSecurityConfig.getExcludeAuthenticationUrls()) {
                if (((HttpServletRequest) req).getRequestURI().equalsIgnoreCase(url)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Attempt to authenticate request
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = getAccessTokenFromCookieOrHeader(request);
        log.info("Attempt authentication for token :", token);
        if (token == null) {
            throw new AuthenticationServiceException(MessageFormat.format("Error | {0}", "Bad Token"));
        }
        AbstractAuthenticationToken userAuthenticationToken = authUserByToken(response, request.getRequestURI(), token);
        if (userAuthenticationToken == null)
            throw new AuthenticationServiceException("Invalid Token");
        return userAuthenticationToken;
    }


    public void notAuthenticated(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        try {
            unsuccessfulAuthentication(request, response, new LockedException("Forbidden"));
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * authenticate the user based on token
     *
     * @return
     */
    private AbstractAuthenticationToken authUserByToken(HttpServletResponse response, String uri, String token) throws AuthenticationException {
        if (token == null) return null;
        AuthenticationToken authenticationToken = RESTAuthenticationToken.find(token);
        if (authenticationToken == null) {
            throw new AuthenticationServiceException(MessageFormat.format("Error | {0}", "Bad Token"));
        }
        User user = userDetailDAO.fetchUser(authenticationToken.getUsername());
        if (!isURIAuthenticate(user, uri)) {
            throw new AccessDeniedException(MessageFormat.format("Error | {0}", "Access denied"));
        }

        RESTSecurityUserDetails restSecurityUserDetails = new RESTSecurityUserDetails(Arrays.asList(new SimpleGrantedAuthority(user.getUserRole().getRole())), user.getUserMail(), user.getPassword(), true, authenticationToken);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(restSecurityUserDetails, user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getUserRole().getRole())));
        log.info("Authentication successfully for token :", token);
        return usernamePasswordAuthenticationToken;
    }

    private boolean isURIAuthenticate(User user, String uri) {
        List<RoleUrlMapping> roleUrlMappings = roleUrlMappingDAO.fetchRoleUrlMapping();
        try {
            if (!roleUrlMappings.isEmpty()) {
                for (RoleUrlMapping roleUrlMapping : roleUrlMappings) {
                    if (roleUrlMapping.getRole().equalsIgnoreCase(user.getUserRole().getRole())) {
                        return roleUrlMapping.getUrls().contains(uri);
                    }
                }
            }
        } catch (Exception e) {
            throw new AccessDeniedException(MessageFormat.format("Error | {0}", "Access denied"));
        }
        return false;
    }
    private String getAccessTokenFromCookieOrHeader(HttpServletRequest request) {
        String tokenValue = null;
        if(restSecurityConfig.getSupportCookie()){
            final Cookie[] cookies = request.getCookies();
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (restSecurityConfig.getTokenHeader().equals(cookie.getName())) {
                        tokenValue = cookie.getValue();
                        break;
                    }
                }
            }
        }else{
        	tokenValue = request.getHeader(restSecurityConfig.getTokenHeader());
        }
        return tokenValue;
    }
}
