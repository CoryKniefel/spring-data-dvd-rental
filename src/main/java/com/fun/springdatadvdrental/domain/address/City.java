package com.fun.springdatadvdrental.domain.address;

import com.fun.springdatadvdrental.domain.store.Store;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id")
    private long id;

    @Column(name = "city")
    private String cityName;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}
