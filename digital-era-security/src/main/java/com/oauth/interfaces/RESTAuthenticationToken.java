package com.oauth.interfaces;

import com.oauth.data.AuthenticationToken;

/**
 * This interface is uses for authentication token implementation.
 */
public interface RESTAuthenticationToken {

    /**
     * This method is used to validate the authentication token.
     *
     * @param token String token
     * @return AuthenticationToken with token information.
     */
    AuthenticationToken find(String token);


    /**
     * This method is used to save the authentication token for particular user.
     *
     * @param username String token
     */
    void save(String username, AuthenticationToken authenticationToken);
}
