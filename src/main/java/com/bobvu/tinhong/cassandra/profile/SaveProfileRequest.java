package com.bobvu.tinhong.cassandra.profile;

import com.bobvu.tinhong.cassandra.user.Gender;
import com.bobvu.tinhong.cassandra.user.Passion;
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
    private Gender gender;
    private List<Passion> passions;
    private Double longitude;
    private Double latitude;


    private Integer distance;

    private List<Gender> genderToShow;
    private Integer yearOfBirth;
    private Integer minAge;
    private Integer maxAge;


}