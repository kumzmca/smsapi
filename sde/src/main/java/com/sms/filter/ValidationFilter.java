package com.sms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.Constants;
import base.util.General;
public class ValidationFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;			
			/* 
			 * Method type needs to check before checking headers/body
			 * Client API may hit other method which may cause error at parsing headers/body element 
			 */ 
			if(!httpServletRequest.getMethod().equals(Constants.method)){
				httpServletResponse.setStatus(httpServletResponse.SC_METHOD_NOT_ALLOWED);
				return;
			}
			//Getting Username/Password from header and decode from base64
			String authCredentials = httpServletRequest.getHeader(Constants.AUTHENTICATION_HEADER);

			ValidationService validationService = new ValidationService();
			boolean authenticationStatus = validationService.authenticate(authCredentials);

			if (authenticationStatus) {
					chain.doFilter(request, response);
			} else {
				if (response instanceof HttpServletResponse) {
					
					httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
		}
		
	}
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
