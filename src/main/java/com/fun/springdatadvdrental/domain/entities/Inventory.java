package com.fun.springdatadvdrental.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inventory_id")
    private long id;
}
