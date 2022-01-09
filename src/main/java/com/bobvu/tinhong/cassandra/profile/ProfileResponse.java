package com.bobvu.tinhong.cassandra.profile;

import com.bobvu.tinhong.cassandra.user.Gender;
import com.bobvu.tinhong.cassandra.user.Passion;
import lombok.Data;

import java.util.List;

@Data
public class ProfileResponse {

    private String username;
    private String fullName;
    private String avatar;

    private List<String> pictures;
    private String about;
    private String jobDescription;
    private String company;
    private String school;
    private Gender gender;
    private List<Passion> passions;
    private Double longitude;
    private Double latitude;


    private int distance;

    private List<Gender> genderToShow;
    private int yearOfBirth;
    private int minAge;
    private int maxAge;


}