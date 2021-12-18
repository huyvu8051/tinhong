package com.bobvu.tinhong.google.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;


@RestController
@Slf4j
@RequestMapping("authentication")
public class GoogleAuthenticationController {
    @PostMapping("/google/login")
    public String authenticate(@RequestBody GoogleAuthenticationRequest request) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier =
                new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                        .setIssuer("accounts.google.com")
                        .build();

// (Receive idTokenString by HTTPS POST)
String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQ5OGY0OWJjNmNhNDU4MWVhZThkZmFkZDQ5NGZjZTEwZWEyM2FhYjAiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiYXpwIjoiNzA3MjMxNTYzODQ0LWU1Y3BrcXJsdDYyZ25jbWo2Yjg0b2Y1c21sOWxwOGc5LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiNzA3MjMxNTYzODQ0LWU1Y3BrcXJsdDYyZ25jbWo2Yjg0b2Y1c21sOWxwOGc5LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTAxMTYyNTU0MjA2MjU0NjYxMzc4IiwiZW1haWwiOiJodXl2dTgwNTFAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF0X2hhc2giOiJ4bVpMZmdSb2M1dC02VHpUTVpER053IiwibmFtZSI6IjcyOTNfVsWpIFbEg24gSHV5IiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hLS9BT2gxNEdpajhRUmpVT1FGazRtTHp6SlhQVDZYOXdCdWJiTndEaGtab2s4Mj1zOTYtYyIsImdpdmVuX25hbWUiOiI3MjkzX1bFqSBWxINuIEh1eSIsImxvY2FsZSI6ImVuIiwiaWF0IjoxNjM5ODE5NTI2LCJleHAiOjE2Mzk4MjMxMjYsImp0aSI6IjJlOTM1MjRmNTliMjcyOWM3NjJhNWM0ODkwNGI4ZjUxZWU5MTQ2MDMifQ.q3QsgCyGfRf4B1yEdu3AZFJ6WaMMsrnl6DDRBMBLGHddFr8qsXxliQvHSLaampiOnhR8FuciWjLXnj3EY-Y9DAu2ce9OcWNzPhvuHdPgYPpJCUcIK3aCCc0SsusDRhYQP-d7PS9EOg1DAD6vz5evjQObB-CoeTQM6pmgS79JNCOD-NDQvTBvmtFJBLi5ppCfLAyH2LqVUo3VLoPzwGqwS_PkYumC1NSGrkG-7IysetL1wju5VrUY1a4TLS7DWEcNZxGFd1Ztu-SB111dVQew31RJ9IZI5saLqcWiA2e6UlC-bNnXDP01UCj4T2iD5dTUTSeRFeH6AkrYDSJAR1eWRA";
        GoogleIdToken idToken = verifier.verify(token);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            // Use or store profile information
            // ...

            return email + " " + emailVerified + " " + name;

        } else {
            System.out.println("Invalid ID token.");
            return "Error";
        }
    }


}
