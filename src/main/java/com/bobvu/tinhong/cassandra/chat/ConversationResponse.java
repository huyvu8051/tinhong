package com.bobvu.tinhong.cassandra.chat;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ConversationResponse {

    private UUID id;
    private String name;
    private List<Partner> partners;

    private Message lastMessage;


    @Data
    @Builder
    public static class Partner{
        private String username;
        private String fullName;
        private String avatar;
    }

    @Data
    @Builder
    public static class Message{
        private String byPartnerId;
        private String text;
        private Timestamp createdDate;
    }
}
