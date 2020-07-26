package com.tel.customer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tel.customer.aop.Auth;
import com.tel.customer.model.Customer;
import com.tel.customer.service.CustomerService;
//@Auth
@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers(@RequestHeader Map<String, String> headers) {
		return ResponseEntity.ok().body(customerService.getCustomers());
	}
	
	@GetMapping("/{customernumber}")
	public ResponseEntity<Customer> getCustomerById(@RequestHeader Map<String, String> headers,
			@PathVariable("customernumber") long id) {
		return ResponseEntity.ok().body(customerService.getCustomerById(id));
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestHeader Map<String, String> headers,
			@RequestBody Customer customer) {
		return ResponseEntity.ok().body(this.customerService.createCustomer(customer));
	}

	@PutMapping("/{customernumber}")
	public ResponseEntity<Customer> updateCustomer(@RequestHeader Map<String, String> headers,
			@PathVariable("customernumber") long id, @RequestBody Customer customer) {
		customer.setId(id);
		return ResponseEntity.ok().body(this.customerService.updateCustomer(customer));
	}

	@DeleteMapping("/{customernumber}")
	public HttpStatus deleteProduct(@RequestHeader Map<String, String> headers,
			@PathVariable("customernumber") long id) {
		this.customerService.deleteCustomerById(id);
		return HttpStatus.OK;
	}
}
