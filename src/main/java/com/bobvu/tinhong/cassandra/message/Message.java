package com.bobvu.tinhong.cassandra.message;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;
import java.util.UUID;

@Table
@Data
@Builder
public class Message {

    @PrimaryKey
    private MessageId primaryKey;
    private String postedById;
    private String text;

    @Data
    @Builder
    @PrimaryKeyClass
    public static class MessageId {

        @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        @CreatedDate
        private Timestamp createdDate;

        @PrimaryKeyColumn( ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        private UUID id;

        @PrimaryKeyColumn(ordinal = 2, type = PrimaryKeyType.PARTITIONED)
        private UUID conversationId;




    }

}
