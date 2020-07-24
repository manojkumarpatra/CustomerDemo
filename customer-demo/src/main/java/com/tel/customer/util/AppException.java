package com.tel.customer.util;

import lombok.Getter;

/*
 * Class to handle all internal Exceptions
 */
@Getter
public class AppException extends RuntimeException {

	private static final long serialVersionUID = 8492167291598574560L;
	private String message;
	private int errorCode;

	public AppException() {
		super();
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}

	public AppException(int errorCode, String message) {
		this.message = message;
		this.errorCode = errorCode;

	}

	public AppException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.errorCode = errorCode;

	}

}
