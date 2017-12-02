package com.selsoft.user.dto;

/**
 * Simple placeholder for info extracted from the JWT
 *
 * @author pascal alma
 */
public class JwtUserDto {

	private Long id;

	private String username;

	private String role;

	private Long iat;

	private Long exp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getIat() {
		return iat;
	}

	public void setIat(Long iat) {
		this.iat = iat;
	}

	public Long getExp() {
		return exp;
	}

	public void setExp(Long exp) {
		this.exp = exp;
	}

}