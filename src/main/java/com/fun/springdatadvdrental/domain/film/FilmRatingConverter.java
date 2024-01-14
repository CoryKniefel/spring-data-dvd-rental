package com.fun.springdatadvdrental.domain.film;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FilmRatingConverter implements AttributeConverter<FilmRating, String> {

    @Override
    public String convertToDatabaseColumn(FilmRating rating) {
        return rating != null ? rating.getValue() : null;
    }

    @Override
    public FilmRating convertToEntityAttribute(String dbData) {
        return dbData != null ? FilmRating.fromValue(dbData) : null;
    }
}

