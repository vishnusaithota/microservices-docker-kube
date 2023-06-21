package com.spiderweb.customer.service;

import com.spiderweb.customer.dto.CustomerRequest;
import com.spiderweb.customer.model.Customer;
import com.spiderweb.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();

//        check if email is valid
//        check if email is not taken
        customerRepository.save(customer);
    }
}
