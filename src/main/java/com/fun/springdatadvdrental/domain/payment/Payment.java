package com.fun.springdatadvdrental.domain.payment;

import com.fun.springdatadvdrental.domain.customer.Customer;
import com.fun.springdatadvdrental.domain.staff.Staff;
import com.fun.springdatadvdrental.domain.rental.Rental;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    private LocalDateTime timestamp;
}
