package com.fun.springdatadvdrental.domain.payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long>, CrudRepository<Payment, Long> {
}
