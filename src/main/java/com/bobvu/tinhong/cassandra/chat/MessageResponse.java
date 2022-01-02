package com.bobvu.tinhong.cassandra.chat;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class MessageResponse {
    private UUID id;

    private UUID conversationId;
    private Timestamp createdDate;
    private String postedById;
    private String text;



}
