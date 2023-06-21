package com.spiderweb.fraud;

import com.spiderweb.fraud.model.FraudCheckHistory;
import com.spiderweb.fraud.repository.FraudRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class FraudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FraudApplication.class,args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(FraudRepository fraudRepository){
        return args -> {
            FraudCheckHistory fraudCheckHistory1 = FraudCheckHistory
                    .builder().customerId(1)
                    .isFraudster(false)
                    .lastChecked(LocalDateTime.now()).build();

            FraudCheckHistory fraudCheckHistory2 = FraudCheckHistory
                    .builder().customerId(2)
                    .isFraudster(true)
                    .lastChecked(LocalDateTime.now()).build();

            fraudRepository.save(fraudCheckHistory1);
            fraudRepository.save(fraudCheckHistory2);
        };
    }
}
