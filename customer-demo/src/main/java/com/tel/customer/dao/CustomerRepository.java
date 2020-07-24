package com.tel.customer.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tel.customer.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {
	Customer findByIdAndStatus(long id, boolean status);
}