package com.bobvu.tinhong.cassandra.talklastmessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TalkLastMessageResponse {
    private String senderId;
    private String targetId;

    private boolean unread;
    private String lastMessage;

    private String targetFullName;
    private String targetAvatar;

    private long timestamp;
}
