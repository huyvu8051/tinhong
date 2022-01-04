package com.bobvu.tinhong.cassandra.chat;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@Data
@Builder
public class UserInConversation {
    @PrimaryKey
    private UserInConversationKey key;



    @Data
    @Builder
    @PrimaryKeyClass
    public static class UserInConversationKey {

        @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private UUID conversationId;

        @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        private String username;
    }
}
