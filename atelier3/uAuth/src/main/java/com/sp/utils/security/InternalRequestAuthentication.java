package com.sp.utils.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class InternalRequestAuthentication extends AbstractAuthenticationToken {

    public InternalRequestAuthentication() {
        super(Collections.singletonList(new SimpleGrantedAuthority("ROLE_INTERNAL")));
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return "internalRequest";
    }
}
