package com.bobvu.tinhong.cassandra.contact;

import com.bobvu.tinhong.cassandra.chat.ContactResponse;

public interface ContactMapper {
    ContactResponse toDto(Contact entity);
}
