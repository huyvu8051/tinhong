package com.bobvu.tinhong.cassandra.talklastmessage;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.Optional;

public interface TalkLastMessageRepository extends CassandraRepository<TalkLastMessage, String> {
    Optional<TalkLastMessage> findOneBySenderIdAndTargetId(String senderId, String id);

    List<TalkLastMessage> findAllBySenderId(String username);
}
