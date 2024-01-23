package com.fun.springdatadvdrental.domain.address;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressDTOMapperTest {

    private final AddressDTOMapper addressDTOMapper = new AddressDTOMapper();


    @Test
    public void fromAddressTest() {
        Address address = Address.builder()
                .address1("2455 Paces Ferry Road Northwest")
                .address2("address2")
                .city(City.builder()
                        .cityName("Atlanta")
                        .country(Country.builder()
                                .countryName("United States")
                                .build())
                        .build())
                .district("GA")
                .postalCode("30339")
                .phone("+17704338211")
                .build();

        AddressDTO addressDTO = addressDTOMapper.fromAddress(address);

        assertEquals(address.getAddress1(), addressDTO.address1);
        assertEquals(address.getAddress2(), addressDTO.address2);
        assertEquals(address.getCity().getCityName(), addressDTO.city);
        assertEquals(address.getCity().getCountry().getCountryName(), addressDTO.country);
        assertEquals(address.getDistrict(), addressDTO.district);
        assertEquals(address.getPostalCode(), addressDTO.postalCode);
        assertEquals(address.getPhone(), addressDTO.phone);


    }
}
