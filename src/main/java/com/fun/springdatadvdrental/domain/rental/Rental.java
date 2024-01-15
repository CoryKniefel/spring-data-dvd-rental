package com.fun.springdatadvdrental.domain.rental;

import com.fun.springdatadvdrental.domain.customer.Customer;
import com.fun.springdatadvdrental.domain.staff.Staff;
import com.fun.springdatadvdrental.domain.inventory.Inventory;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rental_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column(name = "rental_date")
    private LocalDate rentalDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

}
