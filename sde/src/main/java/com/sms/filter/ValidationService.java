package com.sms.filter;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import base.util.General;
import base.util.Validator;
import sun.misc.BASE64Decoder;

public class ValidationService {
	public boolean authenticate(String credential) {
		if (!General.isNotNull(credential)) {
			return false;
		}

		final String encodedUserPassword = credential.replaceFirst(" ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = new BASE64Decoder().decodeBuffer(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":"); 
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		boolean authenticationStatus = "admin".equals(username) && "admin".equals(password);
		return authenticationStatus;
	}
}
