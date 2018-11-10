package com.de.dao.oauth;


import com.de.model.entity.UserProfile;
import com.oauth.data.AuthenticationToken;
import com.oauth.data.User;

/**
 * The Interface UserDetailDAO.
 */
public interface UserRoleDAO {

    /**
     * Gets the login.
     *
     * @param users the users
     * @return the login
     */
    public String getLogin(User users);

    /**
     * Update user session.
     *
     * @param session the session
     * @return the user session
     */
    public AuthenticationToken updateUserSession(AuthenticationToken session);

    /**
     * Fetch user.
     *
     * @param username the users
     * @return the users
     */
    public User fetchAuthUser(String username);

    /**
     * Validate token.
     *
     * @param header the header
     * @return the user session
     */
    public AuthenticationToken validateToken(String header);

    /**
     * Fetch user by user id.
     *
     * @param id the id
     * @return the users
     */
    public User fetchUserByUserId(Integer id);
    
    /**
     * Fetch UserProfile
     * @param username
     * @return
     */
    public UserProfile fetchUserProfile(String username);


}
