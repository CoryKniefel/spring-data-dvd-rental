package com.fun.springdatadvdrental.domain.store;

import com.fun.springdatadvdrental.domain.address.Address;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "store_id")
    private long storeId;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "manager_staff_id")
    private long staffId;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

}
