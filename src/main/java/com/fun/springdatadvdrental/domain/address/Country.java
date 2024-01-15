package com.fun.springdatadvdrental.domain.address;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id")
    private long id;

    @Column(name = "country")
    private String countryName;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
