package com.fun.springdatadvdrental.domain.customer;


import com.fun.springdatadvdrental.domain.address.AddressDTO;
import com.fun.springdatadvdrental.domain.store.StoreDTO;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

/**
 * Full DTO of Customer entity.
 */
@Builder
@Jacksonized
public class CustomerFullDTO {

    public final Long id;

    public final StoreDTO store;

    public final AddressDTO address;

    public final String firstName;

    public final String lastName;

    public final String email;
}
