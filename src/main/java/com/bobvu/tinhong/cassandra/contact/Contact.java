package com.bobvu.tinhong.cassandra.contact;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.sql.Timestamp;
import java.util.UUID;

@Table
@Data
@Builder
public class Contact {

    @PrimaryKey
    private ContactKey key;

    private String partnerId;// partner id
    private String fullName;// partner fullName
    private String avatar; // partner avatar


    private String lastMessageText;
    private String lastMessageFrom;// fullName


    @Builder
    @Data
    @UserDefinedType
    public static class ContactKey{
        @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        private Timestamp lastMessageTime;
        @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        private UUID conversationId;
        @PrimaryKeyColumn(ordinal = 2, type = PrimaryKeyType.PARTITIONED)
        private String owner;
    }

}
