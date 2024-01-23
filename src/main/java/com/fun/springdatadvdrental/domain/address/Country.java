package com.fun.springdatadvdrental.domain.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
