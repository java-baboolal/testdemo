package com.oauth.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Strategy that is called after a successful logout by the {@link LogoutFilter}, to handle redirection or
 * forwarding to the appropriate destination.
 */
@Component
public class RESTLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.getWriter().flush();
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication closed by user");
    	response.setStatus(HttpServletResponse.SC_OK);
    	response.getWriter().flush();
    }
}
