package com.bobvu.tinhong.cassandra.repository;

import com.bobvu.tinhong.cassandra.chat.UserInConversation;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface UserInConversationRepository extends CassandraRepository<UserInConversation, UserInConversation.UserInConversationKey> {
    List<UserInConversation> findAllByKeyConversationId(UUID conversationId);
}
