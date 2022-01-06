package com.bobvu.tinhong.cassandra.profile;

import com.bobvu.tinhong.cassandra.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public ProfileResponse toDto(User entity) {
        if (entity == null) {
            return null;
        }

        ProfileResponse profileResponse = new ProfileResponse();

        profileResponse.setAbout(entity.getAbout());
        profileResponse.setJobDescription(entity.getJobDescription());
        profileResponse.setCompany(entity.getCompany());
        profileResponse.setSchool(entity.getSchool());
        profileResponse.setGender(genderToGender(entity.getGender()));

        return profileResponse;
    }

    protected com.bobvu.tinhong.cassandra.profile.ProfileResponse.Gender genderToGender(User.Gender gender) {
        if (gender == null) {
            return null;
        }

        com.bobvu.tinhong.cassandra.profile.ProfileResponse.Gender gender1;

        switch (gender) {
            case MALE:
                gender1 = com.bobvu.tinhong.cassandra.profile.ProfileResponse.Gender.MALE;
                break;
            case FEMALE:
                gender1 = com.bobvu.tinhong.cassandra.profile.ProfileResponse.Gender.FEMALE;
                break;
            default:
                throw new IllegalArgumentException("Unexpected enum constant: " + gender);
        }

        return gender1;
    }
}
