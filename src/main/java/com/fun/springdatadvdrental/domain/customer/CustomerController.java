package com.fun.springdatadvdrental.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor(staticName = "of")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public Page<CustomerDTO> listCustomers(Pageable pageable) {
        Page<CustomerDTO> customers = customerService.getCustomers(pageable);

        return customers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable("id") long id) {
        return customerService.getCustomerDTOById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }


}
