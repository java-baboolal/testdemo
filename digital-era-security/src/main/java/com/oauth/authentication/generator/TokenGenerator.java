package com.oauth.authentication.generator;

import com.oauth.data.AuthenticationToken;

/**
 * This is used to save token strategy.
 */
public interface TokenGenerator {

    /**
     * This method is used to save the authentication token. Override this method if you want your own generation strategy.
     *
     * @return AuthenticationToken with token, exp time.
     */
    AuthenticationToken generate();
}
