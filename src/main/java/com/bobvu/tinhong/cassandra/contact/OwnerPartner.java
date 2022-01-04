package com.bobvu.tinhong.cassandra.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Data
@Builder
public class OwnerPartner {
    @PrimaryKey
    private OwnerPartnerKey key;

    @Builder
    @Data
    @PrimaryKeyClass
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OwnerPartnerKey{
        @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
        private String owner;

        @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
        private String partnerId;// partner id
    }
}
