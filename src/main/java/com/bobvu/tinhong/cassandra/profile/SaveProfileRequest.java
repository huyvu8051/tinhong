package com.bobvu.tinhong.cassandra.profile;

import com.bobvu.tinhong.cassandra.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SaveProfileRequest {
    private List<String> pictures;
    private String about;
    private String jobDescription;
    private String company;
    private String school;
    private User.Gender gender;
    private List<User.Passion> passions;
    private Double longitude;
    private Double latitude;

}