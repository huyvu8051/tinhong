package com.bobvu.tinhong.cassandra.repository;

import com.bobvu.tinhong.cassandra.chat.Conversation;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ConversationRepository extends CassandraRepository<Conversation, UUID> {



}
