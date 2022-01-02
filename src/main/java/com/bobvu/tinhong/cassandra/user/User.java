package com.bobvu.tinhong.cassandra.user;


import com.bobvu.tinhong.cassandra.audit.Auditable;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import org.springframework.data.domain.Persistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@Table

public class User extends Auditable implements UserDetails, Persistable {
    @Id
    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String username;
    private String password;
    private String fullName;
    private String avatar;
    private Gender gender;

    private Location locations;

    private List<Passion> passions;
    @CassandraType(type = CassandraType.Name.LIST, typeArguments = CassandraType.Name.TEXT)
    private List<String> media;

    private boolean distanceInvisible;

    private List<String> roles;

    private UUID socketId;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getId() {
        return this.username;
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }


    enum Gender{
        MALE, FEMALE
    }
    enum Passion{
        ISFP, Blogging, Potterhead, DIY, Foodie, INFP, V_Pop, StreetFood, Trying_New_Thinks, Netflix, Board_Games, Outdoors, Taurus, Astrology, Golf, Climbing, Hiphop;
    }

    @Data
    @UserDefinedType
    public static class Location{
        private float lat;
        private float lon;
    }


}






