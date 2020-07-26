package com.tel.customer.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tel.customer.aop.Auth;
import com.tel.customer.util.ApplicationConstant;
import com.tel.customer.util.AuthorizationUtils;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

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

		}
		return true;
	}
/**
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// we can add attributes in the modelAndView and use that in the view page
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		logger.info("Request URL::" + request.getRequestURL().toString() + ":: End Time=" + System.currentTimeMillis());
		logger.info("Request URL::" + request.getRequestURL().toString() + ":: Time Taken="
				+ (System.currentTimeMillis() - startTime));
	}
*/
}