package com.bobvu.tinhong.cassandra.profile;

import com.bobvu.tinhong.cassandra.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserMapperImpl implements UserMapper{

    //@Override
    public ProfileResponse toDto(User entity) {
        if (entity == null) {
            return null;
        }

        ProfileResponse profileResponse = new ProfileResponse();

        profileResponse.setAbout(entity.getAbout());
        profileResponse.setJobDescription(entity.getJobDescription());
        profileResponse.setCompany(entity.getCompany());
        profileResponse.setSchool(entity.getSchool());
        profileResponse.setGender(entity.getGender());
        profileResponse.setPictures(entity.getPictures());
        profileResponse.setPassions(entity.getPassions());
        return profileResponse;
    }

    @Override
    public void update(SaveProfileRequest source, User target) {
        if ( source == null ) {
            return;
        }

        if ( source.getGender() != null ) {
            target.setGender( source.getGender() );
        }
        if ( target.getPictures() != null ) {
            List<String> list = source.getPictures();
            if ( list != null ) {
                target.getPictures().clear();
                target.getPictures().addAll( list );
            }
        }
        else {
            List<String> list = source.getPictures();
            if ( list != null ) {
                target.setPictures( new ArrayList<String>( list ) );
            }
        }
        if ( target.getPassions() != null ) {
            List<User.Passion> list1 = source.getPassions();
            if ( list1 != null ) {
                target.getPassions().clear();
                target.getPassions().addAll( list1 );
            }
        }
        else {
            List<User.Passion> list1 = source.getPassions();
            if ( list1 != null ) {
                target.setPassions( new ArrayList<User.Passion>( list1 ) );
            }
        }
        if ( source.getAbout() != null ) {
            target.setAbout( source.getAbout() );
        }
        if ( source.getJobDescription() != null ) {
            target.setJobDescription( source.getJobDescription() );
        }
        if ( source.getCompany() != null ) {
            target.setCompany( source.getCompany() );
        }
        if ( source.getSchool() != null ) {
            target.setSchool( source.getSchool() );
        }
        if(source.getLongitude() != null){
            target.setLongitude(source.getLongitude());
        }

        if(source.getLatitude() != null){
            target.setLatitude(source.getLatitude());
        }
    }


}
