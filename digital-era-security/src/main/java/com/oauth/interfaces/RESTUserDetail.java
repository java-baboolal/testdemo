package com.oauth.interfaces;

import com.oauth.data.User;

/**
 * This interface is used for retrieving the user information.
 */
public interface RESTUserDetail {

    /**
     * Fetch user details by using username.
     *
     * @param username user name.
     * @return User object that contains the user's details.
     */
    public User fetchUser(String username);
}
