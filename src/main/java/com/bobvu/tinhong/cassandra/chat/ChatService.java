package com.bobvu.tinhong.cassandra.chat;

import com.bobvu.tinhong.cassandra.message.ListMessageResponse;
import com.bobvu.tinhong.cassandra.user.User;

import java.util.UUID;

public interface ChatService {
    ListContactResponse findAllConversation(String username);

    void addPartner(User requester, String personRequested) throws Exception;

    void pushMessage(String username,UUID conversationId, String text);

    ListMessageResponse getMessageInConversation(String username, UUID conversationId);
}
