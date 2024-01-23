package com.fun.springdatadvdrental.domain.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long>, CrudRepository<Address, Long> {
}
