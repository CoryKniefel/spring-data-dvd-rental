package com.fun.springdatadvdrental.domain.customer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private static CustomerDTO mockCustomerDTO;

    @BeforeAll
    public static void setup() {
        mockCustomerDTO = CustomerDTO
                .builder()
                .id(1)
                .firstName("Jay")
                .lastName("Z")
                .email("jay.z@tidal.com")
                .build();
    }


    @Test
    public void testGetCustomers() throws Exception {

        List<CustomerDTO> customerList = List.of(mockCustomerDTO);

        Page<CustomerDTO> customerPage = new PageImpl<>(customerList, Pageable.ofSize(1), 1);

        when(customerService.getCustomers(any(Pageable.class)))
                .thenReturn(customerPage);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is((int) mockCustomerDTO.id)))
                .andExpect(jsonPath("$.content[0].firstName", is(mockCustomerDTO.firstName)))
                .andExpect(jsonPath("$.content[0].lastName", is(mockCustomerDTO.lastName)))
                .andExpect(jsonPath("$.content[0].email", is(mockCustomerDTO.email)));
    }

    @Test
    public void testGetCustomerById() throws Exception {

        when(customerService.getCustomerDTOById(1))
                .thenReturn(Optional.of(mockCustomerDTO));

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is(mockCustomerDTO.firstName)))
                .andExpect(jsonPath("$.lastName", is(mockCustomerDTO.lastName)))
                .andExpect(jsonPath("$.email", is(mockCustomerDTO.email)));
    }

    @Test
    public void testGetCustomerByIdNotFound() throws Exception {
        when(customerService.getCustomerDTOById(2))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/customers/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCustomerByIdBadRequest() throws Exception {
        when(customerService.getCustomerDTOById(-1))
                .thenThrow(new RuntimeException());

        mockMvc.perform(get("/customers/-1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Unexpected error occurred")));
    }
}
