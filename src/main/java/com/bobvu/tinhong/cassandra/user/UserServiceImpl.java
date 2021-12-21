package com.bobvu.tinhong.cassandra.user;

import com.bobvu.tinhong.auth.jwt.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public User registerNewUserAccount(RegistrationRequest request) {
        return null;
    }
}
