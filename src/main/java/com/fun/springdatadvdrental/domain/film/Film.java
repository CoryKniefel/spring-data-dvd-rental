package com.fun.springdatadvdrental.domain.film;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Year;

@Entity
@Data
public class Film  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_id")
    private long id;

    private String title;

    private String description;

    @Column(name = "release_year")
    private Year releaseYear;

    @Column(name = "language_id")
    private int languageId;

    @Column(name = "rental_duration")
    private int rentalDuration;

    @Column(name = "rental_rate")
    private double rentalRate;

    private int length;

    @Column(name = "replacement_cost")
    private double replacementCost;

    @Convert(converter = FilmRatingConverter.class)
    private FilmRating rating;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    private String fulltext;

    @Column(name = "special_features")
    private String[] specialFeatures;


}
