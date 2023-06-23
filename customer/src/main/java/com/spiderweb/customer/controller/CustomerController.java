package com.spiderweb.customer.controller;

import com.spiderweb.customer.dto.CustomerRequest;
import com.spiderweb.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/customers")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping()
    public void registerCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        log.info("new Customer registration {}", customerRequest);
        customerService.registerCustomer(customerRequest);
    }
}
