package com.spiderweb.kafka.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public record Message(
        String message,
        LocalDateTime createdAt
) implements Serializable {
}
