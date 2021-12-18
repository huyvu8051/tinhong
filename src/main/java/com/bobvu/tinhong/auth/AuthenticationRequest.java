package com.bobvu.tinhong.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;

}
