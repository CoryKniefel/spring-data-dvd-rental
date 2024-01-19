package com.fun.springdatadvdrental.domain.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerDTOMapper {


    public CustomerDTO toDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.email)
                .build();
    }

    public Customer fromDto(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.id);
        customer.setFirstName(customerDTO.firstName);
        customer.setLastName(customerDTO.lastName);
        customer.setEmail(customerDTO.email);

        return customer;
    }
}
