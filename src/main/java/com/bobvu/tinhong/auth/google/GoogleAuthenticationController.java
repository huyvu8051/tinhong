package com.bobvu.tinhong.auth.google;

import com.bobvu.tinhong.auth.jwt.JwtUtil;
import com.bobvu.tinhong.cassandra.repository.UserRepository;
import com.bobvu.tinhong.cassandra.user.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Optional;


@RestController
@Slf4j
@RequestMapping("authentication")
@AllArgsConstructor
public class GoogleAuthenticationController {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;


    @PostMapping("/google/login")
    public AuthenticationResponse authenticate(@RequestBody GoogleAuthenticationRequest request) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Arrays.asList("1096280217531-liqq0djthhopsvgaom7cg6am19gk4gch.apps.googleusercontent.com"))
                .setIssuer("https://accounts.google.com")
                .build();

        // (Receive idTokenString by HTTPS POST)

        String idToken1 = request.getIdToken();
        GoogleIdToken idToken = verifier.verify(idToken1);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            if (!emailVerified) {
                throw new AuthException("Email not verified!");
            }
            String email = payload.getEmail();

            User user;
            try {
                Optional<User> optional = userRepository.findOneByUsername(email);
                user = optional.orElseThrow(() -> new UsernameNotFoundException("Account not register!"));


            } catch (UsernameNotFoundException e) {

                // create new account
                String userId = payload.getSubject();
                String name = (String) payload.get("name");
               String pictureUrl = (String) payload.get("picture");
               String locale = (String) payload.get("locale");
               String familyName = (String) payload.get("family_name");
               String givenName = (String) payload.get("given_name");// fullname

               // Use or store profile information
               // ...

               User build = User.builder()
                       .username(email)
                       .fullName(name)
                       .avatar(pictureUrl)
                       .gender(User.Gender.FEMALE)

                       .pictures(Arrays.asList(pictureUrl, "https://i.imgur.com/lpzlDQv.jpg", "https://i.imgur.com/pAZ8UUQ.jpg", "https://i.imgur.com/qfLln70.jpg"))
                       .locations(null)
                       .passions(Arrays.asList(User.Passion.Astrology, User.Passion.DIY, User.Passion.Climbing))
                       .about("Chúng ta của hiện tại")
                       .company("Heo khô đi những kỉ niệm xưa kia")
                       .jobDescription("Ngày mai, người luyến lưu theo những giấc mơ từng có ")
                       .school("Liệu có ta?")
                       .roles(Arrays.asList("user"))

                       .build();

               user = userRepository.save(build);


           }

            String token = jwtUtil.generateToken(user);

            return AuthenticationResponse.builder()
                    .jwt(token)
                    .username(email)
                    .fullName(user.getFullName())
                    .avatar(user.getAvatar())
                    .authorities(user.getAuthorities())
                    .build();
        } else {
            //System.out.println("Invalid ID token.");
            log.error("Invalid token id!");
            throw new AuthException("Token id not valid!");
        }
    }


}
