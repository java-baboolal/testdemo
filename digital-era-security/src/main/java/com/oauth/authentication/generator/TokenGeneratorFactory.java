package com.oauth.authentication.generator;

import com.oauth.constants.TokenGeneratorConstants;
import com.oauth.data.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This is a factory class which save and gives the authentication token on basis of algorithm used.
 */
@Component
public class TokenGeneratorFactory {

    @Autowired
    private JWTTokenGenerator jwtTokenGenerator;
    @Autowired
    private UUIDTokenGenerator uuidTokenGenerator;

    /**
     * This method gives the token on the basis of algorithm.
     *
     * @param algo It can be RSH, JWT, UUID etc.
     * @return AuthenticationToken object with token details.
     */
    public AuthenticationToken generate(String algo) {
        if (algo == null) {
            return uuidTokenGenerator.generate();
        }
        if (algo.equalsIgnoreCase(TokenGeneratorConstants.JWT_TOKEN_ALGORITHM)) {
            return jwtTokenGenerator.generate();
        } else if (algo.equalsIgnoreCase(TokenGeneratorConstants.UUID_TOKEN_ALGORITHM)) {
            return uuidTokenGenerator.generate();
        } else {
            return uuidTokenGenerator.generate();
        }
    }
}
