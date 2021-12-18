package com.bobvu.tinhong.elasticsearch.user;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserESRepository extends ElasticsearchRepository<User, String> {
}
