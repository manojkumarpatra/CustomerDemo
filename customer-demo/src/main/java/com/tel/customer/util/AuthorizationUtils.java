package com.tel.customer.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tel.customer.config.AuthInterceptor;

@Component
public class AuthorizationUtils {
	private static final Logger logger = LogManager.getLogger(AuthInterceptor.class);
	@Value( "${auth.username}" )
	private String userName;
	@Value( "${auth.password}" )
	private String password;

	public static void isAuthorized(String userName, String password) {
		StringBuilder msg = new StringBuilder("UserName : ").append(userName).append("Password : ").append(password);
		logger.debug(() -> msg.toString());
		if ((userName == null || password == null)
				|| (!userName.equals(userName) && password.equals(password))) {
			throw new UnAuthorizedException(ApplicationConstant.UNAUTHORIZED);
		}
	}
}
