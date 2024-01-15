package com.fun.springdatadvdrental.domain.language;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "language_id")
    private long id;

    private String name;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
