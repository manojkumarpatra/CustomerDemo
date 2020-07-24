package com.tel.customer.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

/*
 * Class to handle all internal Exceptions
 */
@Getter
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnAuthorizedException() {
		super();
	}

	public UnAuthorizedException(String message) {
		super(message);
	}

	public UnAuthorizedException(Throwable cause) {
		super(cause);
	}
}
