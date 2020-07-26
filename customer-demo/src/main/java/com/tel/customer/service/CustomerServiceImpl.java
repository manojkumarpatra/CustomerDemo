package com.tel.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tel.customer.dao.CustomerRepository;
import com.tel.customer.model.Customer;
import com.tel.customer.sequence.SequenceGeneratorService;
import com.tel.customer.util.AppException;
import com.tel.customer.util.ApplicationConstant;
import com.tel.customer.util.NotFoundException;
import com.tel.customer.util.UnAuthorizedException;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	@Override
	@Cacheable(value = "consumerCache", key = "#id")
	public Customer getCustomerById(long id) {
		Customer customer = customerRepository.findByIdAndStatus(id, true);
		if (customer == null) {
			throw new UnAuthorizedException(ApplicationConstant.INACTIVE_CUSTOMER);
		}
		return customer;
	}

	@Override
	@Cacheable(value = "allConsumerCache", unless = "#result.size() == 0")
	public List<Customer> getCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return customers;
	}

	@Override
	@Caching(put = { @CachePut(value = "consumerCache", key = "#customer.id") }, evict = {
			@CacheEvict(value = "allConsumerCache", allEntries = true) })
	public Customer createCustomer(Customer customer) {
		customer.setId(sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME));
		Customer savedCustomer = customerRepository.save(customer);
		return savedCustomer;
	}

	@Override
	@Caching(put = { @CachePut(value = "consumerCache", key = "#customer.id") }, evict = {
			@CacheEvict(value = "allConsumerCache", allEntries = true) })
	public Customer updateCustomer(Customer customer) {
		Customer customerDb = null;
		// get Customer from DB.
		Optional<Customer> customerOpt = customerRepository.findById(customer.getId());
		// if Customer exists, update.
		if (customerOpt.isPresent()) {
			customerDb = customerOpt.get();
			customerDb.setName(customer.getName());
			customerDb.setDescription(customer.getDescription());
			customerDb.setEmail(customer.getEmail());
			customerDb.setStatus(customer.isStatus());
			customerDb = customerRepository.save(customerDb);
		}
		return customerDb;
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "consumerCache", key = "#id"),
			@CacheEvict(value = "allConsumerCache", allEntries = true) })
	public void deleteCustomerById(long id) throws AppException {
		Optional<Customer> customerOpt = customerRepository.findById(id);
		if (customerOpt.isPresent()) {
			customerRepository.deleteById(id);
		} else {
			throw new NotFoundException(ApplicationConstant.CUSTOMER_NOT_FOUND);
		}
	}

}
