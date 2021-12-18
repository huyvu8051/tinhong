package com.bobvu.tinhong.cassandra.audit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.cassandra.core.mapping.Column;

import java.sql.Timestamp;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Auditable {

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Timestamp createdDate;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private Timestamp modifiedDate;
}