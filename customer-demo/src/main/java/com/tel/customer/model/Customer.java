package com.tel.customer.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "customer")
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	@Id
	private long id;
	@NotNull
	@Indexed(unique = true)
	private String name;
	@Email
	private String email;
	private String description;
	private boolean status;
	@JsonIgnore
	private int createdBy;
	@JsonIgnore
	private int updatedBy;

}