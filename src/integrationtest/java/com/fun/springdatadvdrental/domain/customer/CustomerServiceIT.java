package com.fun.springdatadvdrental.domain.customer;

import com.fun.springdatadvdrental.testutils.DataGeneration;
import com.fun.springdatadvdrental.testutils.ITUtils;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class CustomerServiceIT {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DataGeneration dataGeneration;

    @Autowired
    private ITUtils itUtils;

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

    @Test
    public void testSaveCustomerValid() {
        itUtils.initialize();
        CustomerCreateDTO customerCreateDTO = dataGeneration.customerCreateDTO();

        CustomerFullDTO result = customerService.createCustomer(customerCreateDTO);

        assertEquals(customerCreateDTO.firstName, result.firstName);
        assertEquals(customerCreateDTO.lastName, result.lastName);
        assertEquals(customerCreateDTO.email, result.email);
        assertTrue(result.id > -1);


    }

    @Test
    public void testSaveCustomerInvalid() {
        CustomerCreateDTO customerCreateDTONotValid = CustomerCreateDTO
                .builder()
                .firstName("")
                .lastName("")
                .email("m.m@aftermath.com")
                .storeId(1l)
                .address(null)
                .build();;

        Exception result = assertThrows(ConstraintViolationException.class, () -> customerService.createCustomer(customerCreateDTONotValid));



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

    @AfterEach
    public void cleanup() {
        customerRepository.deleteAll();
    }



}
