package com.bobvu.tinhong.cassandra.chat;

import com.bobvu.tinhong.cassandra.talklastmessage.TalkLastMessageResponse;
import com.bobvu.tinhong.cassandra.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/chat")
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/getMessage")
    public ResponseEntity<List<TalkMessageResponse>> getMessageInConversation(@RequestBody GetMessageInConverRequest request) {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TalkMessageResponse> response = chatService.getMessageInConversation(userDetails.getUsername(), request.getTargetId()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/findAllPartner")
    public ResponseEntity<List<TalkLastMessageResponse>> findAllChatRoom() {

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TalkLastMessageResponse> res = chatService.findAllConversation(userDetails.getUsername());

        return ResponseEntity.ok(res);
    }


    @PostMapping("/pushMessage")
    public void pushMessage(@RequestBody PushMessageRequest request){
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        chatService.pushMessage(userDetails, request.getTargetId(), request.getMessage());
    }
}
