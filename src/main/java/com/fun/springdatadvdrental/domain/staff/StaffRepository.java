package com.fun.springdatadvdrental.domain.staff;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StaffRepository extends PagingAndSortingRepository<Staff, Long>, CrudRepository<Staff, Long> {
}
