package com.bobvu.tinhong.cassandra.chat;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListConversationResponse {
    private List<ConversationResponse> conversations;
}
