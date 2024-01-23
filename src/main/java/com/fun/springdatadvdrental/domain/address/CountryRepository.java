package com.fun.springdatadvdrental.domain.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends PagingAndSortingRepository<Country, Long>, CrudRepository<Country, Long> {
}
