package com.bobvu.tinhong.cassandra.user;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Passion {
    ISFP, Blogging, Potterhead, DIY, Foodie, INFP, V_Pop, StreetFood, Trying_New_Thinks, Netflix, Board_Games, Outdoors, Taurus, Astrology, Golf, Climbing, Hiphop;

    private static final List<Passion> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Passion getRandom() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}