package com.spiderweb.fraud.service;

import com.spiderweb.fraud.model.FraudCheckHistory;
import com.spiderweb.fraud.repository.FraudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudService {

    private final FraudRepository fraudRepository;

    public FraudService(FraudRepository fraudRepository) {
        this.fraudRepository = fraudRepository;
    }

    public Boolean checkFraudster(Integer customerId) {
        FraudCheckHistory fraudCheckHistory = fraudRepository.findByCustomerId(customerId);
        if (fraudCheckHistory == null){
            fraudRepository.save(FraudCheckHistory.builder()
                    .isFraudster(false)
                    .lastChecked(LocalDateTime.now())
                    .customerId(customerId).build());
            return false;
//            throw new IllegalArgumentException("The CustomerId doesn't exit");
        }

        return fraudCheckHistory.getIsFraudster();
    }
}
