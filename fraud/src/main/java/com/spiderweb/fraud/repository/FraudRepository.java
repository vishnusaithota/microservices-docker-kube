package com.spiderweb.fraud.repository;

import com.spiderweb.fraud.model.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FraudRepository
        extends JpaRepository<FraudCheckHistory, Integer> {
    FraudCheckHistory findByCustomerId(Integer customerId);
}
