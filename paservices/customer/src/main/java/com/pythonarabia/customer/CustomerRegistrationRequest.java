package com.pythonarabia.customer;


// Customer DTO
public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
