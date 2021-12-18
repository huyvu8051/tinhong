package com.bobvu.tinhong.auth;

import lombok.Data;

import java.util.List;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private String fullName;
    private String dateOfBirth;
    private String bio;
    private List<String> interesting;
}
