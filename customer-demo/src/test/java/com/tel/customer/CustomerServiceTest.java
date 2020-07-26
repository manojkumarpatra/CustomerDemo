package com.tel.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tel.customer.service.CustomerService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CustomerServiceTest 
{
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void testGetDomainObjectById() {
		//service.getDomainObjectById(10L);
	}
}