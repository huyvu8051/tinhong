package com.bobvu.tinhong.cassandra.match;

import com.bobvu.tinhong.cassandra.profile.ProfileResponse;
import com.bobvu.tinhong.cassandra.user.User;
import org.springframework.data.domain.Pageable;


public interface MatchService {


    PageResponse<ProfileResponse> findAllSuitablePerson(User user, FindSuitablePersonRequest request);
}
