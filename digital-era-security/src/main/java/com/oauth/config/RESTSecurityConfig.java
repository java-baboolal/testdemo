package com.oauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RESTSecurityConfig {
    /**
     * Set this property to enable the csrf protection.
     */
    @Value("${spring.security.rest.csrf.enable:false}")
    private boolean isCsrfInable;

    @Value("${spring.security.rest.username.key:username}")
    private String username;

    @Value("${spring.security.rest.password.key:password}")
    private String password;

    @Value("${spring.security.rest.fully.authenticated.role:PERMIT_ALL}")
    private String permitAll;

    @Value("${spring.security.rest.auth.token.key:X-Auth-Token}")
    private String tokenHeader;
    
    @Value("${spring.security.rest.auth.token.supportCookie:true}")
    private boolean supportCookie;

    @Value("${spring.security.rest.auth.token.cookieSecure:false}")
    private boolean cookieSecure;
    
    @Value("${spring.security.rest.auth.token.cookieHttpOnly:true}")
    private boolean cookieHttpOnly;
    
    @Value("${spring.security.rest.login.url:/login}")
    private String loginUrl;

    @Value("${spring.security.rest.logout.url:/logout}")
    private String logoutUrl;

    @Value("${spring.security.rest.logout.session:false}")
    private boolean logoutFromSession;

    @Value("#{'${spring.security.rest.csrf.exclude.urls:}'.split(',')}")
    private List<String> excludeCSRFUrl;

    @Value("#{'${spring.security.rest.authentication.exclude.urls:}'.split(',')}")
    private List<String> excludeAuthenticationUrls;

    public boolean isCsrfInable() {
        return isCsrfInable;
    }

    public void setIsCsrfInable(boolean isCsrfInable) {
        this.isCsrfInable = isCsrfInable;
    }

    public String getPermitAll() {
        return permitAll;
    }

    public void setPermitAll(String permitAll) {
        this.permitAll = permitAll;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public boolean getSupportCookie() {
		return supportCookie;
	}

	public void setSupportCookie(boolean supportCookie) {
		this.supportCookie = supportCookie;
	}

	public boolean isCookieSecure() {
		return cookieSecure;
	}

	public void setCookieSecure(boolean cookieSecure) {
		this.cookieSecure = cookieSecure;
	}

	public boolean isCookieHttpOnly() {
		return cookieHttpOnly;
	}

	public void setCookieHttpOnly(boolean cookieHttpOnly) {
		this.cookieHttpOnly = cookieHttpOnly;
	}

	public String getLoginUrl() {
        return loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public boolean isLogoutFromSession() {
        return logoutFromSession;
    }

    public void setLogoutFromSession(boolean logoutFromSession) {
        this.logoutFromSession = logoutFromSession;
    }

    public List<String> getExcludeCSRFUrl() {
        return excludeCSRFUrl;
    }

    public void setExcludeCSRFUrl(List<String> excludeCSRFUrl) {
        this.excludeCSRFUrl = excludeCSRFUrl;
    }

    public List<String> getExcludeAuthenticationUrls() {
        return excludeAuthenticationUrls;
    }

    public void setExcludeAuthenticationUrls(List<String> excludeAuthenticationUrls) {
        this.excludeAuthenticationUrls = excludeAuthenticationUrls;
    }
}
