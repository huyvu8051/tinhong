package com.bobvu.tinhong.cassandra.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TalkMessageResponse {

    private long timestamp;

    private String id;

    private String message;
    private String senderId;
    private String targetId;
}
