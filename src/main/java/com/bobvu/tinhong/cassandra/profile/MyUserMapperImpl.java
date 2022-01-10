package com.bobvu.tinhong.cassandra.profile;

import com.bobvu.tinhong.cassandra.user.Gender;
import com.bobvu.tinhong.cassandra.user.Passion;
import com.bobvu.tinhong.cassandra.user.User;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserMapperImpl implements UserMapper{

    @Override
    public ProfileResponse toDto(User entity) {
        if (entity == null) {
            return null;
        }

        ProfileResponse profileResponse = new ProfileResponse();

        List<String> list = entity.getPictures();
        if (list != null) {
            profileResponse.setPictures(new ArrayList<String>(list));
        }
        profileResponse.setAbout(entity.getAbout());
        profileResponse.setJobDescription(entity.getJobDescription());
        profileResponse.setCompany(entity.getCompany());
        profileResponse.setSchool(entity.getSchool());
        profileResponse.setGender(entity.getGender());
        List<Passion> list1 = entity.getPassions();
        if (list1 != null) {
            profileResponse.setPassions(new ArrayList<Passion>(list1));
        }
        profileResponse.setDistance(entity.getDistance());
        List<Gender> list2 = entity.getGenderToShow();
        if (list2 != null) {
            profileResponse.setGenderToShow(new ArrayList<Gender>(list2));
        }
        profileResponse.setYearOfBirth(entity.getYearOfBirth());
        profileResponse.setMinAge(entity.getMinAge());
        profileResponse.setMaxAge(entity.getMaxAge());
        profileResponse.setLatitude(entity.getLat());
        profileResponse.setLongitude(entity.getLon());

        return profileResponse;
    }

    @Override
    public void update(SaveProfileRequest source, User target) {
        if (source == null) {
            return;
        }

        if (source.getGender() != null) {
            target.setGender(source.getGender());
        }
        if (source.getAbout() != null) {
            target.setAbout(source.getAbout());
        }
        if (source.getJobDescription() != null) {
            target.setJobDescription(source.getJobDescription());
        }
        if (source.getCompany() != null) {
            target.setCompany(source.getCompany());
        }
        if (source.getSchool() != null) {
            target.setSchool(source.getSchool());
        }
        if (target.getPictures() != null) {
            List<String> list = source.getPictures();
            if (list != null) {
                target.getPictures().clear();
                target.getPictures().addAll(list);
            }
        } else {
            List<String> list = source.getPictures();
            if (list != null) {
                target.setPictures( new ArrayList<String>( list ) );
            }
        }
        if (target.getPassions() != null) {
            List<Passion> list1 = source.getPassions();
            if (list1 != null) {
                target.getPassions().clear();
                target.getPassions().addAll(list1);
            }
        } else {
            List<Passion> list1 = source.getPassions();
            if (list1 != null) {
                target.setPassions(new ArrayList<Passion>(list1));
            }
        }


        if(source.getDistance() != null){
            target.setDistance(source.getDistance());
        }
        if (target.getGenderToShow() != null) {
            List<Gender> list2 = source.getGenderToShow();
            if (list2 != null) {
                target.getGenderToShow().clear();
                target.getGenderToShow().addAll(list2);
            }
        } else {
            List<Gender> list2 = source.getGenderToShow();
            if (list2 != null) {
                target.setGenderToShow(new ArrayList<Gender>(list2));
            }
        }

        if(source.getYearOfBirth() != null){

            target.setYearOfBirth(source.getYearOfBirth());
        }

        if(source.getMinAge() != null){
            target.setMinAge(source.getMinAge());
        }

        if(source.getMaxAge() != null){
            target.setMaxAge(source.getMaxAge());
        }

        if (source.getLatitude() != null) {
            target.setLat(source.getLatitude());
        }

        if (source.getLongitude() != null){

            target.setLon(source.getLongitude());

        }
    }

    @Override
    public com.bobvu.tinhong.elasticsearch.user.User toESEntity(User cassandraEntity) {
        if (cassandraEntity == null) {
            return null;
        }

        com.bobvu.tinhong.elasticsearch.user.User.UserBuilder user = com.bobvu.tinhong.elasticsearch.user.User.builder();

        user.username(cassandraEntity.getUsername());
        user.fullName(cassandraEntity.getFullName());
        user.avatar(cassandraEntity.getAvatar());
        user.gender(cassandraEntity.getGender());
        user.about(cassandraEntity.getAbout());
        user.jobDescription(cassandraEntity.getJobDescription());
        user.company(cassandraEntity.getCompany());
        user.school(cassandraEntity.getSchool());
        List<String> list = cassandraEntity.getPictures();
        if (list != null) {
            user.pictures(new ArrayList<String>(list));
        }
        List<Passion> list1 = cassandraEntity.getPassions();
        if (list1 != null) {
            user.passions(new ArrayList<Passion>(list1));
        }
        user.distance(cassandraEntity.getDistance());
        List<Gender> list2 = cassandraEntity.getGenderToShow();
        if (list2 != null) {
            user.genderToShow(new ArrayList<Gender>(list2));
        }
        user.yearOfBirth(cassandraEntity.getYearOfBirth());
        user.minAge(cassandraEntity.getMinAge());
        user.maxAge(cassandraEntity.getMaxAge());

        user.location(new GeoPoint(cassandraEntity.getLat(), cassandraEntity.getLon()));

        return user.build();
    }

    @Override
    public ProfileResponse toDto(com.bobvu.tinhong.elasticsearch.user.User user) {
        if (user == null) {
            return null;
        }




        ProfileResponse profileResponse = new ProfileResponse();

        profileResponse.setUsername(user.getUsername());
        profileResponse.setAvatar(user.getAvatar());
        profileResponse.setFullName(user.getFullName());

        List<String> list = user.getPictures();
        if (list != null) {
            profileResponse.setPictures(new ArrayList<String>(list));
        }
        profileResponse.setAbout(user.getAbout());
        profileResponse.setJobDescription(user.getJobDescription());
        profileResponse.setCompany(user.getCompany());
        profileResponse.setSchool(user.getSchool());
        profileResponse.setGender(user.getGender());
        List<Passion> list1 = user.getPassions();
        if (list1 != null) {
            profileResponse.setPassions(new ArrayList<Passion>(list1));
        }
        profileResponse.setDistance(user.getDistance());
        List<Gender> list2 = user.getGenderToShow();
        if (list2 != null) {
            profileResponse.setGenderToShow(new ArrayList<Gender>(list2));
        }
        profileResponse.setYearOfBirth(user.getYearOfBirth());
        profileResponse.setMinAge(user.getMinAge());
        profileResponse.setMaxAge(user.getMaxAge());

        if (user.getLocation() != null) {
            profileResponse.setLatitude(user.getLocation().getLat());
            profileResponse.setLongitude(user.getLocation().getLon());
        }


        return profileResponse;
    }

}
