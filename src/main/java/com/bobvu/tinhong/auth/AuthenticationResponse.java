package com.bobvu.tinhong.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
public class AuthenticationResponse {
    @Getter
    private final String jwt;
    @Getter
    private final String username;
    @Getter
    private final Collection<? extends GrantedAuthority> authorities;
}
