package com.records.customer.util;

import org.springframework.stereotype.Service;

import com.records.customer.dto.CustomerRequest;
import com.records.customer.dto.CustomerResponse;
import com.records.customer.entity.Customer;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .phone(customerRequest.phone())
                .address(customerRequest.address())
                .city(customerRequest.city())
                .build();
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .city(customer.getCity())
                .build();
    }
}
