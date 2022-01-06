package com.bobvu.tinhong.cassandra.profile;

import org.springframework.stereotype.Service;

public interface ProfileService {
    ProfileResponse getProfile(String username);

    void saveProfile(String username, SaveProfileRequest request);
}
