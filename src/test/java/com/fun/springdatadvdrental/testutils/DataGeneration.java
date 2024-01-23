package com.fun.springdatadvdrental.testutils;

import com.fun.springdatadvdrental.domain.address.Address;
import com.fun.springdatadvdrental.domain.address.AddressCreateDTO;
import com.fun.springdatadvdrental.domain.address.City;
import com.fun.springdatadvdrental.domain.address.Country;
import com.fun.springdatadvdrental.domain.customer.CustomerCreateDTO;
import com.fun.springdatadvdrental.domain.customer.CustomerDTO;
import com.fun.springdatadvdrental.domain.store.Store;
import org.springframework.stereotype.Service;

@Service
public class DataGeneration {

    private Store averageStore;
    private Address averageAddress;
    private Country averageCountry;
    private City averageCity;

    public CustomerCreateDTO customerCreateDTO() {
        return CustomerCreateDTO
                .builder()
                .firstName("Marshal")
                .lastName("Mathers")
                .email("m.m@aftermath.com")
                .storeId(1L)
                .address(addressCreateDTO())
                .build();
    }

    public CustomerDTO createCustomerDTO() {
        return CustomerDTO
                .builder()
                .id(1L)
                .firstName("Jay")
                .lastName("Z")
                .email("jay.z@tidal.com")
                .build();
    }

    public AddressCreateDTO addressCreateDTO() {
        return AddressCreateDTO
                .builder()
                .address1("453 W 17th St")
                .phone("555-555-5555")
                .cityId(1L)
                .district("New York")
                .postalCode("10011")
                .countryId(1L)
                .build();
    }

    public Address createAddress() {

        if(averageAddress == null) {
            averageAddress = Address.builder().address1("453 W 17th St").phone("555-555-5555").city(createCity()).district("New York").postalCode("10011").build();
        }
        return averageAddress;

    }

    public Store createStore() {

        if (averageStore == null) {
            Address address = createAddress();
            averageStore = Store.builder().staffId(1).address(address).build();
        }

        return averageStore;
    }

    public Country createCountry() {
        if(averageCountry == null) {
            averageCountry = Country.builder().countryName("United States").id(1).build();
        }

        return averageCountry;
    }

    public City createCity(){
        if(averageCity == null) {
            averageCity  = City.builder().cityName("New York City").country(createCountry()).id(1).build();
        }
        return averageCity;
    }
}
