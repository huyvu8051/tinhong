package com.bobvu.tinhong.cassandra.profile;

import com.bobvu.tinhong.cassandra.user.User;
import lombok.Data;

import java.util.List;

@Data
public class ProfileResponse {
    private List<String> pictures;
    private String about;
    private String jobDescription;
    private String company;
    private String school;
    private User.Gender gender;
    private List<User.Passion> passions;

}