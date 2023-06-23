package com.spiderweb.customer.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank(message = "Invalid FirstName")
        String firstName,
        @NotBlank(message = "Invalid Last Name")
        String lastName,
        @Email(message = "Please Enter a valid Email")
        String email
) {
}
