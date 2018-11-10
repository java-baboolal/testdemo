package com.de.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {

	/** Token type. For now supported: Oauth. */
    private final String tokenType = "Oauth";

    /** Access token. */
    @JsonProperty("access_token")
    private String accessToken;

    /** Value in milliseconds. */
    @JsonProperty("expires_in")
    private Long expiresIn;

    
    public AccessToken() {
    }

    public AccessToken(String accessToken, Long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getTokenType() {
		return tokenType;
	}
}
