package com.fun.springdatadvdrental.domain.customer;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDTO {
    public final long id;
    public final String firstName;
    public final String lastName;
    public final String email;
}
