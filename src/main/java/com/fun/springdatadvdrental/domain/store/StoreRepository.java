package com.fun.springdatadvdrental.domain.store;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StoreRepository extends PagingAndSortingRepository<Store, Long>, CrudRepository<Store, Long> {
}
