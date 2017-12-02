package com.selsoft.user.jwt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Defines where to go after successful login. In this implementation just make
 * sure nothing is done (REST API constains no pages)
 *
 * @author pascal alma
 */
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {

		List<Object> jwt = new ArrayList<Object>();
		jwt.add(request.getHeader("Authorization").split(" ")[1]);
		response.setHeader("jwt", jwt.toString());
		System.out.println("Added JWT to response header 'jwt'");

	}

}
