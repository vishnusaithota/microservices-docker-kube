package com.spiderweb.customer.dto;


public record CustomerRequest(
        String firstName,
        String lastName,
        String email
) {
}
