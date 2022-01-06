package com.bobvu.tinhong.cassandra.chat;

import lombok.Data;

@Data
public class PushMessageRequest {
    private String targetId;
    private String message;


}
