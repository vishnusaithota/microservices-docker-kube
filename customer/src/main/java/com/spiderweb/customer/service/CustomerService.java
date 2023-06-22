package com.spiderweb.customer.service;

import com.spiderweb.customer.dto.CustomerRequest;
import com.spiderweb.customer.exception.EmailTakenException;
import com.spiderweb.customer.model.Customer;
import com.spiderweb.customer.repository.CustomerRepository;
import com.spiderweb.customer.dto.FraudCheckResponse;
import com.spiderweb.customer.utils.CustomerUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final RestTemplate restTemplate;

    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public void registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();


//        check if email is valid
        CustomerUtils.validateFields(customer);
//        check if email is not taken
        checkIfEmailIsTaken(customer.getEmail());

        customerRepository.saveAndFlush(customer);
//        Check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud/{customerId}",
                FraudCheckResponse.class, customer.getId()
        );

        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

    }

    private void checkIfEmailIsTaken(String email) {
        Optional<Customer> customerOptional = customerRepository
                .findByEmail(email);

        if (customerOptional.isPresent()) {
            throw new EmailTakenException("The email is already Present");
        }
    }
}
