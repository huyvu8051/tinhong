package com.bobvu.tinhong.auth.google;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
    private final String jwt;
    private final String username;
    private String fullName;
    private String avatar;
    private String locale;
    private final Collection<? extends GrantedAuthority> authorities;

}