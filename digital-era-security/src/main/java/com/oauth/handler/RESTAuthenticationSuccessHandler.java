package com.oauth.handler;

import com.google.gson.Gson;
import com.oauth.config.RESTSecurityConfig;
import com.oauth.constants.SecurityConstants;
import com.oauth.interfaces.RESTAuthenticationToken;
import com.oauth.json.AuthenticationDetail;
import com.oauth.service.RESTSecurityUserDetails;
import com.oauth.utils.MatchUtil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RESTAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final static Logger log = LoggerFactory.getLogger(RESTAuthenticationSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Autowired
    private RESTSecurityConfig restSecurityConfig;

    @Autowired
    private RESTAuthenticationToken restAuthenticationToken;
    
    public RESTAuthenticationSuccessHandler() {
        super("/success");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("Inside REST Authentication SuccessHandler.");
        response.setStatus(HttpServletResponse.SC_OK);
        if (MatchUtil.checkContentType(request.getHeader(SecurityConstants.CONTENT_TYPE))) {
        	
        	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken)authentication;
        	if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        	
            RESTSecurityUserDetails principal = (RESTSecurityUserDetails) authentication.getPrincipal();
            AuthenticationDetail authenticationDetail = new AuthenticationDetail(principal.getUsername(), principal.getAccessToken().getToken(), principal.getAccessToken().getExpiryDateTime().getTime());
            
            // create session
            restAuthenticationToken.save(principal.getUsername(), principal.getAccessToken());
            
            populateAccessTokenCookie(true,response,authenticationDetail);
            String json = new Gson().toJson(authenticationDetail);
            response.setContentType(SecurityConstants.APPLICATION_JSON);
            response.setCharacterEncoding(SecurityConstants.UTF_ENCODING);
            response.getWriter().print(json);
            response.getWriter().flush();
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    public void populateAccessTokenCookie(boolean supportCookie, HttpServletResponse response, AuthenticationDetail body) {
	    // supportCookie ?
	    if (restSecurityConfig.getSupportCookie() && body != null && !StringUtils.isEmpty(body.getAccessToken())) {
	        CookieGenerator cookieGenerator = new CookieGenerator();
	        cookieGenerator.setCookieSecure(restSecurityConfig.isCookieSecure());
	        cookieGenerator.setCookieHttpOnly(restSecurityConfig.isCookieHttpOnly());

	        cookieGenerator.setCookieName(restSecurityConfig.getTokenHeader());
	        cookieGenerator.setCookiePath("/");
	        long millis = body.getExpiresIn() - System.currentTimeMillis();
	        cookieGenerator.setCookieMaxAge((int) TimeUnit.SECONDS.convert(millis, TimeUnit.MILLISECONDS));
	        cookieGenerator.addCookie(response, body.getAccessToken());
	    }
	}
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            log.info("Can't redirect");
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        String url = "";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<String>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if (isDba(roles)) {
            url = "/db";
        } else if (isAdmin(roles)) {
            url = "/api";
        } else if (isUser(roles)) {
            url = "/home";
        } else {
            url = "/accessDenied";
        }

        return url;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("ROLE_USER")) {
            return true;
        }
        return false;
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }

    private boolean isDba(List<String> roles) {
        if (roles.contains("ROLE_DBA")) {
            return true;
        }
        return false;
    }


}