package com.bobvu.tinhong.elasticsearch.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@Document(indexName = "user")
public class User{
    @Id
    private String id;
    private String username;
    private String password;
}
