package com.bobvu.tinhong.cassandra.talkmessage;

import com.bobvu.tinhong.cassandra.chat.TalkMessageResponse;
import org.mapstruct.Mapper;

//@Mapper
public interface TalkMessageMapper {

    TalkMessageResponse toDto(TalkMessage entity);
}
