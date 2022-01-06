package com.bobvu.tinhong.cassandra.profile;

import com.bobvu.tinhong.cassandra.user.User;
import org.mapstruct.Mapper;

//@Mapper
public interface UserMapper {
    ProfileResponse toDto(User entity);

}
