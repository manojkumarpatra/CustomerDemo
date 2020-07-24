package com.tel.customer.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tel.customer.dao.CustomerRepository;
import com.tel.customer.model.Customer;
import com.tel.customer.sequence.SequenceGeneratorService;
import com.tel.customer.util.AppException;
import com.tel.customer.util.ApplicationConstant;
import com.tel.customer.util.UnAuthorizedException;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	@Override
	public Customer getCustomerById(long id) {
		logger.debug("Inside getCustomerById(...)");
		Customer customer = customerRepository.findByIdAndStatus(id, true);
		logger.debug("Exiting getCustomerById(...) " + customer);
		if (customer == null) {
			throw new UnAuthorizedException(ApplicationConstant.INACTIVE_CUSTOMER);
		}
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {
		logger.debug("Inside getCustomers()");
		List<Customer> customers = customerRepository.findAll();
		logger.debug("Exiting getCustomers()" + customers);
		return customers;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		logger.debug("Inside createCustomer method");
		customer.setId(sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME));
		Customer savedCustomer = customerRepository.save(customer);
		logger.debug("Exiting createCustomer method" + savedCustomer);
		return savedCustomer;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		logger.debug("Inside updateCustomer method");
		Customer customerDb = null;
		Optional<Customer> customerOpt = customerRepository.findById(customer.getId());
		if (customerOpt.isPresent()) {
			customerDb = customerOpt.get();
			customerDb.setName(customer.getName());
			customerDb.setDescription(customer.getDescription());
			customerDb.setEmail(customer.getEmail());
			customerDb.setStatus(customer.isStatus());
			customerDb = customerRepository.save(customerDb);
		}
		logger.debug("Exiting updateCustomer method : " + customerDb);
		return customerDb;
	}

	@Override
	public void deleteCustomerById(long id) throws AppException {
		logger.debug("Inside deleteCustomerById method");
		Optional<Customer> customerOpt = customerRepository.findById(id);
		if (customerOpt.isPresent()) {
			customerRepository.deleteById(id);
		} else {
			throw new UnAuthorizedException(ApplicationConstant.CUSTOMER_NOT_FOUND);
		}
		logger.debug("Exiting deleteCustomerById method");
	}

}
