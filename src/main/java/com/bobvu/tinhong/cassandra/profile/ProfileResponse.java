package com.bobvu.tinhong.cassandra.profile;

import lombok.Data;

import java.util.List;

@Data
public class ProfileResponse {
    private List<String> images;
    private String about;
    private String jobDescription;
    private String company;
    private String school;
    private Gender gender;


    public enum Gender {
        MALE, FEMALE
    }

}