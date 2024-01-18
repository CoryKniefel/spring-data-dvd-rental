package com.fun.springdatadvdrental.domain.customer;

import com.fun.springdatadvdrental.domain.address.Address;
import com.fun.springdatadvdrental.domain.store.Store;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public String email;

    private boolean activebool;

    private int active;

    @Column(name = "create_date")
    private LocalDate createdDate;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;







}
