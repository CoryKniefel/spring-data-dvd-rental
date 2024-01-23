package com.fun.springdatadvdrental.domain.address;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

/**
 *  DTO of Address for creating new Customer entities where the client knows and passes the ID for City and Country;
 */
@Builder
@Jacksonized
@Getter
public class AddressCreateDTO {


    @NotEmpty(message = "Address1 must not be empty")
    @Size(min = 1, max = 50, message = "Address1 must be between 1 and 50")
    public final String address1;

    @Size(max = 50, message = "Address2 must be less than 50")
    public final String address2;

    @NotEmpty(message = "District (or State) must not be empty")
    @Size(min = 1, max = 20, message = "District (or State) must be between 1 and 20")
    public final String district;

    @Min(value = 1, message = "CityID is required to be greater than 0")
    @NotNull(message = "CityID is required")
    public final Long cityId;

    @Min(value = 1, message = "CountryID is required to be greater than 0")
    @NotNull(message = "CountryID is required")
    public final Long countryId;

    @Size(min = 1, max = 10, message = "Postal Code must be between 1 and 10")
    @NotNull(message = "Postal code is required")
    public final String postalCode;

    @NotEmpty(message = "Phone number must not be empty")
    @Size(min = 1, max = 20, message = "Phone number must be between 1 and 20")
    public final String phone;



}
