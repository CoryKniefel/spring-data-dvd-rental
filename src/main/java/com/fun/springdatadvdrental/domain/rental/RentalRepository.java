package com.fun.springdatadvdrental.domain.rental;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RentalRepository extends PagingAndSortingRepository<Rental, Long>, CrudRepository<Rental,Long> {
}
