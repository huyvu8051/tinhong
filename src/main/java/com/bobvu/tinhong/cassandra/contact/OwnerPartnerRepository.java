package com.bobvu.tinhong.cassandra.contact;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;

public interface OwnerPartnerRepository extends CassandraRepository<OwnerPartner, OwnerPartner.OwnerPartnerKey> {
    Optional<OwnerPartner> findOneByKeyOwnerAndKeyPartnerId(String username, String personRequested);
}
