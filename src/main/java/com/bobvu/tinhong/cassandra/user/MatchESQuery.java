package com.bobvu.tinhong.cassandra.user;

import lombok.Data;

@Data
public class MatchESQuery<T> {
    private T match;
}
