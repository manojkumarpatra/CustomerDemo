package com.tel.customer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "customer")
public class Customer {
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	@Id
	private long id;
	// @NotBlank
	// @Size(max = 100)
	@Indexed(unique = true)
	private String name;
	private String email;
	private String description;
	private boolean status;
	private int createdBy;
	private int updatedBy;

}