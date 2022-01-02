package com.bobvu.tinhong.cassandra.repository;

import com.bobvu.tinhong.cassandra.chat.UserConversation;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface UserConversationRepository extends CassandraRepository<UserConversation, UserConversation.UserConversationKey> {
    List<UserConversation> findAllByPrimaryKeyConversationId(UUID conversationId);
}
