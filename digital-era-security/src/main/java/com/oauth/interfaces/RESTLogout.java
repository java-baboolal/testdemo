package com.oauth.interfaces;

/**
 * This interface is used to logout user.
 */
public interface RESTLogout {

    /**
     * Logout particular session using the authentication token.
     *
     * @param token authentication token.
     */
    public void session(String token);


    /**
     * Logout all sessions.
     *
     * @param username
     */
    public void user(String username);
}
