package com.fun.springdatadvdrental.domain.customer;

import com.fun.springdatadvdrental.domain.address.AddressDTOMapper;
import com.fun.springdatadvdrental.domain.store.StoreDTOMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerDTOMapper {

    AddressDTOMapper addressDTOMapper = new AddressDTOMapper();
    StoreDTOMapper storeDTOMapper = new StoreDTOMapper();


    public CustomerDTO toDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.email)
                .build();
    }

    /**
     * For non-lazily loaded Customers when the Address and Store are present in the Customer object
     * @param customer the Customer to convert
     * @return CustomerDTO with Address and Store set
     */
    public CustomerFullDTO toDTOFull(Customer customer) {
        return CustomerFullDTO.builder()
                .id(customer.getId())
                .address(addressDTOMapper.fromAddress(customer.getAddress()))
                .store(storeDTOMapper.fromStore(customer.getStore()))
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.email)
                .build();
    }

    public Customer fromDto(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.id);
        customer.setFirstName(customerDTO.firstName);
        customer.setLastName(customerDTO.lastName);
        customer.setEmail(customerDTO.email);

        return customer;
    }

    public Customer fromFullDto(CustomerFullDTO customerDTO) {
       // Store and Address repository would be required to implement this call
        // and I'm not sure that is worth it
        throw new RuntimeException("Method not implemented.");

    }

}
