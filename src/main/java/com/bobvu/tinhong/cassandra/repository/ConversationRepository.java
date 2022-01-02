package com.bobvu.tinhong.cassandra.repository;

import com.bobvu.tinhong.cassandra.chat.Conversation;
import com.bobvu.tinhong.cassandra.chat.ConversationUser;
import com.bobvu.tinhong.cassandra.chat.Message;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface ConversationRepository extends CassandraRepository<Conversation, UUID> {



}
