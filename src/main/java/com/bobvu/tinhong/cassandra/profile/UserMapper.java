package com.bobvu.tinhong.cassandra.profile;

import com.bobvu.tinhong.cassandra.user.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

//@Mapper
public interface UserMapper {
    ProfileResponse toDto(User entity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update( SaveProfileRequest source, @MappingTarget User target);

    com.bobvu.tinhong.elasticsearch.user.User toESEntity(User cassandraEntity);

    ProfileResponse toDto(com.bobvu.tinhong.elasticsearch.user.User user);
}
