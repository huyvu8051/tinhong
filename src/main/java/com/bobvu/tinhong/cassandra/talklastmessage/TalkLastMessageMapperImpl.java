package com.bobvu.tinhong.cassandra.talklastmessage;

import org.springframework.stereotype.Component;

@Component
public class TalkLastMessageMapperImpl implements TalkLastMessageMapper {

    @Override
    public TalkLastMessageResponse toDto(TalkLastMessage entity) {
        if (entity == null) {
            return null;
        }

        TalkLastMessageResponse talkLastMessageResponse = TalkLastMessageResponse.builder()
                .senderId(entity.getSenderId())
                .targetId(entity.getTargetId())
                .unread(entity.isUnread())
                .lastMessage(entity.getLastMessage())
                .targetFullName(entity.getTargetFullName())
                .targetAvatar(entity.getTargetAvatar())
                .timestamp(entity.getTimestamp())
                .build();

        return talkLastMessageResponse;
    }
}