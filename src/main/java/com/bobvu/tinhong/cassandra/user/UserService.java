package com.bobvu.tinhong.cassandra.user;

import com.bobvu.tinhong.auth.jwt.RegistrationRequest;

public interface UserService {

    User registerNewUserAccount(RegistrationRequest request);
}
