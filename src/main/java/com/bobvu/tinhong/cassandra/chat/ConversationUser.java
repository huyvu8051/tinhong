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
public class ConversationUser {
    @PrimaryKey
    private ConversationUserKey primaryKey;



    @Data
    @Builder
    @PrimaryKeyClass
    public static class ConversationUserKey {

        @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.CLUSTERED)
        private UUID conversationId;

        @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.PARTITIONED)
        private String username;
    }
}
