package com.bobvu.tinhong.config;

import com.bobvu.tinhong.elasticsearch.user.User;
import com.bobvu.tinhong.elasticsearch.user.UserESRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final UserESRepository userESRepository;

    private final com.bobvu.tinhong.cassandra.user.UserRepository userRepository2;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        userESRepository.save(User.builder().id("user1").username("bobvu").password("henxui").build());

        userRepository2.save(com.bobvu.tinhong.cassandra.user.User.builder().password(passwordEncoder.encode("password")).username("bobvu")
                        .roles(new ArrayList<>(Collections.singleton("Admin, Host")))
                .build());


        Optional<User> optional = userESRepository.findById("user1");


        System.out.println("saved " + optional.get().getUsername());
        System.out.println(userRepository2.findById("bobvu").get().getPassword());
    }
}