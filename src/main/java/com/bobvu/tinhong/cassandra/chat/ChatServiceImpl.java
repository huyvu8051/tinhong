package com.bobvu.tinhong.cassandra.chat;

import com.bobvu.tinhong.cassandra.repository.*;
import com.bobvu.tinhong.cassandra.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ConversationRepository conversationRepository;
    private final ConversationUserRepository conversationUserRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final UserConversationRepository userConversationRepository;


    @Override
    public ListConversationResponse findAllConversation(String username) {

        List<ConversationUser> conversationUsers = conversationUserRepository.findAllByPrimaryKeyUsername(username);


        List<ConversationResponse> result = new ArrayList<>();
        for (ConversationUser conversationUser : conversationUsers) {

            Conversation conversation = conversationRepository.findById(conversationUser.getPrimaryKey().getConversationId()).get();

            List<UserConversation> userConversations = userConversationRepository.findAllByPrimaryKeyConversationId(conversation.getId());

            List<User> userInConversation = userRepository.findAllById(userConversations.stream().map(e -> e.getPrimaryKey().getUsername()).collect(Collectors.toList()));

            List<ConversationResponse.Partner> partners = userInConversation.stream().map(e -> ConversationResponse.Partner.builder()
                    .username(e.getUsername())
                    .fullName(e.getFullName())
                    .avatar(e.getAvatar())
                    .build()).collect(Collectors.toList());

            List<Message> listMessage = messageRepository.findAllByPrimaryKeyConversationId(conversation.getId());

            ConversationResponse conversationResponse = ConversationResponse.builder()
                    .id(conversation.getId())
                    .name(conversation.getName())
                    .partners(partners)

                    .build();


            if (listMessage.size() > 0) {

                Message message = listMessage.get(0);

                ConversationResponse.Message ms = ConversationResponse.Message.builder()
                        .byPartnerId(message.getPostedById())
                        .text(message.getText())
                        .createdDate(message.getPrimaryKey().getCreatedDate())
                        .build();

                conversationResponse.setLastMessage(ms);
            }

            result.add(conversationResponse);
        }

        return ListConversationResponse.builder().conversations(result).build();
    }

    @Override
    public void addPartner(User requester, String personRequested) {



        Optional<User> requestedOptional = userRepository.findOneByUsername(personRequested);
        User requested = requestedOptional.orElseThrow(() -> new UsernameNotFoundException("Person requested not found!"));


        Conversation room = com.bobvu.tinhong.cassandra.chat.Conversation.builder().id(UUID.randomUUID()).name("Conversation between " + requester.getUsername() + " and " + personRequested).build();

        conversationRepository.save(room);


        ConversationUser cu1 = ConversationUser.builder()
                .primaryKey(ConversationUser.ConversationUserKey.builder()
                        .conversationId(room.getId())
                        .username(requester.getUsername())
                        .build())
                .build();
        ConversationUser cu2 = ConversationUser.builder().primaryKey(ConversationUser.ConversationUserKey.builder().conversationId(room.getId()).username(personRequested).build()).build();

        conversationUserRepository.save(cu1);
        conversationUserRepository.save(cu2);

        UserConversation uc1 = UserConversation.builder()
                .primaryKey(UserConversation.UserConversationKey.builder().conversationId(room.getId()).username(requester.getUsername()).build())
                .build();

        UserConversation uc2 = UserConversation.builder()
                .primaryKey(UserConversation.UserConversationKey.builder().conversationId(room.getId()).username(personRequested).build())
                .build();

        userConversationRepository.save(uc1);
        userConversationRepository.save(uc2);


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
