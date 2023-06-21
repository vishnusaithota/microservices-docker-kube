package com.spiderweb.fraud.dto;

import java.time.LocalDateTime;

public record FraudCheckResponse(
        Boolean isFraudster
) {
}
