package com.bobvu.tinhong.cassandra.chat;

import com.bobvu.tinhong.cassandra.talklastmessage.TalkLastMessageResponse;
import com.bobvu.tinhong.cassandra.user.User;

import java.util.List;

public interface ChatService {
    List<TalkLastMessageResponse> findAllConversation(String username);

    void pushMessage(User sender, String targetId, String message);

    List<TalkMessageResponse> getMessageInConversation(String sender, String targetId);
}
