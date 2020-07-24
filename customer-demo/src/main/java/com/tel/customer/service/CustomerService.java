package com.tel.customer.service;

import java.util.List;

import com.tel.customer.model.Customer;
import com.tel.customer.util.AppException;

public interface CustomerService {
	/**
	 * Method to get customer details by customer number.
	 * 
	 * @param id
	 * @return Customer
	 */
	Customer getCustomerById(long id);

	/**
	 * Method to Fetch all customer details.
	 * 
	 * @return List<Customer>
	 */
	List<Customer> getCustomers();

	/**
	 * Method to create a customer based on the details provided by user.
	 * 
	 * @param customer
	 * @return
	 */
	Customer createCustomer(Customer customer);

	/**
	 * Method to update customer details.
	 * 
	 * @param customer
	 * @return Customer
	 */
	Customer updateCustomer(Customer customer);

	/**
	 * Method to delete customer details by customer number.
	 * 
	 * @param id
	 * @throws AppException
	 */
	void deleteCustomerById(long id) throws AppException;
}
