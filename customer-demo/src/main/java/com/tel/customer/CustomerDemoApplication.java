package com.tel.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CustomerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDemoApplication.class, args);
	}
}
