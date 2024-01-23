package com.fun.springdatadvdrental.domain.address;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO of Address for CustomerFullDTO
 */
@Builder
@Jacksonized
public class AddressDTO {


    public final String address1;

    public final String address2;

    public final String district;

    public final String city;

    public final String country;

    public final String postalCode;

    public final String phone;
}
