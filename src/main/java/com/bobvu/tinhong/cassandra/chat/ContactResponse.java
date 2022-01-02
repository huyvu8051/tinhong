package com.bobvu.tinhong.cassandra.chat;

import com.bobvu.tinhong.cassandra.contact.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {

    private ContactResponseKey key;
    private String fullName;// partner fullName
    private String avatar; // partner avatar

    private String lastMessageText;
    private String lastMessageFrom;// fullName

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ContactResponseKey {
        private Timestamp lastMessageTime;
        private UUID conversationId;
        private String owner;
        private String partnerId;// partner id
    }

}
