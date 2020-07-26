package com.tel.customer.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

/*
 * Class to handle all internal Exceptions
 */
@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
