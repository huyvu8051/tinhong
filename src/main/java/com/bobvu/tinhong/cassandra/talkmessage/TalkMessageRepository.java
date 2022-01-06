package com.bobvu.tinhong.cassandra.talkmessage;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface TalkMessageRepository extends CassandraRepository<TalkMessage, String> {

    List<TalkMessage> findAllById(String key);
}
