package com.fun.springdatadvdrental.domain.customer;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;


/**
 * Lightweight DTO of Customer entity. Takes advantage of lazily loaded entities, to avoid looking up Address and Store.
 */
@Builder
@Jacksonized
public class CustomerDTO {

    public final Long id;

    public final String firstName;

    public final String lastName;

    public final String email;
}
