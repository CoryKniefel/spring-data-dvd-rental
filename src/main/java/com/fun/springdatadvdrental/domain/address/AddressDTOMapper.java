package com.fun.springdatadvdrental.domain.address;

import org.springframework.stereotype.Service;

@Service
public class AddressDTOMapper {

    public AddressDTO fromAddress(Address address) {
        return AddressDTO
                .builder()
                .address1(address.getAddress1())
                .address2(address.getAddress2())
                .city(address.getCity().getCityName())
                .district(address.getDistrict())
                .country(address.getCity().getCountry().getCountryName())
                .phone(address.getPhone())
                .phone(address.getPhone())
                .postalCode(address.getPostalCode())
                .build();
    }

    /**
     * Method not implemented
     * Reason: City repository would be required in order to get the City and Country objects
     * which is beyond the scope of a mapper class.
     * @throws UnsupportedOperationException because this method is not supported
     */
    public Address fromAddressDTO(AddressDTO addressDTO) {

        throw new UnsupportedOperationException("Conversion from AddressDTO to Address is not supported.");

    }
}
