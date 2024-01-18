package com.fun.springdatadvdrental.domain.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerDTOMapperTest {

    private CustomerDTOMapper customerDTOMapper;

    @BeforeEach
    void setUp() {
        customerDTOMapper = new CustomerDTOMapper();
    }

    @Test
    void testToDTO() {
        Customer customer = new Customer();
        customer.setId(444);
        customer.setFirstName("Jay");
        customer.setLastName("Z");
        customer.setEmail("jay.z@tidal.com");

        CustomerDTO dto = customerDTOMapper.toDTO(customer);

        assertEquals(customer.getFirstName(), dto.firstName);
        assertEquals(customer.getLastName(), dto.lastName);
        assertEquals(customer.getEmail(), dto.email);
        assertEquals(customer.getId(), dto.id);
    }

    @Test
    void testFromDto() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(444)
                .firstName("Jay")
                .lastName("Z")
                .email("jay.z@tidal.com")
                .build();

        Customer customer = customerDTOMapper.fromDto(customerDTO);

        assertEquals(customerDTO.id, customer.getId());
        assertEquals(customerDTO.firstName, customer.getFirstName());
        assertEquals(customerDTO.lastName, customer.getLastName());
        assertEquals(customerDTO.email, customer.getEmail());
    }
}
