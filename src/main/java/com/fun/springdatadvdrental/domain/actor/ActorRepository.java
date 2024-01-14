package com.fun.springdatadvdrental.domain.actor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActorRepository extends PagingAndSortingRepository<Actor, Long>, CrudRepository<Actor, Long> {
}
