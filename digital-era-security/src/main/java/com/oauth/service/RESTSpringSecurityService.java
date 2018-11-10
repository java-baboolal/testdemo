package com.oauth.service;

import com.oauth.authentication.config.TokenGeneratorConfig;
import com.oauth.authentication.generator.TokenGenerator;
import com.oauth.authentication.generator.TokenGeneratorFactory;
import com.oauth.crypto.CryptoConfig;
import com.oauth.crypto.PasswordGeneratorFactory;
import com.oauth.data.AuthenticationToken;
import com.oauth.security.NoOpAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p/>
 * Custom REST service which includes all the essentials methods.
 * <p/>
 *
 * @author Jeevesh Pandey
 */
@Component
public class RESTSpringSecurityService {

    @Autowired
    private TokenGeneratorConfig tokenGeneratorConfig;
    @Autowired
    private TokenGeneratorFactory tokenGeneratorFactory;
    @Autowired
    private PasswordGeneratorFactory passwordGeneratorFactory;
    @Autowired
    private CryptoConfig cryptoConfig;
    @Autowired
    private RESTSecurityUserDetailsService restSecurityUserDetailsService;
    @Autowired
    private NoOpAuthenticationManager noOpAuthenticationManager;

    /**
     * This method is used to save token with the pre defined strategies for token generation.
     * For eg: JWT, UUID
     *
     * @return AuthenticationToken with all the token information.
     */
    public AuthenticationToken generateToken() {
        return tokenGeneratorFactory.generate(tokenGeneratorConfig.getAuthentication());
    }

    /**
     * This method is used to save token with the user defined strategies.
     *
     * @return AuthenticationToken with all the token information.
     */
    public AuthenticationToken generateToken(TokenGenerator tokenGenerator) {
        return tokenGenerator.generate();
    }


    /**
     * This method is used to encrypt the password.
     *
     * @param password String raw password
     * @return encrypted password
     */
    public String encodePassword(String password) {
        PasswordEncoder passwordEncoder = passwordGeneratorFactory.apply(cryptoConfig.getAlgorithm());
        return passwordEncoder.encode(password);
    }

    /**
     * This method is used to authenticate username and password.
     *
     * @param username username
     * @param password password
     * @return RESTSecurityUserDetails object which contains user details.
     */
    public RESTSecurityUserDetails authenticate(String username, String password) {
        RESTSecurityUserDetails restSecurityUserDetails = (RESTSecurityUserDetails) restSecurityUserDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(restSecurityUserDetails, password, restSecurityUserDetails.getAuthorities());
        noOpAuthenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        return restSecurityUserDetails;
    }
}