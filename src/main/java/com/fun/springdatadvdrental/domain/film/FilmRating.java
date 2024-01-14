package com.fun.springdatadvdrental.domain.film;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum FilmRating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String value;

    FilmRating(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static FilmRating fromValue(String value) {
        for (FilmRating rating : values()) {
            if (rating.value.equals(value)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value);
    }
}
