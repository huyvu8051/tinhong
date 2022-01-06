package com.bobvu.tinhong.cassandra.talkmessage;

import com.bobvu.tinhong.cassandra.chat.TalkMessageResponse;
import org.springframework.stereotype.Component;

@Component
public class TalkMessageMapperImpl implements TalkMessageMapper {

    @Override
    public TalkMessageResponse toDto(TalkMessage entity) {
        if (entity == null) {
            return null;
        }

        TalkMessageResponse talkMessageResponse = TalkMessageResponse.builder()
                .timestamp(entity.getTimestamp())
                .id(entity.getId())
                .message(entity.getMessage())
                .senderId(entity.getSenderId())
                .targetId(entity.getTargetId())
                .build();

        return talkMessageResponse;
    }
}