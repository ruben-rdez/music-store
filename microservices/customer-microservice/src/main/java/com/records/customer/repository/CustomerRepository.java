package com.records.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.records.customer.entity.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    

}
