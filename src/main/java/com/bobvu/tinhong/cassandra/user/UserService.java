package com.bobvu.tinhong.cassandra.user;

import com.bobvu.tinhong.auth.RegistrationRequest;

public interface UserService {

    User registerNewUserAccount(RegistrationRequest request);
}
