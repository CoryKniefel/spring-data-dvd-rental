package com.fun.springdatadvdrental.domain.film;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FilmRepository extends PagingAndSortingRepository<Film, Long>, CrudRepository<Film, Long> {
}
