package com.spiderweb.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record FraudCheckResponse(
        Boolean isFraudster
) {
}
