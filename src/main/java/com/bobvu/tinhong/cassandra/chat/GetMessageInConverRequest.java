package com.bobvu.tinhong.cassandra.chat;

import lombok.Data;

import java.util.UUID;

@Data
public class GetMessageInConverRequest {
    private UUID conversationId;
}
