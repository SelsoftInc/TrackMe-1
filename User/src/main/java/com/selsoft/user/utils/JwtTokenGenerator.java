package com.selsoft.user.utils;

import java.time.Instant;
import java.util.Date;

import com.selsoft.user.dto.JwtUserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * convenience class to generate a token for testing your requests. Make sure
 * the used secret here matches the on in your application.yml
 *
 * @author pascal alma
 */
public class JwtTokenGenerator {

	/**
	 * Generates a JWT token containing username as subject, and userId and role as
	 * additional claims. These properties are taken from the specified User object.
	 * Tokens validity is infinite.
	 *
	 * @param u
	 *            the user for which the token will be generated
	 * @return the JWT token
	 */
	public static String generateToken(JwtUserDto u, String secret) {
		Claims claims = Jwts.claims().setSubject(u.getUsername());
		claims.put("userId", u.getId() + "");
		claims.put("role", u.getRole());

		return Jwts.builder().setClaims(claims)
				// Fri Jun 24 2016 15:33:42 GMT-0400 (EDT)
				.setIssuedAt(Date.from(Instant.ofEpochSecond(System.currentTimeMillis())))
				// Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
				.setExpiration(Date.from(Instant.ofEpochSecond(System.currentTimeMillis()+120000L)))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JwtUserDto user = new JwtUserDto();
		user.setId(123L);
		user.setUsername("Sudhansu");
		user.setRole("admin");

		System.out.println("**************************************\n\n" + generateToken(user, "P317XQ")
				+ "\n\n**************************************");
	}
}
