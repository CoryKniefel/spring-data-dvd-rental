package com.fun.springdatadvdrental.domain.store;

import com.fun.springdatadvdrental.domain.address.AddressDTO;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

/**
 *  DTO of Customer for creating new Customer entities.
 */
@Builder
@Jacksonized
public class StoreDTO {

    public long storeId;

    public AddressDTO address;
}
