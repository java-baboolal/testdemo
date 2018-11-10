package com.oauth.handler;

import com.oauth.config.RESTSecurityConfig;
import com.oauth.interfaces.RESTLogout;
import com.oauth.service.RESTSecurityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RESTLogoutCustomHandler implements LogoutHandler {
    @Autowired
    private RESTLogout logoutDAO;
    @Autowired
    private RESTSecurityConfig restSecurityConfig;

    //TODO:Needs to refactor when direct call logout.
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    	try{
	        RESTSecurityUserDetails principal = (RESTSecurityUserDetails) authentication.getPrincipal();
	        if (restSecurityConfig.isLogoutFromSession()) {
	            logoutDAO.session(principal.getAccessToken().getToken());
	        } else {
	            logoutDAO.user(principal.getUsername());
	        }
    	}catch(Exception e){
    		System.out.println("log out exception");
    	}
    }
}
