package com.tel.customer.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tel.customer.util.ApplicationConstant;
import com.tel.customer.util.AuthorizationUtils;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		AuthorizationUtils.isAuthorized(request.getHeader(ApplicationConstant.USER_NAME),
				request.getHeader(ApplicationConstant.PASSWORD));
		/**
		if (handler instanceof HandlerMethod) {
			Auth authorize = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);
			if (null == authorize) {
				return true;
			}
			// authorize if you have @Auth validation annotations
			if (null != authorize) {
				AuthorizationUtils.isAuthorized(request.getHeader(ApplicationConstant.USER_NAME),
						request.getHeader(ApplicationConstant.PASSWORD));
			}
		}*/
		return true;
	}
}