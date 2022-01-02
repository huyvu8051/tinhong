package com.bobvu.tinhong.cassandra.repository;

import com.bobvu.tinhong.cassandra.chat.ConversationUser;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface ConversationUserRepository extends CassandraRepository<ConversationUser, ConversationUser.ConversationUserKey> {
    List<ConversationUser> findAllByPrimaryKeyUsername(String username);

}