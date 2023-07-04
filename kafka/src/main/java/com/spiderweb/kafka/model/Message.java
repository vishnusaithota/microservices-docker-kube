package com.spiderweb.kafka.model;

import java.time.LocalDateTime;

public record Message(
        String message,
        LocalDateTime createdAt
) {
}
