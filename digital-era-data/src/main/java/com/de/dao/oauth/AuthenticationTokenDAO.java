package com.de.dao.oauth;


import com.oauth.data.AuthenticationToken;

public interface AuthenticationTokenDAO {

    AuthenticationToken findByAccessToken(String token);

    String generate(String username);
}
