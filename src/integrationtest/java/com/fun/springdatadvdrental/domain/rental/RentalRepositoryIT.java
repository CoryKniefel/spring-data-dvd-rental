package com.fun.springdatadvdrental.domain.rental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class RentalRepositoryIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RentalRepository rentalRepository;

    @BeforeEach
    public void setup() {
        Rental rental = new Rental();
        rentalRepository.save(rental);
    }

    @Test
    public void testGetCustomers() throws Exception {
        mockMvc.perform(get("/rentals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.rentals", hasSize(1)));
    }
}
