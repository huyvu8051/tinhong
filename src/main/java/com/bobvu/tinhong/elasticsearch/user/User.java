package com.bobvu.tinhong.elasticsearch.user;

import com.bobvu.tinhong.cassandra.user.Gender;
import com.bobvu.tinhong.cassandra.user.Passion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.util.List;

@Data
@Document(indexName = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User{
    @Id
    private String username;
    private String fullName;
    private String avatar;
    private Gender gender;

    private String about;
    private String jobDescription;
    private String company;
    private String school;

    private List<String> pictures;
    private List<Passion> passions;
    @GeoPointField
    private GeoPoint location;

    private int distance;

    private List<Gender> genderToShow;
    private int yearOfBirth;
    private int minAge;
    private int maxAge;

}
