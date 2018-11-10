package com.oauth.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.oauth.data.AuthenticationToken;

public class RESTSecurityUserDetails implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    private Boolean isEnabled;
    private AuthenticationToken accessToken;

    public RESTSecurityUserDetails(List<? extends GrantedAuthority> authorities, String username, String password, Boolean isEnabled, AuthenticationToken accessToken) {
        this.authorities = authorities;
        this.password = password;
        this.username = username;
        this.isEnabled = isEnabled;
        this.accessToken = accessToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public AuthenticationToken getAccessToken() {
        return accessToken;
    }
}
