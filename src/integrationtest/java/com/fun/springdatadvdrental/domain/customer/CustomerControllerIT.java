package com.fun.springdatadvdrental.domain.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fun.springdatadvdrental.domain.address.AddressRepository;
import com.fun.springdatadvdrental.domain.address.City;
import com.fun.springdatadvdrental.domain.address.CityRepository;
import com.fun.springdatadvdrental.testutils.DataGeneration;
import com.fun.springdatadvdrental.testutils.ITUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    DataGeneration dataGeneration;

    @Autowired
    private ITUtils itUtils;

    private Customer customer;


    @BeforeEach
    public void setup() {
        customer = new Customer();
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
                .andExpect(jsonPath("$.content[0].id", is(customer.getId().intValue())))
                .andExpect(jsonPath("$.content[0].firstName", is(customer.getFirstName())))
                .andExpect(jsonPath("$.content[0].lastName", is(customer.getLastName())))
                .andExpect(jsonPath("$.content[0].email", is(customer.getEmail())));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        mockMvc.perform(get("/customers/{id}", customer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(customer.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customer.getLastName())))
                .andExpect(jsonPath("$.email", is(customer.getEmail())));
    }

    @Test
    public void testGetCustomerByIdNotFound() throws Exception {
        mockMvc.perform(get("/customers/{id}", -1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateValidCustomer() throws Exception {
        itUtils.initialize();
        CustomerCreateDTO customerCreateDTO = dataGeneration.customerCreateDTO();
        City expectedCity = cityRepository.findById(customerCreateDTO.address.cityId).orElseThrow(() -> new RuntimeException("Failing to find expected city"));


        String customerJson = objectMapper.writeValueAsString(customerCreateDTO);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName", is(customerCreateDTO.firstName)))
                .andExpect(jsonPath("$.lastName", is(customerCreateDTO.lastName)))
                .andExpect(jsonPath("$.address.address1", is(customerCreateDTO.address.address1)))
                .andExpect(jsonPath("$.address.address2", is(customerCreateDTO.address.address2)))
                .andExpect(jsonPath("$.address.city", is(expectedCity.getCityName())))
                .andExpect(jsonPath("$.address.country", is(expectedCity.getCountry().getCountryName())));


    }

    @AfterEach
    public void cleanup() {
        customerRepository.deleteAll();
    }

}

