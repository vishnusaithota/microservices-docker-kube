package com.spiderweb.fraud;

import com.spiderweb.fraud.model.FraudCheckHistory;
import com.spiderweb.fraud.repository.FraudRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableEurekaClient
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties"),
})
public class FraudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FraudApplication.class,args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(FraudRepository fraudRepository){
//        return args -> {
//            FraudCheckHistory fraudCheckHistory1 = FraudCheckHistory
//                    .builder().customerId(1)
//                    .isFraudster(false)
//                    .lastChecked(LocalDateTime.now()).build();
//
//            FraudCheckHistory fraudCheckHistory2 = FraudCheckHistory
//                    .builder().customerId(2)
//                    .isFraudster(true)
//                    .lastChecked(LocalDateTime.now()).build();
//
//            fraudRepository.save(fraudCheckHistory1);
//            fraudRepository.save(fraudCheckHistory2);
//        };
//    }
}
