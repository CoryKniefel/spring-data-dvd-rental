package com.fun.springdatadvdrental.domain.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>, CrudRepository<Customer,Long> {

    List<Customer> findCustomerById(@Param("id") long id);


}
