package com.fun.springdatadvdrental.domain.inventory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long>, CrudRepository<Inventory, Long> {
}
