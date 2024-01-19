package com.fun.springdatadvdrental.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(staticName = "of")
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDTOMapper customerDTOMapper;


    public Page<CustomerDTO> getCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(customerDTOMapper::toDTO);
    }

    public Optional<CustomerDTO> getCustomerDTOById(long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        return customer.map(customerDTOMapper::toDTO);


    }
}
