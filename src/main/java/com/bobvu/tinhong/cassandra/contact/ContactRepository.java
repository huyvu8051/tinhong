package com.bobvu.tinhong.cassandra.contact;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;

public interface ContactRepository extends CassandraRepository<Contact, Contact.ContactKey> {
    Optional<Contact> findOneByKeyOwnerAndKeyPartnerId(String username, String personRequested);
}
