package com.bobvu.tinhong.cassandra.user;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CassandraRepository<User, String> {
    @Query("SELECT * FROM User WHERE username = :username")
    Optional<User> findOneByUsername(@Param("username") String username);
}
