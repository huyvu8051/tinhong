package com.bobvu.tinhong.cassandra.chat;

import com.bobvu.tinhong.cassandra.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/chat")
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/getMessage")
    public ResponseEntity<ListMessageResponse> getMessageInConversation(@RequestBody GetMessageInConverRequest request){
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ListMessageResponse response = chatService.getMessageInConversation(userDetails.getUsername(), request.getConversationId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/findAllPartner")
    public ResponseEntity<ListConversationResponse> findAllChatRoom(){

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ListConversationResponse res = chatService.findAllConversation(userDetails.getUsername());

        return ResponseEntity.ok(res);
    }

    @PostMapping("/addNewPartner")
    public void addNewPartner(@RequestBody AddNewPartnerRequest request){
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        chatService.addPartner(userDetails, request.getUsername());
    }

    @PostMapping("/pushMessage")
    public void pushMessage(@RequestBody PushMessageRequest request){
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        chatService.pushMessage(userDetails.getUsername(),request.getConversationId(), request.getText());
    }
}
