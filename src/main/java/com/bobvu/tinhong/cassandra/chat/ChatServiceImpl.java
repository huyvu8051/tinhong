package com.bobvu.tinhong.cassandra.chat;

import com.bobvu.tinhong.cassandra.repository.UserRepository;
import com.bobvu.tinhong.cassandra.talklastmessage.TalkLastMessage;
import com.bobvu.tinhong.cassandra.talklastmessage.TalkLastMessageMapper;
import com.bobvu.tinhong.cassandra.talklastmessage.TalkLastMessageRepository;
import com.bobvu.tinhong.cassandra.talklastmessage.TalkLastMessageResponse;
import com.bobvu.tinhong.cassandra.talkmessage.TalkMessage;
import com.bobvu.tinhong.cassandra.talkmessage.TalkMessageMapper;
import com.bobvu.tinhong.cassandra.talkmessage.TalkMessageRepository;
import com.bobvu.tinhong.cassandra.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final TalkMessageRepository talkMessageRepository;
    private final TalkLastMessageRepository talkLastMessageRepository;
    private final UserRepository userRepository;
    private final TalkMessageMapper talkMessageMapper;
    private final TalkLastMessageMapper talkLastMessageMapper;


    @Override
    public List<TalkLastMessageResponse> findAllConversation(String username) {

        List<TalkLastMessage> tlms = talkLastMessageRepository.findAllBySenderId(username);
        List<TalkLastMessageResponse> responses = new ArrayList<>();
        for (TalkLastMessage tlm : tlms) {
            responses.add(talkLastMessageMapper.toDto(tlm));
        }
        return responses;
    }
    private String createKey(String username, String personRequested) {
        int i = username.compareTo(personRequested);
        if (i >= 0) return username + ":" + personRequested;
        return personRequested + ":" + username;
    }

    @Override
    public void pushMessage(User sender, String targetId, String message) {

        TalkMessage tm = TalkMessage.builder()
                .timestamp(System.currentTimeMillis())
                .id(createKey(sender.getUsername(), targetId))
                .message(message)
                .senderId(sender.getId())
                .targetId(targetId)
                .build();




        Optional<User> optionalUser = userRepository.findOneByUsername(targetId);

        User targetUser = optionalUser.orElseThrow(() -> new UsernameNotFoundException("target user not found"));

        TalkLastMessage tlm = createTalkLastMessage(sender.getId(), targetUser, message);
        TalkLastMessage tlm2 = createTalkLastMessage(targetId, sender, message);

        talkMessageRepository.save(tm);
        talkLastMessageRepository.saveAll(Arrays.asList(tlm, tlm2));



    }




    public TalkLastMessage createTalkLastMessage(String senderId, User target, String message){

        TalkLastMessage tlm = TalkLastMessage.builder()
                .senderId(senderId)
                .targetId(target.getId())
                .unread(true)
                .lastMessage(message)
                .targetAvatar(target.getAvatar())
                .targetFullName(target.getFullName())
                .timestamp(System.currentTimeMillis())
                .build();

        return tlm;
    }


    @Override
    public List<TalkMessageResponse> getMessageInConversation(String sender, String targetId) {
        List<TalkMessage> listTM = talkMessageRepository.findAllById(createKey(sender, targetId));

        List<TalkMessageResponse> responses = new ArrayList<>();
        for (TalkMessage talkMessage : listTM) {
            responses.add(talkMessageMapper.toDto(talkMessage));
        }
        return responses;
    }
}
