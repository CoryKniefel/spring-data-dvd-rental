package com.fun.springdatadvdrental.domain.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer();
        customer.setId(444);
        customer.setFirstName("Jay");
        customer.setLastName("Z");
        customer.setEmail("jay.z@tidal.com");

        customer = customerRepository.save(customer);

    }

    @Test
    public void testGetCustomers() throws Exception {

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is((int) customer.getId())))
                .andExpect(jsonPath("$.content[0].firstName", is(customer.getFirstName())))
                .andExpect(jsonPath("$.content[0].lastName", is(customer.getLastName())))
                .andExpect(jsonPath("$.content[0].email", is(customer.getEmail())));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        mockMvc.perform(get("/customers/{id}", customer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) customer.getId())))
                .andExpect(jsonPath("$.firstName", is(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customer.getLastName())))
                .andExpect(jsonPath("$.email", is(customer.getEmail())));
    }

    @Test
    public void testGetCustomerByIdNotFound() throws Exception {
        mockMvc.perform(get("/customers/{id}", -1))
                .andExpect(status().isNotFound());
    }

    @AfterEach
    public void cleanup() {
        customerRepository.deleteAll();
    }

}

