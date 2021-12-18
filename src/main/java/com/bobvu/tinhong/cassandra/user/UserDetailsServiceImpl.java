package com.bobvu.tinhong.cassandra.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        Optional<User> optional = userRepository.findOneByUsername(username);

        return optional.orElseThrow(()->new NullPointerException("User not found"));
    }
}
