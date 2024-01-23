package com.fun.springdatadvdrental.domain.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fun.springdatadvdrental.domain.address.AddressCreateDTO;
import com.fun.springdatadvdrental.domain.address.AddressDTOMapper;
import com.fun.springdatadvdrental.domain.store.StoreDTOMapper;
import com.fun.springdatadvdrental.testutils.DataGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    DataGeneration dataGeneration = new DataGeneration();

    AddressDTOMapper addressDTOMapper = new AddressDTOMapper();

    StoreDTOMapper storeDTOMapper = new StoreDTOMapper();

    @MockBean
    private CustomerService customerService;

    @Test
    public void testGetCustomers() throws Exception {

        CustomerDTO customerDTO = dataGeneration.createCustomerDTO();

        List<CustomerDTO> customerList = List.of(customerDTO);

        Page<CustomerDTO> customerPage = new PageImpl<>(customerList, Pageable.ofSize(1), 1);

        when(customerService.getCustomers(any(Pageable.class)))
                .thenReturn(customerPage);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(customerDTO.id.intValue())))
                .andExpect(jsonPath("$.content[0].firstName", is(customerDTO.firstName)))
                .andExpect(jsonPath("$.content[0].lastName", is(customerDTO.lastName)))
                .andExpect(jsonPath("$.content[0].email", is(customerDTO.email)));
    }

    @Test
    public void testGetCustomerById() throws Exception {

        CustomerDTO customerDTO = dataGeneration.createCustomerDTO();

        when(customerService.getCustomerDTOById(1))
                .thenReturn(Optional.of(customerDTO));

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is(customerDTO.firstName)))
                .andExpect(jsonPath("$.lastName", is(customerDTO.lastName)))
                .andExpect(jsonPath("$.email", is(customerDTO.email)));
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

    @Test
    public void testCreatValidCustomer() throws Exception {
        CustomerCreateDTO customerCreateDTO = dataGeneration.customerCreateDTO();
        CustomerFullDTO customerFullDTO = CustomerFullDTO
                .builder().firstName(customerCreateDTO.firstName).lastName(customerCreateDTO.lastName)
                .email(customerCreateDTO.email)
                .address(addressDTOMapper.fromAddress(dataGeneration.createAddress()))
                .store(storeDTOMapper.fromStore(dataGeneration.createStore()))
                .build();

        when(customerService.createCustomer(any(CustomerCreateDTO.class))).thenReturn(customerFullDTO);

        String customerDtoJson = objectMapper.writeValueAsString(customerCreateDTO);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").exists());

    }

    @Test
    public void testCreatInvalidCustomer() throws Exception {
        CustomerDTO invalidCustomerDTO = CustomerDTO.builder().firstName("A").email("a.com").build();

        String customerDtoJson = objectMapper.writeValueAsString(invalidCustomerDTO);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstName", is("First name must be at least 2 characters and at most 20")))
                .andExpect(jsonPath("$.lastName", is("Last name must not be empty")))
                .andExpect(jsonPath("$.address", is("Address is required")))
                .andExpect(jsonPath("$.email", is("Email must be a valid email")));

    }

    @Test
    public void testCreatInvalidCustomerInvalidAddress() throws Exception {
        AddressCreateDTO addressCreateDTO = AddressCreateDTO
                .builder()
                .address1("")
                .address2("abcdefghijklmnopqrstuvwxyz-abcdefghijklmnopqrstovwxyz") // > 50 chars
                .district("New York New York New York ") // 27
                .cityId(0L) // < 1
                .postalCode("12345678910") // 11
                .countryId(0L) // < 1
                .phone("555-555-5555 555-555-5555") // 24
                .build();

        CustomerCreateDTO customerCreateDTO = CustomerCreateDTO
                .builder()
                .firstName("Marshal")
                .lastName("Mathers")
                .email("m.m@aftermath.com")
                .storeId(1L)
                .address(addressCreateDTO)
                .build();


        String customerDtoJson = objectMapper.writeValueAsString(customerCreateDTO);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['address.address1']", is(anyOf(
                        is("Address1 must not be empty; Address1 must be between 1 and 50"), // order of the errors is unpredictable, so allow for either possibility
                        is("Address1 must be between 1 and 50; Address1 must not be empty")))))
                .andExpect(jsonPath("$['address.address2']", is("Address2 must be less than 50")))
                .andExpect(jsonPath("$['address.district']", is("District (or State) must be between 1 and 20")))
                .andExpect(jsonPath("$['address.cityId']", is("CityID is required to be greater than 0")))
                .andExpect(jsonPath("$['address.countryId']", is("CountryID is required to be greater than 0")))
                .andExpect(jsonPath("$['address.postalCode']", is("Postal Code must be between 1 and 10")))
                .andExpect(jsonPath("$['address.phone']", is("Phone number must be between 1 and 20")));

    }

}
