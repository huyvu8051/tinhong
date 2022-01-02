package com.bobvu.tinhong.config;

import com.bobvu.tinhong.cassandra.repository.UserRepository;
import com.bobvu.tinhong.elasticsearch.user.UserESRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final UserESRepository userESRepository;

    private final UserRepository userRepository2;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

/*
        userESRepository.save(User.builder().id("user1").username("bobvu").password("henxui").build());

        userRepository2.save(com.bobvu.tinhong.cassandra.user.User.builder().password(passwordEncoder.encode("password")).username("bobvu")
                        .roles(new ArrayList<>(Collections.singleton("Admin, Host")))
                .build());


        Optional<User> optional = userESRepository.findById("user1");


        System.out.println("saved " + optional.get().getUsername());
        System.out.println(userRepository2.findById("bobvu").get().getPassword());*/
    }
}