package com.fun.springdatadvdrental.testutils;

import com.fun.springdatadvdrental.domain.address.*;
import com.fun.springdatadvdrental.domain.store.Store;
import com.fun.springdatadvdrental.domain.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ITUtils {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    StoreRepository storeRepository;

    private boolean initialized = false;

    public void initialize() {

        if(!initialized) {
            createCountry();
            createTestCities();
            createStore();
            initialized = true;
        }
    }

    public Address saveAddress(AddressCreateDTO addressDTO){
        Address result = Address
                .builder()
                .id(getNextAddressId())
                .address1(addressDTO.address1)
                .address2(addressDTO.address2)
                .city(cityRepository.findById(addressDTO.cityId).orElseThrow(() -> new RuntimeException("Failing to create Test Address")))
                .district(addressDTO.district)
                .phone(addressDTO.phone)
                .postalCode(addressDTO.postalCode)
                .build();

        result = addressRepository.save(result);


        return result;
    }


    /**
     * The test database doesn't have the sequences so we need to look up the next available
     * @return
     */
    public long getNextAddressId() {
        return addressRepository.count() + 1;

    }

    private void createTestCities() {
        Country usa = countryRepository.findById(1l).orElseThrow(() -> new RuntimeException("Failing to create Test Cities"));
        City nyc = new City(1, "New York City", LocalDateTime.now(), usa);
        City portland = new City(2, "Portland", LocalDateTime.now(), usa);
        cityRepository.save(nyc);
        cityRepository.save(portland);
    }

    private void createCountry(){
        Country usa = new Country(1, "United States", LocalDateTime.now());
        countryRepository.save(usa);

    }


    private void createStore() {
        Address storeAddress = Address.builder().id(3)
                .address1("101 TV HWY")
                .phone("555-521-4369")
                .district("Oregon")
                .city(cityRepository.findById(2l).orElseThrow(() -> new RuntimeException("Failing to create Test Store")))
                .postalCode("9999999")
                .lastUpdate(LocalDateTime.now())
                .build();
        addressRepository.save(storeAddress);


        Store store = Store.builder().storeId(1l).staffId(1L).address(storeAddress).lastUpdate(LocalDateTime.now()).build();

        storeRepository.save(store);
    }
}
