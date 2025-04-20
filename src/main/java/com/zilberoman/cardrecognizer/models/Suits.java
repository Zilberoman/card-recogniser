package com.zilberoman.cardrecognizer.models;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Suits {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;

    private static final Map<String, Character> MAP = Arrays.stream(values())
            .map(Enum::name)
            .map(String::toLowerCase)
            .collect(Collectors.toMap(Function.identity(), value -> value.charAt(0)));


    public static String lookupValue(String name) {
        return String.valueOf(MAP.get(name));
    }
}
