package com.tel.customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tel.customer.dao.CustomerRepository;
import com.tel.customer.model.Customer;
import com.tel.customer.sequence.SequenceGeneratorService;
import com.tel.customer.service.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerTest {

	@InjectMocks
	CustomerServiceImpl customerService;

	@Mock
	CustomerRepository customerRepository;
	@Mock
	SequenceGeneratorService sequenceGeneratorService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllCustomerTest() {
		List<Customer> list = getList();
		when(customerRepository.findAll()).thenReturn(list);

		// test
		List<Customer> customers = customerService.getCustomers();

		assertEquals(3, customers.size());
		verify(customerRepository, times(1)).findAll();
	}

	@Test
	public void getCustomerByIdTest() {

		when(customerRepository.findByIdAndStatus(1, true)).thenReturn(getCutomer());
		Customer customer = customerService.getCustomerById(1);
		assertEquals(1, customer.getId());
		assertEquals("Manoj", customer.getName());
		assertEquals("test@gmail.com", customer.getEmail());
	}

	@Test
	public void createCustomerTest() {
		// sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME)
		when(sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME)).thenReturn(1l);
		Customer customer = getCutomer();
		customerService.createCustomer(customer);
		verify(customerRepository, times(1)).save(customer);
	}

	@Test
	public void deleteCustomerByIdTest() {
		customerRepository.deleteById(1L);
		verify(customerRepository, times(1)).deleteById(1L);

	}

	@Ignore
	@Test
	public void testGetCutomerList() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Username", "technical");
		headers.add("Password", "Assessment");
		HttpEntity request = new HttpEntity(headers);
		final String baseUrl = "http://localhost:" + 8080 + "/customers";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

		// Verify request succeed
		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertEquals(true, response.getBody().contains("name"));
	}

	private Customer getCutomer() {
		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setName("Manoj");
		customer1.setDescription("test");
		customer1.setEmail("test@gmail.com");
		customer1.setStatus(true);
		return customer1;
	}

	private List<Customer> getList() {
		List<Customer> list = new ArrayList<Customer>();
		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setName("Manoj");
		customer1.setDescription("test");
		customer1.setEmail("test@gmail.com");
		customer1.setStatus(true);
		Customer customer2 = new Customer();
		customer2.setId(2);
		customer2.setName("krishna");
		customer2.setDescription("test1");
		customer2.setEmail("kp@gmail.com");
		customer2.setStatus(true);
		Customer customer3 = new Customer();
		customer3.setId(2);
		customer3.setName("Anam.G");
		customer2.setDescription("test2");
		customer3.setEmail("ag@gmail.com");
		customer3.setStatus(true);
		list = Arrays.asList(customer1, customer2, customer3);
		return list;
	}

}