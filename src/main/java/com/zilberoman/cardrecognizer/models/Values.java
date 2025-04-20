package com.zilberoman.cardrecognizer.models;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Values {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private static final Map<String, Values> MAP = Arrays.stream(values())
            .collect(Collectors.toMap(value -> value.name().toLowerCase(), Function.identity()));

    private final String value;

    Values(String value) {
        this.value = value;
    }

    public static String lookupValue(String name) {
        Values value = MAP.get(name);
        return value != null ? value.value : null;
    }
}
