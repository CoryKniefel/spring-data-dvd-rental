package com.fun.springdatadvdrental.domain.customer;

import com.fun.springdatadvdrental.testutils.DataGeneration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerDTOMapperTest {

    private CustomerDTOMapper customerDTOMapper = new CustomerDTOMapper();

    private DataGeneration dataGen = new DataGeneration();


    @Test
    void testToDTO() {
        Customer customer = new Customer();
        customer.setFirstName("Jay");
        customer.setLastName("Z");
        customer.setEmail("jay.z@tidal.com");

        CustomerDTO dto = customerDTOMapper.toDTO(customer);

        assertEquals(customer.getFirstName(), dto.firstName);
        assertEquals(customer.getLastName(), dto.lastName);
        assertEquals(customer.getEmail(), dto.email);
    }

    @Test
    void testToDTOFull() {
        Customer customer = new Customer();
        customer.setFirstName("Jay");
        customer.setLastName("Z");
        customer.setEmail("jay.z@tidal.com");
        customer.setAddress(dataGen.createAddress());
        customer.setStore(dataGen.createStore());

        CustomerFullDTO dto = customerDTOMapper.toDTOFull(customer);

        assertEquals(customer.getFirstName(), dto.firstName);
        assertEquals(customer.getLastName(), dto.lastName);
        assertEquals(customer.getEmail(), dto.email);
    }

    @Test
    void testFromDto() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(444l)
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
