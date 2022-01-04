package com.bobvu.tinhong.cassandra.chat;

import com.bobvu.tinhong.cassandra.contact.*;
import com.bobvu.tinhong.cassandra.message.ListMessageResponse;
import com.bobvu.tinhong.cassandra.message.Message;
import com.bobvu.tinhong.cassandra.message.MessageResponse;
import com.bobvu.tinhong.cassandra.repository.ConversationRepository;
import com.bobvu.tinhong.cassandra.repository.MessageRepository;
import com.bobvu.tinhong.cassandra.repository.UserInConversationRepository;
import com.bobvu.tinhong.cassandra.repository.UserRepository;
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
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final OwnerPartnerRepository ownerPartnerRepository;
    private final UserInConversationRepository userInConversationRepository;


    @Override
    public ListContactResponse findAllConversation(String username) {


        List<Contact> l = contactRepository.findAllByKeyOwner(username);

        List<ContactResponse> list = new ArrayList<>();

        for (Contact contact : l) {
            ContactResponse contactResponse = contactMapper.toDto(contact);
            list.add(contactResponse);
        }

        return ListContactResponse.builder().conversations(list).build();
    }

    @Override
    public void addPartner(User requester, String personRequested) throws Exception {


        // check is conversation existed
        Optional<OwnerPartner> optional = ownerPartnerRepository.findOneByKeyOwnerAndKeyPartnerId(requester.getUsername(), personRequested);
        if (optional.isPresent()) {
            throw new Exception("Conversation existed");
        }
        // create contact


        Conversation conversation = Conversation.builder()
                .name("Conversation between " + requester.getUsername() + " and " + personRequested)
                .id(UUID.randomUUID())
                .build();

        conversationRepository.save(conversation);

        Optional<User> requestedOptional = userRepository.findOneByUsername(personRequested);
        User requested = requestedOptional.orElseThrow(() -> new UsernameNotFoundException("Person requested not found!"));

        Contact contact = createContact(requester.getUsername(), requested, conversation.getId());
        Contact contact1 = createContact(personRequested, requester, conversation.getId());

        OwnerPartner ownerPartner = createOwnerPartner(requester.getUsername(), requested, conversation.getId());
        OwnerPartner ownerPartner1 = createOwnerPartner(personRequested, requester, conversation.getId());

        contactRepository.saveAll(Arrays.asList(contact, contact1));
        ownerPartnerRepository.saveAll(Arrays.asList(ownerPartner, ownerPartner1));

    }


    private OwnerPartner createOwnerPartner(String requester, User personRequested, UUID conversationId) {


        // save owner partner
        OwnerPartner.OwnerPartnerKey key = OwnerPartner.OwnerPartnerKey.builder()
                .owner(requester)
                .partnerId(personRequested.getUsername())
                .build();

        return OwnerPartner.builder()
                .key(key)
                .build();


    }


    private Contact createContact(String requester, User personRequested, UUID conversationId) {

        // save contact
        Contact.ContactKey contactKey = Contact.ContactKey.builder()
                .lastMessageTime(new Timestamp(System.currentTimeMillis()))
                .conversationId(conversationId)
                .owner(requester)
                .partnerId(personRequested.getId())
                .build();


       return Contact.builder()
                .key(contactKey)
                .fullName(personRequested.getFullName())
                .avatar(personRequested.getAvatar())
                .lastMessageText(null)
                .lastMessageFrom(null)
                .build();




    }

    @Override
    public void pushMessage(String username, UUID conversationId, String text) {
        Optional<Conversation> conversationOptional = conversationRepository.findById(conversationId);
        conversationOptional.orElseThrow(() -> new NullPointerException("Conversation not found"));

        Message message = Message.builder().primaryKey(Message.MessageId.builder().createdDate(new Timestamp(System.currentTimeMillis())).id(UUID.randomUUID()).conversationId(conversationId).build()).text(text).postedById(username).build();

        messageRepository.save(message);

        List<UserInConversation> uics = userInConversationRepository.findAllByKeyConversationId(conversationId);



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
