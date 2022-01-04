package com.bobvu.tinhong.cassandra.chat;

import com.bobvu.tinhong.cassandra.contact.ContactResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListContactResponse {
    private List<ContactResponse> conversations;
}
