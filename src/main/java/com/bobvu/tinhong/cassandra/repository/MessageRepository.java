package com.bobvu.tinhong.cassandra.repository;

import com.bobvu.tinhong.cassandra.message.Message;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends CassandraRepository<Message, Message.MessageId> {

    List<Message> findAllByPrimaryKeyConversationId(UUID conversationId);
}