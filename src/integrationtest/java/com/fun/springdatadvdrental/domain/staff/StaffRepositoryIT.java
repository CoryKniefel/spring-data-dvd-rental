package com.fun.springdatadvdrental.domain.staff;

import com.fun.springdatadvdrental.domain.entities.Staff;
import com.fun.springdatadvdrental.domain.entities.StaffRepository;
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
public class StaffRepositoryIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StaffRepository staffRepository;

    @BeforeEach
    public void setup() {
        staffRepository.save(new Staff());

    }

    @Test
    public void testGetStaff() throws Exception {
        mockMvc.perform(get("/staffs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.staffs", hasSize(1)));
    }
}
