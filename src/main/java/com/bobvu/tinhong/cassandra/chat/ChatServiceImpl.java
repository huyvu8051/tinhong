package com.bobvu.tinhong.cassandra.chat;

import com.bobvu.tinhong.cassandra.contact.Contact;
import com.bobvu.tinhong.cassandra.contact.ContactMapper;
import com.bobvu.tinhong.cassandra.contact.ContactRepository;
import com.bobvu.tinhong.cassandra.repository.*;
import com.bobvu.tinhong.cassandra.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final UserConversationRepository userConversationRepository;
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;


    @Override
    public ListConversationResponse findAllConversation(String username) {



        return ListConversationResponse.builder().conversations(result).build();
    }

    @Override
    public ContactResponse addPartner(User requester, String personRequested) {

        // check is conversation existed
        Optional<Contact> optional= contactRepository.findOneByKeyOwnerAndKeyPartnerId(requester.getUsername(), personRequested);
        if(optional.isPresent()){

            return contactMapper.toDto(optional.get());
        }


        Optional<User> requestedOptional = userRepository.findOneByUsername(personRequested);
        User requested = requestedOptional.orElseThrow(() -> new UsernameNotFoundException("Person requested not found!"));


        Conversation conversation = Conversation.builder()
                .name("Conversation between " + requester.getUsername() + " and " + personRequested )
                .id(UUID.randomUUID())
                .build();

        conversationRepository.save(conversation);





    }


    private void createContact(String requester, User personRequested, UUID conversationId ){
        Contact.ContactKey contactKey = Contact.ContactKey.builder()
                .lastMessageTime(null)
                .conversationId(conversationId)
                .owner(requester)
                .partnerId(personRequested.getId())
                .build();


        Contact contact = Contact.builder()
                .key(contactKey)
                .fullName(personRequested.getFullName())
                .avatar(personRequested.getAvatar())
                .lastMessageText(null)
                .lastMessageFrom(null)
                .build();

        contactRepository.save(contact);
    }

    @Override
    public void pushMessage(String username, UUID conversationId, String text) {
        Optional<Conversation> conversationOptional = conversationRepository.findById(conversationId);
        conversationOptional.orElseThrow(() -> new NullPointerException("Conversation not found"));

        Message message = Message.builder().primaryKey(Message.MessageId.builder().createdDate(new Timestamp(System.currentTimeMillis())).id(UUID.randomUUID()).conversationId(conversationId).build()).text(text).postedById(username).build();

        messageRepository.save(message);

    }

    @Override
    public ListMessageResponse getMessageInConversation(String username, UUID conversationId) {

        List<Message> messages = messageRepository.findAllByPrimaryKeyConversationId(conversationId);

        List<MessageResponse> result = new ArrayList<>();
        for (Message message : messages) {
            result.add(MessageResponse.builder().conversationId(message.getPrimaryKey().getConversationId()).id(message.getPrimaryKey().getId()).createdDate(message.getPrimaryKey().getCreatedDate()).postedById(message.getPostedById()).text(message.getText()).build());
        }


        return ListMessageResponse.builder().messages(result).build();
    }
}
