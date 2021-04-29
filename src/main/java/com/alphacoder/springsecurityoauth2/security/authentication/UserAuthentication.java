package com.alphacoder.springsecurityoauth2.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {
    public UserAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UserAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
