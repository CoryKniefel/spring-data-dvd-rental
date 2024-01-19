package com.fun.springdatadvdrental.domain.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class CustomerServiceIT {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testGetCustomersWithPagination() throws Exception {
        List<Customer> customers = createXCustomers(15);
        customerRepository.saveAll(customers);

        Page<CustomerDTO> result = customerService.getCustomers(Pageable.ofSize(5));

        assertEquals(15, result.getTotalElements());
        assertEquals(3, result.getTotalPages());

    }

    @Test
    public void testGetCustomersWithExtraPagination() throws Exception {
        List<Customer> customers = createXCustomers(3);
        customerRepository.saveAll(customers);

        Page<CustomerDTO> result = customerService.getCustomers(Pageable.ofSize(5));

        assertEquals(1, result.getTotalPages());

    }

    @Test
    public void testGetCustomerDTOByIdExisting() {
        Iterable<Customer> customerIterable = customerRepository.saveAll(createXCustomers(1));
        Customer customer = customerIterable.iterator().next();
        Optional<CustomerDTO> customerDTOOptional = customerService.getCustomerDTOById(customer.getId());
        assertTrue(customerDTOOptional.isPresent());
        CustomerDTO customerDTO = customerDTOOptional.get();

        assertEquals(customer.getFirstName(), customerDTO.firstName);
        assertEquals(customer.getLastName(), customerDTO.lastName);
        assertEquals(customer.getEmail(), customerDTO.email);

    }

    @Test
    public void testGetCustomerDTOByIdNonExisting() {
        customerRepository.saveAll(createXCustomers(1));
        Optional<CustomerDTO> customerDTOOptional = customerService.getCustomerDTOById(500);
        assertTrue(customerDTOOptional.isEmpty());
    }


    private static List<Customer> createXCustomers(int amount) {
        List<Customer> result = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Customer customer = new Customer();
            customer.setFirstName("Jay-" + i);
            customer.setLastName("Z");
            customer.setEmail("jay.z@tidal.com");
            result.add(customer);
        }

        return result;

    }



}
