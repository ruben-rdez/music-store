package com.records.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
    String id,
    @NotNull(message = "First name is required")
    String firstName,
    @NotNull(message = "Last name is required")
    String lastName,
    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    String email,
    String phone,
    String address,
    String city) {
}
