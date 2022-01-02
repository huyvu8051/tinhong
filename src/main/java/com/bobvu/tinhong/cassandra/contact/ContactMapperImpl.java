package com.bobvu.tinhong.cassandra.contact;

import com.bobvu.tinhong.cassandra.chat.ContactResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ContactMapperImpl implements ContactMapper{
    @Override
    public ContactResponse toDto(Contact entity) {

        if(entity == null) return null;

        ContactResponse dto = new ContactResponse();

        dto.setAvatar(entity.getAvatar());
        dto.setFullName(entity.getFullName());
        dto.setLastMessageFrom(entity.getLastMessageFrom());
        dto.setLastMessageText(entity.getLastMessageText());

        Contact.ContactKey entityKey = entity.getKey();

        if(entityKey != null){
            ContactResponse.ContactResponseKey dtoKey = new ContactResponse.ContactResponseKey();
            dtoKey.setOwner(entityKey.getOwner());
            dtoKey.setConversationId(entityKey.getConversationId());
            dtoKey.setPartnerId(entityKey.getPartnerId());
            dtoKey.setLastMessageTime(entityKey.getLastMessageTime());
            dto.setKey(dtoKey);
        }

        return dto;
    }
}
