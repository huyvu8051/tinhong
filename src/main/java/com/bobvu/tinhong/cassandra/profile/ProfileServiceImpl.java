package com.bobvu.tinhong.cassandra.profile;

import com.bobvu.tinhong.cassandra.repository.UserRepository;
import com.bobvu.tinhong.cassandra.user.User;
import com.bobvu.tinhong.elasticsearch.user.UserESRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final UserESRepository userESRepository;

    @Override
    public ProfileResponse getProfile(String username) {
        User user = userRepository.findOneByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));


        return userMapper.toDto(user);
    }

    @Override
    public void saveProfile(String username, SaveProfileRequest request) {
        User user = userRepository.findOneByUsername(username).orElseThrow(() -> new NullPointerException("user not found!"));
        userMapper.update(request, user);
        userRepository.save(user);

        com.bobvu.tinhong.elasticsearch.user.User esUser = userMapper.toESEntity(user);

        userESRepository.save(esUser);


    }
}
