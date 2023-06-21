package com.spiderweb.fraud.controller;

import com.spiderweb.fraud.dto.FraudCheckResponse;
import com.spiderweb.fraud.service.FraudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud")
@Slf4j
public class FraudController {

    private final FraudService fraudService;

    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @GetMapping("{customerId}")
    public FraudCheckResponse checkFraudster(@PathVariable("customerId") Integer customerId){
        log.info("fraud check for CustomerId #{}",customerId);
        Boolean isFraudster = fraudService.checkFraudster(customerId);

        return new FraudCheckResponse(isFraudster);
    }
}
