package com.fun.springdatadvdrental.domain.staff;

import com.fun.springdatadvdrental.domain.address.Address;
import com.fun.springdatadvdrental.domain.store.Store;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "staff_id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String LastName;

    private String email;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private boolean active;

    private String username;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    private byte[] picture;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


}
