package com.fun.springdatadvdrental.domain.customer;

import com.fun.springdatadvdrental.domain.address.Address;
import com.fun.springdatadvdrental.domain.address.AddressRepository;
import com.fun.springdatadvdrental.domain.address.City;
import com.fun.springdatadvdrental.domain.address.CityRepository;
import com.fun.springdatadvdrental.domain.store.Store;
import com.fun.springdatadvdrental.domain.store.StoreRepository;
import com.fun.springdatadvdrental.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;
    private final CustomerDTOMapper customerDTOMapper;


    public Page<CustomerDTO> getCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(customerDTOMapper::toDTO);
    }

    public Optional<CustomerDTO> getCustomerDTOById(long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        return customer.map(customerDTOMapper::toDTO);
    }

    /**
     * Creates a new Customer based on the provided DTO.
     *
     * @param customerDTO The DTO containing the customer's information. Must not be null.
     * @return CustomerFullDTO containing the details of the newly created customer, including related entities like Store and Address.
     * @throws EntityNotFoundException if the specified store or city does not exist.
     */
    @Transactional
    public CustomerFullDTO createCustomer(@Valid @NotNull CustomerCreateDTO customerDTO) {
        Store store = storeRepository.findById(customerDTO.storeId).orElseThrow(() -> new EntityNotFoundException("Store", customerDTO.storeId));
        City city = cityRepository.findById(customerDTO.address.cityId).orElseThrow(() -> new EntityNotFoundException("City", customerDTO.address.cityId));

        Customer customer = new Customer();
        customer.setFirstName(customerDTO.firstName);
        customer.setLastName(customerDTO.lastName);
        customer.setEmail(customerDTO.email);

        Address address = Address.builder()
                .address1(customerDTO.address.address1)
                .address2(customerDTO.address.address2)
                .district(customerDTO.address.district)
                .city(city)
                .postalCode(customerDTO.address.postalCode)
                .phone(customerDTO.address.phone)
                .build();

        address = addressRepository.save(address);


        customer.setStore(store);
        customer.setAddress(address);

        Customer result = customerRepository.save(customer);

        return customerDTOMapper.toDTOFull(result);
    }
}
