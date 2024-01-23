package com.fun.springdatadvdrental.domain.customer;

import com.fun.springdatadvdrental.domain.address.AddressCreateDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

/**
 *  DTO of Customer for creating new Customer entities.
 */
@Builder
@Jacksonized
@Getter
public class CustomerCreateDTO {

    @NotEmpty(message = "First name must not be empty")
    @Size(min = 2, max = 20, message = "First name must be at least 2 characters and at most 20")
    public final String firstName;

    @NotEmpty(message = "Last name must not be empty")
    @Size(min = 2, max = 20, message = "First name must be at least 2 characters and at most 20")
    public final String lastName;

    @Email(message = "Email must be a valid email")
    public final String email;

    @NotNull(message = "Address is required")
    @Valid
    public final AddressCreateDTO address;

    @Min(value = 1, message = "StoreId must not be empty and at least 1")
    @Max(value = 500, message = "StoreId must be less than 500")
    public final Long storeId;
}
