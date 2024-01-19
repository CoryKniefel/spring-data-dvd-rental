package com.fun.springdatadvdrental.domain.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerDTOPageSerializationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testPageSerialization() throws Exception {
        CustomerDTO mockCustomerDTO = CustomerDTO
                .builder()
                .id(1)
                .firstName("Jay")
                .lastName("Z")
                .build();

        List<CustomerDTO> customerList = List.of(mockCustomerDTO);
                Page<CustomerDTO> customerPage = new PageImpl<>(customerList, Pageable.ofSize(1), 1);

        String json = objectMapper.writeValueAsString(customerPage);
        assertNotNull(json);
        assertTrue(json.contains("Jay"));
    }
}
