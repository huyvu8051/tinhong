package com.bobvu.tinhong.cassandra.profile;

import com.bobvu.tinhong.cassandra.repository.UserRepository;
import com.bobvu.tinhong.cassandra.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ProfileResponse getProfile(String username) {
        User user = userRepository.findOneByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));


        return userMapper.toDto(user);
    }

    @Override
    public void saveProfile(String username, SaveProfileRequest request) {

    }
}
