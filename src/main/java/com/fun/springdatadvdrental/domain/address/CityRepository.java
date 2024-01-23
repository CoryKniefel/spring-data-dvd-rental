package com.fun.springdatadvdrental.domain.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityRepository extends PagingAndSortingRepository<City, Long>, CrudRepository<City, Long> {
}
