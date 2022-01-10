package com.bobvu.tinhong.cassandra.match;

import com.bobvu.tinhong.cassandra.chat.ChatService;
import com.bobvu.tinhong.cassandra.profile.ProfileResponse;
import com.bobvu.tinhong.cassandra.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("user/match")
public class MatchController {
private final MatchService matchService;
private final ChatService chatService;


    @PostMapping
    public ResponseEntity<PageResponse<ProfileResponse>> findAllSuitablePerson(@RequestBody FindSuitablePersonRequest request){
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(matchService.findAllSuitablePerson(userDetails, request));
    }
}
