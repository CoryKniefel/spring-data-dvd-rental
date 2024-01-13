package com.fun.springdatadvdrental.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "staff_id")
    private long id;
}
