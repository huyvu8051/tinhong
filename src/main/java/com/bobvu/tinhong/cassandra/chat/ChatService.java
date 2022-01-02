package com.bobvu.tinhong.cassandra.chat;

import com.bobvu.tinhong.cassandra.user.User;

import java.util.UUID;

public interface ChatService {
    ListConversationResponse findAllConversation(String username);

    ContactResponse addPartner(User requester, String personRequested);

    void pushMessage(String username,UUID conversationId, String text);

    ListMessageResponse getMessageInConversation(String username, UUID conversationId);
}
