package com.bobvu.tinhong.cassandra.match;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/chat")
public class MatchController {

    @PostMapping("/addPartner")
    public void likePartner(@RequestBody LikePartnerRequest request){

    }
}
