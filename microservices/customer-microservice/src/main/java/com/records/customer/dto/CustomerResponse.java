package com.records.customer.dto;

public record CustomerResponse(
    String id,
    String firstName,
    String lastName,
    String email,
    String phone,
    String address,
    String city){
}
