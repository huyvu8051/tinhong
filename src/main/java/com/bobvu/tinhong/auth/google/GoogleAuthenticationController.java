package com.bobvu.tinhong.auth.google;

import com.bobvu.tinhong.auth.AuthenticationService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;
import java.io.IOException;
import java.security.GeneralSecurityException;


@RestController
@Slf4j
@RequestMapping("authentication")
@AllArgsConstructor
public class GoogleAuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/google/login")
    public String authenticate(@RequestBody GoogleAuthenticationRequest request) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setIssuer("accounts.google.com")
                .build();

        // (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify(request.getIdToken());
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
            //System.out.println("Invalid ID token.");
            log.error("Invalid token id!");
            throw new AuthException("Token id not valid!");
        }
    }


}
