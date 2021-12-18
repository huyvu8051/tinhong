package com.bobvu.tinhong.auth;

import com.bobvu.tinhong.cassandra.user.User;
import com.bobvu.tinhong.cassandra.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("authentication")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final ApplicationEventPublisher eventPublisher;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest request){
        String formattedUsername = request.getUsername().trim().toLowerCase();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(formattedUsername, request.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(formattedUsername);

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    private final UserService userService;

    @PostMapping("/register")
    public void createAuthenticationUser(@RequestBody RegistrationRequest registrationRequest, HttpServletRequest servletRequest){
        User registered = userService.registerNewUserAccount(registrationRequest);

//        String appUrl = servletRequest.getContextPath();
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,
//                registrationRequest.getLocale(), appUrl));
    }


}
