package com.oauth.crypto;

import com.oauth.constants.SecurityConstants;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGeneratorFactory {

    static final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public PasswordEncoder apply(String algorithm) {
        if (algorithm.equalsIgnoreCase(SecurityConstants.BCRYPT)) {
            return bCryptPasswordEncoder;
        }
        return bCryptPasswordEncoder;
    }
}
