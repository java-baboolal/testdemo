package com.oauth.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenConfig {

    /**
     * The iss (issuer) claim identifies the principal that issued the JWT.
     * The processing of this claim is generally application specific.
     * The iss value is a case-sensitive string containing a StringOrURI value. Use of this claim is OPTIONAL.
     */
    @Value("${spring.security.rest.authentication.jwt.iss:springrest}")
    private String iss;

    /**
     * The sub (subject) claim identifies the principal that is the subject of the JWT.
     * The claims in a JWT are normally statements about the subject.
     * The subject value MUST either be scoped to be locally unique in the context of the issuer or be globally unique.
     * The processing of this claim is generally application specific.
     * The sub value is a case-sensitive string containing a StringOrURI value. Use of this claim is OPTIONAL.
     */
    @Value("${spring.security.rest.authentication.jwt.sub:springrest}")
    private String sub;

    /**
     * Salt is random data that is used as an additional input to a one-way function that "hashes" a password or passphrase.
     */
    @Value("${spring.security.rest.authentication.jwt.saltKey:springrest}")
    private String salt;

    /**
     * The exp (expiration time) claim identifies the expiration time on or after which the JWT MUST NOT be accepted for processing.
     * The processing of the exp claim requires that the current date/time MUST be before the expiration date/time listed in the exp claim.
     * Implementers MAY provide for some small leeway, usually no more than a few minutes, to account for clock skew.
     * Its value MUST be a number containing a NumericDate value. Use of this claim is OPTIONAL.
     */
    @Value("${spring.security.rest.authentication.jwt.exp:30}")
    private Integer exp;

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getExpirationTime() {
        return exp;
    }

    public void setExpirationTime(Integer expirationTime) {
        this.exp = expirationTime;
    }
}
