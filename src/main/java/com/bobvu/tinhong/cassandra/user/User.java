package com.bobvu.tinhong.cassandra.user;


import com.bobvu.tinhong.cassandra.audit.Auditable;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.domain.Persistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

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


    // descripton
    private String about;
    private String jobDescription;
    private String company;
    private String school;


    private List<String> pictures;
    private List<Passion> passions;

    private double lat;
    private double lon;
    private int distance;

    private List<Gender> genderToShow;
    private int yearOfBirth;
    private int minAge;
    private int maxAge;


    @CassandraType(type = CassandraType.Name.LIST, typeArguments = CassandraType.Name.TEXT)
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


}






