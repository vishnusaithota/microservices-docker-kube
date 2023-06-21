package com.spiderweb.fraud.service;

import com.spiderweb.fraud.model.FraudCheckHistory;
import com.spiderweb.fraud.repository.FraudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FraudService {

    private final FraudRepository fraudRepository;

    public FraudService(FraudRepository fraudRepository) {
        this.fraudRepository = fraudRepository;
    }

    public Boolean checkFraudster(Integer customerId) {
        Optional<FraudCheckHistory> fraudCheckHistory = fraudRepository.findByCustomerId(customerId);
        Boolean isFraudster;
        isFraudster = fraudCheckHistory.map(FraudCheckHistory::getIsFraudster)
                .orElseGet(() -> {
                    saveNewFraudCheckEntry(customerId);
                    return false;
                });

        return isFraudster;
    }

    private void saveNewFraudCheckEntry(Integer customerId) {
        FraudCheckHistory fraudCheckHistory = FraudCheckHistory
                .builder().customerId(customerId)
                .isFraudster(false)
                .lastChecked(LocalDateTime.now())
                .build();
    }
}
