package com.spiderweb.customer.service;


import com.spiderweb.clients.fraud.FraudCheckResponse;
import com.spiderweb.clients.fraud.FraudClient;
import com.spiderweb.clients.notification.NotificationClient;
import com.spiderweb.clients.notification.NotificationRequest;
import com.spiderweb.customer.dto.CustomerRequest;
import com.spiderweb.customer.exception.EmailTakenException;
import com.spiderweb.customer.model.Customer;
import com.spiderweb.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final RestTemplate restTemplate;

    private final FraudClient fraudClient;

    private final NotificationClient notificationClient;

    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate, FraudClient fraudClient, NotificationClient notificationClient) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
        this.fraudClient = fraudClient;
        this.notificationClient = notificationClient;
    }

    @Transactional
    public void registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();

        checkIfEmailIsTaken(customer.getEmail());

        customerRepository.saveAndFlush(customer);
//        Check if fraudster using restTemplate
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud/{customerId}",
//                FraudCheckResponse.class, customer.getId()
//        );

        FraudCheckResponse fraudCheckResponse =
                fraudClient.checkFraudster(customer.getId());

//        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }else {
           buildAndSendNotification(customer);
        }

    }

    private void buildAndSendNotification(Customer customer) {
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "Thanks For Registering"
        );

        notificationClient.sendNotification(notificationRequest);
    }

    private void checkIfEmailIsTaken(String email) {
        Optional<Customer> customerOptional = customerRepository
                .findByEmail(email);

        if (customerOptional.isPresent()) {
            throw new EmailTakenException("The email is already Present");
        }
    }
}
