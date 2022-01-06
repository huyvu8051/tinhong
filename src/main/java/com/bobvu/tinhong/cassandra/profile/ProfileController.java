package com.bobvu.tinhong.cassandra.profile;

import com.bobvu.tinhong.cassandra.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;


    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(){


        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(profileService.getProfile(userDetails.getUsername()));
    }

    @PostMapping("/profile")
    public void saveProfile(@RequestBody SaveProfileRequest request){

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        profileService.saveProfile(userDetails.getUsername(), request);
    }

}
