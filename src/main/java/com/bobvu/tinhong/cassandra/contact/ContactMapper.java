package com.bobvu.tinhong.cassandra.contact;

public interface ContactMapper {
    ContactResponse toDto(Contact entity);
}
