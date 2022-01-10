package com.bobvu.tinhong.cassandra.match;

import com.bobvu.tinhong.cassandra.profile.ProfileResponse;
import com.bobvu.tinhong.cassandra.user.User;


public interface MatchService {
    PageResponse<ProfileResponse> findAllSuitablePerson(User user, FindSuitablePersonRequest request);

}
