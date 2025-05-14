package com.records.customer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.records.customer.dto.CustomerRequest;
import com.records.customer.dto.CustomerResponse;
import com.records.customer.exception.CustomerNotFoundException;
import com.records.customer.repository.CustomerRepository;
import com.records.customer.util.CustomerMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    
    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public List<CustomerResponse> getAllCustomers() {
        logger.info("Getting all customers");
        return customerRepository.findAll()
            .stream()
            .map(customerMapper::toCustomerResponse)
            .toList();
    }

    public CustomerResponse getCustomerById(String customerId) {
        logger.info("Getting customer by ID: {}", customerId);
        return customerRepository.findById(customerId)
            .map(customerMapper::toCustomerResponse)
            .orElseThrow(() -> new CustomerNotFoundException(
                String.format("Customer with ID %s not found", customerId)));
    }

    public String saveCustomer(CustomerRequest customerRequest) {
        logger.info("Saving customer: {}", customerRequest);
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void deleteCustomer(String customerId) {
        logger.info("Deleting customer by ID: {}", customerId);
        customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerNotFoundException(String.format(
                "Customer with ID %s not found", customerId)));
        customerRepository.deleteById(customerId);
    }

    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
        logger.info("Updating customer: {}", customerRequest);
        return customerRepository.findById(customerRequest.id())
            .map(customer -> {
                customer.setId(customerRequest.id());
                customer.setFirstName(customerRequest.firstName());
                customer.setLastName(customerRequest.lastName());
                customer.setPhone(customerRequest.phone());
                customer.setAddress(customerRequest.address());
                customer.setEmail(customerRequest.email());
                customer.setCity(customerRequest.city());
                return customerMapper.toCustomerResponse(
                    customerRepository.save(customer));
            })
            .orElseThrow(() -> new CustomerNotFoundException(String.format(
                "Customer with ID %s not found", customerRequest.id())));
    }    
}
